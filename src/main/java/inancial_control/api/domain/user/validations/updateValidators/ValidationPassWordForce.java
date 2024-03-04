package inancial_control.api.domain.user.validations.updateValidators;

import inancial_control.api.domain.user.UpdateUserDTO;
import inancial_control.api.domain.user.validations.ValidacaoException;
import inancial_control.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("ValidationPasswordInUpdate.")
public class ValidationPassWordForce implements IValidatorUserUpdate {
    @Override
    public void validator(UpdateUserDTO data) {
        if (data == null) {
            return;
        }
        var regex = "^^(?=.*[a-z])(?=.*[\\d@$!%*?&])[A-Za-z\\d@$!%*?&]{5,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data.password());
        if(!matcher.matches()){
            throw new ValidacaoException("A senha é facra.");
        }

    }
}
