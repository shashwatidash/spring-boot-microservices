CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` int AUTO_INCREMENT PRIMARY KEY,
    `customer_name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `phone_number` varchar(20) NOT NULL,
    `created_at` date DEFAULT NOT NULL,
    `updated_at` date DEFAULT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `accounts` (
    `account_number` int AUTO_INCREMENT PRIMARY KEY,
    `customer_id` int NOT NULL,
    `account_type` varchar(100) NOT NULL,
    `branch_address` varchar(200) NOT NULL,
    `created_at` date DEFAULT NOT NULL,
    `updated_at` date DEFAULT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_by` varchar(20) DEFAULT NULL
);