package phone.service.domain;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import phone.service.domain.model.NormalizedPhone;
import phone.service.domain.model.PhoneNumber;

/**
 * Created by kangell on 6/23/2015.
 */
public class PhoneNormalizationService {
    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    public NormalizedPhone getNormalizedPhone(PhoneNumber phoneNumber) {
        try {
            final Phonenumber.PhoneNumber number =
                    phoneNumberUtil.parseAndKeepRawInput(
                            phoneNumber.getPhoneNumber(),
                            phoneNumber.getRegionCode());

            if (!phoneNumberUtil.isValidNumberForRegion(number, phoneNumber.getRegionCode())) {
                throw new IllegalArgumentException("The phone number is invalid.");
            }

            return new NormalizedPhone.Builder()
                    .setCountryPrefix(number.getCountryCode())
                    .setOriginalNumber(number.getRawInput())
                    .setPhoneNumber(Long.toString(number.getNationalNumber()))
                    .setFormattedPhoneNumber(phoneNumberUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL))
                    .createNormalizedPhone();
        } catch (NumberParseException e) {
            throw new IllegalArgumentException("The phone number cannot be parsed.");
        }
    }
}
