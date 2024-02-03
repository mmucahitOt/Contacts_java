import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        String line = scanner.nextLine();
        String regex = "((^[a-zA-Z]*%s\\W|\\W[a-zA-Z]*%s\\W|\\W[a-zA-Z]*%s$)|(^%s[a-zA-Z]*\\W|\\W%s[a-zA-Z]*\\W|\\W%s[a-zA-Z]*\\W$))".formatted(part, part, part, part, part, part);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);

        if (matcher.find()) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }
}