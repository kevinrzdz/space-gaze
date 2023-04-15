DROP DATABASE IF EXISTS space_gaze;

CREATE DATABASE space_gaze;

CREATE USER IF NOT EXISTS space_gaze@localhost IDENTIFIED BY 'space_gaze2023.';
GRANT ALL ON space_gaze.* TO 'space_gaze'@'localhost';

USE space_gaze;

CREATE TABLE asteroid (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  diameter DOUBLE NOT NULL,
  dangerous TINYINT(1) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE astronomical_event (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE exoplanet (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  discovery_year INT,
  publication_date VARCHAR(10),
  discovery_facility VARCHAR(255),
  mass DOUBLE,
  radius DOUBLE,
  PRIMARY KEY (id)
);

CREATE TABLE planet (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  diameter DOUBLE NOT NULL,
  distance DOUBLE NOT NULL,
  description VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL UNIQUE,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(200) NOT NULL,
  role ENUM('user', 'admin') NOT NULL DEFAULT 'user',
  PRIMARY KEY (id)
);

CREATE TABLE star (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  constellation varchar(100) NOT NULL,
  magnitude varchar(100) NOT NULL,
  distance_earth varchar(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_tracking_asteroid (
  user_id INT NOT NULL,
  asteroid_id INT NOT NULL,
  PRIMARY KEY (user_id, asteroid_id),
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (asteroid_id) REFERENCES asteroid (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_tracking_event (
  user_id INT NOT NULL,
  event_id INT NOT NULL,
  PRIMARY KEY (user_id, event_id),
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (event_id) REFERENCES astronomical_event (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_tracking_exoplanet (
  user_id INT NOT NULL,
  exoplanet_id INT NOT NULL,
  PRIMARY KEY (user_id, exoplanet_id),
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (exoplanet_id) REFERENCES exoplanet (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_tracking_planet (
  user_id INT NOT NULL,
  planet_id INT NOT NULL,
  PRIMARY KEY (user_id, planet_id),
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (planet_id) REFERENCES planet (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE user_tracking_star (
  user_id INT NOT NULL,
  star_id INT NOT NULL,
  PRIMARY KEY (user_id, star_id),
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (star_id) REFERENCES star (id) ON DELETE CASCADE ON UPDATE CASCADE
);