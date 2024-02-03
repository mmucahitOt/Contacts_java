package contacts.contact.validation;

import contacts.contact.enums.Gender;

import java.util.InputMismatchException;

public class ContactValidator {
    public static boolean isValidName(String input) {
        return true;
    }
    public static boolean isValidSurname(String input) {
        return true;
    }
    public static boolean isValidPhoneNumber(String input) {
        return PhoneNumberChecker.isValid(input);
    }

    public static boolean isValidBirthDate(String input) {
        return !input.isEmpty() && !input.isBlank() && BirthDateChecker.isValid(input);
    }
    public static boolean isValidGender(String input) {
        try {
            Gender.toGender(input);
            return true;
        }catch (InputMismatchException e) {
            return false;
        }
    }

    public static boolean isPhoneNumberEmpty(String input) {
        return input.isBlank() || input.isEmpty();
    }
}
