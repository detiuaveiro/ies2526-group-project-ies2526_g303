# Relatório de Trabalho – Iteração 3 (I3)

**Período:** 16–20 abril 2026
**Projeto:** SmartHome System (RabbitMQ + Spring Boot + React + Docker)

---

## 16 de abril de 2026 – Arquitetura e infraestrutura base

**Trabalharam:** Gabriel, Rodrigo

| Tarefa                                  | Responsáveis | Horas |
| --------------------------------------- | ------------ | ----- |
| Decisão do Message Broker (RabbitMQ)    | Gabriel      | 1h    |
| Definição da arquitetura do sistema     | Gabriel      | 1h    |
| Definição inicial do fluxo de mensagens | Gabriel      | 1h    |
| Setup inicial Docker (DB + RabbitMQ)    | Rodrigo      | 2h    |
| Estrutura inicial docker-compose        | Rodrigo      | 1h    |

**Total do dia:** 5h

---

## 17 de abril de 2026 – Backend base (consumo de dados)

**Trabalharam:** Gabriel, Diogo

| Tarefa                                 | Responsáveis | Horas |
| -------------------------------------- | ------------ | ----- |
| Implementação Consumer RabbitMQ        | Gabriel      | 2h    |
| Modelação Sensor / SensorData          | Gabriel      | 2h    |
| Persistência de dados em PostgreSQL    | Gabriel      | 1h    |
| Estrutura inicial API REST (CRUD base) | Diogo        | 2h    |
| Integração backend com base de dados   | Diogo        | 1h    |

**Total do dia:** 8h

---

## 18 de abril de 2026 – Backend + integração parcial frontend

**Trabalharam:** Gabriel, Diogo

| Tarefa                                  | Responsáveis | Horas |
| --------------------------------------- | ------------ | ----- |
| Ajustes Consumer RabbitMQ               | Gabriel      | 1h    |
| Ajustes endpoints REST (Sensors/Data)   | Gabriel      | 1h    |
| Integração inicial frontend (API calls) | Diogo        | 2h    |
| Debug comunicação backend ↔ frontend    | Diogo        | 1h    |

**Total do dia:** 5h

---

## 19 de abril de 2026 – Integração e testes

**Trabalharam:** Diogo, David

| Tarefa                           | Responsáveis | Horas |
| -------------------------------- | ------------ | ----- |
| Integração frontend com API REST | Diogo        | 2h    |
| Testes de fluxo end-to-end       | Diogo        | 2h    |
| Configuração Swagger/OpenAPI     | David        | 2h    |
| Criação Postman Collection       | David        | 2h    |
| Validação de User Stories        | David        | 1h    |

**Total do dia:** 9h

---

## 20 de abril de 2026 – Dockerização e entrega

**Trabalharam:** Rodrigo, David

| Tarefa                                      | Responsáveis | Horas |
| ------------------------------------------- | ------------ | ----- |
| Finalização docker-compose (stack completo) | Rodrigo      | 1h    |
| Dockerização frontend + simulador Python    | Rodrigo      | 2h    |
| Ajustes finais de integração                | David        | 1h    |
| Relatório final da Iteração 3               | David        | 2h    |

**Total do dia:** 5h

---

# Resumo geral da equipa

| Membro          | Horas totais |
| --------------- | ------------ |
| Gabriel Riquito | 8h           |
| Rodrigo Fonseca | 6h           |
| David Cálix     | 7h           |
| Diogo Ruivo     | 8h           |

---

## Observações gerais da iteração

* O backend ficou funcional com consumo de mensagens via RabbitMQ e persistência em base de dados.
* O sistema de sensores está operacional com dados simulados.
* O frontend foi parcialmente desenvolvido, mas ainda não está totalmente estabilizado nem integrado com todos os fluxos.
* A comunicação end-to-end foi demonstrada parcialmente via Postman e testes manuais.
* A base da arquitetura está preparada para evolução na próxima iteração.

