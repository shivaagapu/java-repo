package com.bank.customer.service;

import com.bank.customer.model.Customer;
import com.bank.customer.repository.CustomerRepository;
import com.bank.customer.dto.CustomerRequest;
import com.bank.customer.dto.CustomerResponse;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    // Get all customers
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    // Get customer by ID
    public Optional<CustomerResponse> getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(this::convertToResponse);
    }
    
    // Get customer by email
    public Optional<CustomerResponse> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(this::convertToResponse);
    }
    
    // Get customers by status
    public List<CustomerResponse> getCustomersByStatus(String status) {
        return customerRepository.findByCustomerStatus(status)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    // Get customers by city
    public List<CustomerResponse> getCustomersByCity(String city) {
        return customerRepository.findByCity(city)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    // Create customer
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());
        customer.setState(request.getState());
        customer.setPostalCode(request.getPostalCode());
        customer.setCountry(request.getCountry());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setCustomerStatus(request.getCustomerStatus() != null ? request.getCustomerStatus() : "ACTIVE");
        
        Customer savedCustomer = customerRepository.save(customer);
        return convertToResponse(savedCustomer);
    }
    
    // Update customer
    public Optional<CustomerResponse> updateCustomer(Long id, CustomerRequest request) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstName(request.getFirstName());
                    customer.setLastName(request.getLastName());
                    customer.setEmail(request.getEmail());
                    customer.setPhone(request.getPhone());
                    customer.setAddress(request.getAddress());
                    customer.setCity(request.getCity());
                    customer.setState(request.getState());
                    customer.setPostalCode(request.getPostalCode());
                    customer.setCountry(request.getCountry());
                    customer.setDateOfBirth(request.getDateOfBirth());
                    if (request.getCustomerStatus() != null) {
                        customer.setCustomerStatus(request.getCustomerStatus());
                    }
                    Customer updatedCustomer = customerRepository.save(customer);
                    return convertToResponse(updatedCustomer);
                });
    }
    
    // Delete customer
    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Helper method to convert entity to response
    private CustomerResponse convertToResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getCity(),
                customer.getState(),
                customer.getPostalCode(),
                customer.getCountry(),
                customer.getDateOfBirth(),
                customer.getCustomerStatus(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}
