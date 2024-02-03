package contacts.contact;


import contacts.contact.contact.Contact;
import contacts.contact.enums.ContactType;
import contacts.contact.enums.Gender;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Person extends Contact implements Serializable {
    private static final long serialVersionUID = 7L;
    String name;
    String surname;
    LocalDateTime birthDate;
    Gender gender;

    public Person() {
        super(ContactType.PERSON);
        this.generateTimeStamp();
    }
    public Person (String name, String surname, String phoneNumber) {
        super(ContactType.PERSON, phoneNumber);
        this.name = name;
        this.surname = surname;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setBirthDate(String birthDate) {
        if (birthDate != null) {
            this.birthDate = LocalDateTime.parse(birthDate + "T00:00:00");
        }
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public static class PersonBuilder {
        private Person person = new Person();

        public void addName(String name) {
            this.person.setName(name);
        }

        public void addSurname(String surname) {
            this.person.setSurname(surname);
        }
        public void addPhoneNumber(String phoneNumber) {
            this.person.setPhoneNumber(phoneNumber);
        }

        public void addBirthDate(String birthDate) {
            if (birthDate != null) {
                this.person.setBirthDate(birthDate);
            }
        }
        public void addGender(Gender gender) {
            this.person.setGender(gender);
        }

        public Person build() {
            Person newPerson = this.person;
            this.person = new Person();

            return newPerson;
        }
    }

    public String getInfo() {
        String gender;
        if (this.gender == null) {
            gender = Contact.noData;
        } else {
            gender = this.gender.toString();
        }
        String birthDate;
        if (this.birthDate == null) {
            birthDate = Person.noData;
        } else {
            birthDate = this.convertDate(this.birthDate);
        }
        String phoneNumber;
        if (this.phoneNumber == null || this.phoneNumber.isBlank() || this.phoneNumber.isEmpty()) {
            phoneNumber = Person.noNumber;
        } else {
            phoneNumber = this.phoneNumber;
        }
        return "Name: " + this.name + "\n" +
                "Surname: " + this.surname + "\n" +
                "Birth date: " + birthDate + "\n" +
                "Gender: " + gender + "\n" +
                "Number: " + phoneNumber + "\n" +
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

        return "%s %s".formatted(this.name, this.surname);
    }

    @Override
    public String getContactInformation() {
        String birthDay = "";
        String phoneNumber = "";
        if (this.birthDate != null) {
            birthDay = convertDate(this.birthDate);
        }
        if (this.phoneNumber != null) {
            phoneNumber = this.phoneNumber;
        }
        return this.name + " " + this.surname + " " + " " + this.gender + " " + phoneNumber + " " + birthDay;
    }
}

