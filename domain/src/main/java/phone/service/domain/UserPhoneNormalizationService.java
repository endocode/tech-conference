package phone.service.domain;

import phone.service.dal.UserService;
import phone.service.domain.model.NormalizedPhone;
import phone.service.domain.model.PhoneNumber;
import phone.service.domain.model.User;
import phone.service.domain.model.UserPhone;
import rx.Observable;

public class UserPhoneNormalizationService {
    private final UserService userPhoneService;
    private final PhoneNormalizationService phoneNormalizationService;

    public UserPhoneNormalizationService(UserService userService, PhoneNormalizationService phoneNormalizationService) {
        this.userPhoneService = userService;
        this.phoneNormalizationService = phoneNormalizationService;
    }

    public Observable<NormalizedPhone> getNormalizedPhone(final String userId) {
        return userPhoneService.getUser(userId)
                .map(this::getUserPhone)
                .map(this::getNormalizedPhone);
    }

    public Observable<User> getNormalizedUser(final String userId) {
        return userPhoneService.getUser(userId)
                .map(this::getUserPhone)
                .map(this::getNormalizedUser);
    }

    private User getNormalizedUser(final UserPhone userPhone) {
        return new User.Builder()
                .setFirstName(userPhone.getFirstName())
                .setLastName(userPhone.getLastName())
                .setPhone(getNormalizedPhone(userPhone))
                .createUser();
    }

    private UserPhone getUserPhone(final phone.service.dal.model.User user) {
        return new UserPhone.Builder()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setUserPhoneNumber(user.getPhoneNumber())
                .createUserPhone();
    }

    private NormalizedPhone getNormalizedPhone(UserPhone p) {
        return phoneNormalizationService.getNormalizedPhone(
                new PhoneNumber.Builder()
                        .setRegionCode("US")
                        .setPhoneNumber(p.getUserPhoneNumber())
                        .createPhoneNumber());
    }
}
