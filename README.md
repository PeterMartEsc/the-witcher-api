# The-Witcher-API

__Autor:__ _Pedro Martín Escuela_

Este repositorio implementa una API RESTfull usando Java y Spring-Boot, siguiendo el [proyecto subido por el profesor](./monstruo).

El objetivo es permitir la creación, lectura, actualización y eliminación de registros de:

- Los registros de Usuarios y Roles:

    - Usuarios:
        - Crear usuario: POST /api/v1/add/user/
        - Leer usuario: GET /api/v1/user/{id}
        - Leer todos los usuario: GET /api/v1/users/
        - Actualizar usuario: PUT /api/v1/update/user/{id}
        - Eliminar usuario: DELETE /api/v1/delete/user/{id}

- Los registro de 5 tablas no relacionales con informacion (te dematica libre):

    - Personajes:

        - Crear personaje: POST /api/v1/add/character/
        - Leer personaje: GET /api/v1/character/{id}
        - Leer todos los personaje: GET /api/v1/characters/
        - Actualizar personaje: PUT /api/v1/update/character/{id}
        - Eliminar personaje: DELETE /api/v1/delete/character/{id}

    - Monstruos:

        - Crear monstruo: POST /api/v1/add/monster/
        - Leer monstruo: GET /api/v1/monster/{id}
        - Leer todos los monstruo: GET /api/v1/monsters/
        - Actualizar monstruo: PUT /api/v1/update/monster/{id}
        - Eliminar monstruo: DELETE /api/v1/delete/monster/{id}

    - Alquimia:

        - Crear alquimia: POST /api/v1/add/alchemy/
        - Leer alquimia: GET /api/v1/alchemy/{id}
        - Leer toda la alquimia: GET /api/v1/alchemy/
        - Actualizar alquimia: PUT /api/v1/update/alchemy/{id}
        - Eliminar alquimia: DELETE /api/v1/delete/alchemy/{id}

    - Armas:

        - Crear arma: POST /api/v1/add/weapon/
        - Leer arma: GET /api/v1/weapon/{id}
        - Leer todas las arma: GET /api/v1/weapons/
        - Actualizar arma: PUT /api/v1/update/weapon/{id}
        - Eliminar arma: DELETE /api/v1/delete/weapon/{id}

    - Ubicaciones:

        - Crear ubicacion: POST /api/v1/add/location/
        - Leer ubicacion: GET /api/v1/location/{id}
        - Leer todas las ubicaciones: GET /api/v1/locations/
        - Actualizar ubicacion: PUT /api/v1/update/location/{id}
        - Eliminar ubicacion: DELETE /api/v1/delete/location/{id}


La aplicación usará la gestión de excepciones mediante la clase `ResourceNotFoundException` para manejar casos donde un elemento no es encontrado. Además, la API está documentada con Swagger para facilitar la interacción.

Para arrancar el codigo, hay que ejecutar:

```bash
    mvn clean spring-boot:run
```

> Recordar lanzar el comando en el [directorio específico](./spring-boot-persistence-h2-main/) el proyecto


## Para conectarse y comprobar el servicio RESTfull

- URL: http://localhost:8080/swagger-ui/index.html

## Para comprobar la bbdd que se crea

- URL: http://localhost:8080/h2-console/

- `Username`: sa
- `Password`: password 
