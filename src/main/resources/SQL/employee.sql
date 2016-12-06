CREATE TABLE Employee(
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(256) DEFAULT NULL,
  surname varchar(256) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  startdate timestamp NULL DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;