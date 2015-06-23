package phone.service.model;

public class NormalizedUser {
    private final String firstName;
    private final String lastName;
    private final String formattedPhoneNumber;

    private NormalizedUser(String firstName, String lastName, String formattedPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public static class Builder {
        private String firstName = null;
        private String lastName = null;
        private String formattedPhoneNumber = null;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setFormattedPhoneNumber(String formattedPhoneNumber) {
            this.formattedPhoneNumber = formattedPhoneNumber;
            return this;
        }

        public NormalizedUser createUser() {
            return new NormalizedUser(firstName, lastName, formattedPhoneNumber);
        }
    }
}
