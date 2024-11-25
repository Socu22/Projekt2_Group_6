import java.util.Scanner;

public class TopLevelInterface {
    // Admin password login
    private static final String ADMIN_PASSWORD = "lol123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Velkommen til systemet!");
            System.out.println("1. Login som Admin");
            System.out.println("2. Login som Bruger");
            System.out.println("3. Afslut program");
            System.out.print("Vælg en mulighed: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clears cache (so program doesnt bug)

            switch (choice) {
                case 1:
                    adminLogin(scanner);
                    break;
                case 2:
                    userLogin(scanner);
                    break;
                case 3:
                    System.out.println("Program afsluttes. Farvel!");
                    running = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
            System.out.println(); // Extra line just for formality
        }

        scanner.close();
    }

    // Method for admin-login
    private static void adminLogin(Scanner scanner) {
        System.out.print("Indtast admin password: ");
        String password = scanner.nextLine();

        if (password.equals(ADMIN_PASSWORD)) {
            System.out.println("Velkommen, Admin!");
            // If PW is wrong
        } else {
            System.out.println("Forkert password. Adgang nægtet.");
        }
    }

    // Method for User-login
    private static void userLogin(Scanner scanner) {
        System.out.print("Indtast dit brugernavn: ");
        String username = scanner.nextLine();
        System.out.println("Velkommen, " + username + "!");
    }
}
