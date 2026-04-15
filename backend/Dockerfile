# ---------- BUILD STAGE ----------
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar tudo
COPY . .

# Build do projeto (sem testes para ser mais rápido)
RUN mvn clean package -DskipTests


# ---------- RUN STAGE ----------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiar o .jar gerado
COPY --from=builder /app/target/*.jar app.jar

# Porta da app
EXPOSE 8080

# Executar aplicação
CMD ["java", "-jar", "app.jar"]
