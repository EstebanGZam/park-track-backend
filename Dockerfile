# Imagen base para Gradle con OpenJDK 23
FROM gradle:8.11-jdk21 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de tu proyecto
COPY . .

# Ejecuta Gradle para construir el proyecto
RUN gradle clean build -x test

# Imagen base para ejecutar la aplicación
FROM eclipse-temurin:21-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR generado desde la imagen de construcción
COPY --from=build /app/build/libs/*.jar ./backend.jar

# Expone el puerto en el que se ejecutará el backend
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "backend.jar"]
