# 📚 App Livraria – Autenticação com API Key

Este projeto simula um pequeno sistema de gerenciamento de livraria desenvolvido em **Java**, com foco em organização de código, separação de responsabilidades e implementação de um mecanismo simples de **autenticação utilizando API Key**.

A aplicação permite cadastrar livros, controlar estoque e simular vendas para clientes. Como parte da atividade de segurança em APIs, foi adicionada uma camada de verificação que valida uma chave de acesso enviada nas requisições.

O objetivo principal do projeto é demonstrar conceitos de:

* Organização de projetos Java
* Estrutura em camadas (Model, Repository, Service)
* Implementação de autenticação simples em APIs
* Uso de controle de versão com Git e GitHub
* Trabalho em equipe utilizando issues e branches

---

# 👥 Equipe do Projeto

| Nome                  | Função            | Responsabilidades                                                                  |
| --------------------- | ----------------- | ---------------------------------------------------------------------------------- |
| **Tarik Kassem**      | Tech Lead         | Organização do projeto, definição da estratégia de autenticação, revisão de código |
| **Matheus Schneider** | Backend Developer | Implementação da lógica da API Key, integração com o sistema e ajustes de backend  |

---

# 🎯 Objetivo da Atividade

O objetivo desta atividade foi implementar um método de autenticação em uma aplicação Java simulando um cenário real de desenvolvimento de APIs.

Entre as opções propostas estavam:

* HTTP Digest
* API Key
* HMAC
* JWT com Timestamp

Após discussão entre os integrantes da equipe, optamos pela **API Key**, considerando:

**Complexidade:** baixa
**Facilidade de implementação:** alta
**Facilidade de entendimento:** adequada para o grupo

Essa escolha permitiu focar na estrutura do projeto e no entendimento do fluxo de autenticação.

---

# 🔐 Autenticação com API Key

A autenticação foi implementada utilizando um **filtro HTTP** que intercepta as requisições antes de serem processadas pela aplicação.

O filtro verifica se o cliente enviou um header específico contendo a chave de acesso.

### Header esperado

```
X-API-KEY: livraria123
```

### Funcionamento

1. O cliente envia uma requisição para a API.
2. O filtro intercepta a requisição.
3. O sistema verifica se o header `X-API-KEY` está presente.
4. Se a chave for válida, a requisição continua normalmente.
5. Caso contrário, o servidor retorna um erro de autorização.

### Resposta para chave inválida

```
401 Unauthorized
```

Essa implementação simula um mecanismo básico de proteção utilizado em diversas APIs.

---

# 🛠 Tecnologias Utilizadas

O projeto foi desenvolvido utilizando as seguintes tecnologias:

* **Java**
* **VS Code**
* **Git**
* **GitHub**
* **Jakarta Servlet API**

Também foram aplicados conceitos de **Programação Orientada a Objetos (POO)** para organizar as responsabilidades do sistema.

---

# 📂 Estrutura do Projeto

O projeto foi organizado em camadas para facilitar a manutenção e compreensão do código.

```
src
│
├── model
│   ├── Autor.java
│   ├── Cliente.java
│   └── Livro.java
│
├── repository
│   └── LivroRepository.java
│
├── service
│   ├── GerenciadorEstoque.java
│   └── ServicoVenda.java
│
├── security
│   ├── ApiKeyFilter.java
│   └── ApiKeyFilterConfig.java
│
└── App.java
```

### model

Contém as entidades principais da aplicação, representando os objetos do domínio da livraria.

Exemplos:

* Livro
* Autor
* Cliente

### repository

Responsável por armazenar e gerenciar os dados da aplicação.

### service

Contém a lógica de negócio, como controle de estoque e processamento de vendas.

### security

Responsável pela implementação do mecanismo de autenticação utilizando API Key.

---

# ⚙️ Funcionamento do Sistema

A aplicação simula operações comuns de uma livraria.

Entre elas:

* Cadastro de livros
* Controle de estoque
* Registro de clientes
* Processamento de vendas

Durante a execução, o sistema exibe no terminal informações como catálogo de livros, vendas realizadas e atualização de estoque.

---

# 🚀 Como Executar o Projeto

### 1. Clonar o repositório

```
git clone https://github.com/seu-usuario/app-livraria-api-key-auth.git
```

### 2. Abrir o projeto no VS Code

Abra a pasta do projeto utilizando o Visual Studio Code ou outra IDE de sua preferência.

### 3. Executar o arquivo principal

Execute o arquivo:

```
App.java
```

O sistema irá iniciar e simular as operações da livraria no terminal.

---

# 🧪 Exemplo de Execução

Ao rodar o sistema, o terminal pode apresentar uma saída semelhante a:

```
--- Catálogo de Livros ---

1984 de George Orwell - R$49.9 (10 em estoque)
Dom Casmurro de Machado de Assis - R$39.9 (5 em estoque)

Venda realizada com sucesso
Cliente: Tatiana
Livro: 1984
Quantidade: 2
Total: R$99.8
```

---

# 📋 Organização do Desenvolvimento

Para simular um fluxo real de desenvolvimento, o trabalho foi organizado utilizando recursos do GitHub como:

* **Fork do repositório original**
* **Criação de branches de feature**
* **Commits descritivos**
* **Issues para organização das tarefas**
* **Pull Requests para revisão de código**

Essa organização ajuda a manter o histórico do projeto claro e facilita o trabalho colaborativo.

---

# 📚 Referências

Algumas fontes utilizadas como base para estudo durante o desenvolvimento:

Swagger – API Authentication
https://swagger.io/docs/specification/authentication/

MDN – HTTP Authentication
https://developer.mozilla.org/en-US/docs/Web/HTTP/Authentication

Jakarta Servlet Documentation
https://jakarta.ee/specifications/servlet/

---

# 📌 Considerações Finais

Este projeto foi desenvolvido com fins educacionais para praticar conceitos de desenvolvimento de software e segurança em APIs.

Durante a atividade foi possível reforçar conhecimentos sobre:

* Estruturação de projetos Java
* Implementação de autenticação simples
* Organização de código em camadas
* Uso de Git e GitHub para controle de versão
* Trabalho colaborativo em equipe

---

# 📄 Licença

Projeto desenvolvido apenas para fins acadêmicos.
