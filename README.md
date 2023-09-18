# <img src="./api/src/main/img/logo.png" width="3%"> HelpAnimal
Repositório criado para desenvolvimento de API de voluntariado para ajuda/resgate de animais.

## Logo:
<img src="./api/src/main/img/logo.png" width="20%">

## :pushpin: Desafio proposto
Você deverá criar uma API REST.
A API deverá resolver um problema da sociedade ou ser uma ideia inovadora para
auxiliar algo dentro da sociedade.

## :pushpin: Sobre a HelpAnimal
O intuito desta API é a construção de um app/sistema que funcione como uma "rede social" de pedidos de ajuda/resgate para animais(principalmente os animais de rua). Sendo assim, os usuários podem solicitar ajuda através de publicações, bem como também ajudar outros casos.

De acordo com a Organização Mundial de Saúde (OMS), somente no Brasil, cerca de 30 milhões de animais estão abandonados, sendo aproximadamente 20 milhões de cães e 10 milhões de gatos. Em grandes metrópoles, para cada cinco habitantes há um cachorro. Desses, 10% estão abandonados e enfrentam as adversidades das ruas.

## :pushpin: Endpoints
### Postman
Verifique [aqui](https://documenter.getpostman.com/view/20786077/2s9YC8uW5E) na documentação do postman mais detalhes sobre cada endpoint.

### Usuários:
- POST: Cadastro de novos usuários e sua permissão
- POST: Login de usuários cadastrados

### Publicações:
 - GET: Listar todas as publicações
 - GET: Listar publicação por id
 - POST: Fazer publicação
 - PUT: Atender ajuda
 - PUT: Fazer comentário em publicação
 - DELETE: Excluir publicação

## :pushpin: Arquitetura em camadas 
:file_folder: Pasta controller: Responsável pela requisições HTTP, interage com a camada de serviço (service) encaminhando as solicitações.

:file_folder: Pasta service: Responsável pela lógica e regras de negócio das solicitações vindas da camada controller.

:file_folder: Pasta repository: Responsável pela persistência dos dados no banco de dados.

:file_folder: Pasta exception: Responsável pela tratativa de erros e exceções.

:file_folder: Pasta models: Responsável pelas entidades(tabelas), abstração e modelagem dos dados.

:file_folder: Pasta models/dtos: Responsável pelos DTOS. objetos de transferência entre as camadas.

:file_folder: Pasta security: Resposável pela camada de segurança (autenticação, autorização e token).

##  :pushpin: Tratamento de Exceções
Na pasta exception é encontrada a classe responsável pelas tratativas de exceções. As tratativas são focadas nos dados inseridos pelo usuário, sendo elas:

- Usuário não pode passar um id (path variable) sem ser no formato de número, como por exemplo, uma letra;
- Usuário não pode passar campos em branco, como por exemplo, com aspas vazias ("");
- Usuário não pode mandar o body sem campos.

## Ferramentas e Tecnologias usadas
- Java 11
- Spring Frmework (boot, web, JPA, security)
- Lombok
- BCripyt
- JWT
- PostgreSQL
