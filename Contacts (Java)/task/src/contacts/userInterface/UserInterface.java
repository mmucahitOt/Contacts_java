package contacts.userInterface;

import contacts.contact.Contacts;
import contacts.contact.Organization;
import contacts.contact.Person;
import contacts.contact.contact.Contact;
import contacts.contact.enums.Gender;
import contacts.contact.validation.ContactValidator;
import contacts.serialization.ContactsSerializationUtils;
import contacts.userInterface.enums.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Person.PersonBuilder personBuilder = new Person.PersonBuilder();
    Organization.OrganizationBuilder organizationBuilder = new Organization.OrganizationBuilder();
    private static final Scanner scanner = new Scanner(System.in);
    Contacts contacts;

    ContactsSerializationUtils utils;

    public void initSerialization(String filename) {
        this.utils = new ContactsSerializationUtils(filename);
    }
    public void initContacts() throws IOException, ClassNotFoundException {
        this.contacts = utils.deserializeContact();
        if (this.contacts == null) {
            this.contacts = new Contacts();
        }
    }
    public void loop() throws IOException {
        while (true) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            String commandInput = scanner.nextLine();
            Command command = Command.toCommand(commandInput);

            switch (command) {
                case ADD -> this.addContact();
                case LIST -> this.listContacts();
                case SEARCH -> this.searchMode();
                case COUNT -> this.countContacts();
                case EXIT -> this.exit();
            }
            System.out.println();
        }
    }

    private void listContacts() {
        this.contacts.listContacts();
        System.out.println();
        while (true) {
            System.out.print("[list] Enter action ([number], back): ");
            String commandInput = scanner.nextLine();
            Integer index = null;
            Contact contact = null;
            try {
                index = Integer.parseInt(commandInput);
                contact = contacts.getContactByRecord(index);
                System.out.println(contact.getInfo());
                System.out.println();
            } catch (NumberFormatException ignored) {
            }
            if (index != null) {
                boolean isListModeActive = this.contactMode(contact, index);
                if (!isListModeActive) {
                    return;
                } else {
                    continue;
                }
            }
            Command command = Command.toCommand(commandInput);

            switch (command) {
                case BACK -> {
                    return;
                }
            }
            System.out.println();
        }
    }



    private void searchMode() {
        this.searchForContact();
        System.out.println();
        while (true) {
            System.out.print("[search] Enter action ([number], back, again): ");
            String commandInput = scanner.nextLine();
            Integer index = null;
            Contact contact = null;
            try {
                index = Integer.parseInt(commandInput);
                contact = contacts.getContactByRecord(index);
                System.out.println(contact.getInfo());
                System.out.println();
            } catch (NumberFormatException ignored) {
            }
            if (index != null) {
                boolean isSearchModeActive = this.contactMode(contact, index);
                if (!isSearchModeActive) {
                    return;
                } else {
                    continue;
                }
            }
            Command command = Command.toCommand(commandInput);

            switch (command) {
                case BACK -> {
                    return;
                }
                case AGAIN -> {
                    this.searchForContact();
                    System.out.println();
                    continue;}
            }
            System.out.println();
        }
    }
    private void searchForContact() {
        //if (this.contacts.isEmpty()) {
        //    System.out.println("No records to edit!");
        //    return;
        //}
        System.out.print("Enter search query: ");
        String input = scanner.nextLine();
        ArrayList<Contact> contacts = this.contacts.searchForContacts(input);
        System.out.printf("Found %d results:\n", contacts.size());
        Contacts _contacts = new Contacts();
        _contacts.setContacts(contacts);
        _contacts.listContacts();
    }

    private boolean contactMode(Contact contact, int record) {
        while (true) {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String commandInput = scanner.nextLine();
            Command command = Command.toCommand(commandInput);

            switch (command) {
                case EDIT -> this.editContact(contact);
                case DELETE -> this.removeContact(record);

                case MENU -> {
                    return false;
                }
            }
            System.out.println();
        }
    }



    private void addContact() {
        System.out.print("Enter the type (person, organization): ");
        String contactType = scanner.nextLine();
        if (contactType.equals("person")) {
            this.addPerson();
        } else if (contactType.equals("organization")) {
            this.addOrganization();
        }
    }

    private void addPerson() {
        System.out.print("Enter the name: ");
        String nameInput = scanner.nextLine();
        if (!ContactValidator.isValidName(nameInput)) {
            return;
        }
        personBuilder.addName(nameInput);

        System.out.print("Enter the surname: ");
        String surnameInput = scanner.nextLine();
        if (!ContactValidator.isValidSurname(surnameInput)) {
            return;
        }
        personBuilder.addSurname(surnameInput);

        System.out.print("Enter the birth date: ");
        String birthDateInput = scanner.nextLine();
        if (!ContactValidator.isValidBirthDate(birthDateInput)) {
            System.out.println("Bad birth date!");
            birthDateInput = null;
        }
        personBuilder.addBirthDate(birthDateInput);

        System.out.print("Enter the gender (M, F): ");
        String genderInput = scanner.nextLine();
        Gender gender = null;
        if (!ContactValidator.isValidGender(genderInput)) {
            System.out.println("Bad gender!");
            genderInput = null;
        } else {
            gender = Gender.toGender(genderInput);
        }
        personBuilder.addGender(gender);

        System.out.print("Enter the number: ");
        String phoneNumberInput = scanner.nextLine();
        if (ContactValidator.isPhoneNumberEmpty(phoneNumberInput)) {
            phoneNumberInput = null;
        } else if(!ContactValidator.isValidPhoneNumber(phoneNumberInput)) {
            System.out.println("Wrong number format!");
            phoneNumberInput = null;
        }
        personBuilder.addPhoneNumber(phoneNumberInput);

        contacts.addContact(personBuilder.build());
        System.out.println("The record added.");
    }
    private void addOrganization() {
        System.out.print("Enter the organization name: ");
        String nameInput = scanner.nextLine();
        if (!ContactValidator.isValidName(nameInput)) {
            return;
        }
        organizationBuilder.addName(nameInput);

        System.out.print("Enter the address: ");
        String addressInput = scanner.nextLine();
        organizationBuilder.addAddress(addressInput);

        System.out.print("Enter the number: ");
        String phoneNumberInput = scanner.nextLine();
        if (ContactValidator.isPhoneNumberEmpty(phoneNumberInput)) {
            phoneNumberInput = null;
        } else if(!ContactValidator.isValidPhoneNumber(phoneNumberInput)) {
            System.out.println("Wrong number format!");
            phoneNumberInput = null;
        }
        organizationBuilder.addPhoneNumber(phoneNumberInput);

        contacts.addContact(organizationBuilder.build());
        System.out.println("The record added.");
    }

    private void removeContact(int record) {
        boolean result = contacts.removeContact(record);
        if (result) {
            System.out.println("The record removed!");
        }
    }
    private void editContact(Contact contact) {
        if (contact.isPerson()) {
            this.updatePerson((Person) contact);
        } else  {
            this.updateOrganization((Organization) contact);
        }
        contact.updateTimeStamp();
        System.out.println("Saved");
        System.out.println(contact.getInfo());
    }

    private void updatePerson(Person person) {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = scanner.nextLine();
        switch (field) {
            case "name":
                System.out.print("Enter name: ");
                String newName = scanner.nextLine();
                person.setName(newName);
                break;
            case "surname":
                System.out.print("Enter surname: ");
                String newSurname = scanner.nextLine();
                person.setSurname(newSurname);
                break;
            case "birth":
                System.out.print("Enter birth date: ");
                String newBirthDate = scanner.nextLine();
               if(!ContactValidator.isValidPhoneNumber(newBirthDate)) {
                    System.out.println("Bad birth date!");
                    newBirthDate = null;
                }
                person.setBirthDate(newBirthDate);
                break;
            case "gender":
                System.out.print("Enter gender: ");
                String newGender = scanner.nextLine();
                Gender gender = null;
               if(!ContactValidator.isValidPhoneNumber(newGender)) {
                    System.out.println("Bad birth date!");
               } else {
                   gender = Gender.toGender(newGender);
               }
                person.setGender(gender);
                break;
            case "number":
                System.out.print("Enter number: ");
                String newPhoneNumber = scanner.nextLine();
                if (ContactValidator.isPhoneNumberEmpty(newPhoneNumber)) {
                    newPhoneNumber = null;
                } else if(!ContactValidator.isValidPhoneNumber(newPhoneNumber)) {
                    System.out.println("Wrong number format!");
                    newPhoneNumber = null;
                }
                person.setPhoneNumber(newPhoneNumber);
                break;
            default:
                throw new InputMismatchException("Edit Contact");
        }
    }

    private void updateOrganization(Organization organization) {
        System.out.print("Select a field (name, address, number): ");
        String field = scanner.nextLine();
        switch (field) {
            case "name":
                System.out.print("Enter the organization name: ");
                String newName = scanner.nextLine();
                organization.setName(newName);
                break;
            case "address":
                System.out.print("Enter address: ");
                String newAddress = scanner.nextLine();
                organization.setAddress(newAddress);
                break;
            case "number":
                System.out.print("Enter number: ");
                String newPhoneNumber = scanner.nextLine();
                if (ContactValidator.isPhoneNumberEmpty(newPhoneNumber)) {
                    newPhoneNumber = null;
                } else if(!ContactValidator.isValidPhoneNumber(newPhoneNumber)) {
                    System.out.println("Wrong number format!");
                    newPhoneNumber = null;
                }
                organization.setPhoneNumber(newPhoneNumber);
                break;
            default:
                throw new InputMismatchException("Edit Contact");
        }
    }
    private void infoContact() {
        if (contacts.isEmpty()) {
            System.out.println("No records to view!");
            return;
        }
        contacts.listContacts();
        System.out.print("Enter index to show info: ");
        int record = Integer.parseInt(scanner.nextLine());
        Contact contact = contacts.getContactByRecord(record);

        if (contact.isPerson()) {
            Person person = (Person) contact;
            System.out.print(person.getInfo());
        }
        if (!contact.isPerson()) {
            Organization organization = (Organization) contact;
            System.out.print(organization.getInfo());
        }
    }

    private void countContacts() {
        int count = contacts.getNumberOfContacts();
        System.out.printf("The Phone Book has %d records.\n", count);
    }
    private void exit() throws IOException {
        utils.serializeContact(this.contacts);
        System.exit(0);
    }

}
