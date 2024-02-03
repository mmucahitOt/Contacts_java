package contacts.contact.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberChecker {
    public static boolean isValid(String phoneNumber) {
        String regex = "^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find()) {
            return matcher.matches();
        }
        return false;
    }
}
