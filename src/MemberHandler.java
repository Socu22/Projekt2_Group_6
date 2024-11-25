import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class MemberHandler {

    public static ArrayList<Member> memberList = new ArrayList<>();

    static Member searchByName(String name) throws MemberNotFoundException{
        System.out.println("Indtast navn: ");
        String searchName = name;
        for (Member m: memberList){
            if (m.getName().equalsIgnoreCase(searchName)){
                return m;
            }
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
    static void addToList(){ //temp metode indtil at arraylist har json ting
        memberList.add(new Member("l",LocalDate.of(2024,1,1)));
    }


   /* public static void main(String[] args) {
        Member m1 = new Member("Lasse", LocalDate.of(1989,2,12));
        Member m2 = new Member("Hubert", LocalDate.of(1998,8,12));

        memberList.add(m1);
        memberList.add(m2);
        try {
            System.out.println(MemberHandler.searchByID(2));
        }
        catch (MemberNotFoundException e){
            System.out.println("Medlem ikke fundet i databasen");
            e.printStackTrace();
        }
    }

    */
}

class MemberNotFoundException extends Exception{
    public String toString(){
        return "Could not find Member object in the List";
    }
}