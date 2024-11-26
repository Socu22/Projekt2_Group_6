import java.time.LocalDate;
import java.util.ArrayList;

public class MemberHandler {

    public static ArrayList<Member> memberList = new ArrayList<>();

    static Member searchByName(String name) throws MemberNotFoundException, InvalidInputException{
        ArrayList<Member> foundList = new ArrayList<>();
        System.out.println("Indtast navn: ");
        String searchName = name;
        for (Member m: memberList){
            if (m.getName().equalsIgnoreCase(searchName)){
                foundList.add(m);
            }
        }
        if(foundList.size() == 1){
            return foundList.get(0);
        }
        else if (foundList.size() > 1) {
            System.out.println("Følgende brugere blev fundet med dette navn:");
            int foundUserNR = 1;
            for (Member m : foundList) {
                System.out.println(foundUserNR + ": " + m.getName() + " ID: " + m.getID());
                foundUserNR++;
            }
            int foundUserChoice = InputHandler.inputInt("Hvilken bruger vil du tilgå?");
            return foundList.get(foundUserChoice - 1);
        }
        throw new MemberNotFoundException();
    }

    static Member searchByID(int id) throws MemberNotFoundException{
        System.out.println("Indtast ID: ");
        int searchID = id;
        for (Member m: memberList){
            if (m.getID()==searchID){
                return m;
            }
        }
        throw new MemberNotFoundException();
    }

    public static Member smartSearch() throws MemberNotFoundException, InvalidInputException{
        String userInput = InputHandler.inputString("Indtast navn eller ID på den ønskede bruger:");
        int idInput = 0;
        try{
            idInput = Integer.parseInt(userInput);
        }catch (Exception e){
        }
        if(idInput>0){
            return searchByID(idInput);
        }
        else{
            return searchByName(userInput);
        }
    }

    public static Member getNewestMember(){
        return memberList.getLast();
    }

    public static void createMember() throws InvalidInputException{
        System.out.println("Du har valgt at oprette et nyt medlem i svømmeklubben. Hvilken type medlem skal det være?");
        System.out.println("1: Standard medlem \n2: Passivt medlem \n3: Konkurrence medlem");

        int chosenInput=InputHandler.inputInt("Indtast dit valg:");
        String chosenName=InputHandler.inputString("Indtast navn:");
        LocalDate chosenDate = InputHandler.inputDate("Indtast medlemmets fødselsdato:");

        Member m;

        switch(chosenInput){
            case 1:
                m = new Member(chosenName,chosenDate);
                memberList.add(m);
                break;
            case 2:
                m = new PassiveMember(chosenName, chosenDate);
                memberList.add(m);
                break;

            case 3:
                System.out.println("Denne feature er endnu ikke understøttet");
                break;

            default:
                System.out.println("Ugyldigt input.");
                break;
        }
    }


    public static void main(String[] args) {
        try {
            memberList = DatabaseHandler.load();
        }catch (Exception e){
            System.out.println("Kunne ikke læse fil");
            e.printStackTrace();
        }

        try {
            System.out.println(MemberHandler.smartSearch());
            System.out.println(MemberHandler.smartSearch());
            System.out.println(MemberHandler.smartSearch());
            System.out.println(MemberHandler.smartSearch());
            System.out.println(MemberHandler.smartSearch());
            System.out.println(MemberHandler.smartSearch());

        }
        catch (MemberNotFoundException e){
            System.out.println("Medlem ikke fundet i databasen");
            e.printStackTrace();
        }
        catch (InvalidInputException e) {
            System.out.println("Fejl i indtastning, prøv igen.");
            e.printStackTrace();
        }
    }
}

class MemberNotFoundException extends Exception{
    public String toString(){
        return "Could not find Member object in the List";
    }
}