# Iteração 1 – Apresentação do Projeto

## 1. Introdução

O **Smart Home Dashboard** é uma plataforma web destinada à monitorização e automação de ambientes domésticos inteligentes. O sistema processa fluxos de dados provenientes de sensores de temperatura, humidade, luz, movimento e consumo energético, permitindo melhorar o conforto, a segurança e a eficiência energética da habitação.

### Conceito do Produto

O sistema permitirá:

* Monitorização em tempo real das condições ambientais;
* Controlo remoto de dispositivos (aquecimento e iluminação);
* Automação baseada em regras;
* Geração de alertas;
* Consulta de dados históricos;
* Exportação de relatórios de consumo energético.

O sistema é composto por um **simulador de sensores**, **backend com API REST**, **base de dados persistente** e **dashboard web**.

---

## 2. Equipa

A equipa definiu e atribuiu os papéis principais: Team Manager, Product Owner, Architect, DevOps Master e Developers.

| Nome            | NMec   | Função Principal |
| --------------- | ------ | ---------------- |
| Diogo Ruivo     | 126498 | Team Manager     |
| David Cálix     | 125043 | Product Owner    |
| Gabriel Riquito | 126427 | Architect        |
| Rodrigo Fonseca | 124726 | DevOps Master    |

---

## 3. Decisões da Iteração

1. **Escolha do tema**
   O projeto será desenvolvido como **Smart Home Dashboard**, focado na monitorização e automação doméstica de temperatura, movimento, luz e consumo energético.

2. **Atribuição de papéis**
   A equipa definiu os papéis principais. Detalhes das responsabilidades individuais encontram-se documentados noutro local.

3. **Fluxo de trabalho do repositório**
   Foi adotado **feature-branching**, sem pushes diretos na branch principal (`main`). Todas as alterações passam primeiro pelo branch `develop`.

4. **Gestão do backlog e Scrum Board**
   O **GitHub Projects** será utilizado para planear sprints, priorizar tarefas e rastrear histórias de utilizador.

5. **Implementação da arquitetura**
   Arquitetura em camadas distribuídas:

   * **Simulator**: Geração de dados de sensores virtuais, envio para backend via REST POST.
   * **Backend/API REST**: Processamento de dados, geração de alertas, persistência na base de dados e endpoints REST para frontend.
   * **Base de Dados**: Armazenamento persistente de sensores, eventos e alertas.
   * **Frontend**: Dashboard web para visualização em tempo real, gráficos históricos, alertas e controlo remoto.

---

## 4. Project Specification Report – Smart Home Dashboard


### A. Personas

#### 1. Programador em Teletrabalho

**Objetivos:**

* Garantir conforto térmico no escritório;
* Controlar dispositivos remotamente;
* Manter qualidade do ar adequada;
* Reduzir fadiga ocular.

#### 2. Mãe

**Objetivos:**

* Monitorizar conforto térmico do quarto;
* Receber alertas de movimento durante o sono;
* Garantir que os sensores estão operacionais;
* Consultar histórico de eventos.

#### 3. Reformado

**Objetivos:**

* Monitorizar consumo energético;
* Evitar desperdícios;
* Receber alertas de consumo excessivo;
* Automatizar iluminação noturna;
* Exportar relatórios de gastos.

---

### B. Cenários Suportados (User Stories)

| ID   | User Story                                                                                                                                                                               |
| ---- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| US7  | Como mãe, quero ver no dashboard se todos os sensores estão online e a transmitir dados para confiar na fiabilidade do sistema.                                                          |
| US1  | Como programador em teletrabalho, quero visualizar a temperatura e humidade do escritório em tempo real no portal web para garantir um ambiente de trabalho produtivo.                   |
| US9  | Como reformado, quero visualizar o consumo de energia atual de cada divisão da casa para identificar aparelhos que estejam a gastar demasiado.                                           |
| US5  | Como mãe, quero receber um alerta imediato no portal se for detetado movimento no quarto do bebé durante o seu período de sono para garantir a sua segurança.                            |
| US2  | Como programador em teletrabalho, quero poder ligar ou desligar o aquecimento e as luzes através do dashboard para não interromper as minhas reuniões.                                   |
| US10 | Como reformado, quero que as luzes do corredor se liguem automaticamente se for detetado movimento à noite para evitar quedas no escuro.                                                 |
| US6  | Como mãe, quero consultar gráficos de temperatura das últimas 24 horas para verificar se o quarto se manteve confortável durante a noite.                                                |
| US8  | Como mãe, quero aceder a uma lista de eventos com timestamps de quando as luzes foram ligadas ou o movimento detetado para auditar a rotina do quarto.                                   |
| US11 | Como reformado, quero que o dashboard mostre um alerta se o consumo de energia ultrapassar um limite diário definido por mim para não ter surpresas na conta da luz.                     |
| US3  | Como programador em teletrabalho, quero receber uma notificação visual se a humidade sair dos limites ideais para saber quando devo ventilar o escritório.                               |
| US4  | Como programador em teletrabalho, quero que a intensidade das luzes inteligentes se ajuste automaticamente com base no sensor de luz natural para reduzir a fadiga ocular durante o dia. |
| US12 | Como reformado, quero exportar um relatório semanal simples com os gastos energéticos para poder partilhar e discutir a poupança com a minha família.                                    |

---

### C. Arquitetura do Sistema

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
| - Detecção de alertas  |       | - Logs de controlo     |
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

## 5. Tarefas Concluídas nesta Iteração

| Tarefa                                                            | Responsável     | Estado    |
| ----------------------------------------------------------------- | --------------- | --------- |
| Escrita e priorização das 12 User Stories                         | David Cálix     | Concluído |
| Setup do Repositório, Kanban Board e realização da Ata de Reunião | Diogo Ruivo     | Concluído |
| Definição da arquitetura base (Backend/Frontend)                  | Gabriel Riquito | Concluído |
| Workflow de Git                                                   | Rodrigo Fonseca | Concluído |

---

## 6. Documentos de Apoio e Planeamento da Próxima Iteração

Nesta Iteração 1 foram apresentados:

* **Ata de Reunião**: documentação da primeira reunião com decisões, atribuição de papéis e definição do conceito do projeto.
* **Iteração do Backlog Atual**: todas as 12 User Stories definidas e priorizadas (US1–US12).
* **Lista de Problemas**: poucas alterações e problemas surgidos, dada a fase inicial do projeto.

### Planeamento para a Iteração 2

| Iteração | Foco                                                         | Resultados Esperados                                                                                                                                                                                                                                                                                                |
| -------- | ------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| I2       | 0.2.* Definir a arquitetura do produto e pré-visualização UX | Draft do Project Specification Report incluindo a parte do Architecture Notebook.<br>Protótipos iniciais para as histórias de utilizador principais, consistindo em versões **estáticas** das páginas web já implementadas com as tecnologias alvo, ou **mockups** caso não seja possível implementar. |

> Nota: As tarefas da Iteração 2 incluem começar a formalizar a arquitetura do projeto, criar protótipos das principais funcionalidades (estáticos) e detalhar a especificação do projeto.
