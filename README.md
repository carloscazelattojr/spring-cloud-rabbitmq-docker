# Spring Cloud + Mensageria RabbitMQ + Docker

Estudo sobre Spring Cloud, Mensageria e buildando em Docker 

## Tecnologias
`Java 11` 
`Spring Boot`
`Spring Cloud`
`RabbitMQ`
`Docker`

## Comandos

Eureka
```
http://localhost:8761/
```

## Docker

RabbitMQ
```
# latest RabbitMQ 3.12

docker -it --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management

Login: guest e senha: guest

```


Keycloak

```
docker -it --name keycloack -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:21.1.2 start-dev
```