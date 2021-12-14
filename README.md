# Exchange API
## Funcionalidades

- ##### Consultar câmbio de uma determinada moeda - GET - /api/v1/rates/{baseCurrencyId}/exchange - `use o baseCurrencyId 1 para teste` - Não precisa autenticação

A partir deste endpoint poderá consultar o câmbio de uma moeda específico, retornando os dados de acordo com o Sample Response partilhado.

- ##### Atualizar câmbios - PATCH - /api/v1/rates/{id} - Autenticação necessária
Se adicionar o câmbio MZN-EUR de acordo com a variação diária do câmbio só poderá atualizar, consultando depois o histórico de variação desse câmbio, que é registado a cada atualização.

- ##### Segurança da App usando Spring Security


- ##### Verificar histórico de Câmbio - GET - /api/v1/rates/{rateId}/history - Não precisa Autenticação
Na verificação do histórico pode ainda filtrar os históricos do câmbio por datas `startDate e endDate -  dd-MM-YYYY` podendo colocar uma ou ambas datas. Sendo que, ao colocar apenas startDate irá buscar histórico de datas iguais ou superior e colocando apenas endDate igual ou inferior.

- ##### Gerir Moedas, Câmbios, Provedores e Users(Adicional)

Com consulta de câmbios existe a necessidade de adicionar mais informação que facilitam na melhor estruturação do projecto: sendo assim adicionados os módulos dos câmbios, provedores, moedas e utilizadores, onde cada um dos módulos possui o seu conjunto básico para o CRUD, sendo necessária a autenticação para acesso aos demais endpoints não mencionados acima.


Foi usado o `flyway db` é uma ferramenta que se permite garantir a ordem e organização para os scripts SQL que são executados na base de dados, garantindo o controle de versão dos mesmos.

## Como executar?

- `cd exchange-api`
- `mvnw clean install -DskipTests`
- `java -jar target/exchange-api-0.0.1-SNAPSHOT.jar`

A partir deste momento, seguindo corretamente os passos terá a aplicação correndo no endereço `http://localhost:8080`

NB:
Poderá encontrar uma documentação mais interativa da API no endereço `http://localhost:8080/swagger-ui/`

## Alternativa para executar

- `cd exchange-api`
- `docker build -f Dockerfile -t api/exchange-api .`
- `docker run -d -p 8080:8080 api/exchange-api`

Permite executar criando um container docker