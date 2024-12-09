import java.util.Objects;

public class TopLevelInterface {
    public static final AdminInterface startAdminInterface= new AdminInterface();
    public static final UserInterface startUserInterface= new UserInterface();

    // Admin password login
    private static final String ADMIN_PASSWORD = "melon";

    //Start menu
    public void start(){
        boolean running = true;

        while (running) {
            System.out.println("\uD83D\uDC2CVelkommen til systemet!\uD83D\uDC2C");
            System.out.println("1. Login som Admin \uD83D\uDC68\u200D\uD83D\uDCBB");
            System.out.println("2. Login som Bruger \uD83D\uDC64");
            System.out.println("3. Afslut program \uD83D\uDED1");
            System.out.print("Vælg en mulighed: ");


            int choice;
            choice = InputHandler.inputInt("Vælg et af følgende valg: ");
            switch (choice) {
                case 1:
                    try {
                        adminLogin();

                    }catch (NullPointerException e){
                        System.out.println("NullpointerException");
                    }
                    break;
                case 2:
                    try {
                        userLogin();

                    }catch (NullPointerException e){
                        System.out.println("NullpointerException");
                    }
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
    }


    // Method for admin-login
    private static void adminLogin() {
        AdminInterface.setRunning(true);
        System.out.print("Indtast admin password: ");

        String password;
        password=null;
        password = InputHandler.inputString("Skriv adgangskode: ");
        if (Objects.equals(password, ADMIN_PASSWORD)) {
            System.out.println("Velkommen, Admin!");
            startAdminInterface.start_interface_Admin();
            // If PW is wrong
        } else {
            System.out.println("Forkert password. Adgang nægtet.");
        }
    }

    // Method for User-login
    private static void userLogin() {
        UserInterface.setRunning(true);
       startUserInterface.start_interface_User();
    }
}
