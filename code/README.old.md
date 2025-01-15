<div align="justify">

# <img src=images/spring-logo.png width="40"> Construcción de Servicios con Spring

<div align="center">
   <img src=images/users.png width="400">
</div>

## <img src=images/crud.png width="40"> CRUD de Usuarios en Java y Spring Boot

Este proyecto implementa una API RESTful utilizando **Java** y **Spring Boot** para gestionar usuarios. El objetivo es permitir la creación, lectura, actualización y eliminación de registros de usuarios, mediante los siguientes endpoints:

- **CRUD de usuarios**
  - **Crear usuario**: `POST /api/v1/add/user/`
  - **Leer usuario**: `GET /api/v1/user/{id}`
  - **Leer todos los usuario**: `GET /api/v1/users/`
  - **Actualizar usuario**: `PUT /api/v1/update/user/{id}`
  - **Eliminar usuario**: `DELETE /api/v1/delete/user/{id}`

La aplicación usa la gestión de excepciones mediante la clase `ResourceNotFoundException` para manejar casos donde un usuario no es encontrado. Además, la API está documentada con **Swagger** para facilitar la interacción.

Para arrancarlo debemos de ejecutar:

```console
mvn clean spring-boot:run
```

## Documentación OpenAPI (Swagger UI)

- **URL**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

> En el siguiente [enlace](https://github.com/jpexposito/code-learn/tree/main/segundo/pgv/5-red-servicos) se encuentra la documentación de creación y documentación de servicios en [Spring](https://spring.io/). El cliente para el consumo del servicio de forma programática se encuentra en el siguiente [enlace](https://github.com/jpexposito/spring-boot-persistence-h2-client).

</div>