-- Demo seed data for Space Gaze portfolio deployments.
-- Password for demo user: Demo1234!

INSERT INTO user (email, username, password, role) VALUES
('demo@spacegaze.app', 'demo', '$2a$10$kVFuww3HXOuTTR/q7ddjs.a0p0dKdKq9IcUE2Byuba/pHwwWoxTNi', 'admin');

INSERT INTO planet (name, diameter, distance, description) VALUES
('Mercury', 4879, 57.9, 'Smallest planet and closest to the Sun.'),
('Venus', 12104, 108.2, 'Hottest planet with a dense toxic atmosphere.'),
('Earth', 12742, 149.6, 'Our home world and the only known planet with life.'),
('Mars', 6779, 227.9, 'The Red Planet, target of many exploration missions.'),
('Jupiter', 139820, 778.5, 'Largest planet in the Solar System, a gas giant.'),
('Saturn', 116460, 1434, 'Famous for its spectacular ring system.'),
('Uranus', 50724, 2871, 'Ice giant tilted on its side.'),
('Neptune', 49244, 4495, 'Farthest known major planet from the Sun.');

INSERT INTO asteroid (name, diameter, dangerous, image) VALUES
('Ceres', 939.4, 0, '1df4e310-f00e-4f06-9028-3b72f1e0d8e9_asteroid.jpg'),
('Vesta', 525.4, 0, '3f544332-33db-4ac5-a13b-8f87bc0aa725_Asteroid_Ryugu.jpg'),
('Eros', 16.84, 0, NULL),
('Apophis', 0.37, 1, NULL),
('Bennu', 0.49, 1, '38e6f71e-383c-445c-98d7-5a3d09bb5b13_asteroid.jpg');

INSERT INTO star (name, constellation, magnitude, distance_earth, image) VALUES
('Sirius', 'Canis Major', '-1.46', '8.6 ly', NULL),
('Betelgeuse', 'Orion', '0.42', '642 ly', NULL),
('Vega', 'Lyra', '0.03', '25 ly', NULL),
('Polaris', 'Ursa Minor', '1.98', '433 ly', NULL),
('Proxima Centauri', 'Centaurus', '11.13', '4.24 ly', NULL);

INSERT INTO exoplanet (name, discovery_year, publication_date, discovery_facility, mass, radius, image) VALUES
('Kepler-22b', 2011, '2011-12-05', 'Kepler', NULL, 2.4, NULL),
('Proxima Centauri b', 2016, '2016-08-24', 'ESO La Silla', 1.27, NULL, NULL),
('TRAPPIST-1e', 2017, '2017-02-22', 'TRAPPIST', 0.69, 0.91, NULL),
('HD 209458 b', 1999, '1999-11-23', 'Multiple', 0.69, 1.38, NULL),
('TOI-700 d', 2020, '2020-01-07', 'TESS', NULL, 1.19, NULL);

INSERT INTO astronomical_event (name, description, start_date, end_date) VALUES
('Perseid Meteor Shower Peak', 'One of the best annual meteor showers, associated with comet Swift-Tuttle.', '2026-08-12 00:00:00', '2026-08-13 23:59:59'),
('Total Lunar Eclipse', 'A total lunar eclipse visible across much of the night side of Earth.', '2026-03-03 00:00:00', '2026-03-03 06:00:00'),
('Annular Solar Eclipse', 'An annular solar eclipse creating a ring of fire effect along the path of annularity.', '2026-02-17 00:00:00', '2026-02-17 12:00:00'),
('Opposition of Mars', 'Mars reaches opposition and appears brighter in the night sky.', '2027-02-19 00:00:00', '2027-02-19 23:59:59');
