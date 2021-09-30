DROP DATABASE IF EXISTS StockDB;
CREATE DATABASE IF NOT EXISTS StockDB;
SHOW DATABASES;
USE StockDB;
#------------------
DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    CustID VARCHAR(6),
    CustTitle VARCHAR(5),
    CustName VARCHAR(30),
    CustAddress VARCHAR(30),
    City VARCHAR(20),
    Province VARCHAR(20),
    PostalCode VARCHAR(9),
    CONSTRAINT PRIMARY KEY(CustID)
);
SHOW TABLES;
DESCRIBE Customer;
#----------------------
DROP TABLE IF EXISTS Orders;
CREATE TABLE IF NOT EXISTS Orders(
    OrderID VARCHAR(6),
    OrderDate VARCHAR(15),
    CustID VARCHAR(6),
    CONSTRAINT PRIMARY KEY(OrderID),
    CONSTRAINT FOREIGN KEY(CustID) REFERENCES Customer(CustID) ON DELETE CASCADE ON UPDATE CASCADE
);
SHOW TABLES;
DESCRIBE Orders;
#---------------------------

DROP TABLE IF EXISTS Item;
CREATE TABLE IF NOT EXISTS Item(
    ItemCode VARCHAR(6),
    Description VARCHAR(50),
    PackSize VARCHAR(20),
    UnitPrice DOUBLE DEFAULT 0.00,
    QtyOnHand INT(5) DEFAULT 0,
    CONSTRAINT PRIMARY KEY(ItemCode)
);
SHOW TABLES;
DESCRIBE Item;
#--------------------
DROP TABLE IF EXISTS OrderDetail;
CREATE TABLE IF NOT EXISTS OrderDetail(
    ItemCode VARCHAR(6),
    OrderID VARCHAR(6),
    OrderQty INT(11),
    Discount DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY(ItemCode,OrderID),
    CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Item(ItemCode) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY(OrderID) REFERENCES Orders(OrderID) ON UPDATE CASCADE ON DELETE CASCADE
);
SHOW TABLES;
DESCRIBE OrderDetail;


