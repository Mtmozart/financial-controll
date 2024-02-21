package inancial_control.api.domain.user.validations.updateValidators;

import inancial_control.api.domain.user.UpdateUserDTO;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidationIfEmailAlreadyE   xists.")
public class ValidationIfEmailExists implements IValidatorUser {
    @Autowired
    private UserRepository repository;

    @Override
    public void validator(UpdateUserDTO data) {
        var userExists = repository.findByEmailContainingIgnoreCase(data.email());
        if (userExists.isPresent() && !userExists.get().getEmail().toUpperCase().equals(data.email().toUpperCase())) {
            throw new ValidacaoException("E-mail já cadastrado.");
        }
    }
}
