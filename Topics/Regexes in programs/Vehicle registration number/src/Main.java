import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regNum = scanner.nextLine(); // a valid or invalid registration number
        String registrationRegex = "^[ABEKMHOPCTYX][0-9]{3}[ABEKMHOPCTYX][ABEKMHOPCTYX]$";
        boolean isValid = regNum.matches(registrationRegex);
        System.out.println(isValid);
        /* write your code here */
    }
}