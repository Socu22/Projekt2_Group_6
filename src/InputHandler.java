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

    public static int inputInt(String prompt) throws InvalidInputException {
        System.out.println(prompt);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            throw new InvalidInputException("Ugyldigt input: Der forventes et heltal.");
        }
    }

    public static String inputString(String prompt) throws InvalidInputException {
        System.out.println(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()) {
            throw new InvalidInputException("Input kan ikke være tomt.");
        }
        return input;
    }


    public static LocalDate inputDate(String prompt) throws InvalidInputException {
        System.out.println(prompt + " (YYYY-MM-DD):");
        try {
            String input = scanner.nextLine();
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Ugyldigt datoformat. Forventet format er YYYY-MM-DD.");
        }
    }
}
