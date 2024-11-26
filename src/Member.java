import java.time.LocalDate;
import java.time.Duration;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class Member {
    private String name;
    private int id;
    private final LocalDate birthdate;
    private final LocalDate signUpDate;
    private int age;
    private String memberType;
    private ArrayList<TrainTime> trainTimeList = new ArrayList<>();
    private Contingent contingent;
    private static int idCounter = 1;

    public Member (String name, LocalDate birthdate){
        this.name = name;
        this.birthdate = birthdate;
        this.age = Period.between(birthdate, LocalDate.now()).getYears();
        this.id = idCounter;
        this.signUpDate = LocalDate.now();
        idCounter++;
        this.contingent = new Contingent(this);

            if (this.getAge()<18){
                memberType = "Junior Motionist";
            } else if (this.getAge()>60) {
                memberType = "Ældre Motionist";
            }else {
                memberType = "Senior Motionist";
            }

        if (this instanceof PassiveMember) {
            if (this.getAge()<18){
                memberType = "Junior Passiv";
            } else {
                memberType = "Senior Passiv";
            }
        }
    }
    public Member (String name, LocalDate birthdate, LocalDate signUpDate, int id){
        this.name = name;
        this.birthdate = birthdate;
        this.age = Period.between(birthdate, LocalDate.now()).getYears();
        this.id = idCounter;
        this.signUpDate = signUpDate;

        if (this.getAge()<18){
            memberType = "Junior Motionist";
        } else if (this.getAge()>60) {
            memberType = "Ældre Motionist";
        }else {
            memberType = "Senior Motionist";
        }

        if (this instanceof PassiveMember) {
            if (this.getAge()<18){
                memberType = "Junior Passiv";
            } else {
                memberType = "Senior Passiv";
            }
        }
    }

    public String getName(){
        return name;
    }

    public int getID(){
        return id;
    }

    public int getAge(){
        return age;
    }

    public void payDebt(){
        double amountToPay;
        amountToPay = InputHandler.inputDouble("Du vil gerne betale noget eller hele din gæld, indtast hvor meget: ");      //DUMMY NUMBER, USE INPUTHANDLER
        this.contingent.payDebt(amountToPay);
    }

    public void addDebt(){
        System.out.println("Der er gået et år, tilføjer gæld til bruger:");
        contingent.addDebt();
    }

    public void viewTimes(){
        System.out.println("\nHer er et overblik over dine tider: ");
        for (TrainTime t: trainTimeList){
            System.out.println(t);
        }
    }
    public  String toCSV(){
        return memberType + "," + name + "," + id + "," + birthdate + "," + signUpDate + "," + age;
    }

    public String toString(){
        return "Velkommen " + name + " ID: " + id + "\n\tAlder: " + age + "\n\tMedlemstype: " + memberType + "\n" + contingent + "\n\tMeldt ind den: " + signUpDate;
    }

    void addTime(){
        LocalDate dateOfTime = InputHandler.inputDate("Indtast dato for den tid du vil registrere: ");
        String chosenDiscipline = InputHandler.inputString("Indtast navnet på den disciplin tiden er sat i: ");
        int distanceSwam = InputHandler.inputInt("Indtast den distance du har svømmet: ");
        double durationInSec = InputHandler.inputDouble("Indtast tiden det tog dig i sekunder: ");
        this.trainTimeList.add(new TrainTime(dateOfTime, durationInSec, chosenDiscipline, distanceSwam));
    }

    public static void main(String[] args) {
        Member m1 = new PassiveMember("Frederik", LocalDate.of(1922, 02, 12));
        System.out.println(m1);
        m1.addTime();
        m1.addTime();
        m1.addTime();
        m1.viewTimes();
    }
}

class PassiveMember extends Member{
    public PassiveMember(String name, LocalDate birthdate) {
        super(name, birthdate);
    }
}
