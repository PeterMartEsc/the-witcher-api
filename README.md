# The-Witcher-API

__Autor:__ _Pedro Martín Escuela_

Este repositorio implementa una API RESTfull usando Java y Spring-Boot, siguiendo el [proyecto subido por el profesor](). Las tablas con la iformación toman como base contenido del videojuego The Witcher.

<img src="https://assets.nintendo.com/image/upload/q_auto/f_auto/ncom/software/switch/70010000033071/3f7ee6aa3482b514bd443e116022b038a9728f017916ed37da3f09f731a7d5f2"/>

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

        - Crear personaje: POST /api/v1/characters/add/
        - Leer personaje: GET /api/v1/characters/{id}
        - Leer todos los personaje: GET /api/v1/characters/
        - Actualizar personaje: PUT /api/v1/characters/update/{id}
        - Eliminar personaje: DELETE /api/v1/characters/delete/{id}

    - Monstruos:

        - Crear monstruo: POST /api/v1/add/monsters/
        - Leer monstruo: GET /api/v1/monsters/{id}
        - Leer todos los monstruo: GET /api/v1/monsters/
        - Actualizar monstruo: PUT /api/v1/monsters/update/{id}
        - Eliminar monstruo: DELETE /api/v1/monsters/delete/{id}

    - Alquimia:

        - Crear alquimia: POST /api/v1/add/alchemys/
        - Leer alquimia: GET /api/v1/alchemys/{id}
        - Leer toda la alquimia: GET /api/v1/alchemys/
        - Actualizar alquimia: PUT /api/v1/alchemys/update/{id}
        - Eliminar alquimia: DELETE /api/v1/alchemys/delete/{id}

    - Armas:

        - Crear arma: POST /api/v1/add/weapons/
        - Leer arma: GET /api/v1/weapons/{id}
        - Leer todas las arma: GET /api/v1/weapons/
        - Actualizar arma: PUT /api/v1/weapons/update/{id}
        - Eliminar arma: DELETE /api/v1/weapons/delete/{id}

    - Ubicaciones:

        - Crear ubicacion: POST /api/v1/add/locations/
        - Leer ubicacion: GET /api/v1/locations/{id}
        - Leer todas las ubicaciones: GET /api/v1/locations/
        - Actualizar ubicacion: PUT /api/v1/locations/update/{id}
        - Eliminar ubicacion: DELETE /api/v1/locations/delete/{id}


La aplicación usará la gestión de excepciones mediante la clase `ResourceNotFoundException` para manejar casos donde un elemento no es encontrado. Además, la API está documentada con Swagger para facilitar la interacción.

Para arrancar el codigo, hay que ejecutar:

```bash
    mvn clean spring-boot:run
```

> Recordar lanzar el comando en el [directorio específico](./spring-boot-persistence-h2-main/) el proyecto


## Para conectarse y comprobar el servicio RESTfull con Swagger

- URL: http://localhost:8080/swagger-ui/index.html

## Para comprobar la bbdd que se crea

- URL: http://localhost:8080/h2-console/

- `Username`: root
- `Password`: 

Dentro, para visualizar las tablas especificas creadas, se tendrán que ejecutar las sentencias sql especificas.

Para mostrar las tablas creadas al lanzar la api:

```sql
    USE the_witcher_api;
    SHOW tables;
```

Para mostrar el contenido de una tabla específica:

```sql
    SELECT * FROM <nombre-tabla>;
```

