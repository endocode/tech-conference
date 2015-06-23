package phone.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import phone.service.domain.PhoneNormalizationService;
import phone.service.domain.UserPhoneNormalizationService;
import phone.service.domain.model.PhoneNumber;
import phone.service.model.NormalizedPhone;
import phone.service.model.NormalizedUser;

@RestController
@RequestMapping("/api")
public class PhoneController {

    @Autowired
    private PhoneNormalizationService phoneNormalizationService;

    @Autowired
    private UserPhoneNormalizationService userPhoneNormalizationService;

    @RequestMapping(value = "/phones/{phoneNumber}", method = RequestMethod.GET)
    public NormalizedPhone getNormalizedPhone(@PathVariable("phoneNumber") String phoneNumber) {

        final PhoneNumber domainPhone = new PhoneNumber.Builder()
                .setPhoneNumber(phoneNumber)
                .createPhoneNumber();
        final phone.service.domain.model.NormalizedPhone normalizedPhone =
                phoneNormalizationService.getNormalizedPhone(domainPhone);

        return mapNormalizedPhone(normalizedPhone);
    }

    /**
     * Like async/await pattern in .NET
     * @param userId The Long value of the user Id.
     * @return The deferred result.
     */
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public DeferredResult<NormalizedUser> getUser(@PathVariable("userId") Long userId) {
        final DeferredResult<NormalizedUser> result = new DeferredResult<>();
        // The Observable behaves like an IEnumerator but subscribes to Next instead of polling for Next.
        userPhoneNormalizationService.getNormalizedUser(userId.toString())
                // .map is like the .Select linq extension method for an enumerable.
                .map(this::mapDomainUser)
                // We need to subscribe for anything to happen.
                // The results are set asynchronously when they are available.
                .subscribe(result::setResult, result::setErrorResult);
        return result;
    }

    @RequestMapping(value = "/users/{userId}/phone", method = RequestMethod.GET)
    public DeferredResult<NormalizedPhone> getUserPhone(@PathVariable("userId") Long userId){
        final DeferredResult<NormalizedPhone> result = new DeferredResult<>();
        userPhoneNormalizationService.getNormalizedPhone(userId.toString())
                .map(this::mapNormalizedPhone)
                .subscribe(result::setResult, result::setErrorResult);
        return result;
    }

    private NormalizedUser mapDomainUser(phone.service.domain.model.User u) {
        return new NormalizedUser.Builder()
                .setFirstName(u.getFirstName())
                .setLastName(u.getLastName())
                .setFormattedPhoneNumber(u.getPhone().getFormattedPhoneNumber())
                .createUser();
    }

    private NormalizedPhone mapNormalizedPhone(phone.service.domain.model.NormalizedPhone p) {
        return new NormalizedPhone.Builder()
                .setRegionCode("US")
                .setCountryPrefix(p.getCountryPrefix().toString())
                .setPhoneNumber(p.getPhoneNumber())
                .setOriginalNumber(p.getOriginalNumber())
                .setFormattedPhoneNumber(p.getFormattedPhoneNumber())
                .createNormalizedPhone();
    }
}
