package inancial_control.api.domain.transaction.validations.update;

import inancial_control.api.domain.transaction.UpdateTransactionDTO;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("VerificationUserActiveUpdate")
public class ValidateIfUserIsActive implements IValidationsUpdate {

    @Autowired
    private UserRepository repository;
    @Override
    public void validator(UpdateTransactionDTO data) {
    var user = repository.findUserIdlIifsActive(data.userId());
    if(!user) {
        throw new ValidacaoException("Erro ao atualizar a transação, usuário inativo.");
    }

 }
}
