package phone.service.domain.model;

public class User {
    private final String firstName;
    private final String lastName;
    private final NormalizedPhone phone;

    private User(String firstName, String lastName, NormalizedPhone phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public NormalizedPhone getPhone() {
        return phone;
    }

    public static class Builder {
        private String firstName = null;
        private String lastName = null;
        private NormalizedPhone phone = null;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPhone(NormalizedPhone phone) {
            this.phone = phone;
            return this;
        }

        public User createUser() {
            return new User(firstName, lastName, phone);
        }
    }
}
