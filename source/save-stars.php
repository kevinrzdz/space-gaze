<?php

require_once "database.php";

$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

$url = "https://api.api-ninjas.com/v1/stars";

$params = array(
  "max_distance_light_year" => 254000,
  "offset" => 0
);

$headers = array(
  "X-Api-Key: $api_key"
);

do {
  $ch = curl_init();
  curl_setopt($ch, CURLOPT_URL, "$url?" . http_build_query($params));
  curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
  curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
  $response = curl_exec($ch);
  curl_close($ch);
  $data = json_decode($response, true);

  foreach ($data as $star) {
    $name = $star["name"];
    $constellation = $star["constellation"];
    $magnitude = $star["absolute_magnitude"];
    $distance = $star["distance_light_year"];

    $stmt = $pdo->prepare("INSERT INTO stars (name, constellation, magnitude, distance_earth) VALUES (:name, :constellation, :magnitude, :distance)");
    $stmt->bindParam(":name", $name);
    $stmt->bindParam(":constellation", $constellation);
    $stmt->bindParam(":magnitude", $magnitude);
    $stmt->bindParam(":distance", $distance);
    $stmt->execute();
  }
  $params["offset"] += 30;
} while (count($data) > 0);

echo "Datos de las estrellas insertados correctamente en la base de datos.";
