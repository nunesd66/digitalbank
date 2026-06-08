# :bank: Digital Bank

## :new: Intro

### API REST para simulação de transferências financeiras entre contas.

#### A aplicação foi desenvolvida com foco em consistência transacional, resiliência e concorrência, utilizando Spring Boot, Flyway e H2.
#### O sistema permite:
- Consulta de contas
- Transferência de valores entre contas
- Consulta de transferências realizadas
- Notificação assíncrona após transferência

--- 

## :dna: Tecnologias

- Java 17
- Maven 3.9+
- Docker (opcional)
- Spring Boot
- Spring Data JPA
- H2
- Flyway
- Swagger
- JUnit 5
- Mockito
- Docker

--- 

## :triangular_ruler: Arquitetura

- **Domain** — modelos de negócio (`Conta`, `Transferencia`) e regras puras, sem dependência de frameworks
- **Application** — use cases orquestram o fluxo: `SaveTransferenciaUseCase`, `FindAllContasUseCase`, `FindAllTransferenciaUseCase`
- **Infrastructure** — implementações concretas: JPA, Flyway, listener de eventos, serviço de notificação
- **Web** — controllers REST, DTOs, documentação Swagger e tratamento de exceções

--- 

## :level_slider: Decisões técnicas

- Clean Architecture simplificada
- Flyway para versionamento do banco
- Eventos de domínio para desacoplamento
- Retry para falhas transitórias de notificação
- Lock pessimista para concorrência
- Transações com `@Transactional`

---

## :twisted_rightwards_arrows: Controle de Concorrência

Para evitar inconsistências durante transferências simultâneas, foi utilizado lock pessimista através do Spring Data JPA.
Essa abordagem garante que duas transações concorrentes não debitam o mesmo saldo simultaneamente.

---

## :warning: Notificações

Após uma transferência concluída com sucesso, um evento de domínio é publicado.
O processamento da notificação ocorre de forma desacoplada através de eventos do Spring.
Em caso de falha transitória, o sistema realiza tentativas automáticas de reenvio utilizando Spring Retry.

---

## :tokyo_tower: Endpoints

### Contas

### Listar contas

`GET /conta`

Retorna todas as contas cadastradas.

### Listar transferência

`GET /transferencia`

Retorna todas as transferências cadastradas.

### Criar transferência

`POST /transferencia`

Body:

```json
{
  "idRemetente": 1,
  "idDestinatario": 2,
  "valor": 100.00
}
```

---

## Banco de Dados

O banco utilizado é o H2 em memória.

A estrutura é criada através de migrations Flyway durante a inicialização da aplicação.

Também são carregados dados iniciais para facilitar testes e validação.

---

## :hammer_and_wrench: Como executar

### Maven

`$ mvn clean package`

`$ mvn spring-boot:run`

### Docker

`$ docker build -t digitalbank .`

`$ docker run -p 8080:8080 digitalbank`

## Swagger

http://localhost:8080/swagger-ui/index.html

## Banco

- H2 em memória

## Testes

`$ mvn test`