use yodaDB;

CREATE TABLE IF NOT EXISTS Members
(
  memberID INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  fullName VARCHAR(250) NOT NULL,
  email VARCHAR(200),
  pass VARCHAR(3000),
  phone VARCHAR(20),
  country VARCHAR(200),
  city VARCHAR(200),
  address VARCHAR(2000),
  postcode VARCHAR(200),
  status INT(2),
  mod_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Categories
(
  categoryID INT(3) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  categoryName VARCHAR(250) NOT NULL,
  status INT(2),
  mod_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Products
(
    prodID INT(6) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    prodName VARCHAR(250) NOT NULL,
    description TEXT,
    notification VARCHAR(500),
    price DECIMAL(10,2),
    amount SMALLINT,
    image VARCHAR(200),
    categoryID INT(3) UNSIGNED ,
    mod_date TIMESTAMP,
    FOREIGN KEY fk_cat(categoryID) REFERENCES Categories(categoryID)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS Orders
(
  orderID INT(7) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  memberID INT(6) UNSIGNED,
  shippingAddress VARCHAR(2000),
  des VARCHAR(2000),
  status VARCHAR(10),
  discount TINYINT,
  mod_date TIMESTAMP,
  FOREIGN KEY fk_mem(memberID) REFERENCES Members(memberID)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS OrderDetails
(
  orderID INT(7) UNSIGNED ,
  prodID INT(6) UNSIGNED,
  amount int(4),
  priceAtThatTime DECIMAL(10,2),
  mod_date TIMESTAMP,
  FOREIGN KEY fk_order(orderID) REFERENCES Orders(orderID),
  FOREIGN KEY fk_prod(prodID) REFERENCES Products(prodID)
  ON UPDATE CASCADE
  ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS `ShipCost` (
 `ship_cost_id` INT(3) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 `distance` INT(3) NOT NULL,
 `price` decimal(10,0) NOT NULL,
 `region` varchar(1000) COLLATE utf8mb4_unicode_520_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

CREATE TABLE IF NOT EXISTS `MemberRole` (
 `id` INT(3) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 `email` varchar(1000)  NOT NULL,
 `role` varchar(1000) NOT NULL,
 `member_id` INT(6)  NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

CREATE TABLE IF NOT EXISTS `Banner` (
 `banner_id` INT(4) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 `banner_name` VARCHAR(150) NOT NULL ,
 `description` VARCHAR(1000) NOT NULL ,
 `image` VARCHAR(100) NOT NULL ,
 `link` VARCHAR(100) NOT NULL ,
 `mod_date` DATE NOT NULL ,
  `status` INT(1) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

CREATE TABLE IF NOT EXISTS `Article` (
 `article_id` INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
 `article_name` VARCHAR(200) NOT NULL ,
 `author` VARCHAR(30) NOT NULL ,
 `content` TEXT NOT NULL ,
 `description` VARCHAR(1000) NOT NULL ,
 `image` VARCHAR(100) NOT NULL ,
 `mod_date` TIMESTAMP NOT NULL ,
 `status` INT(2) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_520_ci;