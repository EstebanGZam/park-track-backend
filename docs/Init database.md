# Configuración de la Base de Datos con PostgreSQL y pgAdmin en Docker Compose

Este documento detalla cómo configurar una base de datos PostgreSQL con `pgAdmin` utilizando `Docker Compose`.

## Prerrequisitos

Antes de comenzar, asegúrate de tener instalados los siguientes programas en tu máquina:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/install/)

Estos deben estar funcionando en tu máquina al momento de ejecutar el siguiente script.

## Estructura del archivo `docker-compose.yml`

A continuación se detalla el archivo `docker-compose.yml` que puedes usar para levantar los servicios de `PostgreSQL` y
`pgAdmin`.

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

### 1. Coloca el archivo `docker-compose.yml`

Ubica el archivo `docker-compose.yml` en el directorio donde quieres trabajar.

### 2. Abre la terminal o línea de comandos

Navega hasta el directorio que contiene tu archivo `docker-compose.yml`:

```bash
cd /ruta/del/archivo/
```

### 3. Ejecuta el comando para iniciar los servicios

Para ejecutar todos los servicios definidos en el archivo, usa el siguiente comando:

```bash
docker-compose up -d
```

### 4. Verifica los contenedores en ejecución

Puedes comprobar los contenedores en ejecución con:

```bash
docker ps
```

Una vez hecho esto, ya podrás ejecutar la aplicación y conectarla correctamente con la base de datos.

## Inicialización de la base de datos

Para disponer de algunos valores de prueba en la base de datos y verificar los endpoints de consulta disponibles (como,
por ejemplo, el de consultar un evaluado por ID o el de consultar un tipo de test), ejecuta el siguiente script en la
imagen de pgAdmin. Si no sabes cómo sigue el siguiente tutorial: [Cómo usar pgAdmin](Using%20pgAdmin.md).

``` postgresql
INSERT INTO types_of_test (id, type, description) 
VALUES (1, 'foot-tapping', 'Prueba que consiste en realizar movimientos rápidos de los pies, alternando pie izquierdo y derecho, con el fin de evaluar la coordinación y velocidad de los movimientos.');

INSERT INTO types_of_test (id, type, description) 
VALUES (2, 'heel-tapping', 'Prueba que consiste en golpear el talón contra el suelo de manera repetida para evaluar la fuerza y coordinación del pie y la pierna.');


INSERT INTO types_of_evaluated (id, type) VALUES 
(1, 'Paciente'),
(2, 'Control');

INSERT INTO sexes (id, sex) VALUES 
(1, 'Masculino'),
(2, 'Femenino');


INSERT INTO evaluated (evaluator_id, id_number, first_name, last_name, date_of_birth, email, family_history_parkinson, height, weight, type_of_evaluated_id, sex_id) 
VALUES 
(1, '1234567890', 'Juan', 'Pérez', TO_DATE('1985-04-12', 'YYYY-MM-DD'), 'juan.perez@example.com', 'N', 1.75, 75.0, 
 (SELECT id FROM types_of_evaluated WHERE type = 'Paciente'), 
 (SELECT id FROM sexes WHERE sex = 'Masculino'));

INSERT INTO evaluated (evaluator_id, id_number, first_name, last_name, date_of_birth, email, family_history_parkinson, height, weight, type_of_evaluated_id, sex_id) 
VALUES 
(2, '0987654321', 'María', 'Gómez', TO_DATE('1990-05-22', 'YYYY-MM-DD'), 'maria.gomez@example.com', 'S', 1.65, 60.0, 
 (SELECT id FROM types_of_evaluated WHERE type = 'Control'), 
 (SELECT id FROM sexes WHERE sex = 'Femenino'));

```

**IMPORTANTE**: Debido a que la configuración `spring.jpa.hibernate.ddl-auto=create-drop` está habilitada en el archivo
`application.properties`, cada vez que se reinicie la aplicación de Spring Boot, será necesario ejecutar este script
para poder hacer uso de los datos insertados. 
