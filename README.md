# Smart Home Dashboard

## Project Abstract

O **Smart Home Dashboard** é uma plataforma web destinada à monitorização e automação de ambientes domésticos inteligentes. O sistema processa fluxos de dados provenientes de sensores de temperatura, humidade, luz, movimento e consumo energético, permitindo melhorar o conforto, a segurança e a eficiência energética da habitação.

A solução permitirá:

* Monitorização em tempo real das condições ambientais;
* Controlo remoto de dispositivos (aquecimento e iluminação);
* Automação baseada em regras (ex.: iluminação automática);
* Geração de alertas em situações anómalas;
* Consulta de dados históricos;
* Exportação de relatórios de consumo energético.

O sistema será composto por um simulador de sensores, um backend com API REST, uma base de dados persistente e um dashboard web.

## Project Team
| Nome            | NMec   | Função Principal |
| :---            | :---   | :---             |
| Diogo Ruivo     | 126498 | Team Manager     |
| David Cálix     | 125043 | Product Owner    |
| Gabriel Riquito | 126427 | Architect        |
| Rodrigo Fonseca | 124726 | DevOps Master    |

## Architecture diagram

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
| Backend / API REST     |  -->  |      Base de Dados      |
| - Processamento        |       | - Histórico de sensores |
| - Regras de negócio    |       | - Alertas e eventos     |
| - Detecção de alertas  |       | - Logs de controlo      |
| - Geração de eventos   |       +------------------------+
+-----------+------------+
            ^
            |
            | Dados formatados para visualização / comandos de controlo (REST POST + GET)
            |
+------------------------+
|     Frontend Web       |
|  - Dashboard           |
|  - Gráficos tempo real |
|  - Alertas ativos      |
|  - Controlo remoto     |
+------------------------+
```

## Bookmarks
* [**Gestão de Projeto (Backlog)**](https://github.com/orgs/detiuaveiro/projects/237)
* [**Atas de Reunião**](./minutes/)
* [**Especificacões do projecto**](./reports/)
