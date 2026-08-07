# Genesis Backend - Code Style

## Objetivo

Este documento define o padrão oficial de desenvolvimento do Genesis.

Todo código novo deve seguir este padrão.

---

# Estrutura de uma classe

A ordem dos elementos deve ser sempre:

1. Cabeçalho da Classe

2. Package

3. Imports

4. Declaração da Classe

5. Atributos

6. Construtores

7. Métodos Públicos

8. Métodos Privados

9. Getters

10. Setters (quando realmente necessários)

---

# Imports

Todo import deve possuir um comentário explicando:

- o que é
- por que existe
- quando utilizar

Exemplo:

/*
* Importa uma anotação do Spring.
*
* @Service registra a classe como um Service.
  */
  import org.springframework.stereotype.Service;

---

# Atributos

Todo atributo deve possuir comentário.

Exemplo:

/*
* Repositório responsável pelo acesso aos produtos.
*
* Esta classe depende da interface,
* nunca da implementação.
  */
  private final ProductRepository repository;

---

# Construtores

Todo construtor deve explicar:

- quem chama
- por que existe
- quais dependências recebe

---

# Métodos

Todo método deve conter:

- Responsabilidade
- Fluxo
- Retorno
- Exceções

---

# Comentários internos

Explicar apenas decisões importantes.

Nunca comentar código óbvio.

Errado:

// Soma 1
x++;

Correto:

// Atualiza o estoque após validar a quantidade.
inventory.increase(quantity);

---

# Idioma

Código:

Inglês

Comentários:

Português

---

# Objetivo

Qualquer desenvolvedor deve conseguir entender o projeto
apenas lendo os comentários.
