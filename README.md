# ThinDesk - Sistema de Login e Gerenciamento

## Descrição
Projeto acadêmico que implementa um sistema de autenticação e autorização de usuários com login, logout e controle de acesso baseado em perfis. A aplicação foi desenvolvida utilizando a stack de tecnologias Java, Spring Boot e MongoDB, com uma interface renderizada pelo Thymeleaf.

## Tecnologias Utilizadas
* **Backend:** Java 21, Spring Boot 3.x, Spring Security, Spring Data MongoDB
* **Frontend:** Thymeleaf
* **Banco de Dados:** MongoDB Atlas (Cloud)
* **Build Tool:** Apache Maven
* **Utilitários:** Lombok

---

## Estrutura do Projeto
O projeto segue uma arquitetura em camadas para garantir a separação de responsabilidades e a manutenibilidade do código:

* **/config:** Classes de configuração central, como o `SecurityConfig`, onde as regras de acesso e o fluxo de autenticação são definidos.
* **/controller:** Camada responsável por receber as requisições HTTP, processar as entradas do usuário e retornar as respostas (sejam elas páginas HTML ou dados JSON).
* **/model:** Contém as entidades que mapeiam os documentos do MongoDB (ex: `User`, `Role`).
* **/repository:** Interfaces que estendem o `MongoRepository` do Spring Data, abstraindo o acesso aos dados do banco.
* **/service:** Contém a lógica de negócio da aplicação. É a ponte entre os controllers e os repositórios (ex: `UserServiceImpl` para registrar novos usuários).
* **/dto:** (Data Transfer Objects) Objetos simples usados para transferir dados entre as camadas, como os dados de um formulário (`UserDto`).

---

## Fluxo de Autenticação e Segurança
A segurança da aplicação é gerenciada pelo Spring Security com as seguintes características:

* **Autenticação:** Utiliza um `UserDetailsService` customizado (`CustomUserDetailsService`) que busca as informações do usuário diretamente do MongoDB.
* **Armazenamento de Senhas:** As senhas dos usuários são armazenadas no banco de dados de forma segura, utilizando o algoritmo de hashing **BCrypt**. Em nenhum momento a senha original é guardada.
* **Autorização:** O acesso às rotas é controlado com base nos perfis (`Role`) do usuário. Regras de permissão estão centralizadas no `SecurityConfig`, permitindo um controle granular sobre quem pode acessar cada parte do sistema.
* **Páginas Públicas e Protegidas:** Rotas como `/login` e `/register` são públicas, enquanto o acesso a outras áreas, como o `/dashboard`, exige que o usuário esteja autenticado.

---

## Como Configurar e Executar

### Pré-requisitos
* Java (JDK) 21 ou superior
* Apache Maven

### Passos
1.  Clone este repositório:
    ```bash
    git clone [COLE A URL DO SEU REPOSITÓRIO AQUI]
    ```
2.  Configure a conexão com o banco de dados. Crie um arquivo `application.properties` em `src/main/resources/` com o seguinte conteúdo, substituindo pelos seus dados do MongoDB Atlas:
    ```properties
    spring.data.mongodb.uri=mongodb+srv://SEU_USUARIO:SUA_SENHA@SEU_CLUSTER.mongodb.net/SEU_BANCO?retryWrites=true&w=majority&authSource=SEU_BANCO
    ```
3.  Execute a aplicação pela linha de comando, na raiz do projeto:
    ```bash
    ./mvnw spring-boot:run
    ```
4.  Acesse a aplicação em `http://localhost:8080`.

---

## Status Atual do Projeto

### O que funciona:
* A estrutura completa do projeto está implementada, seguindo as melhores práticas com pacotes para `controller`, `service`, `repository`, `model` e `dto`.
* O sistema de compilação com Maven está configurado e o projeto **compila sem erros**.
* As configurações do Spring Security para proteger rotas e definir o formulário de login estão implementadas.
* O fluxo de registro de usuário, incluindo a criptografia de senhas com BCrypt, está implementado na camada de serviço.

### Problema Conhecido:
A aplicação **não está conseguindo estabelecer a conexão final com o MongoDB Atlas** devido a um erro persistente de autenticação (`bad auth : Authentication failed`).

### Tentativas de Solução:
Para resolver o problema de conexão, foram realizadas diversas e exaustivas tentativas de depuração, incluindo:
* Múltiplas redefinições de senhas e criação de diversos usuários de teste no MongoDB Atlas.
* Verificação completa das regras de Acesso de Rede (IP Access List), garantindo que o acesso global (`0.0.0.0/0`) está ativo.
* Correção e validação da string de conexão para incluir o parâmetro `authSource`.
* Verificação de erros de digitação no nome do usuário e no endereço do cluster.
* Criação de um projeto Spring Boot completamente novo a partir do `start.spring.io` para isolar a causa do problema, confirmando que a falha não reside em configurações ocultas do projeto.

Apesar de todas as verificações, a autenticação continua sendo recusada pelo servidor do Atlas.

---

## Funcionalidades Futuras
Para evoluir o projeto, os próximos passos planejados são:
* **Controle de Acesso por Perfil:** Implementar páginas de administração (`/admin/**`) acessíveis apenas por usuários com o perfil `ROLE_ADMIN`.
* **Validação de Formulários:** Adicionar validações nos formulários de registro para garantir a qualidade dos dados (ex: senha forte, e-mail válido).
* **Página de Perfil:** Criar uma área onde o usuário logado possa visualizar e, futuramente, editar suas informações.