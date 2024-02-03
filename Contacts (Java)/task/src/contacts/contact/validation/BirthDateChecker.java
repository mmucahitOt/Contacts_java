package contacts.contact.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BirthDateChecker {
    public static boolean isValid(String phoneNumber) {
        String regex = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find()) {
            return matcher.matches();
        }
        return false;
    }
}
