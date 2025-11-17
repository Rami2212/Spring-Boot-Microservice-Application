CREATE TABLE `t_orders` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `customer_id` VARCHAR(255) DEFAULT NULL,
    `sku_code` VARCHAR(255),
    `price` DECIMAL(10,2) NOT NULL,
    `quantity` INT(11)
);