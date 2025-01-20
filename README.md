# ForoHub - BackEnd de Foro de Discusión

ForoHub es una API REST para la gestión de un foro de discusión en línea, donde los usuarios pueden crear, listar, actualizar y eliminar tópicos de conversación. El proyecto utiliza tecnologías modernas como Spring Boot, Oracle SQL, y autenticación basada en JWT para garantizar la seguridad.

---

## **Características del Proyecto**
- CRUD completo para la gestión de tópicos.
- Autenticación y autorización mediante JWT.
- Gestión de relaciones entre entidades (Usuarios, Cursos y Tópicos).
- Implementación modular y mantenible.
- Validaciones robustas utilizando `jakarta.validation`.

---

## **Tecnologías Utilizadas**
- **Java 17** 
- **Spring Boot 3.x**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **Base de Datos**: MySQL
- **Lombok**: Para reducir el boilerplate de código.
- **JWT (JSON Web Tokens)**: Para autenticación y autorización segura.
- **Maven**: Para la gestión de dependencias.

---

## **Estructura del Proyecto**
La estructura principal del proyecto es la siguiente:

src/ ├── main/ │ ├── java/ │ │ ├── com.AluraBackEnd.ForoHub/ │ │ │ ├── controlador/ -> Controladores REST │ │ │ ├── dto/ -> Clases DTO (Data Transfer Objects) │ │ │ ├── modelo/ -> Entidades del sistema │ │ │ ├── repositorio/ -> Repositorios para acceso a datos │ │ │ ├── service/ -> Servicios de lógica de negocio │ │ │ ├── config/ -> Configuración de seguridad y otros │ ├── resources/ │ ├── application.properties -> Configuración del proyecto

Endpoints Disponibles
Aquí tienes una lista de los endpoints disponibles en la API:

Autenticación
POST /login: Genera un token JWT.
Body (JSON):
json
Copiar
Editar
{
    "correoElectronico": "usuario@correo.com",
    "contrasena": "password"
}
Respuesta:
json
Copiar
Editar
"Bearer <token_jwt>"
Tópicos
GET /topicos: Lista todos los tópicos.
GET /topicos/{id}: Obtiene el detalle de un tópico por su ID.
POST /topicos: Crea un nuevo tópico.
Headers: Authorization: Bearer <token_jwt>
Body (JSON):
json
Copiar
Editar
{
    "titulo": "Título del Tópico",
    "mensaje": "Mensaje del Tópico",
    "cursoId": 1
}
PUT /topicos/{id}: Actualiza un tópico existente.
DELETE /topicos/{id}: Elimina un tópico por su ID.
