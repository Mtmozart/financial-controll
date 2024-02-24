package inancial_control.api.domain.transaction;

import inancial_control.api.domain.user.UpdateUserDTO;
import inancial_control.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "transactions")
@Entity(name = "Transaction")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MonthTransaction monthTransaction;
    @Enumerated(EnumType.STRING)
    private TransactionOperation type;
    private String description;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private User user;


    public Transaction(CreateTransactionDTO data, User user) {
        this.monthTransaction = data.monthTransaction();
        this.type = data.transactionOperation();
        this.description = data.description();
        this.amount = data.amount();
        this.status = data.status();
        this.user = user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void update(UpdateTransactionDTO data){
        if (data.description() != null) {
            this.description = data.description();
        }
        if (data.monthTransaction() != null) {
            this.monthTransaction = data.monthTransaction();
        }
        if (data.transactionOperation() != null) {
            this.type = data.transactionOperation();
        }
        if (data.amount() != null) {
           this.amount = data.amount();
        }
        if (data.status() != null) {
            this.status = data.status();
        }

    }
    public void removeTransaction() {
        this.user = null;
    }
}
