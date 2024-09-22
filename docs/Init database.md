# Configuración de la Base de Datos con PostgreSQL y pgAdmin en Docker Compose

Este documento detalla cómo configurar una base de datos PostgreSQL con `pgAdmin` utilizando `Docker Compose`.

## Prerrequisitos

Antes de comenzar, asegúrate de tener instalados los siguientes programas en tu máquina:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Estructura del archivo `docker-compose.yml`

A continuación se detalla el archivo `docker-compose.yml` que puedes usar para levantar los servicios de PostgreSQL, `pgAdmin` y `Adminer`.

```yaml
version: '3.8'

services:

  park-track-db:
    platform: linux/x86_64
    command: [ "--max_connections=1000" ]
    image: postgres
    restart: always
    shm_size: 128mb
    environment:
      POSTGRES_DB: 'park-track'
      POSTGRES_USER: 'user'
      POSTGRES_PASSWORD: 'password'
    ports:
      - '5433:5432'
    expose:
      - '5433'
    volumes:
      - my-postgres-db:/var/lib/postgresql/data

  pgadmin:
    image: dpage/pgadmin4
    restart: always
    environment:
      PGADMIN_DEFAULT_EMAIL: 'admin@admin.com'
      PGADMIN_DEFAULT_PASSWORD: 'admin'
    ports:
      - '80:80'
    depends_on:
      - park-track-db

volumes:
  my-db: { }
  my-postgres-db: { }
```
Una vez hecho esto, ya podrás ejecutar la aplicación y conectarla correctamente con la base de datos.