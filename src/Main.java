public class Main {

    //Run from this class to start the program
    public static void main(String[] args) {
        TopLevelInterface software = new TopLevelInterface();
        try {
            MemberHandler.loadFromDatabase();
        }
        catch (Exception e) {
            System.out.println("Filen er tom, du skal tilføje et medlem");
        }
        InputHandler.clearScreen();
        software.start();
    }
}