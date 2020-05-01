CREATE TABLE `rental` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer` varchar(255) DEFAULT NULL,
  `vehicle` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `date` Date DEFAULT NULL,
  PRIMARY KEY (`id`)
);

