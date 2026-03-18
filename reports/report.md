# Project Specification Report – Smart Home Dashboard

---

# A. Conceito do Produto

## A.1. Declaração de Visão (Vision Statement)

O **Smart Home Dashboard** é uma plataforma web centrada no utilizador, destinada a apoiar a gestão e o controlo do seu ambiente doméstico inteligente. O sistema processa fluxos de dados provenientes de sensores de temperatura, humidade, luz, movimento e consumo energético, permitindo ao utilizador melhorar o seu conforto, segurança e eficiência energética no dia a dia.

A solução permitirá ao utilizador:

* Monitorizar em tempo real as condições ambientais da sua habitação;
* Controlar remotamente dispositivos (aquecimento e iluminação);
* Definir automações baseadas em regras (ex.: iluminação automática);
* Receber alertas em situações anómalas;
* Consultar dados históricos;
* Exportar relatórios de consumo energético.

O sistema será composto por um simulador de sensores, um backend com API REST, uma base de dados persistente e um dashboard web.

---

## A.2. Personas

### 1. Programador em Teletrabalho

Utilizador que passa grande parte do tempo a trabalhar em casa e necessita de controlo ambiental para produtividade.
**Objetivos:**

* Garantir conforto térmico no escritório;
* Controlar dispositivos remotamente sem interromper reuniões;
* Manter qualidade do ar adequada;
* Reduzir fadiga ocular com iluminação adaptativa.

---

### 2. Mãe

Utilizadora focada no bem-estar, segurança e monitorização constante do ambiente dos dependentes (bebé).

**Objetivos:**

* Monitorizar o conforto térmico do quarto;
* Receber alertas de movimento durante o sono;
* Garantir que os sensores estão operacionais;
* Consultar histórico de eventos e atividades.

---

### 3. Reformado

Utilizador focado na gestão eficiente de energia e segurança doméstica.

**Objetivos:**

* Monitorizar consumo energético por divisão;
* Evitar desperdícios;
* Receber alertas de consumo excessivo;
* Automatizar iluminação noturna por segurança;
* Exportar relatórios de gastos.

---

### 4. Admin / Técnico de Sistemas

Responsável pela instalação, manutenção técnica e integridade do pipeline de dados do sistema.

**Objetivos:**

* Garantir a integridade do pipeline de dados entre o simulador de sensores e o backend;
* Monitorizar a performance da API REST, identificando latências ou erros antes que afetem o utilizador final;
* Validar a conectividade de todos os sensores virtuais através da Message Queue;
* Centralizar e analisar logs do sistema para facilitar o debugging e a manutenção preventiva;

## A.3. Cenários Suportados (User Stories)

| ID   | User Story | Explicação
|------|------------| -----------
**US1** | Monitorização de Saúde do Sistema | Como **admin**, quero monitorizar a latência e os erros das rotas da API REST através de logs centralizados, para detetar falhas de comunicação entre o backend e o frontend antes que o utilizador note problemas. |
| **US2** | Atuação Remota | Como **programador em teletrabalho**, quero poder ligar ou desligar o aquecimento e as luzes através do dashboard para não interromper as minhas reuniões. |
| **US3** | Alerta de Qualidade do Ar | Como **programador em teletrabalho**, quero receber uma notificação visual se a humidade sair dos limites ideais para saber quando devo ventilar o escritório e quando devo regular a temperatura. |
| **US4** | Automação de Luminosidade | Como **programador em teletrabalho**, quero que a intensidade das luzes inteligentes se ajuste automaticamente com base no sensor de luz natural para reduzir a fadiga ocular durante o dia. |
| **US5** | Deteção de Intrusão/Movimento | Como **mãe**, quero receber um alerta imediato no portal se for detetado movimento no quarto do bebé durante o seu período de sono para garantir a sua segurança. |
| **US6** | Histórico Térmico | Como **mãe**, quero consultar gráficos de temperatura das últimas 24 horas para verificar se o quarto se manteve confortável durante a noite. |
| **US7** | Verificação de Conetividade | Como **admin**, quero consultar um dashboard de estado técnico que indique se todos os sensores virtuais estão a publicar dados com sucesso na Message Queue, para garantir que o pipeline de dados está operacional. |
| **US8** | Log de Atividade | Como **mãe**, quero aceder a uma lista de eventos (timestamps) de quando as luzes foram ligadas ou o movimento detetado para auditar a rotina do quarto. |
| **US9** | Monitorização de Consumo | Como **reformado**, quero visualizar o consumo de energia atual de cada divisão da casa para identificar aparelhos que estejam a gastar demasiado. |
| **US10** | Iluminação de Segurança | Como **reformado**, quero que as luzes do corredor se liguem automaticamente se for detetado movimento à noite para evitar quedas no escuro. |
| **US11** | Notificação de Gastos | Como **reformado**, quero que o dashboard mostre um alerta se o consumo de energia ultrapassar um limite diário definido por mim para não ter surpresas na conta da luz. |
| **US12** | Exportação de Dados | Como **reformado**, quero exportar um relatório semanal simples com os gastos energéticos para poder partilhar e discutir a poupança com a minha família. |

---

# B. Caderno de Arquitetura (Architecture Notebook)

## B.1. Requisitos de Qualidade Principais

### Desempenho
O sistema deve garantir uma experiência fluida de monitorização em tempo real e resposta rápida aos comandos de atuação.
* **Latência End-to-End:** O tempo decorrido entre a geração de um evento no simulador e a sua visualização no dashboard não deve ultrapassar os **500ms** em condições normais de rede.
* **Processamento da API:** Os endpoints da API REST devem responder a pedidos de leitura (GET) em menos de **200ms**.
* **Eficiência de Dados:** O sistema deve ser capaz de processar fluxos simultâneos de, pelo menos, 5 sensores por divisão sem degradação da performance do backend.

### Escalabilidade
A arquitetura foi desenhada para crescer horizontalmente, permitindo a adição de novos módulos ou instâncias sem necessidade de refatoração do código base.
* **Containerização:** O uso de **Docker e Docker Compose** permite isolar os serviços (Frontend, Backend, Database e Message Queue), possibilitando o escalonamento individual de cada módulo conforme a carga de trabalho.

### Disponibilidade
O sistema deve assegurar que o utilizador consiga monitorizar e controlar a sua habitação de forma contínua.
* **Uptime Alvo:** O backend e a base de dados devem garantir uma disponibilidade de **99.5%** durante a fase de prototipagem.
* **Resiliência:** Em caso de falha do simulador de sensores, o backend deve manter-se funcional, permitindo a consulta de dados históricos e a gestão de perfis de utilizador sem interrupções de serviço.

### Segurança
A proteção dos dados e o controlo físico dos atuadores da habitação são tratados como requisitos críticos.
* **Autenticação e Autorização:** Implementação de **JWT (JSON Web Tokens)** para gestão de sessões seguras e stateless.
* **RBAC (Role-Based Access Control):** Diferenciação rigorosa de permissões. Apenas utilizadores com a role `Admin` têm acesso aos logs de saúde do sistema e dashboards de infraestrutura (US1 e US7).
* **Sanitização de Dados:** Validação estrita de todos os inputs da API para prevenir ataques de injeção e garantir a integridade da base de dados.

### Manutenibilidade
O código e a infraestrutura devem ser fáceis de evoluir, testar e corrigir.
* **Arquitetura em Camadas:** Separação clara entre lógica de negócio, persistência de dados e interface (Clean Architecture), facilitando a manutenção isolada de cada componente.
* **Documentação da API:** Uso de **Swagger/OpenAPI** para documentar todos os endpoints, eliminando ambiguidades na integração entre o frontend, backend e simulador.
* **Standardização:** Adoção de normas5203. e convenções de nomeclatura consistentes (Clean Code) em todo o repositório GitHub para facilitar o trabalho colaborativo.

---

## B.2. Visão Arquitetural (Estática)

O sistema adota uma arquitetura de serviços distribuídos e desacoplados, organizada em cinco componentes principais que comunicam via protocolos standard (REST e Pub/Sub).

### 1. Camada de Simulação (IoT Simulator)
Responsável por replicar o comportamento de uma habitação inteligente.
* **Sensores Virtuais:** Gera fluxos de dados sintéticos (Temperatura, Humidade, Luminosidade, Movimento e Consumo).
* **Atuadores Virtuais:** Recebe comandos para alterar o estado de dispositivos (Luzes e Climatização).
* **Comunicação:** Publica dados de telemetria no **Message Broker** e expõe endpoints para controlo direto via **API REST**.

### 2. Message Broker (Event Bus)
O núcleo de integração assíncrona do sistema (ex: RabbitMQ ou Mosquitto/MQTT).
* **Desacoplamento:** Permite que o simulador envie dados sem depender da disponibilidade imediata do backend.
* **Processamento:** Facilita a monitorização de saúde do pipeline (US7) e a distribuição de eventos para múltiplos subscritores.

### 3. Camada de Backend (Core API)
Motor de inteligência e orquestração do sistema, desenvolvido em [Tecnologia, ex: Node.js/Python/Java].
* **Ingestão de Dados:** Consome eventos do Broker e valida a integridade dos dados.
* **Regras de Negócio:** Processa automações (ex: ligar luzes se houver movimento) e gera alertas.
* **API Gateway:** Expõe uma interface **RESTful** documentada (Swagger) para o frontend.

### 4. Camada de Persistência (Database)
Garante a integridade e o histórico da solução.
* **Base de Dados Relacional/Timeseries:** Armazenamento de perfis de utilizador, logs de eventos de sensores e configurações de dispositivos.
* **Audit Log:** Registo de comandos enviados aos atuadores para auditoria futura.

### 5. Camada de Apresentação (Frontend)
Aplicação Web Single Page Application (SPA) desenvolvida em [Tecnologia, ex: React/TypeScript].
* **Dashboard Dinâmico:** Visualização de telemetria em tempo real e estado dos alertas.
* **Painel de Controlo:** Interface para atuação manual e configuração de limites de consumo (US11).
* **Admin View:** Consola técnica para monitorização de latência e saúde dos serviços (US1).
---

Aqui tens a versão corrigida e realista. Deixei de lado o "faz de conta" e meti o que realmente precisas para isso não rebentar ao primeiro teste: **WebSockets** para tempo real e uma estrutura de dados que faz sentido.

---

## B.3. Interações entre Módulos (Visão Dinâmica)

### Fluxo principal de dados

1.  **Simulador -> Backend:** O Simulador envia dados periódicos via **REST POST**. Para comandos (atuadores), o Simulador mantém uma ligação **WebSocket** aberta para receber ordens imediatas do Backend.
2.  **Processamento:** O Backend processa os dados, valida permissões de utilizador, avalia regras de automação e persiste a informação.
3.  **Notificações em Tempo Real:** Sempre que um alerta é gerado ou um dado muda, o Backend "empurra" a info para o Frontend via **WebSockets (ou SSE)**, garantindo feedback imediato sem necessidade de refresh.
4.  **Controlo Remoto:** O Frontend envia comandos (POST). O Backend valida e reencaminha para o Simulador através da ligação bidirecional ativa.


### Exemplo: Alerta de Movimento (US5)

1.  Sensor de movimento deteta atividade.
2.  Backend recebe o dado, cruza com o horário de sono definido para aquele `utilizador_id`.
3.  Sistema cria registo de `Alerta` associado ao `Evento`.
4.  **Push Server-to-Client:** O Dashboard recebe um evento WebSocket e faz o pop-up da notificação instantaneamente.

### Diagrama da Arquitetura (Esboço)

```
+------------------------+           +------------------------+
|  Simulator             |           |      Base de Dados     |
|  (Sensores Virtuais)   |           | - Histórico e Alertas  |
|  - Temperatura / Luz   |           | - Users e Permissões   |
|  - Movimento / Energia |           | - Logs de Atuação      |
+-----------+------------+           +-----------+------------+
            |                                    ^
            | (1) Dados (REST POST)              |
            | (2) Comandos (WebSockets)          | (3) Persistência
            v                                    |
+-----------+------------+           +-----------+------------+
| Backend / API REST     |<----------+  Business Logic / Regras|
| - Auth & Permissões    |           | - Engine de Alertas    |
| - WebSocket Manager    |           | - Processamento Dados  |
+-----------+------------+           +-----------+------------+
            ^
            |
            | (4) Real-time Updates (WebSockets Push)
            | (5) Comandos e Queries (REST)
            |
+-----------+------------+
|     Frontend Web       |
|  - Dashboard (Live)    |
|  - Gráficos & Alertas  |
|  - Gestão de Dispositivos|
+------------------------+
```

---

## C. Modelo de Informação

### Utilizador
* `id` (PK)
* `nome`
* `email` (Unique)
* `password_hash`

### Sensor
* `id` (PK)
* `utilizador_id` (FK) 
* `tipo` (Enum: temperatura, humidade, luz, movimento, energia)
* `unidade_medida` (String: "ºC", "Lux", "kWh", etc.)
* `divisao`
* `estado` (online/offline)

### DadosSensor
* `id` (PK)
* `sensor_id` (FK)
* `valor` (Float)
* `timestamp`

### Evento (Log de Atividade)
* `id` (PK)
* `sensor_id` (FK)
* `tipo_evento` (Ex: "deteção_movimento", "luz_ligada_manual")
* `timestamp`

### Alerta
* `id` (PK)
* `evento_id` (FK) 
* `nivel_criticidade` (baixa, media, alta)
* `descricao`
* `resolvido` (boolean)
* `timestamp`

---

