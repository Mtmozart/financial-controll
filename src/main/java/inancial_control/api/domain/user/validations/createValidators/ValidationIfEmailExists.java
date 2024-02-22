package inancial_control.api.domain.user.validations.createValidators;

import inancial_control.api.domain.user.CreateUserDTO;
import inancial_control.api.domain.user.UpdateUserDTO;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidationIfEmailAlreadyExistsInCreate.")
public class ValidationIfEmailExists implements IValidatorUserCreate {
    @Autowired
    private UserRepository repository;

    @Override
    public void validator(CreateUserDTO data) {
        var userExists = repository.findByEmailContainingIgnoreCase(data.email());
        if (userExists.isPresent()) {
            throw new ValidacaoException("E-mail já cadastrado.");
        }
    }
}
