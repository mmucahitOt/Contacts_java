import java.util.*;

class Date {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String date = scn.nextLine();
        String dateRegex = "(^[1-2][09][0-9][0-9][/\\-.](0[1-9]|1[0-2])[/\\-.](0[1-9]|[1-2][0-9]|3[0-1])$|^(0[1-9]|[1-2][0-9]|3[0-1])[/\\-.](0[1-9]|1[0-2])[/\\-.][1-2][09][0-9][0-9]$)";
        if (date.matches(dateRegex)) {
            System.out.println("Yes");
            return;
        }
        System.out.println("No");
    }
}