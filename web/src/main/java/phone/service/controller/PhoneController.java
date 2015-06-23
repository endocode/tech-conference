package phone.service.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PhoneController {
    @RequestMapping("/phones/{phoneNumber}")
    public String getNormalizedPhone(@PathVariable("phoneNumber") String phoneNumber) {
        return phoneNumber;
    }
}
