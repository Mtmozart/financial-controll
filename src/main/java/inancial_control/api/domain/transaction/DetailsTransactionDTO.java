package inancial_control.api.domain.transaction;

import inancial_control.api.domain.user.User;

import java.math.BigDecimal;

public record DetailsTransactionDTO(
        Long id,
        Long userId,
        MonthTransaction month,
        TransactionOperation type,
        String description,
        BigDecimal amount,
        Status status
) {
    public DetailsTransactionDTO(Transaction transaction) {
        this(transaction.getId(), transaction.getUser().getId(), transaction.getMonthTransaction(),
                transaction.getType(), transaction.getDescription(), transaction.getAmount(), transaction.getStatus());
    }
}
