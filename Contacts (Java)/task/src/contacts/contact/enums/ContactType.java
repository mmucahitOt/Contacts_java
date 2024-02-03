package contacts.contact.enums;

import java.util.InputMismatchException;

public enum ContactType {
    PERSON("person"),
    ORGANIZATION("organizatio ");
    final String value;

    ContactType(String value) {
        this.value = value;
    }
}
