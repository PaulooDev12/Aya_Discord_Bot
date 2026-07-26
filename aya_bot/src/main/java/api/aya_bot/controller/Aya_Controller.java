package api.aya_bot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Aya_Controller {

    @GetMapping("/status")
    public String status(){
        return "OK";
    }
}
