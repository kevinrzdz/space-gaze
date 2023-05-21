<?php

require_once "database.php";

$json = file_get_contents("https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=select+pl_name,disc_year,disc_pubdate,disc_facility,pl_masse,pl_rade+from+ps&format=json");
$data = json_decode($json, true);

$sql = "INSERT INTO exoplanets (planet_name, discovery_year, publication_date, discovery_facility, planet_mass, planet_radius) VALUES (:planet_name, :discovery_year, :publication_date, :discovery_facility, :planet_mass, :planet_radius)";
$stmt = $connection->prepare($sql);

foreach ($data as $planet) {
  $stmt->execute([
    ':planet_name' => $planet['pl_name'],
    ':discovery_year' => $planet['disc_year'],
    ':publication_date' => $planet['disc_pubdate'],
    ':discovery_facility' => $planet['disc_facility'],
    ':planet_mass' => $planet['pl_masse'],
    ':planet_radius' => $planet['pl_rade']
  ]);
}
