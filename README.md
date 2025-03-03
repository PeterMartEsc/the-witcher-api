<div style="text-align: justify;">
    
# The-Witcher-API

__Autor:__ _Pedro Martín Escuela_

<img src="https://assets.nintendo.com/image/upload/q_auto/f_auto/ncom/software/switch/70010000033071/3f7ee6aa3482b514bd443e116022b038a9728f017916ed37da3f09f731a7d5f2"/>

Este repositorio implementa una __API RESTfull__ y otra __SOAP__ usando __Java y Spring-Boot__, siguiendo el [proyecto subido por el profesor](https://github.com/jpexposito/spring-boot-persistence-h2). La temátia de las tablas con información es del videojuego _The Witcher_.

## Índice:

- API RESTfull
    - [Introducción](#introducción)
    - [Intrucciones para el funcionamiento](#instrucciones-para-el-funcionamiento)
    - [Endpoints](#endpoints)
    - [Securización](#securización)

- API SOAP
    - [Introducción API SOAP](#introducción-api-soap)
    - [Enlaces de acceso](#enlaces-de-acceso)
    - [Endpoints](#endpoints-específicos-soap)

## Introducción

La aplicación está realizada con Spring Boot como framework, que usa Spring Data JPA para gestionar la concurrencia de la BBDD. La base de datos es en MySQL, aun que la mayoría de sus tablas son no relacionales. Además, gestionará las excepciones mediante la clase `ResourceNotFoundException` para manejar casos donde un elemento no es encontrado. La API está documentada con Swagger para facilitar la interacción.

## Instrucciones para el funcionamiento

Para arrancar el codigo, hay que ejecutar:

```bash
    mvn clean spring-boot:run
```

> Recordar lanzar el comando en el [directorio específico](./spring-boot-persistence-h2-main/) el proyecto


### Para conectarse y comprobar el servicio RESTfull con Swagger

- URL: http://localhost:8080/swagger-ui/index.html

### Para comprobar la Base de Datos

La BBDD está creada dentro de [resources](./code/src/main/resources/) a partir de un .sql que se encuentra en la misma [carpeta](./code/src/main/resources/back/) que el __backup__.

El __gestor__ de la __Base de Datos__ es Sqlite. Se puede acceder a ella mediante el comando:

```code
sqlite3 <nombre-bbdd>
```

situancose en la terminal, en el mismo directorio donde se encuentra. Ahí se verán reflejados los cambios que se realicen a través de los __endpoints__.

## Endpoints API RESTFull

El objetivo de la aplicación es permitir la creación, lectura, actualización y eliminación de registros de:

- Las tablas Usuarios y Roles:

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

# Securización

La api REST tiene un filtro para securizar las rutas. Las unicas rutas publicas corresponden a las de __Swagger__ y las de __inicio de sesión__ y __registro__. 

Una vez el usuario se registra o inicia sesión, se devuelve un __token jwt__, el cual se debe colocar en la cabecera de las peticiones para que se detecte como __autenticado__. Como usamos Swagger para comprobarlo, se deben en la pestaña que se abre al clicar en lo siguiente:

<div align=center>
    <img src="./resources/authorize.png"/>
</div>

Esto lo que hará es lanzar una cabecera del siguiente tipo con cada petición:

```code
Authorization: Bearer <token-jwt>
```

Así, el filtro detectará que tiene ese token de autorización, lo comprobará y si es correcto, permitira que se vean los enpoints securizados.

Todos los endpoints securizados piden que se esté autenticado y sean o `USER` o `ADMIN`.

## Introducción API SOAP

Aparte de lo mencionado anteriormente, también hemos implementado unos servicios SOAP para familiarizarnos con este tipo de servicios. Las clases están ubicadas en la subcarpeta [soap](./code/src/main/java/petermartesc.springboot/service) de la carpeta service. 

Para realizarlo, hemos añadido las dependencias de cfx-springboot-starter-jaxws para facilitar la configuración e implementación de servicios SOAP en spring-boot y la de jakarta xml bind api que permite pasar (serializar) objetos de java a xml, lo cual es crucial para los servicios SOAP, que usan unicamente XML.

## Enlaces de acceso

Para acceder a la lista de servicios con sus respectivos métodos, acceder a la __URL__: http://localhost:8080/services

Para acceder a cada servicio expecífico, basta con hacer click en el enlace de cada apartado, pero la sintaxis estandar es http://localhost:8080/services + /recurso?wsdl


## Endpoints específicos SOAP 

- __Alchemy WSDL URL__: http://localhost:8080/services/alchemys?wsdl

- __Character WSDL URL__: http://localhost:8080/services/characters?wsdl

- __Location WSDL URL__: http://localhost:8080/services/locations?wsdl

- __Monster WSDL URL__: http://localhost:8080/services/monsters?wsdl

- __Role WSDL URL__: http://localhost:8080/services/roles?wsdl

- __User WSDL URL__: http://localhost:8080/services/users?wsdl

- __Weapons WSDL URL__: http://localhost:8080/services/weapons?wsdl


</div>
