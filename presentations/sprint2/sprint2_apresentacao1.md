# Demonstração Técnica - Iteração 3: Arquitetura End-to-End e API REST

### 1. Orquestração e Infraestrutura (Docker)
**Ação:** Apresentar o terminal com os contentores em execução ou a interface do Docker Desktop.
**Discurso:** > "O objetivo principal desta iteração foi a consolidação da nossa infraestrutura end-to-end. Garantimos que a totalidade do sistema está containerizada, recorrendo ao Docker e ao `docker-compose`. Isto assegura a portabilidade da nossa aplicação para o ambiente de servidor exigido. Neste momento, temos os serviços do PostgreSQL, RabbitMQ, o backend em Spring Boot e o simulador de dados a correrem em simultâneo e em rede fechada."

### 2. Ingestão Assíncrona de Dados (Message Broker)
**Ação:** Mostrar os logs do simulador Python ou o painel de gestão do RabbitMQ (`localhost:15672`) a receber tráfego.
**Discurso:** > "Para a ingestão de dados, implementámos um pipeline assíncrono. O nosso simulador Python atua como *Publisher*, gerando telemetria em tempo real (temperatura e energia) e publicando esses dados no **RabbitMQ**. A adoção deste *message broker* foi uma decisão de arquitetura para garantir o desacoplamento dos serviços e permitir a escalabilidade do sistema caso o volume de sensores aumente no futuro."

### 3. Lógica de Negócio e Persistência (Spring Boot & PostgreSQL)
**Ação:** Mostrar os logs do Spring Boot no terminal a fazer o consumo das mensagens e os respetivos `inserts` via Hibernate.
**Discurso:** > "No lado do backend, desenvolvemos a aplicação em Spring Boot, que atua simultaneamente como *Consumer* da fila do RabbitMQ e provedor da API REST. Assim que as mensagens chegam ao broker, o nosso serviço consome o JSON, realiza a validação dos dados contra a entidade 'Sensor' existente e, através do Hibernate/JPA, garante a persistência segura e relacional destes registos na nossa base de dados PostgreSQL."

### 4. Validação do Contrato da API e Demonstração
**Ação:** Abrir primeiro o Swagger UI (`localhost:8080/swagger-ui/index.html`) e, de seguida, realizar o teste prático no Postman.
**Discurso:** > "Para a definição clara dos contratos da nossa API REST e facilitação da futura integração com o Frontend em React, implementámos o **Swagger/OpenAPI**, que documenta automaticamente todos os nossos *endpoints* CRUD. 
> 
> Para demonstrar o pipeline completo de forma prática, vamos executar um pedido `GET` através do Postman ao nosso *endpoint* de telemetria. Como é possível observar na resposta JSON, o backend está a expor corretamente os dados que estão a ser injetados pelo simulador e guardados na base de dados em tempo real."

***