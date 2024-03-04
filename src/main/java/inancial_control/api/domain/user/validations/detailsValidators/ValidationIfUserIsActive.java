package inancial_control.api.domain.user.validations.detailsValidators;

import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidationIfEmailAlreadyExistsInDetails.")
public class ValidationIfUserIsActive implements IValidatorUserDetails {
    @Autowired
    private UserRepository repository;
    @Override
    public void validator(Long id) {
        boolean user =  repository.findUserIdlIifsActive(id);
        if(!user){
            throw new ValidacaoException("O usuário inativo");
        }
    }
}
