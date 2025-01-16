# Usa la imagen oficial de MySQL como base
FROM mysql:8.0

# Copia el archivo SQL que contiene la creación de la base de datos al directorio donde MySQL lo cargará automáticamente
COPY init.sql /docker-entrypoint-initdb.d/

# Exponer el puerto 3306
EXPOSE 3306
