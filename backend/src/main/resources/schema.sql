

CREATE TABLE `account` (
                           `Id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) DEFAULT NULL,
                           `numberPhone` varchar(45) DEFAULT NULL,
                           `gmail` varchar(45) DEFAULT NULL,
                           `password` varchar(100) DEFAULT NULL,
                           `role` varchar(45) DEFAULT NULL,
                           `customerid` varchar(100) DEFAULT NULL,
                           `timecreate` date DEFAULT NULL,
                           PRIMARY KEY (`Id`)
);





CREATE TABLE `invoices` (
                            `Id` int NOT NULL AUTO_INCREMENT,
                            `IdSubscriptions` int DEFAULT NULL,
                            `Price` int DEFAULT NULL,
                            `Time` date DEFAULT NULL,
                            `Status` int DEFAULT NULL,
                            PRIMARY KEY (`Id`)
);






CREATE TABLE `package` (
                           `Id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) DEFAULT NULL,
                           `priceMonth` int DEFAULT NULL,
                           `priceYear` int DEFAULT NULL,
                           `priceOneTime` int DEFAULT NULL,
                           `recurring` int DEFAULT NULL,
                           `type` int DEFAULT NULL,
                           `description` longtext,
                           PRIMARY KEY (`Id`)
);






CREATE TABLE `payment_method` (
                                  `ID` int NOT NULL AUTO_INCREMENT,
                                  `PaymentID` varchar(100) NOT NULL,
                                  `AccountID` int NOT NULL,
                                  PRIMARY KEY (`ID`)
) ;






CREATE TABLE `subscriptions` (
                                 `Id` int NOT NULL AUTO_INCREMENT,
                                 `IdAccount` int DEFAULT NULL,
                                 `IdPackage` int DEFAULT NULL,
                                 `lastpayment` date DEFAULT NULL,
                                 `price` int DEFAULT NULL,
                                 `recurring` int DEFAULT NULL,
                                 `daycreate` date DEFAULT NULL,
                                 `keyserver` varchar(45) DEFAULT NULL,
                                 `status` int DEFAULT NULL,
                                 PRIMARY KEY (`Id`),
                                 KEY `IdPackage_idx` (`IdPackage`),
                                 CONSTRAINT `IdPackage` FOREIGN KEY (`IdPackage`) REFERENCES `package` (`Id`)
) ;






