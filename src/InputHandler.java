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
        InputHandler.clearScreen();
        return result;
    }

    public static void clearScreen(){
        for (int i = 0; i<50; i++){
            System.out.println("");
        }
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
        InputHandler.clearScreen();
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
        InputHandler.clearScreen();
        return input;
    }

    public static LocalDate inputDate(String prompt) {
        System.out.println("Vælg dato input:");
        System.out.println("1. Vælg dags dato");
        System.out.println("2. Indtast en specifik dato");

        LocalDate result;

        while (true) {
            int choice = inputInt("Indtast dit valg (1 eller 2):");

            switch (choice) {
                case 1:
                    result = LocalDate.now();
                    InputHandler.clearScreen();
                    return result;

                case 2:
                    while (true) {
                        System.out.println(prompt + " (YYYY-MM-DD):");
                        try {
                            String input = scanner.nextLine();
                            result = LocalDate.parse(input);
                            InputHandler.clearScreen();
                            return result;
                        } catch (DateTimeParseException e) {
                            System.out.println("Ugyldigt datoformat. Forventet format er YYYY-MM-DD.");
                        }
                    }

                default:
                    System.out.println("Ugyldigt valg. Vælg venligst 1 eller 2.");
            }
        }
    }

    public static LocalDate inputDateSpecific (String prompt) {
        LocalDate result;

        while (true) {
            System.out.println(prompt + " (YYYY-MM-DD):");
            try {
                String input = scanner.nextLine();
                result = LocalDate.parse(input);
                clearScreen();
                return result;
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldigt datoformat. Forventet format er YYYY-MM-DD.");
            }
        }
    }
}
