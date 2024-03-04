package inancial_control.api.domain.user.validations.updateValidators;

import inancial_control.api.domain.user.UpdateUserDTO;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidationIfUserIsActiveInUpdate.")
public class ValidationIfUserIsActive implements IValidatorUserUpdate{
    @Autowired
    private UserRepository repository;
    @Override
    public void validator(UpdateUserDTO data) {
        if(data == null){
            return;
        }
        var userActive = repository.findUserEmailIifsActive(data.email());
        System.out.println();
        if(!userActive){
            throw new ValidacaoException("O usuário inativo");
        }

    }
}
