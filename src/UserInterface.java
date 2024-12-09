

public class UserInterface {
    public static int inputInt = -1;
    public  Member currentMember;
    public CompetitionMember typeCastMember;
    public static boolean running= true;

    //Logs in user displays menu according to type of member logged in
    public void start_interface_User(){
        while (running) {
            loginUser();
            differentUserLogic();
        }
    }

    //Menu for non-competition members
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

    //Menu for competition members
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

    //Finds user in system and logs them in
    public void loginUser() {
        try {
            currentMember= MemberHandler.smartSearch();
            System.out.println(currentMember);
            if (currentMember instanceof CompetitionMember){
                typeCastMember = (CompetitionMember) currentMember;
            }
        } catch (MemberNotFoundException e) {
            System.out.println("Kunne ikke finde medlem.");
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    //displays different menus according to user type
    public void differentUserLogic(){
        System.out.println("Velkommen " + currentMember.getName());
        System.out.println("Tast 1 for at registrer tid for medlem: ");
        System.out.println("Tast 2 for at se alle reslutater: ");
        if (! (currentMember instanceof CompetitionMember)){
            System.out.println("Tast 3 for at gå tilbage");
        }
        if (currentMember instanceof CompetitionMember){
            System.out.println("Tast 3 for at tilføje et stævne.");
            System.out.println("Tast 4 for at se dine stævner.");
            System.out.println("Tast 5 for at gå tilbage.");
        }
        inputInt = InputHandler.inputInt("Vælg et af følgende valg: ");

        if (! (currentMember instanceof CompetitionMember)){
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
       DatabaseHandler.save();
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
