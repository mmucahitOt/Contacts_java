package contacts.contact;

import contacts.contact.contact.Contact;
import contacts.contact.enums.ContactType;

import java.io.Serializable;

public class Organization extends Contact implements Serializable {
    private static final long serialVersionUID = 8L;
    String name;
    String address;
    public Organization() {
        super(ContactType.ORGANIZATION);
        this.generateTimeStamp();
    }
    public Organization (String name, String address, String phoneNumber) {
        super(ContactType.ORGANIZATION, phoneNumber);
        this.name = name;
        this.address = address;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class OrganizationBuilder {
        private Organization organization = new Organization();

        public void addName(String name) {
            this.organization.setName(name);
        }

        public void addAddress(String surname) {
            this.organization.setAddress(surname);
        }
        public void addPhoneNumber(String phoneNumber) {
            this.organization.setPhoneNumber(phoneNumber);
        }


        public Organization build() {
            Organization newOrganization = this.organization;
            this.organization = new Organization();

            return newOrganization;
        }
    }

    public String getInfo() {
        String phoneNumber;
        if (this.phoneNumber == null || this.phoneNumber.isBlank() || this.phoneNumber.isEmpty()) {
            phoneNumber = Person.noNumber;
        } else {
            phoneNumber = this.phoneNumber;
        }
        return "Organization name: " + this.name + "\n" +
                "Address: " + this.address + "\n" +
                "Number: " + this.phoneNumber + "\n" +
                "Time created: " + this.convertDate(this.createdAt) + "\n" +
                "Time last edit: " + this.convertDate(this.updatedAt) + "\n";
    }
    @Override
    public String toString() {
        String phoneNumber;
        if (this.phoneNumber == null || this.phoneNumber.isBlank() || this.phoneNumber.isEmpty()) {
            phoneNumber = Person.noNumber;
        } else {
            phoneNumber = this.phoneNumber;
        }

        return "%s".formatted(this.name);
    }

    @Override
    public String getContactInformation() {
        return this.name + " " + this.address + " " + this.phoneNumber;
    }
}

