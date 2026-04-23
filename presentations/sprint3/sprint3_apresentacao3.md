# Iteração 3 (I3) — Sistema SmartHome

## 1. Contexto da Iteração

Nesta iteração, o foco principal foi evoluir a arquitetura do sistema para uma solução **end-to-end funcional**, integrando:

- Simulador de sensores (Python)
- Message Broker (RabbitMQ)
- Backend (Spring Boot + PostgreSQL)
- Base de dados relacional
- Primeiros passos de frontend (React/TypeScript)

Apesar de o frontend ainda não estar completamente funcional, foi iniciada a sua estrutura base.

O objetivo principal foi garantir que os **dados fluem corretamente do simulador até à base de dados**, passando pelo broker e backend.

---

## 2. Arquitetura Implementada

### Fluxo de dados

```

Simulador Python → RabbitMQ → Spring Boot Consumer → PostgreSQL

````

### Entidades principais

- Sensor
- SensorData
- Alert
- User

---

## 3. Modelo de Dados (Resumo)

### Sensor
- id (PK, String)
- tipo
- divisao
- unidade

### SensorData
- id (PK, Long)
- sensor (FK → Sensor)
- valor
- timestamp

### Alert
- id (PK, Long)
- sensor (referência lógica via sensorId ou relação futura)
- message
- timestamp
- isRead

### User
- id (PK, Long)
- name
- role
- email

---

## 4. Integração com RabbitMQ

Foi implementado um consumidor no backend:

- Consome mensagens da fila `telemetry_queue`
- Converte JSON em `SensorData`
- Valida existência do sensor na base de dados
- Persiste dados em PostgreSQL

### Formato esperado de mensagem

```json
{
  "sensor": {
    "id": "temp-sala-01"
  },
  "valor": 22.3,
  "timestamp": "2026-04-21T00:00:00"
}
````

---

## 5. Estado do Frontend

O frontend React/TypeScript:

* Estrutura inicial criada
* Componentes base iniciados
* Integração com API REST ainda não concluída
* Dados ainda parcialmente mockados

Conclusão:

> O frontend ainda não está ligado de forma completa ao backend. O foco da I3 foi colocado na estabilidade do fluxo de dados backend + broker + base de dados.

---

## 6. Docker e Infraestrutura

Foi preparada a base para execução em containers:

* Backend (Spring Boot)
* PostgreSQL
* RabbitMQ
* Simulador Python
* Frontend (parcial/incompleto)

Objetivo:

* Garantir execução consistente via `docker-compose`

---

## 7. Distribuição de Trabalho e Esforço

### 2026-04-16 (Dia 1 — Arquitetura e infraestrutura base)

* Gabriel Riquito: decisão da arquitetura do sistema e escolha do RabbitMQ
* Gabriel Riquito: definição inicial do fluxo de mensagens
* Rodrigo Fonseca: configuração inicial Docker (RabbitMQ + base de dados)
* Rodrigo Fonseca: estrutura inicial do docker-compose
* Esforço estimado:

  * Gabriel: 3h
  * Rodrigo: 3h

---

### 2026-04-17 (Dia 2 — Backend base e consumo de dados)

* Gabriel Riquito: implementação do consumer RabbitMQ e modelação de dados (Sensor / SensorData)
* Gabriel Riquito: persistência inicial dos dados na base de dados
* Diogo Ruivo: criação inicial de endpoints REST (CRUD base)
* Diogo Ruivo: ligação backend à base de dados
* Esforço estimado:

  * Gabriel: 5h
  * Diogo: 3h

---

### 2026-04-18 (Dia 3 — Backend e integração parcial frontend)

* Gabriel Riquito: ajustes no consumer e endpoints REST
* Diogo Ruivo: integração inicial do frontend com API (fetch/axios)
* Diogo Ruivo: debug da comunicação frontend ↔ backend
* Esforço estimado:

  * Gabriel: 2h
  * Diogo: 3h

---

### 2026-04-19 (Dia 4 — Integração e testes)

* Diogo Ruivo: integração do frontend com API real
* Diogo Ruivo: testes de fluxo end-to-end (simulador → backend → frontend)
* David Cálix: configuração Swagger/OpenAPI
* David Cálix: criação de Postman Collection
* David Cálix: validação de User Stories
* Esforço estimado:

  * Diogo: 4h
  * David: 5h

---

### 2026-04-20 (Dia 5 — Dockerização e entrega final)

* Rodrigo Fonseca: finalização do docker-compose (ambiente completo)
* Rodrigo Fonseca: dockerização do frontend e simulador Python
* David Cálix: relatório final da iteração e documentação
* Gabriel Riquito: validação final da integração backend + broker
* Esforço estimado:

  * Rodrigo: 3h
  * David: 2h
  * Gabriel: 1h

---

## Resumo geral de esforço

* Gabriel Riquito: 11h
* Rodrigo Fonseca: 6h
* David Cálix: 7h
* Diogo Ruivo: 10h

---

## 8. Observações Técnicas

* O backend está funcional e persistente
* O RabbitMQ está corretamente integrado
* O simulador está a enviar dados continuamente
* O frontend ainda não está completamente integrado
* Existe necessidade de melhorar consistência de mensagens e validação de sensores

---

## 9. Problemas Identificados

* Frontend ainda não consome API REST corretamente
* Pequenas inconsistências no formato de mensagens (em evolução)
* Necessidade de melhor sincronização entre sensorId e base de dados
* Logging excessivo no backend durante testes

---

## 10. Próximos Passos (Iteração 4)

Na próxima iteração será necessário evoluir o sistema para uma versão mais completa e utilizável:

### Frontend

* Desenvolver totalmente os protótipos com framework React
* Consumir corretamente os endpoints REST do backend
* Corrigir problemas de integração existentes

### Backend / Sistema

* Melhorar validação de dados recebidos
* Refinar integração com sensores e alerts
* Melhorar estrutura de APIs REST

### Infraestrutura

* Incrementar deploy em ambiente containerizado
* Garantir estabilidade total via Docker Compose

### Simulador Python

* Melhorar realismo dos dados gerados
* Ajustar consistência de timestamps e sensores
* Possível expansão de tipos de sensores

### Documentação

* Atualização completa do relatório técnico
* Swagger/OpenAPI atualizado
* Postman collections finais

---

## 11. Conclusão

A Iteração 3 permitiu consolidar a base do sistema SmartHome, garantindo:

* Comunicação entre simulador e backend
* Persistência de dados em base relacional
* Estrutura inicial de API REST
* Base de arquitetura distribuída com RabbitMQ

