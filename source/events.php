<?php
// Definir la URL de la API
$url = "https://astronomyapi.com/api/ephemeris";

// Inicializar cURL
$ch = curl_init();

// Establecer las opciones de cURL
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

// Ejecutar cURL y obtener la respuesta
$response = curl_exec($ch);

// Cerrar cURL
curl_close($ch);

// Verificar si la respuesta es válida
if ($response !== false) {
  // Convertir la respuesta JSON a un objeto PHP
  $data = json_decode($response);
  // Mostrar algunos datos de la API
  echo "El sol sale a las " . $data->sunrise . "<br/>";
  echo "La luna está en fase de " . $data->moon_phase . "<br/>";
} else {
  // Mostrar un mensaje de error
  echo "No se pudo obtener los datos de la API";
}
