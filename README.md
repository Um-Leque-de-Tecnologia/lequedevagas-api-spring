# Leque de Vagas — a API

Projeto de referência do curso **Introdução ao Spring Boot** (NickDev). É o
outro lado do mural de vagas: o programa que guarda as vagas, as empresas e as
pessoas, e as entrega para quem pedir.

O código que roda aqui está **no ponto da AV1** (fim da aula 05): as quatro
frentes com CRUD, DTO de entrada e de saída, Bean Validation, erros em Problem
Details e as três entidades guardadas no H2 com Spring Data JPA.

Ele existe para você comparar com o seu: abrir um arquivo, ver como ficou aqui
e entender por quê. Não é para clonar e entregar — o que vale nota é o da sua
equipe, com as decisões dela e um commit por pessoa.

A tela que consome esta API é
[lequedevagas-web](https://github.com/Um-Leque-de-Tecnologia/lequedevagas-web),
projeto da disciplina de Introdução ao Next.js. Os dados daqui são os mesmos de
lá — é de propósito.

## Rodar

```bash
./mvnw spring-boot:run     # Mac e Linux
mvnw.cmd spring-boot:run   # Windows
```

Precisa de **JDK 21**. Sobe em <http://localhost:8080> — procure a linha
`Tomcat started on port 8080` no log.

Depois de subir, abra o `requisicoes.http` e clique em **Send Request** em cima
de cada chamada. O IntelliJ roda direto; no VS Code, instale a extensão REST
Client. Cada chamada diz o status esperado.

O banco é o H2 em memória: some quando a aplicação para e volta com a carga de
`src/main/resources/dados/` na próxima subida. Para olhar as tabelas, abra
<http://localhost:8080/h2-console> com a URL `jdbc:h2:mem:vagas`, usuário `sa`
e senha vazia.

## Estrutura: uma pasta por camada

```
src/main/java/br/edu/faculdade/vagas/
├── VagasApplication.java     o ponto de partida, na raiz, acima de tudo
├── controller/               @RestController — o único que fala HTTP
├── service/                  @Service — onde a regra mora
├── repository/               interfaces JpaRepository — onde o dado está
├── dto/                      records de entrada e de saída — o contrato da API
├── model/                    @Entity — as linhas das tabelas
└── exception/                o TratadorDeErros e as exceções da casa
src/main/resources/
├── application.properties    banco, console, SQL no log, ordem da carga
└── dados/                    um arquivo de carga por frente
```

**Por que as pastas funcionam sem configuração.** O `@SpringBootApplication`
liga o `@ComponentScan`, que procura beans no pacote da `VagasApplication` e em
todos os subpacotes. Por isso ela fica na raiz. Uma classe num pacote **fora**
de `br.edu.faculdade.vagas` não é encontrada, e a subida falha com
`required a bean of type '...' that could not be found`.

**Quem pode depender de quem.** A seta só aponta para baixo:

```
controller  →  service  →  repository  →  model
     └──────── dto ────────┘
```

- O **controller** conhece o service e os DTOs. Nunca o repository, nunca o model.
- O **service** conhece o repository, o model e os DTOs. É ele que traduz um no outro.
- O **repository** conhece só o model.
- O **dto** conhece o model só para traduzir (`VagaResposta.de(vaga)`), num lugar só.
- O **model** não conhece ninguém.

Se um `import ...repository...` aparecer num controller, a regra foi para o
lugar errado.

## As quatro frentes

O projeto é de equipe, e cada pessoa carrega uma frente o semestre inteiro. Este
repositório é de referência, então tem as quatro juntas — no da sua equipe, cada
uma é de alguém, e o `git log` é a evidência disso.

| Frente | Recurso | Arquivos |
| --- | --- | --- |
| 1 | Vaga | `model/Vaga`, `dto/VagaEntrada`, `dto/VagaResposta`, `repository/VagaRepository`, `service/VagaService`, `controller/VagaController`, `dados/vagas.sql` |
| 2 | Empresa | `model/Empresa`, `dto/EmpresaEntrada`, `dto/EmpresaResposta`, `repository/EmpresaRepository`, `service/EmpresaService`, `controller/EmpresaController`, `dados/empresas.sql` |
| 3 | Pessoa | `model/Pessoa`, `dto/PessoaEntrada`, `dto/PessoaResposta`, `repository/PessoaRepository`, `service/PessoaService`, `controller/PessoaController`, `dados/pessoas.sql` |
| 4 | Busca e números | `service/BuscaService`, `service/EstatisticasService`, `controller/BuscaController`, `controller/EstatisticasController` |

Compartilhados — uma pessoa por vez, com `git pull` antes: `pom.xml`,
`application.properties` e `exception/TratadorDeErros`.

A frente 4 não tem recurso próprio: ela lê o `VagaRepository` da frente 1. É de
propósito — buscar é uma responsabilidade, não uma tabela.

## As rotas

| Rota | Frente | Status |
| --- | --- | --- |
| `GET /vagas` | 1 | 200 |
| `GET /vagas/{id}` | 1 | 200 · 404 · 400 se o id não for número |
| `POST /vagas` | 1 | 201 com `Location` · 400 |
| `PUT /vagas/{id}` | 1 | 200 · 404 · 400 |
| `DELETE /vagas/{id}` | 1 | 204 · 404 |
| `GET /empresas` | 2 | 200 |
| `GET /empresas/{slug}` | 2 | 200 · 404 |
| `POST /empresas` | 2 | 201 com `Location` · 400 · 409 slug repetido |
| `DELETE /empresas/{slug}` | 2 | 204 · 404 |
| `GET /pessoas` | 3 | 200 |
| `GET /pessoas/{id}` | 3 | 200 · 404 |
| `POST /pessoas` | 3 | 201 com `Location` · 400 · 409 e-mail repetido |
| `PUT /pessoas/{id}` | 3 | 200 · 404 · 400 |
| `DELETE /pessoas/{id}` | 3 | 204 · 404 |
| `GET /vagas/busca?area=&senioridade=&aceitaIniciante=` | 4 | 200 — inclusive `[]` · 400 com filtro de mais de 60 caracteres |
| `GET /estatisticas` | 4 | 200 |

Todo erro sai em `application/problem+json`, pelo `TratadorDeErros`. Nenhuma rota
devolve 500 nem pilha no corpo.

## O que NÃO tem, de propósito

Se você achar que está faltando, provavelmente está — mas na aula seguinte.

- **Relacionamento entre Vaga e Empresa.** A vaga guarda `empresaSlug`, um
  texto solto, e nada impede um slug que não existe. `@ManyToOne` é a aula 07.
- **Candidatura.** A entidade existe no briefing, e chega na aula 07.
- **Filtrar e contar dentro do banco.** A busca e as estatísticas trazem a
  tabela inteira e filtram em Java — a aula 05 mediu isso. Query methods,
  `@Query`, paginação e transações são a aula 08.
- **Testes.** Só o `contextLoads` que veio do Initializr. Aula 09.
- **Autenticação.** `GET /pessoas` é público e não há campo de senha. Aulas 10
  e 11.

## Detalhes que costumam pegar

**O starter mudou de nome no Spring Boot 4.** O `pom.xml` traz
`spring-boot-starter-webmvc` e `spring-boot-starter-webmvc-test`, e não
`spring-boot-starter-web` e `spring-boot-starter-test`. E o console do H2 precisa
do módulo `spring-boot-h2console`: sem ele, `/h2-console` responde 404.

**`required a bean of type 'VagaRepository'`.** A classe está fora do pacote
da `Application`, ou (na interface) não estende `JpaRepository`.

**`GET /vagas/nao-existe` dá 400, e não 404.** Esperado: o id é `Long`, e o
texto nem chega ao controller. O 404 se confere com `/vagas/999`.

**`DELETE /vagas/999` devolve 204.** Faltou o `existsById`: o `deleteById` de um
id que não existe só faz um select e volta sem erro.

**O primeiro POST dá 409 e o log fala em `PRIMARY KEY ON PUBLIC.VAGA(ID)`.** O
`.sql` de carga tem a coluna id escrita à mão. Tire a coluna.

**No console: `Database "~/test" not found`.** O formulário vem com
`jdbc:h2:~/test`. Use `jdbc:h2:mem:vagas`, o endereço que o log mostra.

**O id pulou um número.** Um INSERT recusado (e-mail repetido) já tinha pedido
um número ao banco, e número pedido não volta. Id identifica; não conta.

## Licença

Material didático do NickDev. Use para estudar, cite quando reaproveitar.
