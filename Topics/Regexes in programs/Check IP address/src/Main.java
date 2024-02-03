import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here

        String IP = scanner.nextLine();
        String IPRegex = "^(2[0-5][0-5]|1[0-9][0-9]|[0-9][0-9]|[0-9])\\.(2[0-5][0-5]|1[0-9][0-9]|[0-9][0-9]|[0-9])\\.(2[0-5][0-5]|1[0-9][0-9]|[0-9][0-9]|[0-9])\\.(2[0-5][0-5]|1[0-9][0-9]|[0-9][0-9]|[0-9])$";

        if (IP.matches(IPRegex)) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }
}