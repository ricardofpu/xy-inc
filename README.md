# Projeto XY INC

Desenvolvido para auxiliar na localização de Pontos de Interesse.

## Instalações ##
O projeto possui as seguintes dependências:

* [Java Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/index.html) (versão 8)
* [Maven](https://maven.apache.org/) (versão 3.5 ou maior)
* [Postgres](https://www.postgresql.org/download/) - banco de dados (versão 9.4);

## Configuração do ambiente ##

Após a instalação das dependências, inicie baixando o projeto executando o seguinte comando:

  git clone https://github.com/ricardofpu/xy-inc.git
  
Dentro do diretório xy-inc\src\main\resources, o arquivo application.properties define as configurações de acesso ao banco, como o datasource para conexão com o banco de dados, usuário e senha.

Os demais arquivos listados abaixo, são arquivos de criação da base de dados. Estes arquivos serão usados automaticamente na inicialização da aplicação.

  data.sql ( insert da base inicial )
  import.sql ( create do banco de dados )
  schema.sql ( create da tabela )

## Compilação do Projeto ##

Executar o seguinte comando dentro do diretório ..\xy-inc para efetuar o build da aplicação. Este comando irá baixar as dependências do pom.xml, executar os testes automáticos e irá gerar um arquivo JAR no diretório ..\xy-inc\target.

 * mvn clean package

## Iniciando a aplicação ##

Acesse a pasta do projeto e execute o seguinte comando para iniciar a aplicação:

 * mvn spring-boot:run
 
Este comando irá realizar o build da aplicação e iniciar o servidor Tomcat. A aplicação ficará disponível na seguinte URL:

 * http://localhost:8080/

