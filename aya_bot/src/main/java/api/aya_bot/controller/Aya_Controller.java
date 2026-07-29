package api.aya_bot.controller;

import api.aya_bot.models.ApiKey;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.aya_bot.repositories.ApiKeyRepository;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Aya_Controller {

    private final ApiKeyRepository apiKeyRepository;

    public Aya_Controller(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }


    @GetMapping("/status")
    public String status(){
        return "OK";
    }
    @GetMapping("/list-keys")
    public ResponseEntity<List<ApiKey>> listKeys(){
        return ResponseEntity.ok().body(apiKeyRepository.findAll());
    }
}
