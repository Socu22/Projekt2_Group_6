import java.time.LocalDate;
import java.util.ArrayList;

public class MemberHandler {

    public static ArrayList<Member> memberList = new ArrayList<>();

    //Search by partial or full name
    static Member searchByName(String name) throws MemberNotFoundException{
        ArrayList<Member> foundList = new ArrayList<>();
        System.out.println("Indtast navn: ");
        //Adds found members to list
        for (Member m: memberList){
            if (m.getName().contains(name)){
                foundList.add(m);
            }
        }
        //If only one member is found, returns found member
        if(foundList.size() == 1){
            return foundList.getFirst();
        }
        //Outputs found members by list, lets user choose
        else if (foundList.size() > 1) {
            System.out.println("Følgende brugere blev fundet med dette navn:");
            int foundUserNR = 1;
            for (Member m : foundList) {
                System.out.println(foundUserNR + ": " + m.getName() + " ID: " + m.getID());
                foundUserNR++;
            }
            int foundUserChoice = InputHandler.inputInt("Hvilken bruger vil du tilgå?");

            try {
                return foundList.get(foundUserChoice - 1);
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("Der er ikke en mulighed med det nummer.");
            }
        }
        throw new MemberNotFoundException();
    }

    public static ArrayList<Member> getMemberList(){
        return memberList;
    }

    static ArrayList<Member> loadFromDatabase(){
        System.out.println("Indlæser database...\n.\n.\n.");
        memberList = DatabaseHandler.load();
        System.out.println("Liste indlæst.");
        return DatabaseHandler.load();
    }

    //Searches users by id
    static Member searchByID(int id) throws MemberNotFoundException{
        System.out.println("Indtast ID: ");
        for (Member m: memberList){
            if (m.getID()== id){
                return m;
            }
        }
        throw new MemberNotFoundException();
    }

    //Combines search methods
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

    //Method for creating member
    public static void createMember() throws InvalidInputException{
        System.out.println("Du har valgt at oprette et nyt medlem i svømmeklubben. Hvilken type medlem skal det være?");
        System.out.println("1: Standard medlem \n2: Passivt medlem \n3: Konkurrence medlem");

        int chosenInput=InputHandler.inputInt("Indtast dit valg:");
        String chosenName=InputHandler.inputString("Indtast navn:");
        LocalDate chosenDate = InputHandler.inputDateSpecific("Indtast medlemmets fødselsdato:");

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
                m = new CompetitionMember(chosenName, chosenDate);
                memberList.add(m);
                break;

            default:
                System.out.println("Ugyldigt input.");
                break;
        }
    }
}

class MemberNotFoundException extends Exception{
    public String toString(){
        return "Could not find Member object in the List";
    }
}