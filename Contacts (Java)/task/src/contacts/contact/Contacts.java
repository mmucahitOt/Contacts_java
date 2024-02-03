package contacts.contact;

import contacts.contact.contact.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contacts implements Serializable {
    ArrayList<Contact> contacts = new ArrayList<>();

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }
    public void listContacts() {
        System.out.println(this);
    }
    public Contact getContactByRecord(int record) {
        return contacts.get(record - 1);
    }

    public int getNumberOfContacts() {
        return contacts.size();
    }

    public boolean removeContact(int record) {
        try {
            Contact contact = contacts.remove(record - 1);
            return true;
        } catch (IndexOutOfBoundsException error) {
            System.out.println("out of range");
        }
        return false;
    }

    public ArrayList<Contact> searchForContacts(String word) {
        Pattern pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        for (Contact contact: this.contacts) {
            String contactInformation = contact.getContactInformation();
            Matcher matcher = pattern.matcher(contactInformation);
            if (matcher.find()) {
                contacts.add(contact);
            }
        }

        return contacts;
    }
    public boolean isEmpty() {
        return this.contacts.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i<this.contacts.size() + 1; i++) {
            builder.append(i).append(". ").append(this.contacts.get(i - 1).toString());
            if (i != this.contacts.size()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
