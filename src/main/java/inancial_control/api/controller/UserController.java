package inancial_control.api.controller;

import inancial_control.api.repository.UserRepository;
import inancial_control.api.user.CreateUserDTO;
import inancial_control.api.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public void createUser(@RequestBody CreateUserDTO datas){
            repository.save(new User(datas));
    }
}
