-- liquibase formatted sql
-- changeset bretthanson:1
CREATE TABLE IF NOT EXISTS dvds(
    dvd_id int(11) NOT NULL AUTO_INCREMENT,
    title varchar(50) NOT NULL,
    released varchar(50) NOT NULL,
    mpaa varchar(50) NOT NULL,
    director varchar(50) NOT NULL,
    studio varchar(50) NOT NULL,
    rating varchar(50) NOT NULL,
    note varchar(50) NOT NULL,
    PRIMARY KEY(dvd_id)
) ENGINE=InnoDb DEFAULT CHARSET=latin1 AUTO_INCREMENT = 23;

-- changeset bretthanson:2
Insert into dvds (title, released, mpaa, director, studio, rating, note)
VALUES ('Star Wars: The Force Awakens', '2015', 'PG-13', 'J.J. Abrams', 'Lucasfilm', '8.5','Still need to see');

Insert into dvds (title, released, mpaa, director, studio, rating, note)
VALUES ('The Hateful Eight', '2015', 'R', 'Quentin Tarantino', 'The Weinstein Company', '8.1','This is a user note');

Insert into dvds (title, released, mpaa, director, studio, rating, note)
VALUES ('The Big Short', '2015', 'PG-13', 'Adam McKay', 'Plan B Entertainment', '8.1','Will see soon');

Insert into dvds (title, released, mpaa, director, studio, rating, note)
VALUES ('Concussion', '2015', 'PG-13', 'Peter Landesman', 'The Cantillon Company', '7.1','This is about conncussions');

Insert into dvds (title, released, mpaa, director, studio, rating, note)
VALUES ('Point Break', '2015', 'PG-13', 'Ericson Core', 'Alcon Entertainment', '5.4','This is probably terrible');