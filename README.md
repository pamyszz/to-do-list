# 🗂️ **To-Do List - API** 
Esta API RESTful foi desenvolvida para facilitar o gerenciamento de tarefas, proporcionando uma maneira eficiente e organizada de acompanhar suas atividades diárias.

## 🚀 **Funcionalidades**

- **CRUD de Tarefas**: Criação, leitura, atualização e exclusão de tarefas.
- **Autenticação de Usuário**: Cadastro e login para gerenciar suas tarefas de forma segura.
- **Endpoints de Gerenciamento**: Fácil adição e visualização de tarefas.

## 📦 **Dependências**

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.9-brightgreen)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-2.7.9-green)
![H2 Database](https://img.shields.io/badge/H2%20Database-1.4.200-blue)
![Lombok](https://img.shields.io/badge/Lombok-1.18.24-yellowgreen)
![Java](https://img.shields.io/badge/Java-17-red)

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Facilitador para acesso a dados e persistência.
- **H2 Database**: Banco de dados em memória para testes rápidos.
- **Spring Security**: Implementa autenticação e autorização.
- **Lombok**: Reduz a quantidade de código boilerplate.

## 📜 **Documentação dos Endpoints**

### 🗒️ **Tarefas**

- **Criar Tarefa**

  - **Método**: `POST`
  - **URL**: `https://to-do-list-ds79.onrender.com/tasks/`
  - **Descrição**: Cria uma nova tarefa.
  - **Autenticação**: Requer autenticação.
  - **Corpo da Requisição**:

    ```json
    {
        "description": "Tarefa para organizar a casa",
        "title": "Organizar a casa",
        "priority": "ALTA",
        "startAt": "2024-07-28T23:30:00",
        "endAt": "2025-07-29T23:35:00"
    }
    ```

- **Listar Tarefas**

  - **Método**: `GET`
  - **URL**: `https://to-do-list-ds79.onrender.com/tasks/`
  - **Descrição**: Obtém uma lista de todas as tarefas.
  - **Autenticação**: Requer autenticação.

- **Obter Tarefa por ID**

  - **Método**: `GET`
  - **URL**: `https://to-do-list-ds79.onrender.com/tasks/{id}`
  - **Descrição**: Obtém os detalhes de uma tarefa específica pelo ID.
  - **Exemplo de URL**: `https://to-do-list-ds79.onrender.com/tasks/c5771d27-c8fd-4e7b-ad1e-82f5f405794e`
  - **Autenticação**: Requer autenticação.

- **Atualizar Tarefa**

  - **Método**: `PUT`
  - **URL**: `https://to-do-list-ds79.onrender.com/tasks/{id}`
  - **Descrição**: Atualiza uma tarefa existente pelo ID.
  - **Autenticação**: Requer autenticação.
  - **Corpo da Requisição**:

    ```json
    {
        "description": "Nova descrição da tarefa",
        "title": "Título atualizado",
        "priority": "MÉDIA",
        "startAt": "2024-08-01T10:00:00",
        "endAt": "2025-08-01T10:05:00"
    }
    ```

- **Deletar Tarefa**

  - **Método**: `DELETE`
  - **URL**: `https://to-do-list-ds79.onrender.com/tasks/{id}`
  - **Descrição**: Remove uma tarefa pelo ID.
  - **Autenticação**: Requer autenticação.

### 👤 **Usuários**

- **Criar Usuário**

  - **Método**: `POST`
  - **URL**: `https://to-do-list-ds79.onrender.com/users/`
  - **Descrição**: Cria um novo usuário.
  - **Corpo da Requisição**:

    ```json
    {
        "name": "Pamela",
        "username": "pamyszz",
        "password": "12345"
    }
    ```

- **Login de Usuário**

  - **Método**: `POST`
  - **URL**: `https://to-do-list-ds79.onrender.com/users/login`
  - **Descrição**: Autentica um usuário e gera um token JWT.
  - **Corpo da Requisição**:

    ```json
    {
        "username": "pamyszz",
        "password": "12345"
    }
    ```
  - **Resposta**: Recebe um token JWT que deve ser incluído no cabeçalho `Authorization` para acessar os endpoints protegidos.

## 🌐 **Acesso à API**
Você pode acessar a API hospedada no Render através do seguinte link: [To-Do List API](https://to-do-list-ds79.onrender.com/).

## 🛠️ **Como Executar o Projeto**
1. **Clone o Repositório**:

   ```bash
   git clone https://github.com/pamyszz/to-do-list.git
   ```

2. **Navegue até o Diretório do Projeto**:

   ```bash
   cd to-do-list
   ```

3. **Construa e Execute a Aplicação**:

   ```bash
   ./mvnw spring-boot:run
   ```

4. **Acesse a API**:
   A API estará disponível em `https://to-do-list-ds79.onrender.com` durante a execução.

## 📄 **Notas Adicionais**
- **Maven**: Certifique-se de ter o Maven instalado e configurado corretamente.
- **Banco de Dados**: O projeto utiliza o H2 para testes. Para ambientes de produção, configure um banco de dados mais robusto.
- **Autenticação**: Utilize o token JWT recebido no login para autenticação nos endpoints protegidos.


