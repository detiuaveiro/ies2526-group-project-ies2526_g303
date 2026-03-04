# Smart Home Dashboard

## Introdução do Projeto

O **Smart Home Dashboard** é uma plataforma web destinada à monitorização e automação de ambientes domésticos inteligentes. O sistema processa fluxos de dados provenientes de sensores de temperatura, humidade, luz, movimento e consumo energético, permitindo melhorar o conforto, a segurança e a eficiência energética da habitação.

A solução permitirá:

* Monitorização em tempo real das condições ambientais;
* Controlo remoto de dispositivos (aquecimento e iluminação);
* Automação baseada em regras (ex.: iluminação automática);
* Geração de alertas em situações anómalas;
* Consulta de dados históricos;
* Exportação de relatórios de consumo energético.

O sistema é composto por um simulador de sensores, um backend que expõe uma API REST, uma base de dados relacional para persistência de dados e um dashboard web.

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

### 1. Camada de Simulação (Simulator)

* Geração de dados de sensores virtuais (temperatura, humidade, luz, movimento e consumo energético);
* Envio dos dados para o backend através de pedidos REST (POST).

### 2. Camada de Backend + API REST (Backend)

* Receção de dados do simulador;
* Processamento de regras de negócio;
* Geração de alertas e eventos;
* Exposição de endpoints REST para o frontend;
* Envio de comandos para atuadores virtuais (luzes e aquecimento).

### 3. Camada de Dados (Base de Dados)

* Persistência de dados numa base de dados relacional;
* Armazenamento de utilizadores, sensores, medições, eventos e alertas.

### 4. Camada de Apresentação (Frontend)

* Dashboard web;
* Visualização em tempo real dos dados;
* Gráficos históricos;
* Interface de controlo remoto e automação.

---

## Diagrama da Arquitetura

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

## Bookmarks

* [**Gestão de Projeto (Backlog)**](https://github.com/orgs/detiuaveiro/projects/237)
* [**Atas de Reunião**](./minutes/)
* [**Especificações do Projeto**](./reports/)
