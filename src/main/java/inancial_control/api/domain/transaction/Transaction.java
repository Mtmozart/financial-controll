package inancial_control.api.domain.transaction;

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


    public Transaction(MonthTransaction monthTransaction, TransactionOperation type, String description, BigDecimal amount, Status status, User user) {
        this.monthTransaction = monthTransaction;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
