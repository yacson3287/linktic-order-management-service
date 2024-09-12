# Prueba técnica  LinkTic
## Yacson Ramirez 
**https://www.linkedin.com/in/yacson3287/**

El Desarrollo consiste en una aplicación muy sencilla de e-commerce para lo que se desarrollaron 2 microservicios con funciones básicas los cuales son:

* **Product-catalog-service:** cuya función es tener un inventario de productos y categoría de productos.

* **Order-management-services:** cuya funcionalidad es llevar un control de ordenes de pedido, asignar productos a dichas órdenes.

Cada servicio está desarrollado en Java 21 con Spring Boot 3.3 se utilizó además:
* PostgreSql version 14.7
* Flyway para control de migraciones
* Swagger para documentación de Apis Rest
* Feing para comunicación entre microservicios

Cada microservicio consta de un modelo de Datos muy simplificado para el caso:

**Product-catalog-service**

![image](https://github.com/user-attachments/assets/38479723-2a41-472e-8872-763117786f35)


**Order-management-services**

![image](https://github.com/user-attachments/assets/68686bdd-8f7e-47f0-858d-dd81abf12b9a)


## Arquitectura de servicios

La estructura interna de servicio está basada en un arquitectura hexagonal la cual consta de tres capas principales:

* **Capa de dominio (domain):** Es la capa más interna donde se encuentran entidades de negocio y definiciones de contratos con el core del servicio mediante interfaces.

* **Cada aplicación (application):** aquí se desarrolla toda la lógica de negocio haciendo uso de las interfaces definidas en la capa de dominio.

* **Capa de infraestructura (infrastructure):** Esta capa implementa adaptadores para acceder a repositorios de información , contiene los controladores Rest de las apis y las configuraciones necesarias a nivel de framework.

## Documentacion de Apis:

para la documentación de apis se utilizó swagger en cada servicio
se puede acceder con las siguientes rutas respectivamente

Product-catalog-service: http://localhost:8001/doc/swagger-ui/index.html

Order-management-services: http://localhost:8002/doc/swagger-ui/index.html

También puede acceder a las colecciones de postman publicadas en:

https://www.postman.com/gold-crater-449442/linktic/overview

## Casos de uso:

Se manejaron casos básicos como:
* Agregar Categoría al catálogo
* Agregar producto al catálogo
* Consultar categorías
* Consultar productos de una categoría
* Crear orden de pedido
* Agregar producto a orden de pedido: en este caso se sustraen productos del catálogo dependiendo y se hacen las validaciones correspondientes respecto al stock que se tenga.
* Estados de orden de pedido, PENDIENTE, COMPLETADA, CANCELADA. En el caso de que una

## CI/CD

Se creó una pipeline utilizando Jenkins donde se ejecutan las diferentes etapas  no se integró con ningún servicio cloud )

Las etapas del pipeline son:
* Test: se ejecutan los test del servicio correspondiente
* Package: Se compila el proyecto y se genera los archivos .jar
* Build: Se crea un imagen docker con las configuraciones y el .jar generado en la etapa anterior listo para ser levantado
* Registry: Esta etapa es dependiente del servicio cloud que se utilice y debe registrar la imagen en el repositorio correspondiente ejemplo: en GCP seria en Artifact registry
* Deploy: Esta etapa también depende del servicio Cloud y consiste en desplegar el servicio ya sea en contenedores de kubernetes o la alternativa que se haya escogido en la infraestructura, ECS, CloudRun etc ..

Los archivos del pipeline se encuentra en cada servicio en el directorio **jenkins/Jenkinsfile**

## nstrucciones para levantar los servicios

**Requerimientos**
* Java 21
* Maven
* Docker, Docker compose
* Algún IDE como Intellij (opcional)

Debe levantar una base de datos PostgreSql . se recomiendo hacerlo en docker compose con el siguiente yml

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

En el archivo raíz de cada servicio ejecute el comando

          mvn spring-boot:run

También puede levantar el servicio desde las opciones del IDE de su preferencia


En caso de que no utilice la base de datos en docker del paso uno entonces deberá hacer los ajustes necesarios en el archivo

**application-dev.properties**

el cual se encuentra en la ruta src/main/resources/application-dev.properties


