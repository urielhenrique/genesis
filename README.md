# Genesis

> Church Financial and Inventory Management System

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)
![Flutter](https://img.shields.io/badge/Flutter-Frontend-02569B)
![Docker](https://img.shields.io/badge/Docker-Container-2496ED)
![License](https://img.shields.io/badge/License-MIT-green)

---

## 📖 Sobre o projeto

Genesis é um sistema de gerenciamento financeiro e de estoque voltado para igrejas.

O objetivo do projeto é servir como um software real e, ao mesmo tempo, como um projeto de referência para estudo de arquitetura de software, Domain-Driven Design (DDD), Clean Architecture e desenvolvimento Full Stack.

Todo o desenvolvimento é realizado seguindo boas práticas de engenharia de software, versionamento e documentação.

---

# Objetivos

- Controle Financeiro
- Controle de Estoque
- Prestação de Contas
- Controle de Eventos
- Gerenciamento de Produtos
- Registro de Operações
- Upload de Comprovantes
- Auditoria

---

# Stack Tecnológica

## Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Flyway
- Maven

## Banco de Dados

- PostgreSQL 17

## Frontend

- Flutter

## Infraestrutura

- Docker
- Docker Compose

## Ferramentas

- IntelliJ IDEA
- DBeaver
- Git
- GitHub

---

# Arquitetura

O projeto utiliza:

- Clean Architecture
- Domain-Driven Design (DDD)
- REST API
- Repository Pattern
- Service Layer
- Flyway Migrations

---

# Estrutura do Projeto

```text
Genesis
│
├── backend/
├── frontend/
├── docs/
│   ├── adr/
│   ├── api/
│   ├── architecture/
│   ├── database/
│   ├── discovery/
│   ├── handbook/
│   └── uml/
│
├── docker/
├── scripts/
└── .github/
```

---

# Documentação

Toda a documentação encontra-se em:

```
docs/
```

Contendo:

- Arquitetura
- Diagramas UML
- Diagramas Draw.io
- DBML
- Handbook
- Discovery
- ADRs

---

# Roadmap

## Sprint 0

- [x] Ambiente
- [x] Git
- [x] Docker
- [x] PostgreSQL
- [x] IntelliJ
- [x] Maven

## Sprint 1

- [x] Estrutura do repositório
- [x] Documentação inicial
- [ ] Backend Spring Boot
- [ ] Banco de Dados
- [ ] Flyway

## Sprint 2

- [ ] Domínio
- [ ] Operation
- [ ] Product
- [ ] Inventory

## Sprint 3

- [ ] Financeiro
- [ ] Dashboard
- [ ] Relatórios

## Sprint 4

- [ ] Flutter
- [ ] Autenticação
- [ ] Deploy

---

# Convenções

## Idioma

Código:

- 🇺🇸 Inglês

Interface:

- 🇧🇷 Português

---

## Commits

O projeto utiliza o padrão **Conventional Commits**.

Exemplos:

```text
feat:
fix:
refactor:
docs:
test:
build:
ci:
chore:
```

---

## Branches

Fluxo simplificado:

```text
main
develop
feature/*
hotfix/*
```

---

# Licença

Distribuído sob a licença MIT.

---

# Autor

**Uriel Henrique Gomes**

GitHub:

https://github.com/urielhenrique

---

# Status

🚧 Em desenvolvimento.