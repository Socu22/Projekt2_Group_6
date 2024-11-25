import java.util.Scanner;

public class UserInterface {
    public static int inputInt = -1;
    public static String inputString ="";
    public  Member currentMember;
    public static boolean running= true;
    static Scanner scanner = new Scanner(System.in);






    public void start_interface_User(String name){
        while (running) {
            System.out.println("Tast 1 for at se specifik medlemoplysninger:");
            System.out.println("Tast 2 for at registrer nyt medlem: ");
            System.out.println("Tast 3 for at tilføj reslutater: ");
            System.out.println("Tast 4 for at se alle reslutater: ");
            inputInt = scanner.nextInt();



            switch (inputInt) {
                case 1:
                    //get current member
                    System.out.println();
                    getMember(name);
                    break;
                case 2:
                    //create Member
                    addTimeToMember();
                    break;
                case 3:
                    //Add Competition to Member  object-
                    addCompetitionToMember();
                case 4:
                    //View swimming results
                    viewTimesForMember();
                case 5:
                   //show competitions for member
                    viewCompetitionForMember();

                case 6:

                    back();
                    break;
            }
            scanner.nextLine();
        }
    }

    public Member getMember(String username) {
        MemberHandler.addToList();
        try {
            currentMember= MemberHandler.searchByName(username);
            System.out.println(currentMember);
        } catch (MemberNotFoundException e) {

        }

        return currentMember;
    }
    public void addTimeToMember(){
       currentMember.addTime();//logic skal ændres.

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
