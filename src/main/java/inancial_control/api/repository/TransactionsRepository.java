package inancial_control.api.repository;

import inancial_control.api.domain.transaction.MonthTransaction;
import inancial_control.api.domain.transaction.Transaction;
import inancial_control.api.domain.transaction.TransactionOperation;
import inancial_control.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUserId(Long id);

    @Query("SELECT t FROM Transaction t WHERE t.monthTransaction = :month AND t.user.id = :userId")
    List<Transaction> findTransactionByMonthByUserId(MonthTransaction month, Long userId);

    @Query("SELECT t FROM Transaction t WHERE t.monthTransaction = :month AND t.user.id = :userId AND t.type = 'ENTRY'")
    List<Transaction> userTransactionsEntriesByMonthByUser(MonthTransaction month, Long userId);

}
