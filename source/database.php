<?php

$host = "localhost";
$user = "space_gaze";
$dbname = "space_gaze";
$password = "space_gaze2023.";
$api_key = "5oVvmVD68MPsv30gyVv0vEdDr8zuLWDgMvoVeEAm";

try {
  $connection = new PDO("mysql:host=$host;dbname=$dbname", $user, $password);
  $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
  die("Error while connecting to the database: " . $e->getMessage());
}

