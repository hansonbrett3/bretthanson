-- liquibase formatted sql
-- changeset bretthanson:1
CREATE TABLE IF NOT EXISTS addresses(
    address_id int(11) NOT NULL AUTO_INCREMENT,
    `first` varchar(50) NOT NULL,
    `last` varchar(50) NOT NULL,
    `street` varchar(50) NOT NULL,
    `city` varchar(50) NOT NULL,
    `state` varchar(50) NOT NULL,
    `zip` varchar(50) NOT NULL,
    PRIMARY KEY(address_id)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 AUTO_INCREMENT = 23;

-- changeset bretthanson:2
Insert into addresses (`first`, `last`, `street`, `city`, `state`, `zip`)
VALUES ('Brett', 'Hanson', '2588 Princeton Road', 'Cleveland Heights', 'OH','44118');

Insert into addresses (`first`, `last`, `street`, `city`, `state`, `zip`)
VALUES ('Jane', 'Doe', '2222 Main Street', 'Baltimore', 'MD','44122');

Insert into addresses (`first`, `last`, `street`, `city`, `state`, `zip`)
VALUES ('John', 'Smith', '3333 Lane Drive', 'Columbus', 'OH','44133');

Insert into addresses (`first`, `last`, `street`, `city`, `state`, `zip`)
VALUES ('Bernie', 'Williams', '4444 Poplar Drive', 'New York', 'NY','44144');

Insert into addresses (`first`, `last`, `street`, `city`, `state`, `zip`)
VALUES ('Sean', 'Thomas', '5555 Spring Street', 'Chicago', 'IL','44155');
