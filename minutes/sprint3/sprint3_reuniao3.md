# Ata de Reunião - 3 (Ponto de Situação e Integração)
**20/04/2026 | Maker Lab**

### Participantes
* [Diogo Ruivo] - Team Manager
* [David Cálix] - Product Owner
* [Gabriel Riquito] - Architect
* [Rodrigo Fonseca] - DevOps Master

### Agenda
- Ponto de situação do Consumer do RabbitMQ
- Criação dos REST Controllers (CRUD)
- Planeamento da integração Frontend-Backend
- Estruturação da documentação e testes da API

### Decisões
- O Diogo assume a criação dos Controllers no Spring Boot para equilibrar a carga de trabalho do Gabriel, que está focado na lógica de ingestão de dados do Broker para a BD.
- O Frontend (React) não terá lógica complexa nesta iteração, focando-se apenas em consumir os endpoints via axios/fetch, conforme indicação do guião.
- Os testes de validação da API serão feitos obrigatoriamente no Postman para provar a persistência de dados caso o Frontend falhe.

### Tarefas
| Tarefa | Responsável | Estado |
|--------|------------|--------|
| Implementar Consumer do RabbitMQ no Spring Boot | Gabriel Riquito | Concluído |
| Criar Endpoints REST (Controllers) no Backend | Diogo Ruivo | Concluído |
| Ligar Frontend (React) à API (substituir mock data) | Diogo Ruivo | Concluído |
| Configurar Swagger/OpenAPI e criar Postman Collections | David Cálix | Concluído |