[![Code Coverage](https://img.shields.io/codecov/c/github/pvorb/property-providers/develop.svg)](https://codecov.io/gh/ricardofpu/xy-inc/property-providers?branch=master)

# Projeto XY INC

Desenvolvido para auxiliar na localização de Pontos de Interesse.

## Tecnologias utilizadas ##

- Java
- Maven
- PostgreSQL
- Docker Compose

## Instalações ##
O projeto possui as seguintes dependências:

* [Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (versão 8)
* [Maven](https://maven.apache.org/) (versão 3.5 ou maior)
* [Docker Compose](https://docs.docker.com/compose/install/) (versão 1.15 ou maior);

## Configuração do ambiente ##

Após a instalação das dependências, inicie baixando o projeto executando o seguinte comando:
```
  git clone https://github.com/ricardofpu/xy-inc.git
```
Dentro do diretório `` /xy-inc/xy-inc-web/src/main/resources ``, o arquivo `` application.properties `` define as configurações de acesso ao banco, como o datasource para conexão com o banco de dados, usuário e senha.

O arquivo abaixo é utilizado pelo spring para criar a tabela no database:

```
   schema.sql ( create da tabela )
```
## Execução ##

Iniciar o docker compose. Basta acessar a pasta principal do processo e executar o comando:

```
docker-compose up
```

Build no projeto com maven:

```
mvn clean install
```

Neste momento é necessário que o docker esteja rodando e que o PostgreSQL seja inicializado para que os testes unitários executem.

## Iniciando a aplicação ##

Acesse a pasta do projeto e execute o seguinte comando para iniciar a aplicação:
```
 mvn spring-boot:run
```
Este comando irá realizar o build da aplicação e iniciar o servidor Tomcat. A aplicação ficará disponível na seguinte URL:
```
 http://localhost:8080/
```
Outra opção para iniciar a aplicação e executar o seguinte comando dentro do diretório do projeto `` /xy-inc/xy-inc-web/target ``:
```
   java -jar xy-inc-1.0.0.jar
```
## Utilização do Serviço REST ##

Dentro do resource `` xy-inc\docs\postman``, estão a collection e environment para testes da API. Os recursos já estão mapeados.

## Documentação das API's

Após o build do projeto, é possível acessar a documentação das API's do projeto dentro do diretório `` /xy-inc-web/target/generated-docs/xy-inc-manual.html `` e abrir pelo navegador de internet.
