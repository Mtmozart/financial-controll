package inancial_control.api.service;

import inancial_control.api.domain.transaction.CreateTransactionDTO;
import inancial_control.api.domain.transaction.DetailsTransactionDTO;
import inancial_control.api.domain.transaction.Transaction;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.TransactionsRepository;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionsRepository repository;
    @Autowired
    UserRepository userRepository;
    public DetailsTransactionDTO create(CreateTransactionDTO data){
        if(data.idUser() != null && !userRepository.existsById(data.idUser())){
            throw new ValidacaoException("Usuário não encontrado.");
        }
        var user = userRepository.findById(data.idUser());
        var transaction = new Transaction(data.monthTransaction(), data.transactionOperation(),
           data.description(), data.amount(), data.status(), user.get());
        repository.save(transaction);
        return new DetailsTransactionDTO(transaction);
    }
}
