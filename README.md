# Smart Home Dashboard

## Introdução do Projeto

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

## Equipa

| Nome            | NMec   | Função Principal |
| --------------- | ------ | ---------------- |
| Diogo Ruivo     | 126498 | Team Manager     |
| David Cálix     | 125043 | Product Owner    |
| Gabriel Riquito | 126427 | Architect        |
| Rodrigo Fonseca | 124726 | DevOps Master    |

---

## Arquitetura do Projeto

O sistema segue uma arquitetura distribuída em camadas:

### 1. Camada de Simulação (IoT Simulator)

* Sensores virtuais: gera dados de Temperatura, Humidade, Luminosidade, Movimento e Consumo.
* Atuadores virtuais: recebe comandos para alterar estado de dispositivos (Luzes, Aquecimento).
* Comunicação: envia telemetria para o **Message Broker** e recebe comandos via **API REST** ou **WebSockets**.

### 2. Message Broker (Event Bus)

* Núcleo de integração assíncrona do sistema (ex: RabbitMQ/MQTT).
* Desacopla Simulador e Backend, permitindo envio de dados sem dependência direta.
* Facilita distribuição de eventos e monitorização do pipeline.

### 3. Camada de Backend + API REST

* Consome dados do **Message Broker** e valida integridade.
* Processa regras de negócio, automações e gera alertas.
* Expõe endpoints **REST** e envia notificações em tempo real via **WebSockets**.
* Encaminha comandos para os atuadores virtuais.

### 4. Camada de Persistência (Base de Dados)

* Armazena histórico de medições, alertas, usuários e logs de atuação.
* Garante integridade e auditabilidade da informação.

### 5. Camada de Apresentação (Frontend)

* Dashboard web **SPA** (React/TypeScript).
* Visualiza dados em tempo real, gráficos históricos e alertas.
* Permite controlo remoto de dispositivos e configuração de limites.
* Consola de administrador para monitorização técnica.

---

## Diagrama da Arquitetura

![Arquitetura](/images/sprint2/sprint2_arquitetura1.png)

---

## Bookmarks

* [**Gestão de Projeto (Backlog)**](https://github.com/orgs/detiuaveiro/projects/237)
* [**Atas de Reunião**](./minutes/)
* [**Especificações do Projeto**](./reports/)
