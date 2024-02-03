import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        // write your code here
        String regex = "password:? *[a-zA-Z0-9]+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        int regexMatchedCount = 0;
        while (true) {
            if (matcher.find()) {
                regexMatchedCount++;
                String text2 = matcher.group();
                String regex2 = "password:?( *)";
                Pattern pattern2 = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE);
                Matcher matcher2 = pattern2.matcher(text2);
                if (matcher2.find()) {
                    int index = matcher2.end();
                    String result = text2.substring(index);
                    System.out.println(result);
                }
            } else if (regexMatchedCount == 0){
                System.out.println("No passwords found.");
                return;
            } else {
                return;
            }
        }
    }
}