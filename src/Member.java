import java.time.LocalDate;
import java.time.Duration;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;

public class Member {
    private String name;
    private int id;
    private LocalDate birthdate;
    private LocalDate signUpDate;
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
        return name + "," + id + "," + birthdate + "," + signUpDate + "," + age + "," + memberType;
    }

    public String toString(){
        return "Velkommen " + name + " ID: " + id + "\n\tAlder: " + age + "\n\tMedlemstype: " + memberType + "\n" + contingent + "\n\tMeldt ind den: " + signUpDate;
    }

    void addTime(){
        System.out.println("Du har valgt at tilføje en tid. Indtast den disciplin for din træning: ");
        LocalDate dateOfTime = LocalDate.now();     //DUMMY, REPLACE WITH INPUTHANDLER
        String chosenDiscipline = "Crawl";          //DUMMY, REPLACE WITH INPUTHANDLER
        System.out.println("Indtast distancen du har svømmet:");
        int distanceSwam = 100;                     //DUMMY, REPLACE WITH INPUTHANDLER
        System.out.println("Indtast det antal sekunder det tog dig at svømme den distance");
        double durationInSec = 100;                 //DUMMY, REPLACE WITH INPUTHANDLER
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
