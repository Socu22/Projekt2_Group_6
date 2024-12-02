import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
            System.out.println("Tast 5 for finde top 5 i alle disicpilner : ");
            System.out.println("Tast 6 for at se medlemmer gæld (IKKE UNDERSTØTTET ENDNU)");
            System.out.println("Tast 7 for at se medlemmer i restance");
            System.out.println("Tast 8 for at gå tilbage");
            input= InputHandler.inputInt("Vælg et af følgende valg: ");


            switch (input) {
                case 1:
                    //get current member
                    setCurrentMember();
                    System.out.println("Du er nu logget på medlemmet: "+currentMember);
                    break;
                case 2:
                    //create Member
                    createMember();
                    break;
                case 3:
                    //Add debt to Member  object-
                    addDebtToMember();
                    break;
                case 4:
                    //pay debt for Member object
                    payDebtForMember();
                    break;
                case 5:
                    topFiveForEachDiscipline();
                    break;
                case 6:
                   // printAllNonPaidMembers();
                    break;
                case 7:

                    break;
                case 8:

                    back();
                    break;
            }
        }
    }

    public static void setRunning(boolean running) {
        AdminInterface.running = running;
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

    public void outputMembersInDebt(){
        System.out.println("virker");
    }

    public Member getCurrentMember(){
        return currentMember;
    }

    public void createMember(){
        try{
            MemberHandler.createMember();
            currentMember = MemberHandler.getNewestMember();
            System.out.println("Genvej(tast): betal gæld(1), quit(2) ");
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

        if (currentMember==null){
            System.out.println("Du bedes vælge specifikt medlem først");
            return;
        }
        getCurrentMember().addDebt();

    }
    public void payDebtForMember(){
        getCurrentMember().payDebt();
    }
    public void back(){
        running=false;

    }
    public void topFiveForEachDiscipline(){
       TimeSort.topFiveForEachDiscipline();
    }
   /* public void printAllNonPaidMembers(){
        List<Member> members = MemberHandler.getMemberList();

        Collections.sort(members,Comparator.comparing(Member::getDebt));
        System.out.println(members);
    }

    */


}
