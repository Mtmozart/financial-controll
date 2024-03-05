package inancial_control.api.domain.transaction.validations.getTransaction;

import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.TransactionsRepository;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("VerificationUserActiveGetTransaction")
public class ValidateIfUserIsActive implements IValidationsGetTransaction {

    @Autowired
    private TransactionsRepository repository;
    @Override
    public void validator(Long id) {
    var user = repository.findUserIdlIifsActive(id);
    if(!user) {
        throw new ValidacaoException("Erro ao criar o transação, usuário inativo.");
    }

 }
}
