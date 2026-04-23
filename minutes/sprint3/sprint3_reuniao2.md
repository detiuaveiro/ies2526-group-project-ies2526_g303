# Ata de Reunião - 2 (Controlo de Infraestrutura)
**2/04/2026 | Maker Lab**

### Participantes
* [Diogo Ruivo] - Team Manager
* [David Cálix] - Product Owner
* [Gabriel Riquito] - Architect
* [Rodrigo Fonseca] - DevOps Master

### Agenda
- Ponto de situação do Docker e RabbitMQ
- Bloqueios na comunicação entre o Backend e o Broker
- Revisão da estrutura JSON do simulador

### Decisões
- O simulador Python tem de ser atualizado para cuspir a estrutura exata de JSON que o Gabriel definiu no contrato, caso contrário a serialização no Java vai estourar.

### Tarefas
| Tarefa | Responsável | Estado |
|--------|------------|--------|
| Testar tolerância a falhas do Spring Boot a ligar ao Rabbit | Gabriel Riquito | Concluído |
| Fechar estrutura final do JSON (Sensores e Energia) | Gabriel Riquito | Concluído |
| Atualizar Simulador Python com novos JSONs | Rodrigo Fonseca | Concluído |