# Voll.med API

Este é o projeto **Voll.med**, uma API REST desenvolvida como parte do curso de Spring da Alura. A aplicação é voltada para a gestão de dados médicos, utilizando o framework Spring Boot.

## 🚀 Tecnologias Utilizadas

- **Java 17**: Linguagem de programação utilizada no projeto.
- **Spring Boot 3.2.4**: Framework para simplificar o desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para integração com banco de dados.
- **Flyway**: Para controle de versão do banco de dados.
- **MySQL**: Banco de dados relacional utilizado.
- **Spring Security**: Para autenticação e autorização.
- **JWT (JSON Web Token)**: Para autenticação baseada em tokens.
- **SpringDoc OpenAPI**: Para documentação da API.
- **Dotenv**: Para gerenciamento de variáveis de ambiente.

## ⚙️ Configuração do Ambiente

### Pré-requisitos

- **Java 17** ou superior instalado.
- **MySQL** instalado e configurado.
- **Maven** configurado no ambiente.

### Configuração do Banco de Dados

Certifique-se de criar um banco de dados no MySQL e configurar as seguintes variáveis de ambiente:

- `DB_NAME`: Nome do banco de dados.
- `DB_USER`: Usuário do banco de dados.
- `DB_PASSWORD`: Senha do banco de dados.
- `JWT_SECRET`: (Opcional) Segredo para geração de tokens JWT. Caso não seja configurado, o padrão será `12345678`.

### Arquivo `.env`

Crie um arquivo `.env` na raiz do projeto com as seguintes configurações:

```env
DB_NAME=vollmed_api
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
JWT_SECRET=seu_segredo
```

## ▶️ Executando o Projeto

1. Clone o repositório:

  ```
   git clone https://github.com/seu-usuario/voll-med.git
   cd voll-med
  ```
2. Compile e execute o projeto utilizando o Maven Wrapper:

  ```
  ./mvnw spring-boot:run
  ```
3. A aplicação estará disponível em:

  ```
  http://localhost:8080
  ```

## 🧪 Executando os Testes

Para executar os testes automatizados, utilize o comando:

```
./mvnw test
```

## 📚 Documentação da API
A documentação gerada automaticamente pela especificação OpenAPI está disponível em:

```
http://localhost:8080/swagger-ui.html
```

## 🗂️ Estrutura do Projeto
```
voll-med/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── seuusuario/
│   │   │           └── vollmed/           # Código-fonte principal da aplicação
│   │   └── resources/
│   │       ├── application.properties     # Configurações da aplicação
│   │       └── db/migration/              # Scripts de versionamento Flyway
│   └── test/
│       └── java/                          # Testes automatizados
├── target/                                # Diretório gerado após a compilação
├── .env                                   # Arquivo de variáveis de ambiente
├── .gitignore                             # Arquivo para ignorar arquivos/pastas no Git
├── mvnw                                   # Maven Wrapper (Linux/macOS)
├── mvnw.cmd                               # Maven Wrapper (Windows)
├── pom.xml                                # Gerenciador de dependências e build
└── README.md                              # Documentação do projeto
```
---
Desenvolvido por: Vitor.