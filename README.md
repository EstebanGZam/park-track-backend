# ParkTrack Backend 🏥🧠

![ICESI University Logo](https://www.icesi.edu.co/launiversidad/images/La_universidad/logo_icesi.png)

## Authors ✒️

- Santiago Valencia  
- Juan Manuel Diaz  
- Juan Jose de la Pava  
- Esteban Gaviria  
- Andres Bueno Cardona  

---

## Project Overview 📄

ParkTrack es una aplicación diseñada para realizar pruebas de zapateo y taconeo que permiten identificar señales asociadas con el Parkinson, basándose en la escala **MDS-UPDRS**. Implementa una API REST usando **Spring Boot**, con soporte de **PostgreSQL** para gestionar la base de datos. Además, emplea **JWT (JSON Web Tokens)** para proteger los endpoints y garantizar la seguridad en el acceso a las funcionalidades críticas.

La aplicación también se integra con un servicio implementado en **Python**, encargado de interpretar señales provenientes de una **ESP32**. Estas señales son enviadas y recibidas mediante un servidor **MQTT**, que coordina el inicio de pruebas y la devolución de resultados. Cada prueba está asociada a un paciente registrado en la aplicación sobre la cual un evaluador puede realizar cambios y llevar controles de la misma.

### Evaluado (Paciente)

La tabla de **evaluado** en la base de datos incluye los siguientes campos:

- **idNumber**: Identificación única del paciente.  
- **firstName**: Nombre del paciente.  
- **lastName**: Apellido del paciente.  
- **dateOfBirth**: Fecha de nacimiento.  
- **email**: Correo electrónico del paciente.  
- **familyHistoryParkinson**: Historial familiar de Parkinson (`Y` para sí, `N` para no).  
- **height**: Altura del paciente (en cm).  
- **weight**: Peso del paciente (en kg).  
- **typeOfEvaluated**: Relación con la tabla `TypeOfEvaluated`, que clasifica al paciente según el tipo de evaluación.  
- **sex**: Relación con la tabla `Sex`, que identifica el sexo del paciente.

### Evaluador (Doctor)

- **idNumber**: Identificación única del doctor.
- **firstName**: Nombre del doctor.
- **lastName**: Apellido del doctor.
- **email**: Correo electrónico del doctor.

El evaluador tiene permisos exclusivos para:

- CRUD de pacientes.
- Iniciar pruebas.
- Consultar resultados asociados a cada paciente.

Installation Guide 🛠️
## Instalación de la base de datos
[Guía para crear la base de datos PostgreSQL con Docker Compose](docs/Init%20database.md)

Pruebas con la Base de Datos
1. Asegúrate de que el contenedor de la base de datos está activo.
2. Abre tu navegador e ingresa a:
http://127.0.0.1/login?next=/browser/
3. Ingresa con las siguientes credenciales:
Usuario: admin@admin.com
Contraseña: admin
(Puedes cambiar el idioma de la página si lo prefieres).
4. Haz doble clic en Servidores o Servers para configurar la conexión. Ingresa la contraseña del usuario configurado para la base de datos:
Contraseña: password
5. Si necesitas ejecutar un script SQL, utiliza la herramienta de consulta disponible en la interfaz de administración.

Architecture Overview 🏗️
Backend (Java Spring Boot)
- Controladores REST: Gestión de evaluadores, evaluados y pruebas.
- Protección de Endpoints: Uso de JWT para garantizar la seguridad de las operaciones.
- Persistencia: Conexión con PostgreSQL para almacenamiento de datos.

Servicios Python
- Análisis de señales: Procesamiento en tiempo real de datos enviados desde dispositivos ESP32.

Technologies Used 🛠️
<div style="text-align: left"> <p> <a href="https://www.java.com" target="_blank"> <img alt="Java" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="60" width="60"></a> <a href="https://spring.io/projects/spring-boot" target="_blank"> <img alt="Spring Boot" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" height="60" width="60"></a> <a href="https://www.postgresql.org/" target="_blank"> <img alt="PostgreSQL" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original-wordmark.svg" height="60" width="60"></a> <a href="https://www.python.org/" target="_blank"> <img alt="Python" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/python/python-original.svg" height="60" width="60"></a> <a href="https://mqtt.org/" target="_blank"> <img alt="MQTT" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mqtt/mqtt-original-wordmark.svg" height="60" width="60"></a> </p> </div>
Version Control System 📌
<div style="text-align: left"> <a href="https://git-scm.com/" target="_blank"> <img alt="Git" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original-wordmark.svg" height="60" width="60"></a> <a href="https://github.com/" target="_blank"> <img alt="GitHub" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original-wordmark.svg" height="60" width="60"></a> </div> ``



