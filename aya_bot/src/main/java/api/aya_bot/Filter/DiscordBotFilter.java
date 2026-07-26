package api.aya_bot.Filter;

import api.aya_bot.repositories.ApiKeyRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class DiscordBotFilter extends OncePerRequestFilter {

    private final ApiKeyRepository apiKeyRepository;

    public DiscordBotFilter(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

            String apiKey = request.getHeader("aya-key");
            if (apiKey == null || apiKey.isBlank()) {
                throwError(response,"Chave inexistente ou invalida");
                return;
            }
            boolean valida = apiKeyRepository.findByApiKeyStringAndActiveTrue(apiKey).isPresent();
            if(!valida){
                throwError(response,"Unexistent key or Invalid key");
                return;
            }
            filterChain.doFilter(request, response);

    }
    public void throwError(HttpServletResponse res, String message) throws IOException {
        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().print("Error: " + message);
    }

}
