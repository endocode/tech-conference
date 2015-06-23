package phone.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableHystrix
@EnableHystrixDashboard
public class PhoneServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneServiceApplication.class, args);
    }
}
