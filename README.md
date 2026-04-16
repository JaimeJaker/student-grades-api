# 🎯 API REST - Gestión Educativa (Backend)

Esta es la implementación del **Backend**. Se trata de una **API RESTFull** construida sobre **Java 17** y **Spring Boot** para gestionar alumnos, materias y sus respectivas notas mediante un CRUD completo.

---

## 🛠️ Tecnologías y Herramientas Evaluadas

- **Lenguaje:** Java 17
- **Framework:** Spring Boot 3.5.4 (Spring Web, Spring Validation)
- **Persistencia:** Spring Data JPA + Hibernate
- **Base de Datos:** MySQL 8.0
- **Gestor de Dependencias:** Maven
- **Despliegue:** Docker y Docker Compose

---

## 📋 Requisitos Previos

Para ejecutar este proyecto garantizando su contención total, únicamente necesitas tener instalados:

- **Docker** (v20 o superior)
- **Docker Compose** (v2 o superior)
- Git (opcional, para clonar)

---

## 🚀 Guía de Ejecución Rápida (Máximo 10 Comandos)

El proyecto está diseñado para levantar toda la infraestructura (Base de Datos + API) de forma automática con Docker y variables de entorno. 

Sigue estos 3 comandos desde la raíz de este directorio (`backend`):

1. **Construir y levantar los contenedores en segundo plano:**
   ```bash
   docker-compose up --build -d
   ```

2. **Verificar que los contenedores estén corriendo:**
   ```bash
   docker-compose ps
   ```

3. **Ver los registros en vivo de la API (Opcional):**
   ```bash
   docker logs -f evaluacion-backend
   ```

¡Listo! 🎉 La aplicación inicializará la base de datos MySQL, volcará los datos de prueba y compilará la API.

---

## 🔗 Endpoints y Verificación

Una vez finalizado el comando anterior, la API estará expuesta en el puerto `8080` de tu máquina. 
Puedes comprobar su funcionamiento ingresando desde tu navegador o herramienta como cURL/Postman:

👉 **URL de prueba principal:** [http://localhost:8080/alumnos](http://localhost:8080/alumnos)

Los endpoints disponibles (basados en requerimientos REST) incluyen:
- `/alumnos` (GET, POST, PUT, DELETE)
- `/materias` (GET, POST, PUT, DELETE)
- `/notas` e historiales cruzados.

---

## ⚙️ Variables de Entorno y Configuración (Docker)

El `docker-compose.yml` está pre-configurado, pero permite ajustar la configuración del servidor sin quemar rutas. Dichas variables determinan la conexión entre los servicios y son:

| Variable | Valor por defecto en Compose | Descripción |
|----------|-------------------|-------------|
| `DB_URI` | `jdbc:mysql://db:3306/evaluacion?useSSL=false` | Ruta hacia el contenedor de MySQL expuesto internamente |
| `DB_USER`| `root` | Usuario gestor de base de datos |
| `DB_PASSWORD`| `root` | Contraseña del gestor DB |
| `DB_DRIVER`| `com.mysql.cj.jdbc.Driver` | Driver conector de MySQL para la capa JPA |

---

## 🗄️ Datos de Prueba (Dump) Iniciales

Tal como se solicitó, se incluye información sembrada (Alumnos, Materias y Notas iniciales) para su veloz evaluación. 

El archivo (ubicado en `db/evaluacion.sql`) es interceptado y ejecutado por el Entrypoint de MySQL dentro del volumen provisto para `docker-entrypoint-initdb.d`. El evaluador no requiere hacer ninguna importación de base de datos manual.

---

## 🛑 Detener el Ambiente

Para apagar la base de datos y la API (y limpiar la red virtual generada), simplemente ejecuta:
```bash
docker-compose down
```
