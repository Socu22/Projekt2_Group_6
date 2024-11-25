import java.time.LocalDate;

public class Interface_Admin {
    public static int input=-1;
    public  Member currentMember;

    public void start_interface_Admin(){
        switch (input){
            case 1:
                //get current member
                getCurrentMember();
                break;
            case 2:
                //create Member
                createMember();
                break;
            case 3:
                //osv
        }
    }

    public Member getCurrentMember() {
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

    }
}
