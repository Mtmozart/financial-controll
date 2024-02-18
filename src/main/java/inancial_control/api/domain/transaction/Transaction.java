package inancial_control.api.domain.transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/*@Table(name = "users")
@Entity(name = "User")*/
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(of = "id")
public class Transaction {
    private Long id;
    private Month month;
    private TransactionOperation type;
    private String description;
    private BigDecimal amount;
}
