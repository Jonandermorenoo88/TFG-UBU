# ISOAssetManager
📌 **Descripción**

**ISOAssetManager** es una aplicación web desarrollada con **Spring Boot** orientada a la gestión de activos de información y al apoyo en el cumplimiento de los controles de seguridad definidos en la norma **ISO/IEC 27002**.
El proyecto forma parte del **Trabajo de Fin de Grado (TFG)** del Grado en Ingeniería Informática en la Universidad de Burgos.

La aplicación permite administrar controles de seguridad, gestionar evidencias documentales, verificar la integridad de la información mediante un sistema de **Blockchain** privado, y generar informes de auditoría en formato Excel, todo ello bajo un sistema de roles y permisos.

---

🚀 **Características principales**

*   ✅ **Gestión de controles ISO 27002**
*   🔐 **Sistema de autenticación y roles**:
    *   **ADMIN**: Administración global del sistema.
    *   **AUDITOR**: Auditoría de empresas y controles.
    *   **DIRECCIÓN**: Controles organizativos (A.5).
    *   **RRHH**: Controles relacionados con personas (A.6).
    *   **FACILITIES**: Controles físicos (A.7).
    *   **IT / TÉCNICO**: Controles tecnológicos (A.8).
*   📦 **Gestión de activos de información**
*   📂 **Gestión de evidencias documentales**
*   🔗 **Verificación de integridad mediante Blockchain**
*   📊 **Exportación de resultados a Excel (XLSX)**
*   🖥️ **Interfaz web basada en Thymeleaf**

---

🧰 **Tecnologías utilizadas**

*   **Java 21 (LTS)**
*   **Spring Boot 3.5.0**
*   **Spring Web (MVC)**
*   **Spring Data JPA**
*   **Spring Security**
*   **Thymeleaf**
*   **MySQL 8.0**
*   **Apache POI (Excel)**
*   **Docker & Docker Compose**
*   **Maven**
*   **Microsoft Azure (Máquina Virtual Linux)**

---

⚙️ **Requisitos previos**

**Para ejecución local (sin Docker)**
*   JDK 21
*   MySQL Server
*   Maven

**Para ejecución con Docker**
*   Docker
*   Docker Compose

---

🖥️ **Ejecución en entorno local (sin Docker)**

1.  **Clonar el repositorio**
    ```bash
    git clone <url-del-repositorio>
    cd ISOAssetManager
    ```

2.  **Configurar base de datos MySQL**
    *   Asegúrate de tener un servidor MySQL corriendo en el puerto 3306.
    *   **No es necesario crear la base de datos manualmente**: La aplicación la creará automáticamente al iniciar (`createDatabaseIfNotExist=true`).
    *   Si tus credenciales no son `root` / `1234`, actualízalas en:
        `src/main/resources/application.properties`

3.  **Ejecutar la aplicación**
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Acceso**
    *   `http://localhost:8081/inicio`

---

🐳 **Ejecución con Docker (recomendado)**

El proyecto incluye un archivo `docker-compose.yml` que levanta:
*   Contenedor Spring Boot
*   Contenedor MySQL
*   Volúmenes persistentes para:
    *   Base de datos
    *   Evidencias documentales

▶️ **Arranque**
```bash
docker compose up --build
```

🌐 **Acceso a la aplicación**
`http://localhost:8081/inicio`

---

☁️ **Despliegue en Microsoft Azure**

La aplicación se encuentra desplegada en una máquina virtual Linux (**Ubuntu Server 24.04 LTS**) en Microsoft Azure, utilizando Docker y Docker Compose.

**Características del entorno:**
*   **VM**: Standard B2ls v2
*   **Sistema Operativo**: Ubuntu Server 24.04 LTS
*   **Acceso por SSH**
*   **Contenedores Docker**
*   **Persistencia de datos mediante volúmenes**

🌍 **Acceso público mediante DNS**

La aplicación es accesible a través del siguiente DNS público de Azure:

👉 **[http://isoassetmanager.northeurope.cloudapp.azure  .com:8081/inicio](http://isoassetmanager.northeurope.cloudapp.azure.com:8081/inicio)**

*(El puerto 8081 se encuentra expuesto mediante Docker y configurado en el firewall de Azure).*

---

🗄️ **Acceso a la base de datos (Docker + Azure)**

La base de datos MySQL se ejecuta en un contenedor Docker independiente.

▶️ **Acceder a MySQL dentro del servidor**
```bash
docker exec -it isoassetmanager-db mysql -u root -p
```
*   **Contraseña configurada**: `1234`

▶️ **Seleccionar la base de datos**
```sql
USE isoassetmanager;
SHOW TABLES;
```
*Este acceso permite:*
*   *Verificar datos*
*   *Realizar pruebas*
*   *Simular alteraciones para la demostración del sistema Blockchain*

---

**Modelo de Datos**

El sistema persiste la información en una base de datos relacional con las siguientes tablas principales:

| Tabla | Descripción |
| :--- | :--- |
| `activo` | Inventario de activos de información de la empresa. |
| `bloque` | Cadena de bloques (Blockchain) para garantizar la integridad de las evidencias. |
| `categoria` | Clasificación de los dominios de control de la norma ISO 27001. |
| `control` | Controles de seguridad específicos asociados a cada categoría. |
| `departamento` | Departamentos internos de las organizaciones. |
| `empresa` | Entidades u organizaciones gestionadas en el sistema. |
| `evidencia` | Metadatos y rutas de los archivos probatorios subidos. |
| `pregunta` | Cuestionarios de auditoría asociados a los controles. |
| `respuesta_empresa` | Respuestas específicas proporcionadas por las empresas a los cuestionarios. |
| `respuesta_posible` | Catálogo de opciones de respuesta (ej. Cumple, No cumple, N/A). |
| `rol` | Definición de los roles de seguridad (ADMIN, AUDITOR, etc.). |
| `usuario` | Credenciales y datos de perfil de los usuarios del sistema. |
| `usuario_roles` | Relación muchos a muchos entre usuarios y roles asignados. |

---

📂 **Carga inicial de preguntas (SQL)**

Las preguntas de los cuestionarios ISO se cargan automáticamente mediante los scripts SQL configurados en `application.properties`.
Los archivos se encuentran en:
`src/main/resources/sql/`

**Scripts disponibles:**
*   `data-a5.sql`
*   `data-a6.sql`
*   `data-a7.sql`
*   `data-a8.sql`

*Estos scripts se ejecutan al iniciar la aplicación si la configuración `spring.sql.init.mode` lo permite (actualmente configurado como `always`).*

---

🧹 **Limpieza del proyecto**
```bash
./mvnw clean
```

📚 **Documentación técnica (Javadoc)**
```bash
./mvnw javadoc:javadoc
```
*   **Ruta generada**: `target/reports/apidocs/index.html`

---

📁 **Estructura del proyecto**

```
ISOAssetManager/
├── src/main/java
│   ├── config
│   ├── controller
│   ├── model
│   ├── repository
│   └── service
├── src/main/resources
│   ├── sql
│   ├── static
│   ├── templates
│   └── application.properties
├── uploads/
├── Dockerfile
├── docker-compose.yml
├── mvnw
└── pom.xml
```

---

👤 **Autor**

**Jon Ander Incera Moreno**
Estudiante del Grado en Ingeniería Informática
Universidad de Burgos
**Trabajo de Fin de Grado — Curso 2024/2025**

⭐ **Observación final**
Este proyecto tiene un carácter académico, pero ha sido diseñado siguiendo criterios reales de seguridad, arquitectura y despliegue, alineados con prácticas profesionales del ámbito de la ciberseguridad y la ingeniería del software.