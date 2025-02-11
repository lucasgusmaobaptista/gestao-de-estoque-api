# Gestão de Estoque API

## Descrição
Esta é uma API REST para gerenciamento de estoque, desenvolvida em Java com Spring Boot e MySQL. A API permite o controle de produtos, categorias, fornecedores e movimentações de estoque, garantindo segurança com autenticação JWT.

## Tecnologias Utilizadas
- Java 21
- Spring Boot
- Spring Security (JWT)
- MySQL
- Spring Data JPA
- Hibernate
- Maven
- Swagger (OpenAPI)

## Ferramentas Utilizadas
- IntelliJ IDEA (IDE)
- Postman (Testes de Endpoints)

## Funcionalidades
- Cadastro, edição e exclusão de produtos
- Gerenciamento de categorias e fornecedores
- Controle de entradas e saídas de estoque
- Autenticação e autorização com JWT
- Documentação da API com Swagger

## Configuração do Projeto
### Pré-requisitos
- JDK 17 ou superior
- MySQL instalado e configurado
- Maven

### Configuração do Banco de Dados
Crie um banco de dados no MySQL:
```sql
CREATE DATABASE gestaoestoque_db;
```
Altere as configurações do banco no `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestaoestoque_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Instalação e Execução
1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/estoque-api.git
   cd estoque-api
   ```
2. Compile e execute o projeto:
   ```sh
   mvn spring-boot:run
   ```

## Autenticação JWT
Primeiro, é necessário criar um usuário para autenticação. Isso pode ser feito através do endpoint de cadastro:
### Requisição:
```http
POST /auth/register
```
Corpo da Requisição:
```json
{
  "username": "username",
  "password": "password",
  "role": "ADMIN"
}
```

Após isso, para acessar as rotas protegidas, obtenha um token JWT através do login
### Requisição:
```http
POST /auth/login
```
Corpo da requisição:
```json
{
  "username": "admin",
  "password": "admin"
}
```
A resposta conterá o token JWT:
```json
{
  "token": "seu_token_jwt"
}
```
Use esse token no header `Authorization` para acessar as rotas protegidas:
```http
Authorization: Bearer seu_token_jwt
```

## Principais Endpoints da Api

### Produtos
#### `GET /produtos`
- **Descrição:** Retorna a lista de produtos cadastrados.
- **Headers:**
  ```http
  Authorization: Bearer seu_token_jwt
  ```
- **Response:**
  ```json
  [
    {
      "id": 1,
      "nome": "Produto A",
      "descricao": "Descrição do Produto A",
      "preco" : 50.0,
      "categoriaId": "id",
      "fornecedorId": "id",
      "quantidade": 10
    }
  ]
  ```

#### `POST /produtos`
- **Descrição:** Cadastra um novo produto.
- **Headers:**
  ```http
  Authorization: Bearer seu_token_jwt
  ```
- **Request Body:**
  ```json
  {
    "nome": "Produto B",
    "descricao": "Descrição do Produto B",
    "categoriaId": "id",
    "fornecedorId": "id",
    "preco": 100.0,
    "quantidade": 5
  }
  ```
- **Response:**
  ```json
  {
    "id": 2,
    "nome": "Produto B",
    "descricao": "Descrição do Produto B",
    "categoriaId": "id",
    "fornecedorId": "id",
    "preco": 100.0,
    "quantidade": 5
  }
  ```

### Categorias
#### `GET /categorias`
- **Descrição:** Retorna a lista de categorias cadastradas.
- **Headers:**
  ```http
  Authorization: Bearer seu_token_jwt
  ```
- **Response:**
  ```json
  [
    {
      "id": 1,
      "nome": "Eletrônicos"
    }
  ]
  ```

### Fornecedores
#### `GET /fornecedores`
- **Descrição:** Retorna a lista de fornecedores cadastrados.
- **Headers:**
  ```http
  Authorization: Bearer seu_token_jwt
  ```
- **Response:**
  ```json
  [
    {
      "id": 1,
      "nome": "Fornecedor A",
      "contato": "fornecedor@example.com"
    }
  ]
  ```

## Documentação da API
A documentação da API também pode ser acessada via Swagger após a execução do projeto:
```
http://localhost:8080/swagger-ui.html
```


.
