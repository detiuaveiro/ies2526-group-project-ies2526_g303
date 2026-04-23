# Ata de Reunião - 1
**00/03/2026 | Maker Lab**

### Participantes
* [Diogo Ruivo] - Team Manager
* [David Cálix] - Product Owner
* [Gabriel Riquito] - Architect
* [Rodrigo Fonseca] - DevOps Master

### Agenda
- Colocar ficheiro com dados e horas dos trabalhos
- Definir qual message broker vamos usar
- Definir como comecar a construir o frontend e o backend
- Melhorar a organizacao das apresentacoes (horas de estudo + definir bem o que vamos fazer no inicio das proximas iteracoes)

### Decisões
- Foi decidido por unanimidade avançar com o RabbitMQ como Message Broker em detrimento do Mosquitto, devido aos requisitos do projeto.
- A arquitetura será 100% baseada em contentores (Docker).

### Tarefas
| Tarefa | Responsável | Estado |
|--------|------------|--------|
| Escolher Broker e definir Contrato da API (Endpoints REST) | Gabriel Riquito | Concluído |
| Criar `docker-compose.yml` base com PostgreSQL e RabbitMQ | Rodrigo Fonseca | Concluído |
| Atualizar tracker de horas da equipa | Diogo Ruivo | Concluído |
