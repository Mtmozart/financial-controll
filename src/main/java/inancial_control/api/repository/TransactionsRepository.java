package inancial_control.api.repository;

import inancial_control.api.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    List<Transaction>  findAllByUserId(Long id);
}
