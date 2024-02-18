package inancial_control.api.domain.transaction;

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
}
