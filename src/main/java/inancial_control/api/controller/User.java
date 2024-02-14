package inancial_control.api.controller;

import inancial_control.api.usuario.CreateUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class User {
    @PostMapping
    public void createUser(@RequestBody CreateUserDTO json){
        var user = json;
        System.out.println(user);
    }
}
