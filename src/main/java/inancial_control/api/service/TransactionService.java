package inancial_control.api.service;

import inancial_control.api.domain.transaction.*;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.TransactionsRepository;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public DetailsTransactionUpdateDTO update(Long id, UpdateTransactionDTO data){
        var user = userRepository.getReferenceById(data.userId());
        if (user == null) {
            throw new ValidacaoException("Usuário não encontrado.");
        }
        var transaction = repository.getReferenceById(id);
        if (transaction == null) {
            throw new ValidacaoException("Transação não encontrada.");
        }
        if(user.getId() != transaction.getUser().getId()){
            throw new ValidacaoException("Usuário inválido.");
        }
        transaction.update(data);
        return new DetailsTransactionUpdateDTO(id, transaction);

    }

    @Transactional
    public String delete(Long id) {
      var transaction = repository.getReferenceById(id);
      var user = userRepository.getReferenceById(transaction.getUser().getId());
      transaction.removeTransaction();
      repository.deleteById(transaction.getId());
      user.removeTransaction(transaction);
      return "Transação removido com suceso.";
    }
}
