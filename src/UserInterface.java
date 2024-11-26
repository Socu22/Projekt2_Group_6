import java.util.Scanner;

public class UserInterface {
    public static int inputInt = -1;
    public static String inputString ="";
    public  Member currentMember;
    public static boolean running= true;
    static Scanner scanner = new Scanner(System.in);






    public void start_interface_User(){
        while (running) {
            loginUser();
            System.out.println("Velkommen " + currentMember.getName());
            System.out.println("Tast 1 for at registrer tid for medlem: ");
            System.out.println("Tast 2 for at tilføj reslutater: ");
            System.out.println("Tast 3 for at se alle reslutater: ");
            try {
                inputInt = InputHandler.inputInt("Vælg et af følgende valg: ");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }




            switch (inputInt) {
                case 1:
                    //create Member
                    addTimeToMember();
                    break;
                case 2:
                    //Add Competition to Member  object-
                    addCompetitionToMember();
                case 3:
                    //View swimming results
                    viewTimesForMember();
                case 4:
                   //show competitions for member
                    viewCompetitionForMember();

                case 5:

                    back();
                    break;
            }
            scanner.nextLine();
        }
    }

    public void loginUser() {
        try {
            currentMember= MemberHandler.smartSearch();
            System.out.println(currentMember);
        } catch (MemberNotFoundException e) {
            System.out.println(e.toString());
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTimeToMember(){
       currentMember.addTime();// logic skal ændres.

    }
    public void addCompetitionToMember(){
       // currentMember.addC

    }
    public void viewTimesForMember(){
        currentMember.viewTimes();
    }
    public void viewCompetitionForMember(){
        //MemberHandler.searchByName();
    }

    public void back(){
        running=false;

    }
}
