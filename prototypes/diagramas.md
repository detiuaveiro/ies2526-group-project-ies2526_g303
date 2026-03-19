# Diagramas de User Stories + Protótipos

---

## **US2 – Verificação de Conetividade (Admin – Dashboard de estado técnico)**

Protótipo: ![Abrir protótipo](./us/us2.png)

```mermaid
graph TD
    Admin["Admin"] -->|Visualiza estado dos sensores| Frontend["Dashboard Web"]
    Frontend -->|Consulta status| Backend["Backend / API REST"]
    Backend -->|Verifica dados publicados| MQ["Message Queue"]
    Backend --> DB["Base de Dados"]
    MQ -->|Recebe dados de| SIM["Simuladores de Sensores Virtuais"]
```

**Explicação:** O admin vê o dashboard que recolhe informações da Message Queue e da base de dados para mostrar se todos os sensores estão a funcionar.

---

## **US9 – Monitorização de Consumo (Reformado – Consumo de energia por divisão)**

Protótipo: ![Abrir protótipo](./us/us9.png)

```mermaid
graph TD
    UserR["Reformado"] -->|Visualiza consumo| FrontendR["Dashboard Web"]
    FrontendR -->|Consulta dados| BackendR["Backend / API REST"]
    BackendR --> DBR["Base de Dados de Energia"]
    BackendR -->|Consulta sensores de energia| SensorsR["Sensores de Energia por Divisão"]
```

**Explicação:** O reformado quer ver consumo em cada divisão; os dados vêm dos sensores de energia e da base de dados.

---

## **US7 – Deteção de Intrusão/Movimento (Mãe – Alerta no quarto do bebé)**

Protótipo: ![Abrir protótipo](./us/us7.png)

```mermaid
graph TD
    Mom["Mãe"] -->|Recebe alerta| FrontendM["Portal Web / App"]
    FrontendM -->|Verifica eventos| BackendM["Backend / API REST"]
    BackendM -->|Processa movimento| SensorsM["Sensor de Movimento"]
    BackendM --> DBM["Base de Dados de Eventos"]
    BackendM -->|Envia notificação| Mom
```

**Explicação:** Quando o sensor deteta movimento, o backend cria um evento, armazena na base de dados e envia alerta imediato ao portal.

---

## **US5 – Atuação Remota (Programador – Controlo de luzes e aquecimento)**

Protótipo: ![Abrir protótipo](./us/us5.png)

```mermaid
graph TD
    Dev["Programador"] -->|Liga/desliga| FrontendD["Dashboard Web"]
    FrontendD -->|Envia comando| BackendD["Backend / API REST"]
    BackendD -->|Atua sobre| ActuatorsD["Luzes / Aquecimento"]
    BackendD --> DBD["Base de Dados de Comandos"]
```

**Explicação:** O programador envia comandos pelo dashboard; o backend processa e controla os atuadores virtuais, registando a ação na base de dados.

---

## Arquitetura Geral do Sistema

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

## Todos os Protótipos das User Stories

![Todos os protótipos](./prototipos.png)
