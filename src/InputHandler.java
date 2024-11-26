import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class InputHandler {

    private static final Scanner scanner = new Scanner(System.in);

    public static int inputInt(String prompt) {
        int result;
        while (true) {
            System.out.println(prompt);
            try {
                result = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Ugyldigt input: Der forventes et heltal.");
            }
        }
        return result;
    }

    public static double inputDouble(String prompt) {
        double result;
        while (true) {
            System.out.println(prompt);
            try {
                result = scanner.nextDouble();
                scanner.nextLine();
                break;
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Ugyldigt input: Der forventes et kommatal.");
            }
        }
        return result;
    }

    public static String inputString(String prompt) {
        String input;
        while (true) {
            System.out.println(prompt);
            input = scanner.nextLine();
            if (input.isBlank()) {
                System.out.println("Input kan ikke være tomt.");
            } else {
                break;
            }
        }
        return input;
    }

    public static LocalDate inputDate(String prompt) {
        LocalDate result;
        while (true) {
            System.out.println(prompt + " (YYYY-MM-DD):");
            try {
                String input = scanner.nextLine();
                result = LocalDate.parse(input);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldigt datoformat. Forventet format er YYYY-MM-DD.");
            }
        }
        return result;
    }
}
