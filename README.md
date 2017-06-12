# Projeto XY INC

Desenvolvido para auxiliar na localização de Pontos de Interesse.

## Instalações ##
O projeto possui as seguintes dependências:

* [Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (versão 8)
* [Maven](https://maven.apache.org/) (versão 3.5 ou maior)
* [Postgres](https://www.postgresql.org/download/) - banco de dados (versão 9.4);

## Configuração do ambiente ##

Após a instalação das dependências, inicie baixando o projeto executando o seguinte comando:

 * git clone https://github.com/ricardofpu/xy-inc.git
  
Dentro do diretório xy-inc\src\main\resources, o arquivo application.properties define as configurações de acesso ao banco, como o datasource para conexão com o banco de dados, usuário e senha.

Os demais arquivos listados abaixo, são arquivos de criação da base de dados. Estes arquivos serão usados automaticamente na inicialização da aplicação.

  - data.sql ( insert da base inicial )
  - import.sql ( create do banco de dados )
  - schema.sql ( create da tabela )

## Compilação do Projeto ##

Executar o seguinte comando dentro da pasta do projeto (\xy-inc) para efetuar o build da aplicação. Este comando irá baixar as dependências do pom.xml, executar os testes automáticos e irá gerar um arquivo JAR no diretório \xy-inc\target.

 - mvn clean package

## Iniciando a aplicação ##

Acesse a pasta do projeto e execute o seguinte comando para iniciar a aplicação:

 - mvn spring-boot:run
 
Este comando irá realizar o build da aplicação e iniciar o servidor Tomcat. A aplicação ficará disponível na seguinte URL:

 - [http://localhost:8080/]
 
Outra opção para iniciar a aplicação e executar o seguinte comando dentro do diretório do projeto (\xy-inc\target) :

  - java -jar xy-inc-1.0.0.jar

## Utilização do Serviço REST ##

Segue os seguintes exemplos para utilização dos serviços REST

* Listar todos os Pontos de Interesse: 
     -- GET em [http://localhost:8080/poi]
* Salvar novo Ponto de Interesse: 
     -- POST em [http://localhost:8080/poi] enviar JSON via Body
  -- Examplo de requisição HTTP:
      POST /poi HTTP/1.1
      Host: localhost:8080
      Content-Type: application/json
      Cache-Control: no-cache
      Postman-Token: 01bfb377-9742-1281-267b-402ebb1d3432

      {
        "nome": "Padaria",
        "coordenadaX": 15,
        "coordenadaY": 6
      } --
* Atualizar Ponto de interesse: 
     --PUT em [http://localhost:8080/poi] enviar JSON via Body
  -- Examplo de requisição HTTP:
      PUT /poi HTTP/1.1
      Host: localhost:8080
      Content-Type: application/json
      Cache-Control: no-cache
      Postman-Token: 29847fa5-2719-803e-974e-f4dab73ac857

      {
        "id": 3,
        "nome": "Joalheria",
        "coordenadaX": 15,
        "coordenadaY": 12
      }
* Buscar Ponto de interesse: 
     -- GET em [http://localhost:8080/poi/{id}] retorna um JSON caso o ID exista
* Deletar Ponto de interesse: 
     -- DELETE em [http://localhost:8080/poi/{id}] deleta pelo ID passado na URL
* Buscar Pontos de interesse passando coordenadas X e Y e distancia máxima:
     -- GET em [http://localhost:8080/poi/search?x=20&y=10&dMax=10] (os parametros passados são apenas um exemplo) enviar via parametro x, y e dMax
  



