package inancial_control.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("incomes")
public class Income {

    @PostMapping
    public void register(@RequestBody String json){
        System.out.println(json);
    }
}
