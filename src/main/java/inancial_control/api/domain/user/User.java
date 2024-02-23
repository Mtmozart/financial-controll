package inancial_control.api.domain.user;

import inancial_control.api.domain.transaction.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;



@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Transaction> transactions = new ArrayList<>();
    private boolean active;

    public User(CreateUserDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.active = true;
    }

    public void setTransactions(Transaction t){
        transactions.forEach(e -> e.setUser(this));
        this.transactions = transactions;
    }

    public void update(UpdateUserDTO data){

        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
        if (data.password() != null) {
            this.password = data.password();
        }
    }

    public void delete(Long id) {
        this.active = false;
    }
}
