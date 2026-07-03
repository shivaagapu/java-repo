package com.chandramani.user.service.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.chandramani.user.service.exception.EmptyFields;
import com.chandramani.user.service.exception.ResourceConflictException;
import com.chandramani.user.service.exception.ResourceNotFound;
import com.chandramani.user.service.external.AccountService;
import com.chandramani.user.service.model.Status;
import com.chandramani.user.service.model.dto.CreateUser;
import com.chandramani.user.service.model.dto.UserDto;
import com.chandramani.user.service.model.dto.UserUpdate;
import com.chandramani.user.service.model.dto.UserUpdateStatus;
import com.chandramani.user.service.model.dto.response.Response;
import com.chandramani.user.service.model.entity.User;
import com.chandramani.user.service.model.entity.UserProfile;
import com.chandramani.user.service.model.external.Account;
import com.chandramani.user.service.model.mapper.UserMapper;
import com.chandramani.user.service.repository.UserRepository;
import com.chandramani.user.service.service.UserService;
import com.chandramani.user.service.utils.FieldChecker;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    private UserMapper userMapper = new UserMapper();

    @Value("${spring.application.success}")
    private String responseCodeSuccess;

    @Value("${spring.application.not_found}")
    private String responseCodeNotFound;

    /**
     * Creates a new user.
     *
     * @param userDto The user data transfer object containing user information.
     * @return A response indicating the result of the user creation.
     * @throws ResourceConflictException If the emailId is already registered as a user.
     */
    @Override
    public Response createUser(CreateUser userDto) {

        // Check if email already exists in DB
        if (userRepository.existsByEmailId(userDto.getEmailId())) {
            log.error("This emailId is already registered as a user");
            throw new ResourceConflictException("This emailId is already registered as a user");
        }

        UserProfile userProfile = UserProfile.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName()).build();

        User user = User.builder()
                .emailId(userDto.getEmailId())
                .contactNo(userDto.getContactNumber())
                .status(Status.PENDING)
                .userProfile(userProfile)
                .authId(UUID.randomUUID().toString())
                .identificationNumber(UUID.randomUUID().toString()).build();

        userRepository.save(user);

        return Response.builder()
                .responseMessage("User created successfully")
                .responseCode(responseCodeSuccess).build();
    }

    /**
     * Retrieves all users and their corresponding details.
     *
     * @return a list of UserDto objects containing the user information
     */
    @Override
    public List<UserDto> readAllUsers() {

        return userRepository.findAll().stream().map(user -> {
            UserDto userDto = userMapper.convertToDto(user);
            userDto.setUserId(user.getUserId());
            userDto.setEmailId(user.getEmailId());
            userDto.setIdentificationNumber(user.getIdentificationNumber());
            return userDto;
        }).collect(Collectors.toList());
    }

    /**
     * Reads a user from the database using the provided authId.
     *
     * @param authId the authentication id of the user
     * @return the UserDto object representing the user
     * @throws ResourceNotFound if the user is not found on the server
     */
    @Override
    public UserDto readUser(String authId) {

        User user = userRepository.findUserByAuthId(authId)
                .orElseThrow(() -> new ResourceNotFound("User not found on the server"));

        UserDto userDto = userMapper.convertToDto(user);
        userDto.setEmailId(user.getEmailId());
        return userDto;
    }

    /**
     * Updates the status of a user.
     *
     * @param id         The ID of the user.
     * @param userUpdate The updated user status.
     * @return The response indicating the success of the update.
     * @throws ResourceNotFound If the user is not found.
     * @throws EmptyFields      If the user has empty fields.
     */
    @Override
    public Response updateUserStatus(Long id, UserUpdateStatus userUpdate) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found on the server"));

        if (FieldChecker.hasEmptyFields(user)) {
            log.error("User is not updated completely");
            throw new EmptyFields("please updated the user", responseCodeNotFound);
        }

        // Keycloak enable/verify step removed — status is just saved directly
        user.setStatus(userUpdate.getStatus());
        userRepository.save(user);

        return Response.builder()
                .responseMessage("User updated successfully")
                .responseCode(responseCodeSuccess).build();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user to retrieve
     * @return the UserDto object representing the user
     * @throws ResourceNotFound if the user is not found
     */
    @Override
    public UserDto readUserById(Long userId) {

        return userRepository.findById(userId)
                .map(user -> userMapper.convertToDto(user))
                .orElseThrow(() -> new ResourceNotFound("User not found on the server"));
    }

    /**
     * Updates a user with the given ID.
     *
     * @param id         The ID of the user to update.
     * @param userUpdate The updated information for the user.
     * @return The response indicating the success or failure of the update operation.
     * @throws ResourceNotFound if the user with the given ID is not found.
     */
    @Override
    public Response updateUser(Long id, UserUpdate userUpdate) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found on the server"));

        user.setContactNo(userUpdate.getContactNo());
        BeanUtils.copyProperties(userUpdate, user.getUserProfile());
        userRepository.save(user);

        return Response.builder()
                .responseCode(responseCodeSuccess)
                .responseMessage("user updated successfully").build();
    }

    /**
     * Retrieves a UserDto by the given accountId.
     *
     * @param accountId The account ID of the user.
     * @return The UserDto object corresponding to the given accountId.
     * @throws ResourceNotFound If the account or user is not found on the server.
     */
    @Override
    public UserDto readUserByAccountId(String accountId) {

        ResponseEntity<Account> response = accountService.readByAccountNumber(accountId);
        if (Objects.isNull(response.getBody())) {
            throw new ResourceNotFound("account not found on the server");
        }
        Long userId = response.getBody().getUserId();
        return userRepository.findById(userId)
                .map(user -> userMapper.convertToDto(user))
                .orElseThrow(() -> new ResourceNotFound("User not found on the server"));
    }
}