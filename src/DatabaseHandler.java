import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseHandler {

    private final static String path = "src//Database.csv"; //makes sure where Database.csv is.
    private static String line;                             //For load() to separate csv lines.
    private static  ArrayList<Member> memberlist;           //For load() And save()
    //from now on it is attributes for load().
    private static Member currentMember;                    //the currentMemberIn load()
    private static BufferedReader br;                       //used for reading .csv files in load from path.
    private static String[] fields;                         //This is how we know what things are in Database.csv. 0 is for example the first before the comma, look further into the private methods to understand further.
    private static String type;                 //For Member, type is how we uses the differrentUsersLogic() in UserInterface
    private static String name;                 //For Member
    private static int id;                      //For Member
    private static LocalDate birthdate;         //For Member
    private static LocalDate signUpDate;        //For Member
    private static boolean isPaid;              //For Member, this is how we know whether Member has paid.

    private static LocalDate date;              //for TraintimeOrCompetition
    private static double duration;             //for TraintimeOrCompetition, could be duration of traintime practise, or competitions result
    private static String discipline;           //for TraintimeOrCompetition, Same as above, discipline for traintime or Competition.
    private static int distance;                //for Traintime, because in competitions we mostly care about placement than distance.
    private static TrainTime trainTime;         //for Traintime, this is the traintime loaded form load()
    private static String competitionName;      //for Competition, the name like "Swim Youth xx"
    private static int placement;               //for Competition, placement from the competition from the member
    private static Competition competition;     //for Competition, this is the competition loaded from load()
    //from now on it is attributes for load().
    private static FileWriter writer;


//Methods for converting .csv file to arraylist depening on logic, used in load().
    public static void establishSaveFoundation() throws FileNotFoundException {


        memberlist = new ArrayList<>();
        br = new BufferedReader(new FileReader(path));
        currentMember = null;

    }
    private static void csvMemberReadLogic(){

        type = fields[0];
        name = fields[1]; //Adds values from .csv file to static values
        id = Integer.parseInt(fields[2]);
        birthdate = LocalDate.parse(fields[3]);
        signUpDate = LocalDate.parse(fields[4]);
        isPaid = Boolean.parseBoolean(fields[5]);

        //Checks member type and adds values to member
        if (type.equalsIgnoreCase("aktivmedlem")) {
            currentMember = new Member(name, birthdate, signUpDate, id, isPaid);
        } else if (type.equalsIgnoreCase("passivmedlem")) {
            currentMember = new PassiveMember(name, birthdate, signUpDate, id, isPaid);
        } else if (type.equalsIgnoreCase("konkurrencemedlem")) {
            currentMember = new CompetitionMember(name, birthdate, signUpDate, id, isPaid);
        }

        if (currentMember != null) {
            memberlist.add(currentMember);
        }
    }
    private static void csvTrainTimeReadLogic(){
        date = LocalDate.parse(fields[0]); //Local variables are added from .csv
        duration = Double.parseDouble(fields[1]);
        discipline = fields[2];
        distance = Integer.parseInt(fields[3]);
        // Local values are added to traintime object
        trainTime = new TrainTime(currentMember,date, duration, discipline, distance);
        currentMember.getTrainTimeList().add(trainTime); //Train time object is added to currentMember object
    }
    private static void csvCompetitionReadLogic(){
        date = LocalDate.parse(fields[0]);
        duration = Double.parseDouble(fields[1]);
        discipline = fields[2];
        distance = Integer.parseInt(fields[3]);
        competitionName = fields[4];
        placement = Integer.parseInt(fields[5]);
        competition = new Competition(currentMember, competitionName, discipline, placement, date, duration, distance);
        ((CompetitionMember) currentMember).getComepetitionList().add(competition); //Smart way to Cast something when you know that it is something from a child of the super class
    }
    public static ArrayList<Member> load() {
        try {
            establishSaveFoundation();
           while ((line = br.readLine()) != null) {
                fields = line.split(",");

                // Checks whether element in .csv file is member or time
                if (fields.length == 6) {
                    csvMemberReadLogic();
                }// Checks if it is reading a time value
                else if (fields.length == 4 && currentMember != null) {
                    csvTrainTimeReadLogic();
                } // Checks if it is a Competition
                else if (fields.length == 7 && currentMember != null) {
                   csvCompetitionReadLogic();
                }
            }
            Member.setIdCounter(currentMember.getID()+1);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return memberlist;
    }

//Overwrites current Database with end Arraylist from MemberHandler class as it receives from load() a Arraylist.
    private static void establishWriteFoundation() throws IOException {
        memberlist = MemberHandler.getMemberList();
        writer = new FileWriter(path, false);


    }
    private static void memberSaveLogic(Member m) throws IOException {
        //Program checks type of member
        if (m instanceof PassiveMember) {
            writer.write("Passivmedlem," + memberInfoToCSV(m));
        }
        if (m instanceof CompetitionMember){
            writer.write("Konkurrencemedlem," + memberInfoToCSV(m));
        }
        if (m instanceof Member && !(m instanceof PassiveMember) && !(m instanceof CompetitionMember)) {
            writer.write("Aktivmedlem," + memberInfoToCSV(m));
        }

        writer.write("\n");
        for (TrainTime t: m.getTrainTimeList()){
            writer.write(timeInfoToCSV(t));
            writer.write("\n");
        }
        writer.write("\n");
        if (m instanceof CompetitionMember){
            for (Competition c : ((CompetitionMember) m).getComepetitionList()) {
                writer.write(c.compToCSV(c));
                writer.write("\n");
            }
        }
    }

    public static void save() {

        try {
            establishWriteFoundation();

            //Loops through list of members
            for (Member m: memberlist) {
                memberSaveLogic(m);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Gem til fil fejlede.");
        }
    }

    public static String memberInfoToCSV(Member m){
        return m.getName() + "," + m.getID() + "," + m.getBirthdate() + "," +  m.getSignUpDate() + "," +  m.getContingent().getDebt();
    }

    public static String timeInfoToCSV(TrainTime t){
        return t.getDate() + "," + t.getDuration() + "," + t.getDiscipline() + "," + t.getDistance();
    }




}