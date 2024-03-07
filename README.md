# Financial Control

Um projeto em Java utilizando spring-boot, com o objetivo de melhorar minhas habilidades em ambos, além de demostrar meus conhecimento, para eventuais contratações.

#URL para consultar resultados e requisitos da API: http://localhost:8080/swagger-ui/index.html


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
  @NoArgsConstructor
  @AllArgsConstructor
  public class Person {
    private String firstName;
    private String lastName;
  }
  ```
  ## Anotações spring:
  Em spring, algumas anotações são necessárias para "falar" ao spring o que deve ser feito com aquela classe, se por exemplo
  é um service o controller, ou entitie.
- Anotações úteis em controller 
  - RestController - determina que é um controlle da arquitertura MVC, poderia ser um Service ou simplesmente Component;
  - IMPORTATE: quando mencionar um Component, devo colocar o nome netre parenteses da descrição.
  - ResquetMapping - Determina uma rota comum a todos as rotas/protocolos;
  - PostingMapping - Determina que o protocolo é tipo post, poderia ser Get, Delete, Put, PATH etc, dentro dele posso por a rota que ele será usado.
  - Transactional - Informa ao SPring que ocorrerá uma transação de dados.
  - RequestBody - Determina que anotação é usada para indicar que o parâmetro do método de controle (Controller method) deve ser extraído do corpo da requisição HTTP.
  - PathVariable - Determina que uma virá da url, exemplo @PathVariable Long id
 # Exemplo:
```java
@PutMapping("{id}")
@Transactional
@SecurityRequirement(name="bearer-key")
public ResponseEntity update(@RequestBody @Valid UpdateUserDTO data, @PathVariable Long id) {
var user =  service.update(data, id);
return ResponseEntity.ok(user);
}
```

- **flyway:
-  É uma dependência a qual utilizo para realizar migrations no banco de dados, como critério é ficar atento a como o arquivo deve ser chamado e deve ter a extenssão .sql, como no exemplo: V1__create-table-users.sql, dentro passo os parâmetros sql.

**Bean validation:**
- É uma dependência a qual utilizo para realizar validações, passando suas anotações, para que assim crie uma validação, são exemplo:
```java
public record CreateUserDTO(
        @NotBlank
        String name,
        @NotBlank @Email
        String email,
        @NotBlank @Pattern(regexp = "^(?=.*[0-9])?(?=.*[@$!%*?&])?(?=.*[A-Z])?[A-Za-z\\d@$!%*?&]{5,}$")
        String password) {
}
```
Se eu quiser passar uma mensagem, eu posso passar o padrão message = "", dentro da anotação, também posso criar um aquivo 
na pasta resources, com o nome "ValidationMessages.properties", e dentro do bean passo message={nome_parametro.message}, como no exemplo:
Paramêtro passado no arquivo: cpf.obrigatorio=CPF é obrigatório. email.obrigatorio=mensagem email.invalido=messagem
```java
  @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{email.obrigatorio}") @Email(message = "{email.invalido}")
        String email,
```
 Não pode esquecer de passar a anotação @valid no campo onde desejo informar que é verificado em outra parte, como no exemplo, pois nele eu faço a validação no DTO:

```java
  @PostMapping
    public void createUser(@RequestBody @Valid CreateUserDTO datas){
            repository.save(new User(datas));
    }
```

**Nota sobre o  Java Persistence API (JPA)**
- Falando brevemente sobre o JPA, esta se trata de é uma especificação do Java para mapeamento objeto-relacional (ORM). O objetivo do JPA é fornecer uma maneira padrão de mapear objetos Java para tabelas em um banco de dados relacional.
### Pricipais anotações:
- Table - determina o nome da tabela;
- Entity -> determina o nome da entidade;
- Id -> Determina o atributo que será o id;
-  GeneratedValue(strategy = GenerationType.IDENTITY) -> O tipo de ID, se será número ou outro como UUIDD
-   OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER) -> Determina a relação com outra entidade, podendo variar entre uma para uma, muito para muitos e um para muitos, porem nesses casos também é necessário a declarar em outra parte do código.
- Existem também outras anotações como: @ManyToOne e OneToOne, existe a possibilidade de passagem de parâmetros dentro da anotação.

```Java
@Table(name = "users")
@Entity(name = "User")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  private String password;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Collection<Transaction> transactions = new ArrayList<>();
  private boolean active;
```

### Muito importante:
- Nessas relações de ManyToOne, é necessário fazer um set para atualizar a informação

```Java
  public void setTransactions(Transaction t) {
  transactions.forEach(e -> e.setUser(this));
  this.transactions = transactions;
}
```

### Outro ponto impornate em JPA, criação do REPOSITORY:

- Criada a entidade, é necessário a criar no banco de dados, além disso, é necessário criar uma iterface repository, sendo nos moldes do exemplo:

```Java
 public interface TransactionsRepository extends JpaRepository<Transaction, Long> {}

```
- Nela já existe algum métodos como findById, mas é posspivek oassar parâmetros de duas maneiras, sendo a primeira em camelorcase e a segunda por query:

```Java
List<Transaction> findAllByUserId(Long id);

@Query("SELECT t FROM Transaction t WHERE t.monthTransaction = :month AND t.user.id = :userId")
List<Transaction> findTransactionByMonthByUserId(MonthTransaction month, Long userId);

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


