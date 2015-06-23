package phone.service.domain.model;

/**
 * Created by kangell on 6/16/2015.
 */
public final class NormalizedPhone {
    private final Integer countryPrefix;
    private final String phoneNumber;
    private final String formattedPhoneNumber;
    private final String originalNumber;

    private NormalizedPhone(Integer countryPrefix, String phoneNumber, String formattedPhoneNumber, String originalNumber) {
        this.countryPrefix = countryPrefix;
        this.phoneNumber = phoneNumber;
        this.formattedPhoneNumber = formattedPhoneNumber;
        this.originalNumber = originalNumber;
    }

    public Integer getCountryPrefix() {
        return countryPrefix;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public String getOriginalNumber() {
        return originalNumber;
    }

    public static class Builder {
        private Integer countryPrefix = null;
        private String phoneNumber = null;
        private String formattedPhoneNumber = null;
        private String originalNumber = null;

        public Builder setCountryPrefix(Integer countryPrefix) {
            this.countryPrefix = countryPrefix;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setFormattedPhoneNumber(String formattedPhoneNumber) {
            this.formattedPhoneNumber = formattedPhoneNumber;
            return this;
        }

        public Builder setOriginalNumber(String originalNumber) {
            this.originalNumber = originalNumber;
            return this;
        }

        public NormalizedPhone createNormalizedPhone() {
            return new NormalizedPhone(countryPrefix, phoneNumber, formattedPhoneNumber, originalNumber);
        }
    }
}
