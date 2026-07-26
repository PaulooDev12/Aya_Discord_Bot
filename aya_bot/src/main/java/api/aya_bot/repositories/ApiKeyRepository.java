package api.aya_bot.repositories;

import api.aya_bot.models.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {
    Optional<ApiKey> findByApiKeyStringAndActiveTrue(String apiKeyString);
}
