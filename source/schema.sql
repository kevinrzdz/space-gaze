-- Schema for Space Gaze (MySQL 8+)
-- Safe to run against an empty database (e.g. Railway / Docker MYSQL_DATABASE).

CREATE TABLE IF NOT EXISTS asteroid (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  diameter DOUBLE NOT NULL,
  dangerous TINYINT(1) NOT NULL,
  image VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS astronomical_event (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS exoplanet (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  discovery_year INT,
  publication_date VARCHAR(10),
  discovery_facility VARCHAR(255),
  mass DOUBLE,
  radius DOUBLE,
  image VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS planet (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  diameter DOUBLE NOT NULL,
  distance DOUBLE NOT NULL,
  description VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL UNIQUE,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(200) NOT NULL,
  role ENUM('user', 'admin') NOT NULL DEFAULT 'user',
  image VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS star (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  constellation VARCHAR(100) NOT NULL,
  magnitude VARCHAR(100) NOT NULL,
  distance_earth VARCHAR(100) NOT NULL,
  image VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_tracking_asteroid (
  user_id INT NOT NULL,
  asteroid_id INT NOT NULL,
  PRIMARY KEY (user_id, asteroid_id),
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (asteroid_id) REFERENCES asteroid (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS user_tracking_event (
  user_id INT NOT NULL,
  event_id INT NOT NULL,
  PRIMARY KEY (user_id, event_id),
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (event_id) REFERENCES astronomical_event (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS user_tracking_exoplanet (
  user_id INT NOT NULL,
  exoplanet_id INT NOT NULL,
  PRIMARY KEY (user_id, exoplanet_id),
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (exoplanet_id) REFERENCES exoplanet (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS user_tracking_planet (
  user_id INT NOT NULL,
  planet_id INT NOT NULL,
  PRIMARY KEY (user_id, planet_id),
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (planet_id) REFERENCES planet (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS user_tracking_star (
  user_id INT NOT NULL,
  star_id INT NOT NULL,
  PRIMARY KEY (user_id, star_id),
  FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (star_id) REFERENCES star (id) ON DELETE CASCADE ON UPDATE CASCADE
);
