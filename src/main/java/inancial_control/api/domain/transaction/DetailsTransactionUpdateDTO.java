package inancial_control.api.domain.transaction;

import java.math.BigDecimal;

public record DetailsTransactionUpdateDTO(
        Long id,
        Long userId,
        MonthTransaction month,
        TransactionOperation type,
        String description,
        BigDecimal amount,
        Status status
) {
    public DetailsTransactionUpdateDTO(Long id, Transaction transaction) {
        this(id, transaction.getUser().getId(), transaction.getMonthTransaction(),
                transaction.getType(), transaction.getDescription(), transaction.getAmount(), transaction.getStatus());
    }
}
