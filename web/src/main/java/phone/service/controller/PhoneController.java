package phone.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phone.service.domain.PhoneNormalizationService;
import phone.service.domain.model.PhoneNumber;
import phone.service.model.NormalizedPhone;

@RestController
@RequestMapping("/api")
public class PhoneController {

    @Autowired
    private PhoneNormalizationService phoneNormalizationService;

    @RequestMapping("/phones/{phoneNumber}")
    public NormalizedPhone getNormalizedPhone(@PathVariable("phoneNumber") String phoneNumber) {

        final PhoneNumber domainPhone = new PhoneNumber.Builder()
                .setPhoneNumber(phoneNumber)
                .createPhoneNumber();
        final phone.service.domain.model.NormalizedPhone normalizedPhone =
                phoneNormalizationService.getNormalizedPhone(domainPhone);

        return new NormalizedPhone.Builder()
                .setRegionCode(domainPhone.getRegionCode())
                .setPhoneNumber(normalizedPhone.getOriginalNumber())
                .setCountryPrefix(normalizedPhone.getCountryPrefix().toString())
                .setFormattedPhoneNumber(normalizedPhone.getFormattedPhoneNumber())
                .setPhoneNumber(normalizedPhone.getPhoneNumber())
                .createNormalizedPhone();
    }
}
