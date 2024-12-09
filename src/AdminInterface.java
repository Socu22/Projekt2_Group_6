
public class AdminInterface {
    public static int input = -1;
    public static String inputString;
    private Member currentMember;
    public static boolean running = true;


    public void start_interface_Admin() {
        while (running) {

            printAndSetChoice();


            switch (input) {
                case 1:
                    //get current member
                    setCurrentMember();
                    System.out.println("Du er nu logget på medlemmet: " + currentMember);
                    break;
                case 2:
                    //create Member
                    createMember();
                    break;
                case 3:
                    //deletes member
                    deleteCurrentMember();
                    break;
                case 4:
                    //Add debt to Member  object-
                    addDebtToMember();
                    break;
                case 5:
                    //pay debt for Member object
                    payDebtForMember();
                    break;
                case 6:
                    topFiveForEachDiscipline();
                    break;
                case 7:
                    outputMembersInDebt();
                    break;
                case 8:
                    back();
                    break;
            }
        }
    }
    private void printAndSetChoice(){
        System.out.println("Tast 1 find specifikt medlem: ");
        System.out.println("Tast 2 for at registerer nyt medlem: ");
        System.out.println("Tast 3 for at fjerne et medlem");
        System.out.println("Tast 4 for at tilføje gæld til medlem: ");
        System.out.println("Tast 5 for at registerer betalt gæld for medlem: ");
        System.out.println("Tast 6 for finde top 5 i alle discipliner : ");
        System.out.println("Tast 7 for at se medlemmer gæld");
        System.out.println("Tast 8 for at gå tilbage");
        input = InputHandler.inputInt("Vælg et af følgende valg: ");


    }

    public static void setRunning(boolean running) {
        AdminInterface.running = running;
    }

    public void setCurrentMember() {
        try {
            currentMember = MemberHandler.smartSearch();
            System.out.println(currentMember);
        } catch (MemberNotFoundException e) {
            System.out.println(e.toString());
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public Member getCurrentMember() {
        return currentMember;
    }

    public void createMember() {
        try {
            MemberHandler.createMember();
            currentMember = MemberHandler.getNewestMember();
            System.out.println("Betal gæld med det samme?: \n\t1: Ja \n\t2. Nej ");
            input = InputHandler.inputInt("Vælg et af følgende valg: ");
            DatabaseHandler.save();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
        switch (input) {
            case 1:
                currentMember.payDebt();
                break;
            case 2:
                break;
        }
    }


    public void deleteCurrentMember() {
        if (currentMember == null) {
            System.out.println("Du skal først søge efter medlem, før du kan slette.");
            try {
                setCurrentMember(); // Prompt the user to search for a member
            } catch (Exception e) {
                System.out.println("Kunne ikke finde medlemmet: " + e.getMessage());
                return; // Exit if no member is found
            }
        }

        System.out.println("Vil du slette medlem: " + getCurrentMember().getName());
        System.out.println("Skriv 'Ja' hvis du vil slette " + getCurrentMember().getName());
        System.out.println("og 'Nej' hvis du vil gå tilbage");
        inputString = InputHandler.inputString("indtast valg");
        switch (inputString) {
            case "Ja":
                try {
                    boolean removed = MemberHandler.getMemberList().removeIf(member -> member.equals(currentMember));
                    if (removed) {
                        System.out.println("Medlemmet " + currentMember.getName() + " er blevet slettet.");
                        currentMember = null;
                        DatabaseHandler.save();

                    } else System.out.println("Medlemmet kunne ikke findes på listen");
                } catch (Exception e) {
                    System.out.println("der opstod en fejl under sletning af ");
                }
                break;
            case "Nej":
                System.out.println("Sletning af bruger annulleret.");
                break;
        }
        back();
    }


    public void addDebtToMember(){
        setCurrentMember();
        if (currentMember==null){
            System.out.println("Du bedes vælge specifikt medlem først");
            return;
        }
        getCurrentMember().addDebt();
        DatabaseHandler.save();
    }
    public void payDebtForMember(){
        setCurrentMember();
        getCurrentMember().payDebt();
        DatabaseHandler.save();
    }

    public void back(){
        running=false;

    }
    public void topFiveForEachDiscipline(){
       TimeSort.topFiveForEachDiscipline();
    }

    public void outputMembersInDebt(){
        for (Member m: MemberHandler.getMemberList()){
            if (m.getContingent().getDebt() == false){
                System.out.println(m.getName() + ": Har ikke betalt");
            }
        }
    }

}
