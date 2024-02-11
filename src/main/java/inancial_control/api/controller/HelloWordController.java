package inancial_control.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {
    @RequestMapping("/hello")

    @GetMapping
    public String helloWord(){
        return  "Hello Word Spring";
    }

}
