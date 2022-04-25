# CapPay
CapPay digital bank account

------------------------------------------------------------------------------------------------------------------------
 Esta API está toda documentada no Swagger, para ver a documentação, é necessário que o projeto                       
 esteja rodando, então, deve acessar a URL "http://localhost:8080/swagger-ui.html", la é possível testar todos os     
 end points                                                                                                           
------------------------------------------------------------------------------------------------------------------------

1. Para rodar esta aplicação é necessário ter a versão 11 do java.

2. caso a IDE ou editor de código não faça o download das dependências de forma automática, é necessário entrar na
pasta raíz do projeto por linha de comando, e executar o comando "mvn install" para instalar todas as dependências
(é necessário ter o maven instalado na sua máquina).

3. É preciso ter um serviço local do banco de dados PostgreSQL rodando, para isso, existem duas possibilidades:

        -> pela linha de comando, já dentro do projeto, você deve entrar na pasta docker-compose que fica na raíz do
           projeto CapPay, dentro da pasta você deve executar o comando "docker-compose up" para assim, executar uma
           imagem docker do postgreSQL que irá simular uma instância local;

        -> Deixar rodando um serviço do banco de dados PostgreSQL localmente, na porta 5432, com o username e password
           sendo "postgres", assim como está especificado no arquivo "application.properties";

4. Não é necessário criar a estrutura do banco de dados manualmente pois, no momento em que a aplicação for executada, o JPA
faz a criação de toda a estrutura automaticamente, mas, caso seja necessário, na raíz do projeto encontra-se o arquivo
"cappay.sql" que contém toda a estrutura de criação de tabelas bem como toda a estrutura do banco.

5. Para executar o teste unitário é necessário que o banco de dados local esteja rodando.
