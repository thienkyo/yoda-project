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
  status INT(2);
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
)
