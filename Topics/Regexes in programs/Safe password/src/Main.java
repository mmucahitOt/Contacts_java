import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String lowercaseRegex = ".*[a-z]+.*";
        String uppercaseRegex = ".*[A-Z]+.*";
        String digitRegex = ".*[0-9]+.*";
        String symbolCountRegex = ".{12,}";

        String password = scanner.nextLine();

        if (password.matches(lowercaseRegex) && password.matches(uppercaseRegex) && password.matches(symbolCountRegex) &&  password.matches(digitRegex)) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }
}