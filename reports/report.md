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

Utilizador que passa grande parte do tempo a trabalhar em casa.

**Objetivos:**

* Garantir conforto térmico no escritório;
* Controlar dispositivos remotamente sem interromper reuniões;
* Manter qualidade do ar adequada;
* Reduzir fadiga ocular com iluminação adaptativa.

---

### 2. Mãe

Utilizadora responsável pelo bem-estar e segurança do bebé.

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

## A.3. Cenários Suportados (User Stories)

| ID   | User Story |
|------|------------|
| US7  | Como admin, quero consultar um dashboard de estado técnico que indique se todos os sensores virtuais estão a publicar dados com sucesso na Message Queue, para garantir que o pipeline de dados está operacional após o setup. |
| US1  | Como admin, quero monitorizar a latência e os erros das rotas da API REST através de logs centralizados, para detetar falhas de comunicação entre o backend e o frontend antes que o utilizador note problemas. |
| US9  | Como reformado, quero visualizar o consumo de energia atual de cada divisão da casa para identificar aparelhos que estejam a gastar demasiado. |
| US5  | Como mãe, quero receber um alerta imediato no portal se for detetado movimento no quarto do bebé durante o seu período de sono para garantir a sua segurança. |
| US2  | Como programador em teletrabalho, quero poder ligar ou desligar o aquecimento e as luzes através do dashboard para não interromper as minhas reuniões. |
| US10 | Como reformado, quero que as luzes do corredor se liguem automaticamente se for detetado movimento à noite para evitar quedas no escuro. |
| US6  | Como mãe, quero consultar gráficos de temperatura das últimas 24 horas para verificar se o quarto se manteve confortável durante a noite. |
| US8  | Como mãe, quero aceder a uma lista de eventos com timestamps de quando as luzes foram ligadas ou o movimento detetado para auditar a rotina do quarto. |
| US11 | Como reformado, quero que o dashboard mostre um alerta se o consumo de energia ultrapassar um limite diário definido por mim para não ter surpresas na conta da luz. |
| US3  | Como programador em teletrabalho, quero receber uma notificação visual se a humidade sair dos limites ideais para saber quando devo ventilar o escritório e quando devo regular a temperatura, para assim manter um trabalho produtivo. |
| US4  | Como programador em teletrabalho, quero que a intensidade das luzes inteligentes se ajuste automaticamente com base no sensor de luz natural para reduzir a fadiga ocular durante o dia. |
| US12 | Como reformado, quero exportar um relatório semanal simples com os gastos energéticos para poder partilhar e discutir a poupança com a minha família. |

---

# B. Caderno de Arquitetura (Architecture Notebook)

## B.1. Requisitos de Qualidade Principais

### Desempenho

O sistema deverá processar fluxos de dados em tempo real provenientes dos sensores simulados, garantindo baixa latência na atualização do dashboard.

### Escalabilidade

A arquitetura será modular e baseada em containers (Docker), permitindo escalar individualmente o simulador, backend ou frontend.

### Disponibilidade

A API REST e o backend deverão estar disponíveis continuamente para permitir consulta e controlo remoto.

### Segurança

O acesso ao sistema deverá incluir autenticação básica e validação de permissões por tipo de utilizador.

### Manutenibilidade

O sistema será organizado em módulos independentes (simulador, backend, frontend), com código documentado e estruturado.

---

## B.2. Visão Arquitetural (Estática)

O sistema segue uma arquitetura em camadas distribuídas:

1. **Camada de Simulação (Simulator)**

   * Geração de dados de sensores virtuais (temperatura, humidade, luz, movimento, consumo energético);
   * Envio dos dados para o backend via API REST.

2. **Camada de Backend + API REST (Backend)**

   * Receção de dados do simulador via API REST;
   * Processamento de regras de negócio;
   * Geração de alertas e eventos;
   * Exposição de endpoints REST para o frontend e possíveis apps móveis;
   * Envio de comandos para atuadores virtuais (luzes, aquecimento/ar condicionado).

3. **Camada de Dados (Base de Dados)**

   * Persistência de dados em base de dados relacional ou timeseries;
   * Armazenamento de utilizadores, sensores, eventos e alertas.

4. **Camada de Apresentação (Frontend)**

   * Dashboard web;
   * Visualização em tempo real dos dados;
   * Gráficos históricos;
   * Interface de controlo remoto e automação.

---

## B.3. Interações entre Módulos (Visão Dinâmica)

### Fluxo principal de dados

1. O **Simulador** gera dados periódicos de sensores.
2. Os dados são enviados para o **Backend** através da API REST POST.
3. O Backend:

   * Processa os dados;
   * Avalia regras de automação;
   * Gera alertas quando necessário;
   * Armazena os dados na base de dados.
4. O **Frontend** realiza pedidos à API REST para:

   * Obter dados atuais;
   * Consultar histórico;
   * Visualizar alertas;
   * Enviar comandos de atuação (ex.: ligar luz).

### Exemplo: Alerta de Movimento (US5)

1. Sensor de movimento virtual gera evento.
2. Backend deteta movimento durante período de sono.
3. Sistema cria registo de alerta.
4. Dashboard apresenta notificação visual imediata.

### Diagrama da Arquitetura

```
+------------------------+
|  Simulator             |
|  (Sensores Virtuais)   |
|  - Temperatura         |
|  - Humidade            |
|  - Luz                 |
|  - Movimento           |
|  - Consumo Energético  |
+-----------+------------+
            |
            | Dados dos sensores (REST POST)
            v
+------------------------+       +------------------------+
| Backend / API REST     |  -->  |      Base de Dados     |
| - Processamento        |       | - Histórico de sensores|
| - Regras de negócio    |       | - Alertas e eventos    |
| - Deteção de alertas   |       | - Logs de controlo     |
| - Geração de eventos   |       +------------------------+
+-----------+------------+
            ^
            |
            | Dados para visualização / comandos (REST GET + POST)
            |
+------------------------+
|     Frontend Web       |
|  - Dashboard           |
|  - Gráficos tempo real |
|  - Alertas ativos      |
|  - Controlo remoto     |
+------------------------+
```

---

## C. Modelo de Informação

De forma preliminar, o sistema será suportado por um modelo de dados composto pelas seguintes entidades principais.

### Utilizador

* id
* nome
* email
* password (encriptada)

### Sensor

* id
* tipo (temperatura, humidade, luz, movimento, energia)
* divisão
* estado (online/offline)

### DadosSensor

* id
* sensor_id
* valor
* timestamp

### Evento

* id
* tipo_evento (movimento, luz ligada, etc.)
* timestamp
* divisão

### Alerta

* id
* tipo
* descrição
* timestamp
* resolvido (boolean)

> Nota: Esta estrutura foi definida de forma preventiva. Alterações poderão ser realizadas conforme o desenvolvimento do projeto avance.
