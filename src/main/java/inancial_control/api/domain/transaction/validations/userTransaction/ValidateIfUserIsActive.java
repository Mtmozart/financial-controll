package inancial_control.api.domain.transaction.validations.userTransaction;

import inancial_control.api.domain.transaction.UpdateTransactionDTO;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("VerificationUserActiveByUser")
public class ValidateIfUserIsActive implements IValidationsByUser {

    @Autowired
    private UserRepository repository;
    @Override
    public void validator(Long id) {
    var user = repository.findUserIdlIifsActive(id);
    if(!user) {
        throw new ValidacaoException("Erro ao buscar as transações, usuário inativo.");
    }

 }
}
