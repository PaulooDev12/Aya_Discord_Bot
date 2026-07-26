package api.aya_bot.filter_config;

import api.aya_bot.Filter.DiscordBotFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    private final DiscordBotFilter discordBotFilter;

    public FilterConfig(DiscordBotFilter discordBotFilter) {
        this.discordBotFilter = discordBotFilter;
    }
    @Bean
    public FilterRegistrationBean<DiscordBotFilter> filterRegistrationBean() {
        FilterRegistrationBean<DiscordBotFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(discordBotFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

}
