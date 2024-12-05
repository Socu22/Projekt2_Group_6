import java.time.LocalDate;
import java.time.Duration;
import java.time.Period;
import java.time.Year;
import java.util.*;
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

    public static void setIdCounter(int i){
        idCounter = i;
    }

    public Member (String name, LocalDate birthdate, LocalDate signUpDate, int id, boolean isPaid){
        this.name = name;
        this.birthdate = birthdate;
        this.age = Period.between(birthdate, LocalDate.now()).getYears();
        this.id = id;
        this.signUpDate = signUpDate;
        this.contingent = new Contingent(this);
        this.contingent.setContingentToCSV(isPaid);
        if (this.id>idCounter){
            idCounter = this.id+1;
        }

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


    public Contingent getContingent() {
        return contingent;
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
        this.contingent.payDebt();
        System.out.println("Kontingent betalt for " + this.getName());
    }

    public void addDebt(){
        System.out.println("Der er gået et år, tilføjer gæld til bruger:");
        contingent.addDebt();
    }

    public void viewTimesLegacy(){
        System.out.println("\nHer er et overblik over dine tider: ");
        for (TrainTime t: trainTimeList){
            System.out.println(t);
        }
    }


    public void viewTimes(){
        HashMap<String, Integer> disciplineMap = new HashMap<>();
        for (TrainTime t: trainTimeList){
            String discipline = t.getDiscipline();
            disciplineMap.put(discipline, disciplineMap.getOrDefault(discipline, 0) +1);
        }
        for(Map.Entry<String, Integer> entry: disciplineMap.entrySet()){
            String discipline = entry.getKey();
            int amountOfTimes = entry.getValue();
            System.out.println(discipline + "- " + amountOfTimes + " tider registreret i systemet");
        }
        String chosendiscipline = InputHandler.inputString("Indtast den disciplin du vil have dine tider vist for:");
        ArrayList<TrainTime> sortedTimeListOfChosenDiscipline = new ArrayList<>();
        for (TrainTime t2: trainTimeList){
            if(t2.getDiscipline().equalsIgnoreCase(chosendiscipline)){
                sortedTimeListOfChosenDiscipline.add(t2);
            }
        }
        sortedTimeListOfChosenDiscipline.sort(null);
        for (TrainTime t3: sortedTimeListOfChosenDiscipline){
            System.out.println(t3);
        }
    }


    public ArrayList<TrainTime> getTrainTimeList(){
        return trainTimeList;
    }

    public LocalDate getBirthdate(){
        return birthdate;
    }

    public LocalDate getSignUpDate(){
        return signUpDate;
    }

    public  String toCSV(){
        return memberType + "," + name + "," + id + "," + birthdate + "," + signUpDate + "," + age;
    }

    public String getMemberType(){
        return memberType;
    }

    public String toString(){
        return "Velkommen \uD83D\uDC4B\uD83D\uDC4B\uD83D\uDC4B " + name + " ID: " + id + "\n\tAlder: " + age + "\n\tMedlemstype: " + memberType + "\n" + contingent + "\n\tMeldt ind den: " + signUpDate;
    }

    void addTime(){
        LocalDate dateOfTime = InputHandler.inputDate("Indtast dato for den tid du vil registrere:");
        String chosenDiscipline=InputHandler.inputString("Input Disciplinen du har svømmet");
        int distanceSwam = InputHandler.inputInt("Indtast den distance du har svømmet: (Helt tal, i meter)");
        double durationInSec = InputHandler.inputDouble("Indtast tiden det tog dig i sekunder: ");
        this.trainTimeList.add(new TrainTime(this,dateOfTime, durationInSec, chosenDiscipline, distanceSwam));
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
    public PassiveMember(String name, LocalDate birthdate, LocalDate signUpDate, int id, boolean isPaid) { //Contstructer 2
        super(name, birthdate, signUpDate, id, isPaid);
    }

}
