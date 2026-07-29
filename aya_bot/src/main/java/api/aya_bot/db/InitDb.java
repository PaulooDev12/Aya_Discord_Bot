package api.aya_bot.db;

import api.aya_bot.models.ApiKey;
import api.aya_bot.repositories.ApiKeyRepository;
import api.aya_bot.service.HashUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Order(1)
public class InitDb implements CommandLineRunner {
    private final ApiKeyRepository apiKeyRepository;
    static final Logger LOGGER = LoggerFactory.getLogger(InitDb.class);
    public InitDb(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       if(apiKeyRepository.count()==0){
           String key = "adm_" + UUID.randomUUID().toString().replace("-", "");
           ApiKey apiKey = new ApiKey();
           String hashedKey = HashUtils.hashSHA256(key);
           apiKey.setApiKeyString(hashedKey);
           apiKey.setOwner("admin_p");
           apiKey.setActive(true);
           apiKeyRepository.save(apiKey);
           LOGGER.info("API KEY ADDED, ");
           System.out.println("Copy this key: " + key);

       }
    }
}
