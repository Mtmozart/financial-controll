package inancial_control.api.domain.user.validations;

import inancial_control.api.domain.user.UpdateUserDTO;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidationIfEmailAlreadyE   xists.")
public class ValidationIfEmailExists implements IValidatorUser{
    @Autowired
   private UserRepository repository;
    @Override
    public void validator(UpdateUserDTO data) {
    var userExists  = repository.existsByEmail(data.email());
    if(userExists){
        throw new ValidacaoException("E-mail já cadastrado.");
    }
    }
}
