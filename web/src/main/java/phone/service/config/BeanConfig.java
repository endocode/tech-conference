package phone.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.ribbon.Ribbon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import phone.service.dal.UserService;
import phone.service.dal.client.UserServiceClient;
import phone.service.domain.PhoneNormalizationService;
import phone.service.domain.UserPhoneNormalizationService;

@Configuration
public class BeanConfig {
    @Bean
    public PhoneNormalizationService phoneNormalizationService() {
        return new PhoneNormalizationService();
    }

    @Bean
    public UserPhoneNormalizationService userPhoneNormalizationService(UserService userService, PhoneNormalizationService phoneNormalizationService) {
        return new UserPhoneNormalizationService(userService, phoneNormalizationService);
    }

    @Bean
    public UserService userService(UserServiceClient userServiceClient, ObjectMapper mapper) {
        return new UserService(userServiceClient, mapper);
    }

    @Bean
    public UserServiceClient userServiceClient() {
        return Ribbon.from(UserServiceClient.class);
    }
}
