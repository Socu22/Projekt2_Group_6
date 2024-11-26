import java.time.LocalDate;
import java.util.Scanner;

public class AdminInterface {
    public static int input=-1;
    private Member currentMember;
    public static boolean running= true;


    public void start_interface_Admin(){
        while (running) {

            System.out.println("Tast 1 find specifikt medlem: ");
            System.out.println("Tast 2 for at registerer nyt medlem: ");
            System.out.println("Tast 3 for at tilføje gæld til medlem: ");
            System.out.println("Tast 4 for at registerer betalt gæld for medlem: ");
            input= InputHandler.inputInt("Vælg et af følgende valg: ");


            switch (input) {
                case 1:
                    //get current member
                    setCurrentMember();
                    break;
                case 2:
                    //create Member
                    createMember();
                    break;
                case 3:
                    //Add debt to Member  object-
                    addDebtToMember();
                case 4:
                    //pay debt for Member object
                    payDebtForMember();

                case 5:

                    back();
                    break;
            }
        }
    }

    public void setCurrentMember() {
        try {
            currentMember= MemberHandler.smartSearch();
            System.out.println(currentMember);
        } catch (MemberNotFoundException e) {
            System.out.println(e.toString());
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public Member getCurrentMember(){
        return currentMember;
    }

    public void createMember(){
        try{
            MemberHandler.createMember();
            currentMember = MemberHandler.getNewestMember();
            System.out.println("Tast 1 for betaling af nuværende gæld.");
            System.out.println("Tast 2 for at gå tilbage.");
            input=InputHandler.inputInt("Vælg et af følgende valg: ");
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
        switch (input){
            case 1:
                currentMember.payDebt();
                break;
            case 2:
                break;
        }
    }

    public void addDebtToMember(){
        getCurrentMember().addDebt();

    }
    public void payDebtForMember(){
        getCurrentMember().payDebt();
    }
    public void back(){
        running=false;

    }

    // Beregner top 5 resultater for samtlige discipliner
    public void bestTimesPerDisciplin(){
        currentMember.viewTimes();
    }
}
