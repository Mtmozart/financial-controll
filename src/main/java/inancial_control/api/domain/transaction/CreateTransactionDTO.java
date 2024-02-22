package inancial_control.api.domain.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateTransactionDTO(
        @NotNull
        Long idUser,
        @NotBlank
        String description,
        @NotNull
        BigDecimal amount,
        @NotNull
        Status status,
        @NotNull
        MonthTransaction monthTransaction,
        @NotNull
        TransactionOperation transactionOperation
) {
}
