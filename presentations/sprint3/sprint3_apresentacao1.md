# Guião de Apresentação - Iteração 3 (Arquitetura e API)

### 1. O Motor (Infraestrutura e Fluxo de Dados)
**O que tens no ecrã:** O terminal com o `docker compose up` a correr (onde se vê os logs do Spring Boot e os inserts na base de dados) ou o painel do RabbitMQ no browser.
**O que tu dizes:**
> "Professor, nesta iteração o nosso foco foi garantir a arquitetura end-to-end. A nossa infraestrutura está 100% dockerizada. 
> Neste momento, temos o nosso simulador em Python a gerar dados de temperatura e energia continuamente. Esses dados são atirados para o **RabbitMQ**, que escolhemos como message broker pela sua escalabilidade. 
> Do outro lado, temos o nosso backend em Spring Boot a atuar como consumidor, que processa a fila de forma assíncrona, valida os dados do sensor e guarda os registos no PostgreSQL."

### 2. O Contrato (Documentação da API)
**O que tens no ecrã:** A página do Swagger aberta no browser (`http://localhost:8080/swagger-ui/index.html`).
**O que tu dizes:**
> "Para garantir que a equipa de frontend sabe exatamente como consumir estes dados na próxima iteração, implementámos o **Swagger**. 
> Como pode ver, todos os nossos REST Controllers (Sensores, Users, Alertas) estão documentados automaticamente a partir do código, definindo claramente os métodos e a estrutura JSON de resposta esperada."

### 3. A Prova dos Nove (Demonstração Live)
**O que tens no ecrã:** O Postman aberto com a vossa *Collection* carregada.
**O que tu dizes:**
> "Para provar que a persistência está a ocorrer em tempo real e a API está a responder corretamente, vamos testar o endpoint de listagem diretamente aqui no Postman."
> *(Neste momento o teu colega clica no botão 'Send' do pedido `GET /api/sensores`)*
> "Como pode verificar pelo JSON retornado, a base de dados está a devolver as leituras reais que o simulador acabou de injetar no sistema, completando o ciclo end-to-end com sucesso."

---

É isto. Curto, grosso, lógico e sem dar espaço para o professor fazer perguntas manhosas porque vocês já explicaram o fluxo todo de A a Z. 

Vais ser tu a debitar este texto enquanto o teu colega clica nos botões, ou vão dividir os três pontos da apresentação entre vocês?