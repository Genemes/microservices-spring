# microservices-spring
Microservices with Spring Boot, Java 11 and Docker


A aplicação foi desenvolvida utilizando o Spring Boot na versão 2.2.6, Java 11 e utilizamos nosso banco de dados rodando no Docker. Para essa prova de conceitos implementamos microservices com autenticação.
Por estarmos usando o Spring acabamos usando por conseguinte o padrão de projetos MVC, que é característica desse tipo de projeto. Para autenticação do usuário utilizamos o Spring Security, Token JWT e criptografia na senha.
Antes de iniciar a aplicação é necessário ter um serviço de BD, nessa aplicação utilizamos uma instancia do MySQL que está rodando em um container dentro do Docker. Para criação do container pode utilizar no seu Dockfiler os seguintes comandos.
version: '3.1'

services:
  db:
    image: mysql
    command: --default-authentication-plugin=mysql_native_password
    ports:
      - 3306:3306
    environment:
      MYSQL_USER: root
      MYSQL_ROOT_PASSWORD: password
    volumes:
      - microservices_genesedev:/var/lib/mysql

volumes:
  microservices_genesedev:


Você pode utilizar o código acima em algum arquivo com a extensão .yml e executar com os seguintes comandos:
 1. Roda o docker com o comando “docker-compose -f arquivo.yml up”;
 2. Para pegar o id do container utilize “docker os”;
 3. Para acessar o mysql: “docker exec -it container_id bash”;
 4. “mysql -uroot –p” e em seguida digita a senha
 5. Para visualizar quais os bancos existentes: “show databases”; 
 6. Para criar outro banco: “CREATE DATABASE genesedev”;
 7. Definir qual o banco de dados que será usado nas consultas: “use nome_do_banco”.

OBS: Ao final de cada comando SQL sempre utilizamos “;”

Para nossa aplicação temos um único ponto de entrada para as requisições, o gateway. Para fins acadêmicos utilizaremos um único banco de dados, mas poderíamos utilizar um banco para cada microservice. Utilizamos o service Discovery para que o gateway envie a requisição para o microservice correto.
O nosso Service Discovery foi implementado utilizando o Eureka e o Gateway utiliza o Zuul. 
Para executar o projeto precisamos que o serviço de bd esteja ativo, depois excutamos os projetos na seguinte ordem:
1.	Execute o Discovery;
2.	Em seguida execute o Gateway;
3.	Logo após execute o auth;
4.	Por fim execute course.
Precisamos ter um usuário no nosso BD, para isso podemos inserir através de um comando SQL um usuário com username criptografado “admin” e password “admin”

INSERT INTO application_user (username, password, role) VALUES ('admin', '$2a$10$w6tqDoKh.4zI8ZTDdgW11uGmnvsBdX0Pj9AJxEX0sTAoVy0/omrWe', 'ADMIN');


Depois de iniciada teremos acesso ao endpoint para fazer login. Utilize o método POST para sua requisição passando como entrada os dados do usuário cadastrado no BD:

GET login - Obtem token JWT e acesso aos outros endpoints
http://localhost:8080/gateway/auth/login 
entrada: 
{
    "username": "admin", 
    "password": "admin"
}

Depois de ter feito o login é gerado um token JWT no seguinte formato: Bearer eyJ...khNA, copie o todo o conteúdo do token depois do "Bearer ". Nos próximos endpoints que utilizar cole o conteudo desse token no authorizathion de cada requisição.


ENDPOINT COURSE
GET course – Lista todos os cursos cadastrados no banco de dados (esse endpoint necessita de autorização).
http://localhost:8080/gateway/course/v1/admin/course 
saída: 
[]

