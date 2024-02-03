package contacts.contact.contact;


import contacts.contact.enums.ContactType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Contact implements Serializable {

    ContactType type;
    public static String noNumber = "[no number]";
    public static String noData = "[no data]";
    protected String phoneNumber = "";

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Contact(ContactType type) {
        this.type = type;
    }
    public Contact (ContactType type, String phoneNumber) {
        this.type = type;
        this.phoneNumber = phoneNumber;
    }

    public abstract String getInfo();

    protected String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    protected void generateTimeStamp() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateTimeStamp() {
        this.updatedAt = LocalDateTime.now();
    }

    protected void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public boolean hasNumber() {
        return !this.phoneNumber.isBlank();
    }

    public boolean isPerson() {
        return this.type == ContactType.PERSON;
    }

    protected String convertDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return date.format(formatter);
    }

    public String getContactInformation() {
        return this.phoneNumber;
    }
}
