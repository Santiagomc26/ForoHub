# Foro Hub API

Foro Hub es una API REST desarrollada en Java utilizando Spring Boot. Esta API permite gestionar tópicos en un foro, proporcionando funcionalidades de CRUD (Crear, Leer, Actualizar y Eliminar). Además, incluye autenticación mediante JWT y validaciones según reglas de negocio.

## Características principales

1. **CRUD de tópicos:**

   - Crear un nuevo tópico.
   - Listar todos los tópicos.
   - Obtener detalles de un tópico específico.
   - Actualizar un tópico.
   - Eliminar un tópico.

2. **Autenticación y autorización:**

   - Inicio de sesión con nombre de usuario y contraseña.
   - Generación de tokens JWT para proteger las rutas.

3. **Persistencia:**

   - Uso de una base de datos MySQL para almacenar información.
   - Migraciones de base de datos gestionadas con Flyway.

4. **Validaciones:**

   - Validación de datos obligatorios para todos los campos.
   - Prevención de duplicados en los tópicos (mismo título y mensaje).

5. **Arquitectura limpia:**

   - Separación de responsabilidades entre controladores, servicios y repositorios.

## Requisitos previos

- **Java 17 o superior.**
- **Maven 4.**
- **MySQL 8.0**.

## Tecnologías utilizadas

- **Spring Boot:** Framework principal para el desarrollo de la API.
- **Spring Data JPA:** Para la gestión de datos.
- **Spring Security:** Para la autenticación y autorización.
- **Lombok:** Para reducir el boilerplate en las clases.
- **Flyway:** Para las migraciones de base de datos.
- **Swagger:** Para documentación interactiva de la API.

## Instalación y configuración

1. Clona este repositorio:

   ```bash
   git clone https://github.com/tu_usuario/foro-hub.git
   ```

2. Importa el proyecto en tu IDE favorito.

3. Configura las credenciales de la base de datos en el archivo `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/forohub
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=none
   ```

4. Ejecuta las migraciones de Flyway:

   ```bash
   mvn flyway:migrate
   ```

5. Compila y ejecuta la aplicación:

   ```bash
   mvn spring-boot:run
   ```

## Endpoints principales

### Autenticación

- **POST /login**
  - **Cuerpo:**
    ```json
    {
      "nombre": "usuario",
      "password": "contraseña"
    }
    ```
  - **Respuesta:**
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiJ9..."
    }
    ```

### CRUD de tópicos

- **POST /topicos**

  - Crear un nuevo tópico.
  - **Cuerpo:**
    ```json
    {
      "titulo": "Mi primer tópico",
      "mensaje": "¿Cómo usar Spring Boot?",
      "autor": { "id": 1 },
      "curso": { "id": 1 }
    }
    ```

- **GET /topicos**

  - Listar todos los tópicos.

- **GET /topicos/{id}**

  - Obtener detalles de un tópico específico.

- **PUT /topicos/{id}**

  - Actualizar un tópico.
  - **Cuerpo:**
    ```json
    {
      "titulo": "Nuevo título",
      "mensaje": "Nuevo mensaje"
    }
    ```

- **DELETE /topicos/{id}**

  - Eliminar un tópico.

## Contribuciones

Si deseas contribuir al proyecto:

1. Crea un fork de este repositorio.
2. Crea una rama nueva:
   ```bash
   git checkout -b mi-nueva-funcionalidad
   ```
3. Realiza los cambios y haz commit:
   ```bash
   git commit -m "Agregada nueva funcionalidad"
   ```
4. Envía un pull request.

## Autor

Este proyecto fue desarrollado por **Santiago Muñoz Córdoba**.

