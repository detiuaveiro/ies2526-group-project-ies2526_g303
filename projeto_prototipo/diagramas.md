### **1. Admin – Dashboard de estado técnico**

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

### **2. Reformado – Consumo de energia por divisão**

```mermaid
graph TD
    UserR["Reformado"] -->|Visualiza consumo| FrontendR["Dashboard Web"]
    FrontendR -->|Consulta dados| BackendR["Backend / API REST"]
    BackendR --> DBR["Base de Dados de Energia"]
    BackendR -->|Consulta sensores de energia| SensorsR["Sensores de Energia por Divisão"]
```

**Explicação:** O reformado quer ver consumo em cada divisão; os dados vêm dos sensores de energia e da base de dados.

---

### **3. Mãe – Alerta de movimento no quarto do bebé**

```mermaid
graph TD
    Mom["Mãe"] -->|Recebe alerta| FrontendM["Portal Web / App"]
    FrontendM -->|Verifica eventos| BackendM["Backend / API REST"]
    BackendM -->|Processa movimento| SensorsM["Sensor de Movimento"]
    BackendM --> DBM["Base de Dados de Eventos"]
    BackendM -->|Envia notificação| Mom
```

**Explicação:** Quando o sensor detecta movimento, o backend cria um evento, armazena na base de dados e envia alerta imediato ao portal.

---

### **4. Programador em teletrabalho – Controlar aquecimento e luzes**

```mermaid
graph TD
    Dev["Programador"] -->|Liga/desliga| FrontendD["Dashboard Web"]
    FrontendD -->|Envia comando| BackendD["Backend / API REST"]
    BackendD -->|Atua sobre| ActuatorsD["Luzes / Aquecimento"]
    BackendD --> DBD["Base de Dados de Comandos"]
```

**Explicação:** O programador envia comandos pelo dashboard; o backend processa e controla os atuadores físicos virtuais, registando a ação na base de dados.

