# Servicio para gestión de órdenes de pedido

**Descripción**

Esta es una aplicación Spring Boot desarrollada con Java 21 que [describir la funcionalidad principal del proyecto].

**Tecnologías**

* **Spring Boot:** 3.3.3
* **Java:** 21  
* **PostgreSql:** 14.7
* **Swagger**



**Cómo ejecutar**

Se necesita de una base de datos postgres puede  levantar en un contenedor Docker utilizando el siguiente .yml 
con el comando: **docker-compose up -d**
```yaml
version: "3"
services:
  postgres-linktic:
    image: postgres:14.7
    container_name: "postgresql-linktic-5433"
    environment:
    - POSTGRES_USER=postgres
    - POSTGRES_PASSWORD=123456
    - POSTGRES_DB=postgres
    ports:
    - "5433:5432"
    volumes:
      - ./postgresql-linktic:/var/lib/postgresql/data/
    networks:
      - net
volumes:
  postgresql-linktic: {}

networks:
    net:
```

Puede levantar el servicio utilizando maven con el comando: **mvn spring-boot:run** o utilizando algun IDE como **Intellij Idea**

Para ver la documentación de las Apis Rest pega esta url en tu navegador: **http://localhost:8002/doc/swagger-ui/index.html**