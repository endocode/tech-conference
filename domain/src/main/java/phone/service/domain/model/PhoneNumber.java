package phone.service.domain.model;

/**
 * Created by kangell on 6/16/2015.
 */
public final class PhoneNumber {
    private final String regionCode;
    private final String phoneNumber;

    private PhoneNumber(String regionCode, String phoneNumber) {
        this.regionCode = regionCode;
        this.phoneNumber = phoneNumber;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class Builder {
        private String regionCode = "US";
        private String phoneNumber;

        public Builder setRegionCode(String regionCode) {
            this.regionCode = regionCode;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PhoneNumber createPhoneNumber() {
            return new PhoneNumber(regionCode, phoneNumber);
        }
    }
}
