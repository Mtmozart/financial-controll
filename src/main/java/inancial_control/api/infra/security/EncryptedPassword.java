package inancial_control.api.infra.security;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptedPassword {
    public String encodePassword(String password) {
        String salt = BCrypt.gensalt(10);
        String hashPassword = BCrypt.hashpw(password, salt);
        return hashPassword;
    }


}
