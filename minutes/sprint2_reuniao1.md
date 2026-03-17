# Ata de Reunião - 2
**10/03/2026 | Maker Lab**

### Participantes
* [Diogo Ruivo] - Team Manager
* [David Cálix] - Product Owner
* [Gabriel Riquito] - Architect
* [Rodrigo Fonseca] - DevOps Master

### Agenda
- Refinação do modelo de negócio e estratégia de comercialização.
- Evolução da arquitetura: suporte para atuadores e controlo ativo.
- Revisão e conversão de User Stories em requisitos técnicos de sistema.
- Definição do modelo de operação (Instalação técnica vs. Utilizador final).
- Seleção da stack de interface (Decisão: Web vs. Mobile).
- Análise de referências de mercado (Projeto Open-source: Home Assistant).

### Decisões

1. **Estratégia de Produto e Comercialização**
   O **Rodrigo** assume a responsabilidade de refinar a proposta de valor, focando-se na forma como o dashboard será apresentado como uma solução integrada e competitiva.

2. **Evolução da Arquitetura (Atuadores)**
   Por proposta do **Riquito**, o sistema passará a suportar **atuadores**. Isto permite que o projeto evolua de uma monitorização passiva para o controlo ativo de dispositivos (ex: ajuste de temperatura e iluminação).

3. **Refatoração de User Stories para Requisitos**
   O **David** identificou que as duas primeiras User Stories originais possuem natureza de base estrutural. Foi decidido convertê-las em **Requisitos Funcionais/Sistema** para garantir maior rigor técnico no backlog.

4. **Modelo de Implementação e Perfis de Acesso**
   Ficou definido que a instalação e configuração inicial do sistema será realizada por um **técnico especializado**. Como consequência, será implementada uma nova Role: **Admin/Técnico**, com permissões elevadas de gestão de hardware.

5. **Definição da Interface (Frontend)**
   Para garantir o cumprimento dos prazos dos Sprints e a estabilidade do ambiente de produção, o foco será exclusivamente **Web**, descartando-se o desenvolvimento de uma aplicação mobile nativa para o MVP.

6. **Benchmarking e Referências**
   A equipa passa a utilizar o projeto **Home Assistant** como referência para o design da pipeline de dados e modelos de integração de dispositivos.

### Tarefas
| Tarefa | Responsável | Estado |
|--------|------------|--------|
| Refinação da estratégia de venda e proposta de valor | Rodrigo Fonseca | Em curso |
| Integração da lógica de atuadores na arquitetura | Gabriel Riquito | Em curso |
| Conversão de US em requisitos e atualização do Backlog | David Cálix | Em curso |
| Planeamento do perfil Admin/Técnico e RBAC na API | Gabriel Riquito | Planeado |
| Redação da ata de reunião 2 | Diogo Ruivo | Concluído |
