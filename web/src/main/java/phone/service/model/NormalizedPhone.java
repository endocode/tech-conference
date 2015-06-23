package phone.service.model;

/**
 * Created by kangell on 6/16/2015.
 */
public final class NormalizedPhone {
    private final String regionCode;
    private final String countryPrefix;
    private final String phoneNumber;
    private final String formattedPhoneNumber;
    private final String originalNumber;

    NormalizedPhone(String regionCode, String countryPrefix, String phoneNumber, String formattedPhoneNumber, String originalNumber) {
        this.regionCode = regionCode;
        this.countryPrefix = countryPrefix;
        this.phoneNumber = phoneNumber;
        this.formattedPhoneNumber = formattedPhoneNumber;
        this.originalNumber = originalNumber;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public String getCountryPrefix() {
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
        private String regionCode = null;
        private String countryPrefix = null;
        private String phoneNumber = null;
        private String formattedPhoneNumber = null;
        private String originalNumber = null;

        public Builder setRegionCode(String regionCode) {
            this.regionCode = regionCode;
            return this;
        }

        public Builder setCountryPrefix(String countryPrefix) {
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
            return new NormalizedPhone(regionCode, countryPrefix, phoneNumber, formattedPhoneNumber, originalNumber);
        }
    }
}
