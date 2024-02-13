# Financial Control

Um projeto em Java utilizando spring-boot, com o objetivo de melhorar minhas habilidades em ambos, além de demostrar meus conhecimento, para eventuais contratações.

## Lista de dependências para fultura consulta

- Este projeto utiliza as seguintes dependências:

- **spring-boot-starter-web:**
    - Descrição: Fornece um conjunto de dependências para criar aplicativos da web usando o Spring MVC. Inclui tudo o que é necessário para configurar o ambiente de desenvolvimento web.

- **spring-boot-devtools:**
    - Descrição: Fornece ferramentas de desenvolvimento adicionais para aumentar a produtividade durante o desenvolvimento. Automaticamente reinicia a aplicação quando detecta alterações no código, entre outras funcionalidades, é necessário configurar no intellij.

- **lombok:**
    - Descrição: O Projeto Lombok é uma biblioteca do Java que auxilia na redução da verbosidade do código, fornecendo anotações para gerar automaticamente métodos como getters, setters, construtores, etc. Isso ajuda a tornar o código mais limpo e conciso.
Exemplo: 
  ```java
  import lombok.AllArgsConstructor;
  import lombok.Getter;
  import lombok.Setter;
  
  @Getter
  @Setter
  @AllArgsConstructor
  public class Person {
    private String firstName;
    private String lastName;
  }
  ```
  ## Anotações spring:
  Em spring, algumas anotações são necessárias para "falar" ao spring o que deve ser feito com aquela classe, se por exemplo
  é um service o controller, ou entitie.
- Anotações úteis em controle 
  - RestController - determina que é um controlle da arquitertura MVC, poderia ser um Service ou simplesmente Component;
  - ResquetMapping - Determina uma rota comum a todos as rotas/protocolos;
  - PostingMapping - Determina que o protocolo é tipo post, poderia ser Get, Delete, Put, PATH etc.
  - RequestBody = Determina que anotação é usada para indicar que o parâmetro do método de controle (Controller method) deve ser extraído do corpo da requisição HTTP. 
```
@RestController
@RequestMapping("incomes")
public class Income {

    @PostMapping
    public void register(@RequestBody String json){
        System.out.println(json);
    }
```

## Índice

- [Visão Geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Como Contribuir](#como-contribuir)
- [Instalação](#instalação)
- [Uso](#uso)
- [Documentação](#documentação)
- [Licença](#licença)

## Visão Geral

Breve explicação sobre o que é o projeto, seu propósito e contexto.

## Funcionalidades

Lista das principais funcionalidades e recursos oferecidos pelo projeto.

## Tecnologias Utilizadas

- Lista das principais tecnologias, linguagens e frameworks utilizados no projeto.

## Como Contribuir

Instruções sobre como contribuir para o projeto, incluindo diretrizes para abertura de problemas (issues) e envio de pull requests.

## Instalação

Passos para instalar e configu
