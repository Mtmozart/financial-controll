package inancial_control.api.infra.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VerifyTokenAndUser {
    @Autowired
    private TokenService tokenService;

    public void verifyUserAndToken(HttpServletRequest request) {


    }

}
