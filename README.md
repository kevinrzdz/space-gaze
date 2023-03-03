# Space Gaze 

## 🇪🇸 Introducción 🇪🇸

Space Gaze es un proyecto de página web que tiene como objetivo proporcionar información completa y actualizada sobre varios objetos y cuerpos celestes, incluyendo planetas, exoplanetas, asteroides y otras entidades cósmicas. Este proyecto busca hacer que esta información sea accesible al público en general, incluyendo a personas que no tengan conocimientos científicos o técnicos.

## 🇪🇸 Objetivos 🇪🇸

El proyecto fue concebido con el objetivo de fomentar el interés público en la astronomía y áreas relacionadas, proporcionando contenido confiable y atractivo que destaque las últimas investigaciones y descubrimientos en este campo. La plataforma está diseñada para ser fácil de usar e interactiva, fomentando a los visitantes a explorar y aprender más sobre el universo en el que vivimos.

A través de esta iniciativa, Space Gaze tiene como objetivo fomentar la colaboración y el intercambio de conocimientos entre la comunidad científica en general, así como facilitar la participación y comprensión del público en general en el fascinante campo de la astronomía.

## Prototipo (preliminar)

La web tendría las siguientes funcionalidades:

- Mostrar información detallada sobre distintos cuerpos celestes.
- Permitir a los usuarios buscar y filtrar información por distintos criterios, como nombre, tipo de objeto, tamaño, distancia, entre otros.
- Añadir a favoritos los cuerpos celestes a los que deseen realizar un seguimiento más detallado.
- Proporcionar información actualizada sobre eventos astronómicos, como eclipses, alineaciones planetarias y lluvias de meteoros.

La base de datos tendría las siguientes tablas y relaciones:

Tabla "objetos_celestes":

- id: identificador único del objeto celeste (clave primaria).
- nombre: nombre del objeto celeste.
- tipo: tipo de objeto celeste (planeta, exoplaneta, asteroide, cometa, estrella, galaxia, etc.).
- tamaño: tamaño del objeto celeste.
- distancia: distancia del objeto celeste a la Tierra.
- descripción: descripción detallada del objeto celeste.
- imagen: enlace a una imagen del cuerpo celeste.

Tabla "eventos":

- id: identificador único del evento astronómico (clave primaria).
- nombre: nombre del evento astronómico.
- fecha: fecha en la que ocurre el evento astronómico.
- descripción: descripción detallada del evento astronómico.
- imagen: enlace a una imagen del evento astronómico.

Tabla "imagenes":
- id_imagen: identificador único para cada imagen (clave primaria).
- id_cuerpo_celeste: identificador del cuerpo celeste al que pertenece la imagen.
- nombre_archivo: nombre del archivo de imagen.
- url: url del archivo de imagen.
- descripcion: descripción de la imagen.

Tabla "usuarios":

- id: identificador único del usuario (clave primaria).
- nombre: nombre del usuario.
- correo: correo electrónico del usuario.
- contraseña: contraseña del usuario.

Tabla "favoritos":

- id: identificador único del registro de favorito (clave primaria).
- id_usuario: identificador del usuario que agregó el objeto a favoritos (clave foránea de la tabla "usuarios").
- id_objeto: identificador del objeto celeste que se agregó a favoritos (clave foránea de la tabla "objetos_celestes").

## 🇬🇧 Introduction 🇬🇧

Space Gaze is a web page project that aims to provide comprehensive and up-to-date information on various celestial objects and bodies, including planets, exoplanets, asteroids, and other cosmic entities. This project seeks to make this information accessible to the general public, including individuals who may not have a scientific or technical knowledge.

## 🇬🇧 Objectives 🇬🇧

The project was conceived with the objective of fostering public interest in astronomy and related fields, by providing reliable and engaging content that highlights the latest research and discoveries in this area. The platform is designed to be user-friendly and interactive, encouraging visitors to explore and learn more about the universe we inhabit.

Through this initiative, Space Gaze aims to foster collaboration and knowledge-sharing among the broader scientific community, as well as facilitate public engagement and understanding of the fascinating field of astronomy.

## 🇬🇧 Prototype (preliminary) 🇬🇧

The website would have the following functionalities:

- Show detailed information about different celestial bodies.
- Allow users to search and filter information by various criteria, such as name, object type, size, distance, among others.
- Add favorite celestial bodies for users to track in detail.
- Provide updated information about astronomical events, such as eclipses, planetary alignments, and meteor showers.

The database would have the following tables and relationships:

Table "celestial_objects":

- id: unique identifier of the celestial object (primary key).
- name: name of the celestial object.
- type: type of celestial object (planet, exoplanet, asteroid, comet, star, galaxy, etc.).
- size: size of the celestial object.
- distance: distance of the celestial object to Earth.
- description: detailed description of the celestial object.
- image: link to an image of the celestial body.

Table "events":

- id: unique identifier of the astronomical event (primary key).
- name: name of the astronomical event.
- date: date when the astronomical event occurs.
- description: detailed description of the astronomical event.
- image: link to an image of the astronomical event.

Table "images":

- id_image: unique identifier for each image (primary key).
- id_celestial_object: identifier of the celestial object to which the image belongs.
- filename: name of the image file.
- url: url of the image file.
- description: description of the image.

Table "users":

- id: unique identifier of the user (primary key).
- name: name of the user.
- email: user's email address.
- password: user's password.

Table "favorites":

- id: unique identifier of the favorite record (primary key).
- id_user: identifier of the user who added the object to favorites (foreign key to the "users" table).
- id_object: identifier of the celestial object that was added to favorites (foreign key to the "celestial_objects" table).
