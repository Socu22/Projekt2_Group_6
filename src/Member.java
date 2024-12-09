import java.time.LocalDate;
import java.time.Period;
import java.util.*;

//Contains attributes to member
public class Member {
    private final String name;
    private final int id;
    private final LocalDate birthdate;
    private final LocalDate signUpDate;
    private int age;
    private String memberType;                                            //Contains type of member
    private ArrayList<TrainTime> trainTimeList = new ArrayList<>();       //Contains all performances by member
    private Contingent contingent;                                        //Contains contingent objects
    private static int idCounter = 1;                                     //Makes sure members are assigned unique id's

    //Constructor for creating new member in system
    public Member (String name, LocalDate birthdate){
        this.name = name;
        this.birthdate = birthdate;
        this.age = Period.between(birthdate, LocalDate.now()).getYears();
        this.id = idCounter;
        this.signUpDate = LocalDate.now();
        idCounter++;
        this.contingent = new Contingent(this);

            //Logic for assigning member type
            if (this.getAge()<18){
                memberType = "Junior Motionist";
            } else if (this.getAge()>60) {
                memberType = "Ældre Motionist";
            }else {
                memberType = "Senior Motionist";
            }
        //Logic for assigning passive member
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

    //Constructor for .csv
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
}

class PassiveMember extends Member{
    //Constructor for system
    public PassiveMember(String name, LocalDate birthdate) {
        super(name, birthdate);
    }
    //Constructor for .csv
    public PassiveMember(String name, LocalDate birthdate, LocalDate signUpDate, int id, boolean isPaid) {
        super(name, birthdate, signUpDate, id, isPaid);
    }

}
