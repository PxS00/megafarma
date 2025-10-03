# megafarma

Este projeto é um sistema acadêmico desenvolvido para a disciplina de *JAVA* na FIAP. O objetivo é aplicar conceitos de desenvolvimento backend utilizando o framework Quarkus, explorando práticas modernas de APIs REST, persistência e empacotamento de aplicações Java.

## 🏗️ Sobre a estrutura

O projeto implementa uma API REST para consulta de remédios, utilizando a seguinte estrutura de classes:

- 💊 **RemedioTO.java**: Classe de transferência de dados (TO - Transfer Object) que representa o remédio, com atributos como código, nome, preço, data de fabricação e validade.
- 🗄️ **RemedioDAO.java**: Responsável por simular o acesso a dados, retornando uma lista de remédios pré-cadastrados em memória.
- ⚙️ **RemedioBO.java**: Camada de regras de negócio (BO - Business Object), que faz a ponte entre o DAO e a camada de recursos REST.
- 🌐 **RemedioResource.java**: Classe que expõe o endpoint REST `/megafarma`, permitindo consultar todos os remédios cadastrados via requisição HTTP GET.

Esse fluxo segue boas práticas de separação de responsabilidades, facilitando a manutenção e evolução do sistema.

## 🚀 Sobre o Quarkus

Este projeto utiliza o Quarkus, o Supersonic Subatomic Java Framework. O Quarkus é focado em oferecer alta performance, baixo consumo de memória e inicialização rápida, sendo ideal para aplicações cloud-native e microsserviços.

Saiba mais em: <https://quarkus.io/>

## 🛠️ Como rodar a aplicação em modo desenvolvimento

Você pode rodar a aplicação em modo dev, que permite live coding, usando:

```shell
./mvnw quarkus:dev
```

> **Nota:** O Quarkus possui uma Dev UI disponível apenas em modo dev: <http://localhost:8080/q/dev/>

## 📦 Empacotando e executando a aplicação

Para empacotar a aplicação:

```shell
./mvnw package
```

O comando acima gera o arquivo `quarkus-run.jar` em `target/quarkus-app/`.

Para rodar:

```shell
java -jar target/quarkus-app/quarkus-run.jar
```

Se quiser gerar um _über-jar_ (jar único com dependências):

```shell
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

E execute com:

```shell
java -jar target/*-runner.jar
```

## 🧊 Criando um executável nativo

Para criar um executável nativo (requer GraalVM):

```shell
./mvnw package -Dnative
```

Ou, para buildar em container (sem GraalVM local):

```shell
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

O executável estará em `./target/megafarma-1.0.0-SNAPSHOT-runner`

Mais informações: <https://quarkus.io/guides/maven-tooling>

## 📚 Guias úteis do Quarkus

- REST: <https://quarkus.io/guides/rest>
- REST Jackson: <https://quarkus.io/guides/rest#json-serialisation>

---

Projeto acadêmico - FIAP | Engenharia de Software
