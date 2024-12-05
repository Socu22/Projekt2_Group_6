import java.util.Scanner;

public class UserInterface {
    public static int inputInt = -1;
    public static String inputString ="";
    public  Member currentMember;
    public CompetitionMember typeCastMember;
    public static boolean running= true;

    public void start_interface_User(){
        while (running) {
            loginUser();
            differentUserLogic();
        }
    }

    public void regularMenu(){
        switch (inputInt) {
            case 1:
                //add time to member
                addTimeToMember();
                break;
            case 2:
                //View swimming results
                viewTimesForMember();
                break;
            case 3:
                back();
                break;

        }
    }

    public void compMenu(){
        switch (inputInt) {
            case 1:
                //add time to member
                addTimeToMember();
                break;
            case 2:
                //View swimming results
                viewTimesForMember();
                break;
            case 3:
                //add competitionToMember
                addCompetitionToMember();
                break;

            case 4:
                //show competitions for member
                viewCompetitionForMember();
                break;

            case 5:
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
            if (currentMember instanceof CompetitionMember){
                typeCastMember = (CompetitionMember) currentMember;
            }
        } catch (MemberNotFoundException e) {
            System.out.println(e.toString());
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }

    }
    public void differentUserLogic(){
        System.out.println("Velkommen " + currentMember.getName());
        System.out.println("Tast 1 for at registrer tid for medlem: ");
        System.out.println("Tast 2 for at se alle reslutater: ");
        if (currentMember instanceof Member &&! (currentMember instanceof CompetitionMember)){
            System.out.println("Tast 3 for at gå tilbage");
        }
        if (currentMember instanceof CompetitionMember){
            System.out.println("Tast 3 for at tilføje et stævne.");
            System.out.println("Tast 4 for at se dine stævner.");
            System.out.println("Tast 5 for at gå tilbage.");
        }
        inputInt = InputHandler.inputInt("Vælg et af følgende valg: ");

        if (currentMember instanceof Member &&! (currentMember instanceof CompetitionMember)){
            regularMenu();
        }
        if (currentMember instanceof CompetitionMember){
            compMenu();
        }
    }

    public void addTimeToMember(){
       currentMember.addTime();//
        DatabaseHandler.save();
    }

    public void addCompetitionToMember(){
       typeCastMember.addCompetitionPerformance();
    }

    public void viewTimesForMember(){
        currentMember.viewTimes();
    }

    public void viewCompetitionForMember(){
        typeCastMember.viewCompetitions();
    }

    public void back(){
        running=false;
    }
}
