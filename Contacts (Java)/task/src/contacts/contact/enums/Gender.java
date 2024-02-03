package contacts.contact.enums;

import java.util.InputMismatchException;

public enum Gender {
    MALE("M"),
    FEMALE("F");
    final String value;

    Gender(String value) {
        this.value = value;
    }
    static public Gender toGender(String value) {
        return switch (value) {
            case "M" -> MALE;
            case "F" -> FEMALE;
            default -> throw new InputMismatchException();
        };
    }

    @Override
    public String toString() {
        if (this == Gender.MALE) {
            return "M";
        }
        return "F";
    }
}
