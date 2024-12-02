import java.util.Scanner;

public class UserInterface {
    public static int inputInt = -1;
    public static String inputString ="";
    public  Member currentMember;
    public static boolean running= true;






    public void start_interface_User(){
        while (running) {
            loginUser();
            System.out.println("Velkommen " + currentMember.getName());
            System.out.println("Tast 1 for at registrer tid for medlem: ");
            System.out.println("Tast 2 for at tilføj reslutater: ");
            System.out.println("Tast 3 for at se alle reslutater: ");
            if (currentMember instanceof Member &&! (currentMember instanceof CompetitionMember)){
                System.out.println("Tast 4 for at gå tilbage");
            }
            if (currentMember instanceof CompetitionMember){
                System.out.println("Tast 4 for at tilføje et stævne.");
                System.out.println("Tast 5 for at se dine stævner.");
                System.out.println("Tast 6 for at gå tilbage.");
            }
            inputInt = InputHandler.inputInt("Vælg et af følgende valg: ");

            if (currentMember instanceof Member &&! (currentMember instanceof CompetitionMember)){
                regularMenu();
            }
            if (currentMember instanceof CompetitionMember){
                compMenu();
            }

        }
    }

    public void regularMenu(){
        switch (inputInt) {
            case 1:
                //create Member
                addTimeToMember();
                break;
            case 2:
                //Add Competition to Member  object-
                addTimeToMember();
            case 3:
                //View swimming results
                viewTimesForMember();
            case 4:
                //show competitions for member
                back();
                break;
        }
    }

    public void compMenu(){
        switch (inputInt) {
            case 1:
                //create Member
                addTimeToMember();
                break;
            case 2:
                //Add Competition to Member  object-
                addTimeToMember();
            case 3:
                //View swimming results
                viewTimesForMember();
            case 4:
                //show competitions for member
                viewCompetitionForMember();

            case 5:
                //addCompetition()
                break;
            case 6:
                //viewCompetitions()
                break;
            case 7:
                back();
                break;
        }
    }

    public static void setRunning(boolean running) {
        UserInterface.running = running;
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
