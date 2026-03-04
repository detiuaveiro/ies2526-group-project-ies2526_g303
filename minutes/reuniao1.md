# Ata de Reunião - 1

### Participantes
* [Diogo Ruivo] - Team Manager
* [David Cálix] - Product Owner
* [Gabriel Riquito] - Architect
* [Rodrigo Fonseca] - DevOps Master

### Agenda
- Definição do conceito e enquadramento do projeto.

- Levantamento e escrita das User Stories iniciais.

- Setup técnico do ambiente de trabalho (GitHub, Projects e Branches).

- Distribuição inicial de tarefas pelos membros do grupo.

### Decisões

1. **Escolha do tema**
   O projeto será desenvolvido como **Smart Home Dashboard**, focado em monitorização e automação doméstica de temperatura, movimento, luz e consumo energético.

2. **Atribuição de papéis**
   A equipa definiu e atribuiu os papéis principais: Team Manager, Product Owner, Architect, DevOps Master e Developers.

3. **Fluxo de trabalho do repositório**
   Será adotado **feature-branching**, sem pushes diretos na branch principal (`main`). Todas as alterações passam primeiro pelo branch `develop` e depois são integradas na `main`.

4. **Gestão do backlog e Scrum Board**
   O **GitHub Projects** será usado para planear sprints, priorizar tarefas e rastrear histórias de utilizador.

5. **Implementação da arquitetura**
   O projeto seguirá uma arquitetura em camadas distribuídas:

   * **Simulator**: Geração de dados de sensores virtuais e envio para o backend via API REST POST.
   * **Backend/API REST**: Processamento dos dados, geração de alertas e eventos, persistência na base de dados e exposição de endpoints REST para o frontend.
   * **Base de Dados**: Armazenamento persistente de sensores, eventos e alertas.
   * **Frontend**: Dashboard web para visualização de dados em tempo real, gráficos históricos, alertas e controlo remoto.

### Tarefas
| Tarefa | Responsável | Estado |
|--------|------------|--------|
| Escrita e priorização das 12 User Stories | David Cálix | Concluído |
| Setup do Repositório, criação do Kanban Board e realização da ata da reunião | Diogo Ruivo | Concluído |
| Definição da arquitetura base (Backend/Frontend) | Gabriel Riquito | Concluído |
| Workflow de Git | Rodrigo Fonseca | Concluído |
