<?php

require_once "database.php";
$url = "https://astronomyapi.com/api/ephemeris";

$ch = curl_init();

curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

$response = curl_exec($ch);

curl_close($ch);

if ($response !== false) {
  $data = json_decode($response);
} else {
  echo "No se pudo obtener los datos de la API";
}
