<?php
// Establecer las variables de conexión a la base de datos
$host = "localhost";
$dbname = "asteroids";
$username = "asteroids_app";
$password = "asteroids_app2023";

// Establecer la conexión a la base de datos usando PDO
try {
  $pdo = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
} catch (PDOException $e) {
  die("Error al conectarse a la base de datos: " . $e->getMessage());
}

// Establecer las opciones para PDO
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

// Establecer la URL de la API y el API Key
$url = "https://api.api-ninjas.com/v1/stars";
$api_key = "gINtdhWYEx2CD2VCTle9ug==VY575ynAYuYezmdC";

// Establecer los parámetros para la solicitud GET
$params = array(
  "max_distance_light_year" => 254000,
  "offset" => 0
);

// Establecer los encabezados para la solicitud GET
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
  // Decodificar la respuesta JSON en un array asociativo
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
