-- This file runs automatically when MySQL container starts
-- It creates all the databases needed by each service

CREATE DATABASE IF NOT EXISTS user_service;
CREATE DATABASE IF NOT EXISTS account_service;
CREATE DATABASE IF NOT EXISTS fund_transfer_service;
CREATE DATABASE IF NOT EXISTS transaction_service;
CREATE DATABASE IF NOT EXISTS sequence_db;

-- Grant all permissions to root
GRANT ALL PRIVILEGES ON user_service.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON account_service.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON fund_transfer_service.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON transaction_service.* TO 'root'@'%';
GRANT ALL PRIVILEGES ON sequence_db.* TO 'root'@'%';

FLUSH PRIVILEGES;

-- Sample dummy data for local development/testing
USE user_service;

INSERT INTO `user_profile` (user_profile_id, first_name, last_name, gender, address, occupation, martial_status, nationality)
VALUES
  (1, 'Alice', 'Johnson', 'FEMALE', '123 Main Street, New York', 'Software Engineer', 'MARRIED', 'American'),
  (2, 'Bob', 'Smith', 'MALE', '45 Market Road, London', 'Bank Manager', 'SINGLE', 'British'),
  (3, 'Clara', 'Nguyen', 'FEMALE', '78 River Avenue, Singapore', 'Teacher', 'MARRIED', 'Singaporean');

INSERT INTO `user` (user_id, email_id, contact_no, auth_id, identification_number, creation_on, status, user_profile_id)
VALUES
  (1, 'alice@example.com', '+1-555-0101', 'auth-alice', 'ID123456', '2025-01-10', 'APPROVED', 1),
  (2, 'bob@example.com', '+44-7700-900123', 'auth-bob', 'ID234567', '2025-01-12', 'APPROVED', 2),
  (3, 'clara@example.com', '+65-9123-4567', 'auth-clara', 'ID345678', '2025-01-15', 'PENDING', 3);

USE account_service;

INSERT INTO account (account_id, account_number, account_type, account_status, opening_date, available_balance, user_id)
VALUES
  (1, '100001', 'SAVINGS_ACCOUNT', 'ACTIVE', '2025-01-10', 12500.50, 1),
  (2, '100002', 'FIXED_DEPOSIT', 'ACTIVE', '2025-01-12', 8750.25, 2),
  (3, '100003', 'SAVINGS_ACCOUNT', 'PENDING', '2025-01-15', 3200.00, 3);

USE transaction_service;

INSERT INTO transaction (transaction_id, reference_id, account_id, transaction_type, amount, transaction_date, status, comments)
VALUES
  (1, 'TXN-1001', '100001', 'DEPOSIT', 5000.00, '2025-01-10 09:30:00', 'COMPLETED', 'Salary deposit'),
  (2, 'TXN-1002', '100001', 'WITHDRAWAL', 1200.00, '2025-01-11 14:20:00', 'COMPLETED', 'Grocery purchase'),
  (3, 'TXN-1003', '100002', 'INTERNAL_TRANSFER', 750.00, '2025-01-12 18:05:00', 'PENDING', 'Transfer to savings');

USE fund_transfer_service;

INSERT INTO fund_transfer (fund_transfer_id, transaction_reference, from_account, to_account, amount, status, transfer_type, transferred_on)
VALUES
  (1, 'FT-2001', '100001', '100002', 500.00, 'SUCCESS', 'INTERNAL', '2025-01-11 10:15:00'),
  (2, 'FT-2002', '100002', '100003', 250.00, 'PENDING', 'EXTERNAL', '2025-01-13 16:40:00');

USE sequence_db;

INSERT INTO sequence (sequence_id, account_number)
VALUES (1, 1000000001);