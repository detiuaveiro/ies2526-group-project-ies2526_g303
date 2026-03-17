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
