package inancial_control.api.controller;

import inancial_control.api.domain.user.DataLogin;
import inancial_control.api.domain.user.User;
import inancial_control.api.infra.security.TokenDataJWT;
import inancial_control.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {
    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataLogin data){
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = manager.authenticate(authToken);
        var tokenjtw = tokenService.gerarToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new TokenDataJWT(tokenjtw));
    }
}
