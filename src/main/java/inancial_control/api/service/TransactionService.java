package inancial_control.api.service;

import inancial_control.api.domain.transaction.*;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.TransactionsRepository;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class TransactionService {

    @Autowired
    TransactionsRepository repository;
    @Autowired
    UserRepository userRepository;

    public DetailsTransactionDTO create(CreateTransactionDTO data) {
        var user = userRepository.findById(data.idUser());
        if (data.idUser() != null && !user.isPresent()) {
            throw new ValidacaoException("Usuário não encontrado.");
        }

        var transaction = new Transaction(data, user.get());
        repository.save(transaction);
        return new DetailsTransactionDTO(transaction);
    }


    public DetailsTransactionDTO details(Long id) {
        var transaction = repository.findById(id);
        if (!transaction.isPresent()) {
            throw new ValidacaoException("Transação não encontrada.");
        }
        return new DetailsTransactionDTO(transaction.get());
    }

    public DetailsTransactionUpdateDTO update(Long id, UpdateTransactionDTO data){
        var user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new ValidacaoException("Usuário não encontrado.");
        }
        var transaction = repository.findById(id);
        if (!transaction.isPresent()) {
            throw new ValidacaoException("Transação não encontrada.");
        }
        if(user.get().getId() != transaction.get().getUser().getId()){
            throw new ValidacaoException("Usuário inválido.");
        }

       transaction.get().update(data);

        return new DetailsTransactionUpdateDTO(id,transaction.get() );


    }
}
