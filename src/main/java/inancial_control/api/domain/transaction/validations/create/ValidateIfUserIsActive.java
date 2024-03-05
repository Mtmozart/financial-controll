package inancial_control.api.domain.transaction.validations.create;

import inancial_control.api.domain.transaction.CreateTransactionDTO;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("VerificationUserActive")
public class ValidateIfUserIsActive implements IValidationsCreate {

    @Autowired
    private UserRepository repository;
    @Override
    public void validator(CreateTransactionDTO data) {
    var user = repository.findUserIdlIifsActive(data.idUser());
    if(!user) {
        throw new ValidacaoException("Erro ao criar a transação, usuário inativo.");
    }

 }
}
