# ISOAssetManager

## Descripción
ISOAssetManager es una aplicación web desarrollada con Spring Boot para la gestión de activos y controles relacionados con normativas ISO. Este proyecto forma parte de un Trabajo de Fin de Grado (TFG) en la Universidad de Burgos.

La aplicación permite administrar controles, verificar la integridad de los datos mediante tecnología Blockchain y generar reportes en Excel, todo ello gestionado bajo un sistema de roles.

## Características Principales
- **Gestión de Controles**: Creación, edición y visualización de controles ISO.
- **Seguridad y Roles**: Sistema de autenticación con roles diferenciados:
  - **Administrador (ADMIN)**: Acceso total al sistema.
  - **Auditor (AUDITOR)**: Gestión de auditorías y acceso a todos los cuestionarios.
  - **Dirección (DIRECCION)**: Acceso a controles organizacionales (A5).
  - **Recursos Humanos (RRHH)**: Acceso a controles de personas (A6).
  - **Facilities (FACILITIES)**: Acceso a controles físicos (A7).
  - **IT/Técnico (IT/TECNICO)**: Acceso a controles tecnológicos (A8).
- **Verificación Blockchain**: Mecanismo para verificar la integridad de los registros mediante hash/blockchain.
- **Exportación de Datos**: Funcionalidad para exportar listados y respuestas a formato Excel (XLSX).
- **Interfaz Web**: Interfaz de usuario amigable construida con Thymeleaf.

## Tecnologías Utilizadas
- **Java 21**: Lenguaje de programación principal.
- **Spring Boot 3.5.0**: Framework para el desarrollo de la aplicación.
  - *Spring Data JPA*: Persistencia de datos.
  - *Spring Security*: Seguridad y autenticación.
  - *Spring Web*: Controlador MVC.
- **Thymeleaf**: Motor de plantillas para la vista.
- **MySQL**: Base de datos relacional.
- **Apache POI**: Librería para la generación de archivos Excel.
- **Maven**: Gestor de dependencias y construcción.

## Requisitos Previos
- **Java Development Kit (JDK) 21** instalado.
- **MySQL Server** instalado y en ejecución.

## Instalación y Configuración

1. **Clonar el repositorio**
   ```bash
   git clone <url-del-repositorio>
   cd ISOAssetManager
   ```

2. **Configurar la Base de Datos**
   - Asegúrate de tener un servidor MySQL corriendo en el puerto `3306`.
   - Crea una base de datos llamada `isoassetmanager` (o la aplicación intentará crearla si tienes permisos).
   - Verifica las credenciales en `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/isoassetmanager...
     spring.datasource.username=root
     spring.datasource.password=1234
     ```
   - Modifica `username` y `password` según tu configuración local.

3. **Ejecutar la aplicación**
   Usa el wrapper de Maven incluido para ejecutar la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```
   (En Windows puedes usar `mvnw.cmd spring-boot:run`)

## Documentación (Javadoc)
Para generar la documentación técnica del proyecto (Javadoc), ejecuta el siguiente comando:
```bash
./mvnw javadoc:javadoc
```
La documentación generada estará disponible en: `target/reports/apidocs/index.html`.

## Uso
Una vez iniciada la aplicación, abre tu navegador web y accede a:
http://localhost:8081

El puerto por defecto está configurado en `8081` (ver `application.properties`).

## Estructura del Proyecto
- `src/main/java`: Código fuente Java (Controladores, Modelos, Servicios, Configuración).
- `src/main/resources`: Archivos de configuración y plantillas (Thymeleaf).
- `pom.xml`: Definición de dependencias Maven.

## Autor
Jon Ander Incera Moreno
Estudiante del Grado en Ingeniería Informática — Universidad de Burgos
Trabajo de Fin de Grado 2025