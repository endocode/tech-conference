package phone.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    /**
     * Swagger API documentation config.
     * @return Swagger Docket configuration.
     */
    @Bean
    public Docket customSwaggerConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Phone Service")
                .genericModelSubstitutes(DeferredResult.class)
                .apiInfo(new ApiInfo("Phone Service API", "Phone normalization API.", "1.0", null, null, null, null))
                .select().paths(s -> s.startsWith("/api")).build();
    }
}
