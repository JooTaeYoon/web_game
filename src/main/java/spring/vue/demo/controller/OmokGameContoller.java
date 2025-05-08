package spring.vue.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/omok")
public class OmokGameContoller {


    @GetMapping("/")
    public String test(){
        return "hi";
    }

}
