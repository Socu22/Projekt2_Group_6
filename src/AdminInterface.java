import java.util.*;

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
                    break;
                case 4:
                    //pay debt for Member object
                    payDebtForMember();
                    break;
                case 5:
                    TopFiveBestTimesPerDisciplin();
                    break;

                case 6:

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
    public void TopFiveBestTimesPerDisciplin(){

        List<TrainTime> topFive = new ArrayList<>();

        for (int i = 0; i < MemberHandler.getMemberList().size(); i++) {
            currentMember=MemberHandler.getMemberList().get(i);
            for (int j = 0; j < currentMember.getTrainTimeList().size() ; j++) {
                topFive.add(currentMember.getTrainTimeList().get(i));

            }
        }
        //should sort from best time to worst and discipline
        Collections.sort(topFive, Comparator.comparingDouble(TrainTime::getDuration).thenComparing(TrainTime::getDiscipline).reversed());
        System.out.println(topFive);

    }
}
