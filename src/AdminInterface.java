import java.time.LocalDate;
import java.util.Scanner;

public class AdminInterface {
    public static int input=-1;
    public  Member currentMember;
    public static boolean running= true;
    static Scanner scanner = new Scanner(System.in);


    public void start_interface_Admin(){
        while (running) {

            System.out.println("Tast 1 for at finde specifikt medlem: ");
            System.out.println("Tast 2 for at registerer nyt medlem: ");
            System.out.println("Tast 3 for at tilføje gæld til medlem: ");
            System.out.println("Tast 4 for at registerer betalt gæld for medlem: ");
            input= scanner.nextInt();
            switch (input) {
                case 1:
                    //get current member
                    getMember();
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
            scanner.nextLine();
        }
    }

    public Member getMember() {
        return currentMember;
    }
    public Member createMember(){
        return new Member("test", LocalDate.now()); // change logic to real
    }
    public void addDebtToMember(){

    }
    public void payDebtForMember(){

    }
    public void back(){
        running=false;

    }
}
