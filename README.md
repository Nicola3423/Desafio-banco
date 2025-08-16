# 💳 PicPay Backend Challenge – DDD + SOLID

Este projeto é uma implementação do **desafio backend do PicPay**, desenvolvido com **Java + Spring Boot**, aplicando **Domain-Driven Design (DDD)** e **princípios SOLID** para garantir alta coesão, baixo acoplamento e facilidade de manutenção.

## 🚀 Objetivo
O sistema simula a **transferência de valores entre carteiras digitais**, validando regras de negócio reais e integrando com serviços externos de autorização e notificação.

## 🏗️ Arquitetura e Padrões Utilizados
O projeto está organizado seguindo o **DDD** e dividido em camadas bem definidas:

- **Domain** – Contém as **entidades**, **regras de negócio** e **interfaces de repositórios**.
- **Application** – Implementa os **casos de uso** do sistema (services) e os **DTOs** para entrada e saída de dados.
- **Infrastructure (Infra)** – Implementa a persistência com **JPA/Hibernate**, configurações do Spring e clientes HTTP para serviços externos.
- **Presentation** – Controllers REST para exposição das funcionalidades.
- **Exception Handling** – Tratamento centralizado de exceções com respostas claras para o cliente.

Os princípios **SOLID** foram aplicados para:
- **Single Responsibility** – Cada classe tem um único propósito.
- **Open/Closed** – Fácil extensão sem modificação de código existente.
- **Liskov Substitution** – Abstrações respeitando contratos de interface.
- **Interface Segregation** – Interfaces específicas para cada caso de uso.
- **Dependency Inversion** – Camadas dependem de abstrações, não implementações concretas.

## 🔍 Funcionalidades
- Criar **carteiras digitais** (Wallets) de diferentes tipos.
- Efetuar **transferências** entre carteiras.
- Validação de:
  - **Saldo insuficiente**.
  - **Tipo de carteira não autorizado a transferir**.
  - **Duplicidade de dados**.
  - **Autorização externa** para transações.
- Integração com serviço externo para **notificação** de transações.
- Persistência em banco de dados via **JPA**.

## 🗂️ Estrutura de Pacotes
```
src/main/java/desafio/backend/picpay
│
├── application
│   ├── transfer
│   └── wallet
│
├── domain
│   ├── transfer
│   ├── wallet
│   └── walletType
│
├── infra
│   ├── client
│   ├── config
│   ├── exception
│   └── persistence
│
└── presentation
    ├── transfer
    └── wallet
```

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database** (para testes)
- **Maven**
- **Lombok**
- **RestTemplate** para consumo de APIs externas

## 📦 Como Executar
```bash
# Clonar repositório
git clone https://github.com/seu-usuario/picpay-backend.git

# Entrar no diretório
cd picpay-backend

# Executar aplicação
./mvnw spring-boot:run
```
A API estará disponível em: `http://localhost:8080`

## 📌 Exemplos de Endpoints

### Criar carteira
**POST** `/wallets`
```json
{
  "fullName": "João Silva",
  "cpfCnpj": "12345678900",
  "email": "joao@email.com",
  "password": "123456",
  "walletTypeId": 1
}
```

### Transferir valor
**POST** `/transfers`
```json
{
  "payerId": 1,
  "payeeId": 2,
  "amount": 100.50
}
```

---

Se quiser que eu gere também uma versão com badges, GIF demonstrativo e um diagrama UML simples para inserir no README, me avise!
