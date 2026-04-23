# Iteração 3 (I3) — SmartHome System

## Relatório Técnico de Implementação e Progresso

**Período:** 16 a 20 de abril de 2026
**Arquitetura:** Spring Boot + PostgreSQL + RabbitMQ + React + Python Simulator

---

# 1. Objetivo da Iteração

A Iteração 3 teve como objetivo evoluir o sistema de uma base composta por esqueletos e componentes incompletos para uma arquitetura funcional end-to-end.

O sistema deveria demonstrar um fluxo completo de dados desde a geração de sensores até à sua persistência e exposição via API REST.

### Objetivos principais:

* Implementar comunicação assíncrona com RabbitMQ
* Persistir dados em PostgreSQL via Spring Boot
* Implementar API REST funcional
* Estruturar integração com frontend React
* Garantir execução via Docker
* Validar fluxo completo através de Postman

---

# 2. Arquitetura do Sistema

O sistema segue uma arquitetura baseada em eventos (event-driven architecture).

## Fluxo de dados

```text
Simulador Python
      ↓
RabbitMQ (telemetry_queue)
      ↓
Spring Boot Consumer
      ↓
SensorData (JPA Entity)
      ↓
PostgreSQL
      ↓
REST API (Controllers)
      ↓
Frontend React (Dashboard)
```

---

# 3. Modelo de Dados (Esquema Relacional)

## SENSOR

| Campo   | Tipo   | Descrição                                   |
| ------- | ------ | ------------------------------------------- |
| id (PK) | String | Identificador único do sensor               |
| tipo    | String | Tipo de sensor (temperatura, energia, etc.) |
| divisao | String | Localização do sensor                       |
| unidade | String | Unidade de medida                           |

---

## SENSOR_DATA

| Campo          | Tipo          | Descrição                |
| -------------- | ------------- | ------------------------ |
| id (PK)        | Long          | Identificador da leitura |
| sensor_id (FK) | String        | Referência ao sensor     |
| valor          | Double        | Valor medido             |
| timestamp      | LocalDateTime | Data/hora da leitura     |

---

## ALERT

| Campo              | Tipo          | Descrição               |
| ------------------ | ------------- | ----------------------- |
| id (PK)            | Long          | Identificador do alerta |
| sensor (FK lógico) | Sensor        | Sensor associado        |
| message            | String        | Mensagem do alerta      |
| timestamp          | LocalDateTime | Data/hora               |
| isRead             | boolean       | Estado de leitura       |

---

## USER

| Campo   | Tipo   | Descrição                   |
| ------- | ------ | --------------------------- |
| id (PK) | Long   | Identificador do utilizador |
| name    | String | Nome                        |
| email   | String | Email                       |
| role    | String | Tipo de utilizador          |

---

# 4. Inicialização de Dados (Seed)

O sistema inicializa automaticamente sensores através de `DataInitializer`.

Sensores criados:

* temp-sala-01
* energia-cozinha-01
* temp-quarto-bebe

---

# 5. Backend (Spring Boot)

## Componentes principais

### 5.1 RabbitMQ Consumer

* Consome mensagens da queue `telemetry_queue`
* Converte JSON em entidade `SensorData`
* Resolve relação com entidade `Sensor`
* Persiste dados em PostgreSQL

---

### 5.2 Persistência de dados

* JPA/Hibernate utilizado para ORM
* Relação ManyToOne entre SensorData e Sensor
* Timestamp gerado no backend

---

### 5.3 API REST

Endpoints principais:

* GET /sensors

* POST /sensors

* PUT /sensors/{id}

* DELETE /sensors/{id}

* GET /sensor-data

* GET /sensor-data/{sensorId}

* GET /alerts

---

# 6. Simulador (Python)

## Funcionalidade

* Geração de dados simulados de sensores
* Envio contínuo para RabbitMQ
* Simulação de diferentes tipos de sensores

## Formato da mensagem

```json
{
  "sensor": {
    "id": "temp-sala-01"
  },
  "valor": 22.3,
  "timestamp": "2026-04-21T00:00:00"
}
```

---

# 7. Frontend (Estado Atual)

## Estado: implementação parcial

O frontend foi iniciado em React/TypeScript, mas não foi o foco principal desta iteração.

### Implementado:

* Estrutura base do projeto React
* Componentes iniciais de dashboard
* Preparação para integração com API REST
* Layout inicial de visualização

### Não implementado completamente:

* Integração total com backend
* Substituição completa de dados mockados
* Atualização dinâmica em tempo real
* Integração final com API estável

### Nota:

O frontend encontra-se numa fase inicial, pois a prioridade da I3 foi garantir a estabilidade do backend, incluindo mensageria, persistência e API REST funcional.

---

# 8. Dockerização

O sistema foi containerizado para garantir portabilidade e consistência.

## Serviços incluídos:

* backend (Spring Boot)
* frontend (React)
* simulator (Python)
* rabbitmq
* postgres

## Execução:

```bash
docker-compose up
```

---

# 9. Testes e Validação

Foram realizados testes utilizando:

* Postman (API REST)
* Simulador Python
* Logs do RabbitMQ
* PostgreSQL (verificação de persistência)
* Testes parciais no frontend

---

# 10. User Stories suportadas

* US2: Monitorização de sensores
* US6: Visualização de dados históricos
* US7: Alertas de eventos
* US9: Monitorização de consumo

---

# 11. Problemas encontrados

## 11.1 Integração frontend-backend

* API ainda em evolução
* Falta de estabilização de endpoints
* Integração incompleta no frontend

## 11.2 WebSockets

* Implementação ainda não finalizada
* Comunicação em tempo real ainda em desenvolvimento

---

# 12. Estado do Sistema

## Funcional:

* Simulador → RabbitMQ
* RabbitMQ → Backend
* Backend → Base de dados
* API REST funcional
* Docker básico funcional

## Parcial:

* Frontend React
* Integração em tempo real

---

# 13. Conclusão

A Iteração 3 permitiu consolidar a base arquitetural do sistema, transformando um conjunto de componentes isolados numa arquitetura distribuída funcional.

Foi implementada com sucesso uma pipeline completa de dados baseada em eventos, garantindo:

* Desacoplamento entre serviços
* Persistência consistente de dados
* Comunicação assíncrona via RabbitMQ
* API REST funcional e testável

No entanto, a integração completa do frontend ainda se encontra em desenvolvimento, sendo o principal ponto de melhoria para futuras iterações.
