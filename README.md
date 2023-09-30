# Bootcamp Cielo - Prospectar clientes

## Desafio individual

Executado por Anselmo Blanco Dominguez

## Tecnologias

- Spring Boot 3.1.4
- Maven 4
- Java 17.0.7
- React 18.2.0
- Node.js v20.7.0

## Detalhes da implementação

### Backend

Backend implementa endpoits de API necessários para
CRUD e listagem de Pessoas Físicas e Pessoas Jurídicas.

Tanto na inclusão quando na alteração de pessoas, estas 
são adicionadas em uma fila FIFO.

Dois endpoins lidam com a fila, um consultando apenas
a identificação da próxima pessoa da fila, e outro 
retirando a próxima pessoa da fila e retornando todos
os seus dados.

Os cadastros estão persistidos em memória, utilizando
H2, e a fila está implementada em memória utilizando
java.util.Queue.

Além dos campos indicados na especificação os dois
cadastros de pessoas, foram criados outros dois campos.
Um "id" e um "versao", qie recebe a data da ultima 
gravação.

O campo versão é necessário para atender a funcionalidade
de passar uma pessoa com cadastro editado para o final da
fila. A fila FIFO não sofre nenhuma manutenção além das
previstas pelo padrão. Então, no registro da pessoa da 
fila além de ser informado o número de cadastro dessa
pessoa é informada a versão do cadastro que gerou aquela
entrada. Ao ser consumida a fila, se a versão da pessoa
lida na fila não bate com a versão atual do cadastro desta,
esse item da fila é descartado, pois essa pessoa, com
certeza está inserida novamente mais para o final da fila.

### Frontend

Frontend desenvolvido em React utiliza apenas os dois 
endpoints que lidam com a fila, para prospecção de
clientes.

## Diretórios

- prospect-back: Implementação do backend
- prospect-front:  Implementação do frontend

## URLs

- Documentação da API
  - http://localhost:8080/v3/api-docs
- Swagger UI
  - http://localhost:8080/swagger-ui/index.html
- H2 console
  - http://localhost:8080/h2-console
- Frontend
  - http://localhost:3000/
