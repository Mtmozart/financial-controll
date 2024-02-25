package inancial_control.api.service;

import inancial_control.api.domain.transaction.*;
import inancial_control.api.domain.user.DetailsUserDTO;
import inancial_control.api.domain.user.User;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.TransactionsRepository;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileDescriptor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TransactionService {

    @Autowired
    TransactionsRepository repository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public DetailsTransactionDTO create(CreateTransactionDTO data) {
        var user = verifyUser(data.idUser());
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
        return converteData(transactions);
    }

    public List<DetailsTransactionDTO> userTransactionsByMonth(Long id, MonthTransaction month) {
        var user = verifyUser(id);
        var transactions = repository.findTransactionByMonthByUserId(month, user.getId());
        if (transactions.isEmpty()) {
            throw new ValidacaoException("Nenhuma transação encotrada para o mês.");
        }
        return converteData(transactions);
    }

    public List<DetailsTransactionDTO> userTransactionsEntriesByMonthByUser(Long id, MonthTransaction month) {
        var user = verifyUser(id);
        var transactions = repository.userTransactionsEntriesByMonthByUser(month, user.getId());
        if (transactions.isEmpty()) {
            throw new ValidacaoException("Nenhuma transação encotrada para o mês.");
        }
        return converteData(transactions);
    }

    public List<DetailsTransactionDTO> userTransactionsExitsByMonthByUser(Long id, MonthTransaction month) {
        var user = verifyUser(id);
        var transactions = repository.userTransactionsExitsByMonthByUser(month, user.getId());
        if (transactions.isEmpty()) {
            throw new ValidacaoException("Nenhuma transação encotrada para o mês.");
        }
        return converteData(transactions);
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

    private List<DetailsTransactionDTO> converteData(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> new DetailsTransactionDTO(t))
                .collect(Collectors.toList());
    }

}
