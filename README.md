# megafarma

Este projeto é um sistema acadêmico desenvolvido para a disciplina de *JAVA* na FIAP. O objetivo é aplicar conceitos de desenvolvimento backend utilizando o framework Quarkus, organizando o código em camadas (TO, DAO, BO, Resource) e expondo uma API REST para gerenciamento de remédios, clientes, vendas e itens vendidos.

## 🏗️ Estrutura do projeto

O projeto segue a separação de responsabilidades em camadas:

- TO (Transfer Object): classes de transferência de dados (ex.: `RemedioTO`, `ClienteTO`, `VendaTO`, `ItensVendidosTO`).
- DAO (Data Access Object): acesso ao banco / SQL direto (`*DAO.java`).
- BO (Business Object): regras de negócio e ponte entre Resource e DAO (`*BO.java`).
- Resource: endpoints REST usando JAX-RS/Quarkus (`*Resource.java`).

Arquivos principais recém-alinhados ao padrão do projeto:
- `RemedioTO`, `RemedioDAO`, `RemedioBO`, `RemedioResource` (modelo)
- `ClienteTO`, `ClienteDAO`, `ClienteBO`, `ClienteResource`
- `VendaTO`, `VendaDAO`, `VendaBO`, `VendaResource`
- `ItensVendidosTO`, `ItensVendidosDAO`, `ItensVendidosBO`, `ItensVendidosResource`

## 📦 Tabelas (DDL)

Tabelas usadas no projeto (exemplo):

CREATE TABLE DDD_CLIENTES (
    CODIGO NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NOME VARCHAR2(100 BYTE),
    CPF VARCHAR2(20 BYTE),
    EMAIL VARCHAR2(50 BYTE),
    DATA_DE_NASCIMENTO DATE
);

CREATE TABLE DDD_VENDAS (
    CODIGO NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    DATA_DA_VENDA DATE,
    CODCLIENTE NUMBER,
    CONSTRAINT FK_VENDAS_CLIENTE FOREIGN KEY (CODCLIENTE) REFERENCES DDD_CLIENTES(CODIGO)
);

CREATE TABLE DDD_ITENSVENDIDOS (
    CODVENDA NUMBER,
    CODREMEDIO NUMBER,
    QUANTIDADE NUMBER,
    CONSTRAINT PK_ITENSVENDIDOS PRIMARY KEY (CODVENDA, CODREMEDIO),
    CONSTRAINT FK_ITENSVENDIDOS_VENDA FOREIGN KEY (CODVENDA) REFERENCES DDD_VENDAS(CODIGO),
    CONSTRAINT FK_ITENSVENDIDOS_REMEDIO FOREIGN KEY (CODREMEDIO) REFERENCES DDD_REMEDIOS(CODIGO)
);

(Existe também a tabela `DDD_REMEDIOS` usada pelo módulo de remédios.)

## 🌐 Endpoints REST (padrão)

Cada recurso segue o padrão definido no `RemedioResource` (mesma convenção e códigos HTTP):

- Remédios
  - GET  /megafarma                → listar todos
  - GET  /megafarma/{codigo}      → obter por código
  - POST /megafarma               → criar (recebe JSON, validações via `@Valid`)
  - PUT  /megafarma/{codigo}      → atualizar (recebe JSON)
  - DELETE /megafarma/{codigo}    → excluir

  Observação: o `RemedioTO` agora tem o campo `imagem` que deve conter um link HTTP ou HTTPS (ex.: `https://exemplo.com/img.jpg`)

- Clientes
  - GET  /clientes
  - GET  /clientes/{codigo}
  - POST /clientes
  - PUT  /clientes/{codigo}
  - DELETE /clientes/{codigo}

- Vendas
  - GET  /vendas
  - GET  /vendas/{codigo}
  - POST /vendas
  - PUT  /vendas/{codigo}
  - DELETE /vendas/{codigo}

- Itens Vendidos (chave composta codVenda/codRemedio)
  - GET  /itensvendidos
  - GET  /itensvendidos/{codVenda}/{codRemedio}
  - POST /itensvendidos
  - PUT  /itensvendidos/{codVenda}/{codRemedio}
  - DELETE /itensvendidos/{codVenda}/{codRemedio}

Observações:
- Os `Resource` usam `@Valid` nos parâmetros de entrada para aplicar as constraints definidas nos TOs.
- As respostas seguem o mesmo padrão do `RemedioResource`: `ok()` para sucesso na consulta, `created(null)` para criação/atualização bem-sucedida, `status(204)` para exclusão bem-sucedida, `status(400)` para requisições inválidas e `status(404)` quando o recurso não é encontrado.

## 🗓 Convenção de datas e persistência

- As classes TO usam `java.time.LocalDate` para representar datas (ex.: `dataDeFabricacao`, `dataDeValidade`, `dataDeNascimento`, `dataDaVenda`).
- Nos DAOs, a leitura é feita com `rs.getDate("coluna").toLocalDate()`.
- Ao salvar/atualizar, os DAOs usam `ps.setDate(index, java.sql.Date.valueOf(localDate))` (sem condicionais), garantindo o mesmo comportamento do `RemedioDAO`.

## ✅ Validações (jakarta.validation)

As classes TO possuem anotações de validação seguindo o padrão do `RemedioTO`:
- Strings: `@NotBlank`, `@Size(max = ...)`, `@Email` quando aplicável.
- Datas: `@NotNull`, `@Past` ou `@PastOrPresent` conforme o caso.
- Números: `@NotNull`, `@Positive` / `@PositiveOrZero` quando necessário.

Isso faz com que requisições inválidas retornem `400 Bad Request` automaticamente nas resources que usam `@Valid`.

## 🧭 Camada de negócio (BO)

- Os `*BO` estão uniformizados: cada método instancia o DAO correspondente e delega as operações.
- O `RemedioBO` foi alinhado para inicializar o `RemedioDAO` também dentro do `save()` para evitar inconsistências.

## 🛠️ Como executar (Windows / cmd.exe)

No Windows (cmd.exe) use os scripts `mvnw.cmd` incluídos no projeto:

- Rodar em modo dev (hot reload):

```cmd
mvnw.cmd quarkus:dev
```

- Empacotar o projeto:

```cmd
mvnw.cmd package
```

- Executar o quarkus-run.jar gerado:

```cmd
java -jar target\quarkus-app\quarkus-run.jar
```

- Gerar um uber-jar:

```cmd
mvnw.cmd package -Dquarkus.package.jar.type=uber-jar
```

- Executar o runner (após gerar uber-jar):

```cmd
java -jar target\* -runner.jar
```

## 🔎 Testes rápidos via curl (exemplos)

Listar remédios:

```cmd
curl -X GET http://localhost:8080/megafarma -H "Accept: application/json"
```

Criar remédio (exemplo JSON incluindo imagem):

```cmd
curl -X POST http://localhost:8080/megafarma -H "Content-Type: application/json" -d "{\"nome\":\"Paracetamol\",\"preco\":10.5,\"dataDeFabricacao\":\"2024-01-01\",\"dataDeValidade\":\"2026-01-01\",\"imagem\":\"https://exemplo.com/images/paracetamol.jpg\"}"
```

Criar cliente (exemplo JSON):

```cmd
curl -X POST http://localhost:8080/clientes -H "Content-Type: application/json" -d "{\"nome\":\"Fulano\",\"cpf\":\"12345678900\",\"email\":\"fulano@exemplo.com\",\"dataDeNascimento\":\"1990-01-01\"}"
```

> Observação: ajuste as URLs/JSON conforme o seu cliente HTTP preferido.

## 📚 Referências
- Quarkus: https://quarkus.io/
- JAX-RS: APIs REST padrão em Java
- Jakarta Validation: https://jakarta.ee/specifications/validation/

---

Projeto acadêmico - FIAP | Java
