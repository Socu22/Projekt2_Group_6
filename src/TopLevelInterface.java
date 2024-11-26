import java.util.Scanner;

public class TopLevelInterface {
    public static final AdminInterface startAdminInterface= new AdminInterface();
    public static final UserInterface startUserInterface= new UserInterface();


    // Admin password login
    private static final String ADMIN_PASSWORD = "lol123";

    public void start(){
        boolean running = true;

        while (running) {
            System.out.println("Velkommen til systemet!");
            System.out.println("1. Login som Admin");
            System.out.println("2. Login som Bruger");
            System.out.println("3. Afslut program");
            System.out.print("Vælg en mulighed: ");

            int choice;
            try {
                choice = InputHandler.inputInt("Vælg et af følgende valg: ");
                switch (choice) {
                    case 1:
                        adminLogin();
                        break;
                    case 2:
                        userLogin();
                        break;
                    case 3:
                        System.out.println("Program afsluttes. Farvel!");
                        running = false;
                        break;
                    default:
                        System.out.println("Ugyldigt valg. Prøv igen.");
                }
            }
            catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
               System.out.println(); // Extra line just for formality
        }
    }


    // Method for admin-login
    private static void adminLogin() {
        System.out.print("Indtast admin password: ");
        String password;
        try {
            password = InputHandler.inputString("Skriv adgangskode: ");
            if (password.equals(ADMIN_PASSWORD)) {
                System.out.println("Velkommen, Admin!");
                startAdminInterface.start_interface_Admin();
                // If PW is wrong
            } else {
                System.out.println("Forkert password. Adgang nægtet.");
            }
        }
        catch (InvalidInputException e){
            System.out.println(e.getMessage());
        }
    }


    // Method for User-login
    private static void userLogin() {
       startUserInterface.start_interface_User();
    }
}
