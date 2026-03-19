# Diagramas de User Stories + Protótipos

---

# Admin / Técnico de Sistemas

## User Stories
- **US2** – Verificação de Conetividade  
- **US1** – Monitorização de Saúde do Sistema  

### Destaque: US2 – Verificação de Conetividade

Protótipo: ![Abrir protótipo](./us/us2.png)

```mermaid
graph TD
    Admin["Admin"] -->|Visualiza estado dos sensores| Frontend["Dashboard Web"]
    Frontend -->|Consulta status| Backend["Backend / API REST"]
    Backend -->|Verifica dados publicados| MQ["Message Broker"]
    Backend --> DB["Base de Dados"]
    MQ -->|Recebe dados de| SIM["Simuladores de Sensores Virtuais"]
```

**Explicação:** O admin monitoriza o estado do sistema garantindo que todos os sensores estão a publicar corretamente.

---

# Reformado

## User Stories

* **US9** – Monitorização de Consumo
* **US10** – Iluminação de Segurança
* **US11** – Notificação de Gastos
* **US12** – Exportação de Dados

### Destaque: US9 – Monitorização de Consumo

Protótipo: ![Abrir protótipo](./us/us9.png)

```mermaid
graph TD
    UserR["Reformado"] -->|Visualiza consumo| FrontendR["Dashboard Web"]
    FrontendR -->|Consulta dados| BackendR["Backend / API REST"]
    BackendR --> DBR["Base de Dados"]
    BackendR -->|Consulta sensores| SensorsR["Sensores de Energia"]
```

**Explicação:** O utilizador acompanha o consumo energético por divisão para reduzir gastos.

---

# Mãe

## User Stories

* **US7** – Deteção de Intrusão/Movimento
* **US6** – Histórico Térmico
* **US8** – Log de Atividade

### Destaque: US7 – Deteção de Intrusão/Movimento

Protótipo: ![Abrir protótipo](./us/us7.png)

```mermaid
graph TD
    Mom["Mãe"] -->|Recebe alerta| FrontendM["Dashboard Web"]
    FrontendM -->|Consulta eventos| BackendM["Backend / API REST"]
    BackendM -->|Processa dados| SensorsM["Sensor de Movimento"]
    BackendM --> DBM["Base de Dados"]
    BackendM -->|Notificação em tempo real| Mom
```

**Explicação:** O sistema gera alertas imediatos quando é detetado movimento, garantindo segurança.

---

# Programador em Teletrabalho

## User Stories

* **US5** – Atuação Remota
* **US3** – Alerta de Qualidade do Ar
* **US4** – Automação de Luminosidade

### Destaque: US5 – Atuação Remota

Protótipo: ![Abrir protótipo](./us/us5.png)

```mermaid
graph TD
    Dev["Programador"] -->|Envia comando| FrontendD["Dashboard Web"]
    FrontendD -->|Pedido REST| BackendD["Backend / API REST"]
    BackendD -->|Envia comando| MQ["Message Broker"]
    MQ --> ActuatorsD["Atuadores (Luzes / Aquecimento)"]
    BackendD --> DBD["Base de Dados"]
```

**Explicação:** O utilizador controla remotamente dispositivos sem interromper o trabalho.

---

# Arquitetura Geral do Sistema

```mermaid
graph TD
    subgraph "Camada de Simulação"
        SIM["Simuladores (Python)"]
    end

    subgraph "Camada IoT"
        S["Sensores"]
        A["Atuadores"]
    end

    MQ["Message Broker"]

    subgraph "Backend"
        BL["Lógica de Negócio (Java)"]
        API["API REST (Springboot)"]
    end

    DB[("Base de Dados (SQL)")]

    subgraph "Frontend"
        WA["Web Dashboard (TypeScript)"]
    end

    SIM --> S
    SIM --> A

    S --> MQ
    MQ --> BL
    BL <--> DB

    WA --> API
    API --> BL
    BL --> MQ
    MQ --> A
```

---

# Todos os Protótipos

![Todos os protótipos](./prototipos.png)
