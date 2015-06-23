package phone.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import phone.service.domain.PhoneNormalizationService;

@Configuration
public class BeanConfig {
    @Bean
    public PhoneNormalizationService phoneNormalizationService() {
        return new PhoneNormalizationService();
    }
}
