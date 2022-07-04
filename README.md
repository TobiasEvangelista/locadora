<h1 align="center">Desafio Spring - Gerenciamento de carro </h1>


Neste código foi feito um REST CRUD API para gerenciamento de carros.


### Especificações principais

* [Spring](https://spring.io/) - v5.1.2.RELEASE - Framework Web Java/Kotlin MVW
* [Spring Boot](https://spring.io/projects/spring-boot) - 2.7.1 - Framework Initializer
* [Java](https://www.java.com/) - 11.0.13 - Linguagem
* [Hibernate](http://hibernate.org/orm/) - v5.6.9.Final - ORM
* [Swagger](https://swagger.io/) - v3.0.0 - Gerenciador de Documentação e Testes Funcionais
* [Postgres](https://www.postgresql.org) - v13.7-1 - SGBD
* [Intellij IDEA](https://www.jetbrains.com/idea/) - v2022.1.3 - IDE
* [Tomcat](http://tomcat.apache.org/) - v9.0.64 - Servidor


### Instalação

Clone o projeto e importe com a IDE suportada que lhe convir como um projeto Maven.

Caso venha a usar o Postgres como persistencia o projeto já está com o driver do mesmo instalado, então vá para:

```
src > main > resources > application.properties
```
Caso venha a utilizar outro banco será necessário adicionar o Driver do mesmo no pom.xml. Após isso, vá ao application.properties e ajuste os dados de acordo com o seu SGBD.

Agora aguarde sua IDE baixar as dependencias ou execute o mvn spring-boot:run caso tenha configurado o Maven separadamente.

### Rotas
Estas são as rodas para uso deste webservice:

#### Carros:
| Função | Rota | Parametro | Tipo |
| ------ | ------ | ------ | ------ |
| Listar Todos | /api/carro | Nenhum | GET
| Exibir | /api/carro/id | ID do Carro | GET
| Cadastrar | /api/carro | JSON do Carro | POST
| Editar | /api/carro/id | ID do Carro | PUT
| Remover | /api/carro/id | ID do Carro | DELETE

## Para mais detalhes e efetuar os testes, basta executar o Swagger do projeto pelo endereço:
```
http://localhost:8080/swagger-ui.html
```


