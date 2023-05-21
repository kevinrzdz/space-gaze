<?php

require_once "database.php";
set_time_limit(500);

$url = "https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=" . $api_key;
$more = true;

while ($more) {
  $json = file_get_contents($url);
  $data = json_decode($json);
  $asteroids = $data->near_earth_objects;

  foreach ($asteroids as $asteroid) {
    $sql = "INSERT INTO asteroids (name, diameter, dangerous) VALUES (:name, :diameter, :dangerous)";
    $last_id;

    try {
      $stmt = $connection->prepare($sql);
      $stmt->execute([
        ":name" => $asteroid->name,
        ":diameter" => $asteroid->estimated_diameter->meters->estimated_diameter_max,
        ":dangerous" => ($asteroid->is_potentially_hazardous_asteroid ? 1 : 0)
      ]);

      $last_id = intval($connection->lastInsertId());
    } catch (PDOException $e) {
      echo "Error: " . $e->getMessage() . "<br/>";
    }
    foreach ($asteroid->close_approach_data as $approach) {
      if ($approach->orbiting_body == "Earth") {
        $sql2 = "INSERT INTO asteroids_approaches (asteroid_id, date, speed) VALUES (:asteroid_id, :date, :speed)";

        try {
          $stmt2 = $connection->prepare($sql2);
          $stmt2->execute([
            ":asteroid_id" => $last_id,
            ":date" => $approach->close_approach_date,
            ":speed" => floatval($approach->relative_velocity->kilometers_per_hour)
          ]);
        } catch (PDOException $e) {
          echo "Error: " . $e->getMessage() . "<br/>";
        }
      }
    }
  }

  if (isset($data->links)) {
    if (isset($data->links->next)) {
      $url = $data->links->next;
    } else {
      $more = false;
    }
  } else {
    $more = false;
  }
}

$connection = null;
