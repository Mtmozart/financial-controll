package inancial_control.api.repository;

import inancial_control.api.domain.transaction.MonthTransaction;
import inancial_control.api.domain.transaction.Transaction;
import inancial_control.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    List<Transaction>  findAllByUserId(Long id);


    List<Transaction> findAllByMonthTransactionAndUserId(MonthTransaction month, Long userId);
}
