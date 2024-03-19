
# MINI-UBER

Projeto com Spring Boot backend de um mini uber.

O fluxo começa com a criação da corrida, onde devem ser informados todos os dados necessários:
- data da solicitação
- cpf do cliente
- nome do cliente
- latitude e longitude de origem - onde o motorista    deve buscar o cliente
- latitude e longitude de destino - onde o motorista deve chegar
- latitude e longitude atuais - onde o carro que vai fazer a corrida se encontra no momento
- Situação da corrida -> inicia como Aguardando
- placa do veículo que vai realizar a corrida
- nome do motorista
- descrição do veículo
- cor do veículo

Após criada a corrida, quando o motorista chega para buscar o cliente, sua situação é atualizada para 'Em andamento'. Alternativamente, enquanto a corrida não está em andamento, é possível realizar o cancelamento da corrida. 

Durante a corrida, a latitude e longitude atuais são atualizadas de tempos em tempos, refletindo a localização do carro.

Finalmente, a qualquer tempo durante a corrida em andamento, é possível sua atualização para Concluída.

- Requisitos funcionais:

- Criar uma api REST que deve atender no endereço http://localhost:xxxx/rmXXXXXX/corridas. 
- xxxx são os 4 últimos dígitos da sua matrícula. 
- rmXXXXXX é a sua matrícula.


- Operações:
- POST http://localhost:xxxx/rmXXXXXX/corridas - Deve receber um json com os dados necessários para criar um registro na tabela acima. Deve retornar um JSON com a corrida criada, incluindo o ID gerado.

- Regras:
- O par latitude e longitude de origem não podem ser iguais ao par latitute e longitude de destino
- Não é permitida a criação de uma corrida para um cliente se ele já possuir uma corrida em andamento
- A situação inicial da corrida ao ser criada é 'A' - Aguardando
- Ao ser criada, a data da solicitação da corrida assume a data e hora do momento da criação.

- PUT http://localhost:xxxx/rmXXXXXX/corridas/{id}/situacao - Deve receber a situação no Body da requisição e atualizar a situação da corrida enviada no id.

- Regras:
- Se não existir corrida com o id passado, devolver um erro.
- Os fluxos possíveis das situações são: 
- Aguardando -> Em andamento -> Concluída
- Aguardando -> Cancelada
- Só devem ser permitidas atualizações que atendam a um dos fluxos acima. Em caso negativo, deve retornar um erro
- Se o status recebido for Cancelada ou Concluída, a data de finalização deve ser atualizada para a data e hora do momento da chamada
- Se o status recebido for Em andamento, deve ser verificado se a latitude e longitude atuais são iguais às latitudes e longitudes de origem. Caso contrário, deve lançar um erro.


- PUT http://localhost:xxxx/rmXXXXXX/corridas/{id}/posicao-atual - Deve receber no corpo da requisição um json com a latitude e longitude atual para atualização dos dados na tabela da corrida com o id recebido

- Regras:
- Se não existir corrida com o id recebido, retornar erro
- nenhum dos valores pode ser nulo


- Requisitos não funcionais:

- Pilha tecnológica a ser utilizada: Spring Boot + Spring Web + Spring Data JPA + Lombok + Validation + Banco Oracle
- Não expor as entidades na API. Utilizar DTO para entrada e saída de dados
- Utilizar validação nos DTOs e customizar as mensagens de erro
- Regras de negócio não devem estar na classe de API. Utilizar Services
- Criar uma exceção não checada própria para lançar erros de negócio
- Utilizar lombok para reduzir a verbosidade das classes, evitando getters e setters
- Utilizar Records para criar os DTOs

