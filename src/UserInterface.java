import java.util.Scanner;

public class UserInterface {
    public static int inputInt = -1;
    public static String inputString ="";
    public  Member currentMember;
    public static boolean running= true;
    static Scanner scanner = new Scanner(System.in);






    public void start_interface_User(String name){
        while (running) {
            inputInt = scanner.nextInt();


            System.out.println("1 get member ");
            switch (inputInt) {
                case 1:
                    //get current member
                    getMember(name);
                    break;
                case 2:
                    //create Member
                    addTimeToMember();
                    break;
                case 3:
                    //Add debt to Member  object-
                    addCompetitionToMember();
                case 4:
                    //pay debt for Member object
                    viewTimesForMember();
                case 5:
                    //pay debt for Member object
                   //
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
            currentMember= MemberHandler.instance.searchByName(username);
            System.out.println(currentMember);
        } catch (MemberNotFoundException e) {

        }

        return currentMember;
    }
    public double addTimeToMember(){
        int res = scanner.nextInt(); //logic skal ændres.
        return res;
    }
    public Competition addCompetitionToMember(){

        return new Competition();//refers Competition Class,

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
