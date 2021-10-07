-- -----------------------------------------------------
-- Schema inventory-pos
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `inventory-pos`;

CREATE SCHEMA `inventory-pos`;
USE `inventory-pos` ;


-- -----------------------------------------------------
-- Table `inventory-pos`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory-pos`.`company` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `phone_number` VARCHAR(255) NULL DEFAULT NULL,
  `email_address` VARCHAR(255) NULL DEFAULT NULL,
  `website_address` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `inventory-pos`.`product_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory-pos`.`product_category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;



-- -----------------------------------------------------
-- Table `inventory-pos`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory-pos`.`product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `purchase_price` DECIMAL(13,2) DEFAULT NULL,
  `sale_price` DECIMAL(13,2) DEFAULT NULL,
  `stock` BIGINT(20) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL, 
  `image_name` VARCHAR(100) DEFAULT NULL,
  `image_url` VARCHAR(255) DEFAULT NULL,
  `category_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_category` (`category_id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`)
  )
ENGINE=InnoDB
AUTO_INCREMENT = 1;



-- -----------------------------------------------------
-- Table `inventory-pos`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory-pos`.`invoice` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(255) DEFAULT NULL,
  `order_date` DATE DEFAULT NULL,
  `subtotal` DECIMAL(13,2) DEFAULT NULL,
  `tax` DECIMAL(13,2) DEFAULT NULL,
  `discount` DECIMAL(13,2) DEFAULT NULL,
  `total` DECIMAL(13,2) DEFAULT NULL,
  `paid` DECIMAL(13,2) DEFAULT NULL,
  `due` DECIMAL(13,2) DEFAULT NULL,
  `payment_type` VARCHAR(10) NULL DEFAULT NULL,  
  `company_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_company` (`company_id`),
  CONSTRAINT `fk_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
  )
ENGINE=InnoDB
AUTO_INCREMENT = 1;

-- -----------------------------------------------------
-- Table `inventory-pos`.`invoice_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory-pos`.`invoice_detail` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `invoice_id` BIGINT(20) NOT NULL,
  `product_id` BIGINT(20) NOT NULL,
  `product_name` VARCHAR(255) DEFAULT NULL,
  `product_quantity` BIGINT(20) DEFAULT NULL,
  `product_price` DECIMAL(13,2) DEFAULT NULL,
  `order_date` DATE DEFAULT NULL,    
  PRIMARY KEY (`id`),
  KEY `fk_invoice` (`invoice_id`),
  CONSTRAINT `fk_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  KEY `fk_product` (`product_id`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`))
ENGINE=InnoDB
AUTO_INCREMENT = 1;



