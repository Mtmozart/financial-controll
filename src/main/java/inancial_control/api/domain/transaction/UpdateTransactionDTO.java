package inancial_control.api.domain.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateTransactionDTO(
        Long id,
        String description,
        BigDecimal amount,
        Status status,
        MonthTransaction monthTransaction,
        TransactionOperation transactionOperation
) {
}
