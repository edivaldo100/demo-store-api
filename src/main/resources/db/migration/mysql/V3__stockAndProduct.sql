CREATE TABLE `product` (
  id int(6) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

	
CREATE TABLE `stock` (
  id int NOT NULL auto_increment,
  quantity int(20) NOT NULL,
   PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
