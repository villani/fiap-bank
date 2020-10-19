# SPRING #
## Integrantes: ##
Carlos Mendes  
Felipe Birches  
Guilherme Moraes  
Leonardo Villani  
Luiz Galesso 

## Tecnologias utilziadas ##
### Back: Java utilizando Spring Boot. porta 8081 ###
### Docker: para subir o banco da aplicação que foi configurado para a porta 3307 para não entrar em conflito caso a porta 3306 já esteja sendo utilziada. ###
### MySQL: utilizado dentro do Docker para armazernar os dados gerados pela aplciação. ###
### H2 Database: utilizado pela classe de teste integrado, o banco é resetado para cada execução da classe de teste integrado. ###
### Swagger: utilizado para documentação do projeto. ###

## Estrutura do projeto ##
O projeto foi organizado da seguinte maneira: pacote config, controller, dto, entity, repository, service, util. É possível executar os testes unitários e integrados do projeto via IDE bem como ver a % de código coberto.  
No projeto, temos 3 controllers sendo elas: AlunoController responsável pelo crud de alunos, sendo que o campo de matrícula deve ser único.    
A CartaoController é responsável pelo crud de cartões, sendo que o núemro do cartão deve ser único, é nessa controller inclusive que é possível gerar o extrato a partir de um número de cartão, sendo gerado um arquivo .csv.  
A TransacaoController é responsável pelo crud de transações sendo necessário um número válido de cartão.

## Como executar ##
O primeiro passo é subir o banco de dados que será utilizado pela aplicação, que está disponível no arquivo docker-compose.yaml, para executá-lo acesse a pasta da aplicação e execute o seguinte comando:
```
docker-compose up 
``` 
Opcionalmente, pode ser acrescentado o parâmetro -d ao comando acima para que o terminal seja liberado.  
Após subir o banco de dados da aplicação, pode-se colocar a aplicação para executar pela IDE por exemplo.
Após a aplicação ser iniciada, será importado via batch os dados do arquivo lista_alunos.txt que se encontra dentro da pasta resources, a primeira coluna será o nome do aluno, a segunda será a matrícula e a terceira coluna que seria o número do cartão, por não ser o número único, foi descartada, assim o o número do cartão foi formado pelo número da matrícula do aluno, acrescentando "-00", a cada registro importado é printado no console o nome do aluno inserido, assim são gerado cerca de 10900 registros na tabela de aluno, 10900 registros na tabela de cartao e 10900 registros na tabela de transação que seria é a massa de dados simulada.  
No endereço http://127.0.0.1:8081/swagger-ui.html é possível acessar a documentação do projeto, não é necessário esperar o batch ser executado por completo para poder interagir com os endpoints, entretando os dados que serão importados pelo batch, só estarão disponíveis após a conclusão da importação.  
O extrato fica disponível no endereço /cartoes/{numeroCartao}/extrato gerando um arquivo.csv.
