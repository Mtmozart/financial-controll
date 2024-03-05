package inancial_control.api.domain.transaction.validations.getAll;

import inancial_control.api.domain.transaction.UpdateTransactionDTO;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("VerificationUserActiveGetters")
public class ValidateIfUserIsActive implements IValidationsGetters {

    @Autowired
    private UserRepository repository;
    @Override
    public void validator(Long id) {
    var user = repository.findUserIdlIifsActive(id);
    if(!user) {
        throw new ValidacaoException("Erro ao buscar a transação, usuário inativo.");
    }

 }
}
