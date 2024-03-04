package inancial_control.api.service;

import inancial_control.api.domain.transaction.*;
import inancial_control.api.domain.transaction.validations.create.IValidationsCreate;
import inancial_control.api.domain.transaction.validations.create.globaValidations.IValidationsGeneral;
import inancial_control.api.domain.user.User;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.TransactionsRepository;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TransactionService {

    @Autowired
    TransactionsRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private List<IValidationsGeneral> validationsGenerals;

    @Transactional
    public DetailsTransactionDTO create(CreateTransactionDTO data) {
        var user = verifyUser(data.idUser());
        validationsGenerals.forEach(v -> v.validator(data));
        var transaction = new Transaction(data, user);
        repository.save(transaction);
        return new DetailsTransactionDTO(transaction);
    }
    @Transactional
    public DetailsTransactionDTO details(Long id) {
        var transaction = verifyTransaction(id);

        return new DetailsTransactionDTO(transaction);
    }

    @Transactional
    public DetailsTransactionUpdateDTO update(Long id, UpdateTransactionDTO data) {
        var user = verifyUser(data.userId());
        validationsGenerals.forEach(v -> v.validator(data));
        var transaction = verifyTransaction(id);
        if (user.getId() != transaction.getUser().getId()) {
            throw new ValidacaoException("Usuário inválido.");
        }
        transaction.update(data);
        return new DetailsTransactionUpdateDTO(id, transaction);
    }

    @Transactional
    public String delete(Long id) {
        var transaction = verifyTransaction(id);
        var user = verifyUser(transaction.getUser().getId());
        transaction.removeTransaction();
        user.removeTransaction(transaction);
        repository.deleteById(transaction.getId());
        return "Transação removido com suceso.";
    }

    public List<DetailsTransactionDTO> allTransactionsForUser(Long id) {

        var transactions = repository.findAllByUserId(id);
        return convertData(transactions);
    }

    public List<DetailsTransactionDTO> userTransactionsByMonth(Long id, MonthTransaction month) {
        var user = verifyUser(id);
        var transactions = repository.findTransactionByMonthByUserId(month, user.getId());
        if (transactions.isEmpty()) {
            throw new ValidacaoException("Nenhuma transação encotrada para o mês.");
        }
        return convertData(transactions);
    }

    public List<DetailsTransactionDTO> userTransactionsOperationByMonthByUser(Long id, MonthTransaction month,
                                                                              TransactionOperation operation) {
        var user = verifyUser(id);
        var transactions = repository.userTransactionsOperationByMonthByUser(month, user.getId(), operation);
        if (transactions.isEmpty()) {
            throw new ValidacaoException("Nenhuma transação encotrada para o mês.");
        }
        return convertData(transactions);
    }


    public BigDecimal balance(Long id, MonthTransaction month) {

        var user = verifyUser(id);
        var transactionsEntries = repository.userTransactionsOperationByMonthByUser(month, user.getId(), TransactionOperation.ENTRY);
        if (transactionsEntries.isEmpty()) {
            throw new ValidacaoException("Nenhuma transação encotrada para o mês.");
        }
        var totalTransactionEntry = transactionsEntries.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var transactionsExits = repository.userTransactionsOperationByMonthByUser(month, user.getId(), TransactionOperation.EXIT);
        if (transactionsExits.isEmpty()) {
            throw new ValidacaoException("Nenhuma transação encotrada para o mês.");
        }
        var totalTransactionsExits = transactionsExits.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var balance = totalTransactionEntry.subtract(totalTransactionsExits);
        return balance;
    }

    public List<DetailsTransactionDTO> allTransactionStutusByMonthByUser(Long id, MonthTransaction month,
                                                                         TransactionOperation operation, Status status) {
        var user = verifyUser(id);
        var transactions = repository.userTransactionsStatusAndOperationByMonthByUser(month, user.getId(), operation, status);
        if (transactions.isEmpty()) {
            throw new ValidacaoException("Nenhuma transação encotrada para o mês.");
        }
        return convertData(transactions);
    }

    public BigDecimal balancePaidAndUnpaidByMonthAndUser(Long id, MonthTransaction month) {
        var user = verifyUser(id);
        var paidTransactionsEntry = repository.userTransactionsStatusAndOperationByMonthByUser(month,
                user.getId(), TransactionOperation.ENTRY,
                Status.PAID);
        var TotalPaidTransactionsEntry = paidTransactionsEntry.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var unpaidTransactionsEntry = repository.userTransactionsStatusAndOperationByMonthByUser(month,
                user.getId(), TransactionOperation.EXIT,
                Status.PAID);

        var TotalUnpaidTransactionsEntry = unpaidTransactionsEntry.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return TotalPaidTransactionsEntry.subtract(TotalUnpaidTransactionsEntry);
    }


    /* method to simplify the code*/
    private User verifyUser(Long id) {
        var user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new ValidacaoException("Usuário não encontrado.");
        }
        return user.get();
    }

    private Transaction verifyTransaction(Long id) {
        var transaction = repository.findById(id);
        if (!transaction.isPresent()) {
            throw new ValidacaoException("Transação não encontrada.");
        }
        return transaction.get();
    }

    private List<DetailsTransactionDTO> convertData(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> new DetailsTransactionDTO(t))
                .collect(Collectors.toList());
    }



}
