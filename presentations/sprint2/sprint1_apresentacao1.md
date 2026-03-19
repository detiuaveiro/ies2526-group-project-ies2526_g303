# **Smart Home Dashboard – Sprint 2**

---

## 1. Introdução ao Projeto

O **Smart Home Dashboard** é uma plataforma web centrada no utilizador, destinada a apoiar a gestão do ambiente doméstico inteligente.

O objetivo principal do projeto é permitir ao utilizador:

* Monitorizar em tempo real as condições da habitação;
* Controlar remotamente dispositivos como aquecimento e iluminação;
* Definir automações baseadas em regras;
* Receber alertas em situações anómalas;
* Consultar dados históricos e exportar relatórios de consumo energético.

O sistema é composto por: **Simulador de sensores**, **Backend com API REST**, **Base de Dados** e **Dashboard Web**.

---

## 2. Sprint 2 – O que fizemos

Durante esta sprint, realizámos várias atividades chave:

### Principais Conquistas

* **Protótipos concluídos:** Criados e refinados em **Figma**, com fluxo de navegação simples e interface funcional.
* **Arquitetura finalizada:** Definida de forma distribuída, contemplando simuladores, backend, frontend e base de dados.
* **Report.md atualizado:** Documenta arquitetura, user stories, diagramas e decisões da equipa.
* **Diagramas de User Stories:** Documentam interações entre utilizadores, frontend, backend e sensores.

### Reuniões

| Reunião | Data       | Foco                     | Conquistas                                                                                                |
| ------- | ---------- | ------------------------ | --------------------------------------------------------------------------------------------------------- |
| **1**   | 10/03/2026 | Arquitetura e requisitos | Suporte a atuadores, conversão de US em requisitos técnicos, definição de interface Web para MVP.         |
| **2**   | 17/03/2026 | Protótipos e UI          | Planeamento de protótipos na Stitch/Figma, definição de fluxo de navegação e responsabilidades da equipa. |
| **3**   | 18/03/2026 | Consolidação e report    | Protótipos concluídos, arquitetura final, diagramas de User Stories, report.md redigido.                  |

---

## 3. Arquitetura do Sistema

A arquitetura definida para o **Smart Home Dashboard** é distribuída e desacoplada, com cinco componentes principais:

* **Camada de Simulação (Simuladores Python)**: Sensores e atuadores virtuais que geram e recebem dados.
* **Camada IoT**: Sensores e atuadores conectados ao **Message Broker**.
* **Backend (API REST em Springboot)**: Lógica de negócio, processamento de eventos, gestão de comandos e base de dados.
* **Base de Dados (SQL)**: Armazena histórico de sensores, eventos e comandos.
* **Frontend (Web Dashboard em TypeScript)**: Visualização em tempo real, controlo de dispositivos, dashboards de alertas.

### Diagrama da Arquitetura

```mermaid
graph TD
    subgraph "Camada de Simulação"
        SIM["Simuladores (Python)"]
    end

    subgraph "Camada IoT"
        S[Sensores]
        A[Atuadores]
    end

    MQ[Message Broker]

    subgraph "Backend"
        BL["Lógica de Negócio (Java)"]
        API["API REST (Springboot)"]
    end

    DB[("Base de Dados (SQL)")]

    subgraph "Frontend"
        WA["Web Dashboard (TypeScript)"]
    end

    SIM <--> S
    SIM <--> A
    S --> MQ
    MQ --> BL
    BL <--> DB
    WA <--> API
    API <--> BL
    BL --> MQ
    MQ --> A
```

---

## 4. Protótipos em Figma

Os protótipos foram criados para **representar as principais funcionalidades** e fluxos de utilizador do sistema, com destaque para:

* Dashboard técnico do Admin;
* Controle remoto de aquecimento e luzes;
* Visualização de consumo energético e alertas de movimento;
* Histórico de temperatura e atividade.

**Nota:** Os diagramas detalhados e a ligação entre os protótipos e a arquitetura estão documentados em **diagrama.md**, incluindo links para cada protótipo HTML.

---

## 5. Próximo Sprint – Sprint 3 (I3)

As atividades previstas incluem:

* Definir e implementar todos os **endpoints da API REST (CRUD)**;
* Demonstrar a arquitetura **end-to-end** usando **Postman**;
* Incrementar **deployment em containers** no servidor;
* Atualizar e completar **documentação da API** no report.

---

## 6. Equipa

| Nome            | Função        |
| --------------- | ------------- |
| Diogo Ruivo     | Team Manager  |
| David Cálix     | Product Owner |
| Gabriel Riquito | Architect     |
| Rodrigo Fonseca | DevOps Master |
