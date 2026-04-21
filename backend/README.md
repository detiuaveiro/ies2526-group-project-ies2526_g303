#  SmartHome Backend

Backend do sistema SmartHome desenvolvido em **Spring Boot**, com integração a **PostgreSQL (Docker)** e **RabbitMQ**, e testes configurados com **H2 in-memory database**.

---

##  Como correr o projeto

### 1. Subir todo o sistema (recomendado)

Na raiz do projeto:

```bash
docker compose up --build
````

Isto irá iniciar automaticamente:

*  PostgreSQL (base de dados)
*  RabbitMQ (message broker)
*  Backend Spring Boot

---

### 2. Aceder à aplicação

Após o arranque:

* Backend API: [http://localhost:8080](http://localhost:8080)
* PostgreSQL: `db:5432`
* RabbitMQ Management (se ativo): [http://localhost:15672](http://localhost:15672)

---

##  Build e testes com Maven

Para compilar e testar localmente:

```bash
cd backend
mvn clean package
```

Este comando:

* Compila o código
* Executa testes unitários e de integração
* Gera o `.jar` final

 Output:

```
backend/target/backend-0.0.1-SNAPSHOT.jar
```

---

##  Base de dados de testes (H2)

Os testes usam uma base de dados **H2 em memória**, o que permite:

* Execução rápida dos testes
* Independência do Docker
* Isolamento do ambiente de produção

Configuração localizada em:

```
src/test/resources/application.properties
```

---

##  Arquitetura do sistema

| Componente               | Tecnologia          |
| ------------------------ | ------------------- |
| Backend                  | Spring Boot         |
| Base de dados (produção) | PostgreSQL (Docker) |
| Base de dados (testes)   | H2 in-memory        |
| Message Broker           | RabbitMQ            |
| Build                    | Maven               |
| Deploy                   | Docker Compose      |

---

##  Docker

O backend é automaticamente containerizado.

### Build manual do backend:

```bash
cd backend
docker build -t smarthome-backend .
```

### Dockerfile (resumo)

```dockerfile
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

---

##  Notas importantes

* O backend liga automaticamente à base de dados PostgreSQL no Docker
* Os testes usam H2 e não dependem do Docker
* Maven e Docker são independentes:

  * Maven → build e testes
  * Docker → execução do sistema completo

---

##  Estado atual do projeto

✔ Backend Spring Boot funcional
✔ PostgreSQL em Docker operacional
✔ RabbitMQ ativo
✔ Testes com H2 configurados
✔ Docker Compose totalmente funcional

---

##  Resumo

* Usa `docker compose up` para correr tudo
* Usa `mvn clean package` para build/testes
* Usa H2 para testes isolados e rápidos
