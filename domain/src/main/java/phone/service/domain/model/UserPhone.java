package phone.service.domain.model;

public final class UserPhone {
    private final String firstName;
    private final String lastName;
    private final String userPhoneNumber;

    private UserPhone(String firstName, String lastName, String userPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static class Builder {
        private String firstName = null;
        private String lastName = null;
        private String userPhoneNumber = null;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setUserPhoneNumber(String userPhoneNumber) {
            this.userPhoneNumber = userPhoneNumber;
            return this;
        }

        public UserPhone createUserPhone() {
            return new UserPhone(firstName, lastName, userPhoneNumber);
        }
    }
}
