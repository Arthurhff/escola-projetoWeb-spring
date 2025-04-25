# Projeto Escola - API REST com Spring Boot

## Introdução

Este projeto consiste em uma API REST desenvolvida em Spring Boot para gerenciar Cursos e Alunos de uma instituição de ensino fictícia. A aplicação permite realizar operações CRUD (Create, Read, Update, Delete) completas para ambas as entidades, com persistência de dados em um banco de dados MariaDB (MySQL).

Este trabalho foi desenvolvido por *Arthur Fernandes* como parte da disciplina de *Arquitetura de Aplicações Web* no *Centro Universitário Newton Paiva*.

## Funcionalidades

* Cadastro, listagem, busca por ID, atualização e exclusão de Cursos.
* Cadastro, listagem, busca por ID, atualização e exclusão de Alunos.
* Relacionamento entre Aluno e Curso (Um Aluno pertence a Um Curso, Um Curso pode ter Muitos Alunos).
* API RESTful seguindo boas práticas.
* Persistência de dados configurada para MariaDB/MySQL.
* Uso de Lombok para redução de código boilerplate nas entidades.
* Geração/Atualização automática do schema do banco de dados via Hibernate (ddl-auto=update).

## Tecnologias Utilizadas

* *Java 17+*
* *Spring Boot*
    * Spring Web: Para criação de APIs REST.
    * Spring Data JPA: Para persistência de dados e abstração do banco.
    * Hibernate: Implementação JPA utilizada pelo Spring Data JPA.
* *Maven:* Gerenciador de dependências e build do projeto.
* *MariaDB (MySQL):* Banco de dados relacional.
* *Lombok:* Biblioteca para reduzir código boilerplate (getters, setters, construtores).
* *Jackson:* Biblioteca para manipulação de JSON (usada internamente pelo Spring).
* *XAMPP:* Utilizado para facilitar a instalação e gerenciamento do MariaDB e phpMyAdmin.

## Pré-requisitos

Antes de começar, garanta que você tenha instalado:

* ✅ Java JDK 17 ou superior ([Download aqui](https://www.oracle.com/java/technologies/downloads/))
* ✅ Maven (Opcional, pois o projeto inclui o Maven Wrapper mvnw) ([Download aqui](https://maven.apache.org/download.cgi))
* ✅ XAMPP (Com o módulo MariaDB/MySQL ativado) ([Download aqui](https://www.apachefriends.org/index.html))
* ✅ VS Code ou outra IDE de sua preferência ([Download VS Code](https://code.visualstudio.com/))
* ✅ Postman ou Bruno para testar a API ([Download Postman](https://www.postman.com/downloads/), [Download Bruno](https://www.usebruno.com/downloads))

## Configuração do Ambiente

### 1. Banco de Dados (MariaDB com XAMPP)

1.  *Inicie o XAMPP:* Abra o painel de controle do XAMPP.
2.  *Inicie o Módulo MySQL:* Clique no botão "Start" ao lado do módulo "MySQL".
3.  *Acesse o phpMyAdmin:* Clique no botão "Admin" ao lado do módulo "MySQL". Isso abrirá o phpMyAdmin no seu navegador.
4.  *Crie o Banco de Dados:* Vá para a aba "SQL" ou "Bancos de dados" e crie um novo banco de dados chamado escola_db. Você pode usar o seguinte comando SQL:
    sql
    CREATE DATABASE escola_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
    
5.  *Verifique a Configuração da Aplicação:* O arquivo src/main/resources/application.properties já está configurado para conectar a este banco de dados localmente sem senha:
    properties
    spring.datasource.url=jdbc:mysql://localhost:3306/escola_db
    spring.datasource.username=root
    spring.datasource.password=
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.open-in-view=false
    
    * *Se o seu MySQL/MariaDB tiver uma senha definida para o usuário root*, adicione-a na linha spring.datasource.password=SUA_SENHA_AQUI.
    * A propriedade spring.jpa.hibernate.ddl-auto=update fará com que o Hibernate crie ou atualize automaticamente as tabelas (curso, aluno) no banco escola_db quando a aplicação iniciar.

## Como Rodar o Projeto

1.  *Clone ou Baixe o Repositório:*
    bash
    git clone [https://github.com/Arthurhff/escola-projetoWeb-spring](https://github.com/Arthurhff/escola-projetoWeb-spring) # Substitua pela URL do seu repo
    cd seu-repositorio
    
    Ou baixe o ZIP e extraia o projeto.

2.  *Abra no VS Code (ou IDE):*
    * Abra o VS Code.
    * Vá em Arquivo > Abrir Pasta... (ou File > Open Folder...).
    * Selecione a pasta raiz do projeto que você clonou/extraiu.

3.  *Execute a Aplicação:*
    * Abra um terminal integrado no VS Code (Terminal > Novo Terminal ou Ctrl+').
    * Certifique-se de que você está na pasta raiz do projeto (onde está o arquivo pom.xml e mvnw).
    * Execute um dos seguintes comandos:

        * *No Windows (PowerShell):*
            bash
            .\mvnw.cmd spring-boot:run
            
        * *No Linux/Mac:*
            bash
            ./mvnw spring-boot:run
            
        * *Alternativa (se você tem o Maven instalado globalmente):*
            bash
            mvn spring-boot:run
            

4.  *Aguarde a Inicialização:* Espere até que o console mostre mensagens indicando que o Tomcat iniciou na porta 8080 e a aplicação foi iniciada, algo como:
    
    ... Tomcat started on port(s): 8080 (http) with context path ''
    ... Started EscolaApplication in X.XXX seconds (JVM running for Y.YYY)
    
    A aplicação estará acessível em http://localhost:8080.

## Testando os Endpoints (API REST)

Use o Postman ou Bruno para enviar requisições para os endpoints abaixo. A URL base é http://localhost:8080.

---

### Endpoints de Cursos (/cursos)

* *Listar todos os Cursos*
    * *Método:* GET
    * *URL:* /cursos
    * *Resultado:* 200 OK com a lista de cursos no corpo.

* *Criar um novo Curso*
    * *Método:* POST
    * *URL:* /cursos
    * *Body (raw JSON):*
        json
        {
          "nome": "Nome do Curso",
          "descricao": "Descrição do Curso",
          "cargaHoraria": 3000
        }
        
    * *Resultado:* 201 Created com o curso criado (incluindo o ID) no corpo.

* *Buscar Curso por ID*
    * *Método:* GET
    * *URL:* /cursos/{id} (Substitua {id} pelo ID do curso)
    * *Resultado:* 200 OK com os detalhes do curso. Se o ID não existir, retorna 404 Not Found.

* *Atualizar um Curso*
    * *Método:* PUT
    * *URL:* /cursos/{id} (Substitua {id} pelo ID do curso)
    * *Body (raw JSON):* (Envie todos os campos que deseja manter/atualizar)
        json
        {
          "nome": "Nome Atualizado",
          "descricao": "Descrição Atualizada",
          "cargaHoraria": 3100
        }
        
    * *Resultado:* 200 OK com os dados atualizados do curso. Se o ID não existir, retorna 404 Not Found.

* *Deletar um Curso*
    * *Método:* DELETE
    * *URL:* /cursos/{id} (Substitua {id} pelo ID do curso)
    * *Resultado:* 204 No Content (sem corpo) se deletado com sucesso. Se o ID não existir, retorna 404 Not Found.

---

### Endpoints de Alunos (/alunos)

* *Listar todos os Alunos*
    * *Método:* GET
    * *URL:* /alunos
    * *Resultado:* 200 OK com a lista de alunos no corpo.

* *Criar um novo Aluno*
    * *Método:* POST
    * *URL:* /alunos
    * *Body (raw JSON):* (Certifique-se que o id do curso existe)
        json
        {
          "nome": "Nome do Aluno",
          "email": "aluno@email.com",
          "dataNascimento": "2000-01-15",
          "curso": { "id": 1 }
        }
        
    * *Resultado:* 201 Created com o aluno criado (incluindo o ID) no corpo.

* *Buscar Aluno por ID*
    * *Método:* GET
    * *URL:* /alunos/{id} (Substitua {id} pelo ID do aluno)
    * *Resultado:* 200 OK com os detalhes do aluno. Se o ID não existir, retorna 404 Not Found.

* *Atualizar um Aluno*
    * *Método:* PUT
    * *URL:* /alunos/{id} (Substitua {id} pelo ID do aluno)
    * *Body (raw JSON):* (Envie todos os campos, certifique-se que o id do curso existe)
        json
        {
          "nome": "Nome Atualizado Aluno",
          "email": "aluno.atualizado@email.com",
          "dataNascimento": "2000-01-15",
          "curso": { "id": 1 }
        }
        
    * *Resultado:* 200 OK com os dados atualizados do aluno. Se o ID não existir, retorna 404 Not Found.

* *Deletar um Aluno*
    * *Método:* DELETE
    * *URL:* /alunos/{id} (Substitua {id} pelo ID do aluno)
    * *Resultado:* 204 No Content (sem corpo) se deletado com sucesso. Se o ID não existir, retorna 404 Not Found.

---

## Autor

* *Arthur Fernandes* - Estudante no Centro Universitário Newton Paiva
