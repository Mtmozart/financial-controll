package inancial_control.api.service;

import inancial_control.api.domain.transaction.*;
import inancial_control.api.domain.user.DetailsUserDTO;
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
        var user = userRepository.findById(data.idUser());
        if (data.idUser() != null && !user.isPresent()) {
            throw new ValidacaoException("Usuário não encontrado.");
        }

        var transaction = new Transaction(data, user.get());
        repository.save(transaction);
        return new DetailsTransactionDTO(transaction);
    }


    @Transactional
    public DetailsTransactionDTO details(Long id) {
        var transaction = repository.findById(id);
        if (!transaction.isPresent()) {
            throw new ValidacaoException("Transação não encontrada.");
        }
        return new DetailsTransactionDTO(transaction.get());
    }

    @Transactional
    public DetailsTransactionUpdateDTO update(Long id, UpdateTransactionDTO data) {
        var user = userRepository.getReferenceById(data.userId());
        if (user == null) {
            throw new ValidacaoException("Usuário não encontrado.");
        }
        var transaction = repository.getReferenceById(id);
        if (transaction == null) {
            throw new ValidacaoException("Transação não encontrada.");
        }
        if (user.getId() != transaction.getUser().getId()) {
            throw new ValidacaoException("Usuário inválido.");
        }
        transaction.update(data);
        return new DetailsTransactionUpdateDTO(id, transaction);

    }

    @Transactional
    public String delete(Long id) {
        var transaction = repository.findById(id);
        if (!transaction.isPresent()) {
            throw new ValidacaoException("Transação não encontrada.");
        }
        var user = userRepository.findById(transaction.get().getUser().getId());
        if (!user.isPresent()) {
            throw new ValidacaoException("Usuário não encontrado.");
        }
        transaction.get().removeTransaction();
        user.get().removeTransaction(transaction.get());
        repository.deleteById(transaction.get().getId());
        return "Transação removido com suceso.";
    }

    public List<DetailsTransactionDTO> allTransactionsForUser(Long id) {
        List<DetailsTransactionDTO> transactionDTOS = new ArrayList<>();
        var transactions = repository.findAllByUserId(id);
        transactions.forEach(t ->  transactionDTOS.add( new DetailsTransactionDTO(t))
        );
        return transactionDTOS;
    }

    public List<DetailsTransactionDTO> userTransactionsByMonth(Long id, MonthTransaction month){
        var user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new ValidacaoException("Usuário não encontrado.");
        }
        var transactions = repository.findTransactionByEmailByUserId(month, user.get().getId());

        if(transactions.isEmpty()){
            throw new ValidacaoException("Nenhuma transação encotrada para o mês.");
        }
        return  transactions.stream()
                .map(t -> new DetailsTransactionDTO(t))
                .collect(Collectors.toList());
    }
}
