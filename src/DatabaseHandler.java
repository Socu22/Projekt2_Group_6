import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseHandler {

    private final static String path = "src//Database.csv";
    private static String line = "";
    private static  ArrayList<Member> Memberlist;




    public static ArrayList<Member> load() { //Method for converting .csv file to arraylist
        Memberlist = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            Member currentMember = null;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                // Checks whether element in .csv file is member or time
                if (fields.length == 6) {
                    String type = fields[0];
                    String name = fields[1]; //Adds values from .csv file to local values
                    int id = Integer.parseInt(fields[2]);
                    LocalDate birthdate = LocalDate.parse(fields[3]);
                    LocalDate signUpDate = LocalDate.parse(fields[4]);
                    boolean isPaid = Boolean.parseBoolean(fields[5]);

                    //Checks membertype and adds values to member
                    if (type.equalsIgnoreCase("aktivmedlem")) {
                        currentMember = new Member(name, birthdate, signUpDate, id, isPaid);
                    } else if (type.equalsIgnoreCase("passivmedlem")) {
                        currentMember = new PassiveMember(name, birthdate, signUpDate, id, isPaid);
                    } else if (type.equalsIgnoreCase("konkurrencemedlem")) {
                        currentMember = new CompetitionMember(name, birthdate, signUpDate, id, isPaid);
                    }

                    if (currentMember != null) {
                        Memberlist.add(currentMember);
                    }
                }// Checks if it is reading a time value
                else if (fields.length == 4 && currentMember != null) {
                    LocalDate date = LocalDate.parse(fields[0]); //Local variables are added from .csv
                    double duration = Double.parseDouble(fields[1]);
                    String discipline = fields[2];
                    int distance = Integer.parseInt(fields[3]);
                        // Local values are added to traintime object
                    TrainTime trainTime = new TrainTime(currentMember,date, duration, discipline, distance);
                    currentMember.getTrainTimeList().add(trainTime); //Traintime object is added to currentMember object
                } // Checks if it is a Comepetiton
                else if (fields.length == 7 && currentMember != null) {
                    LocalDate date = LocalDate.parse(fields[0]);
                    double timeResult = Double.parseDouble(fields[1]);
                    String disciplin = fields[2];
                    int distance = Integer.parseInt(fields[3]);
                    String competitionName = fields[4];
                    int placement = Integer.parseInt(fields[5]);
                    Competition competition = new Competition(currentMember, competitionName, disciplin, placement, date, timeResult, distance);
                    ((CompetitionMember) currentMember).getComepetitionList().add(competition);
                }
            }
            Member.setIdCounter(currentMember.getID()+1);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Memberlist;
    }

    public static void save() {
        ArrayList<Member> memberlist = MemberHandler.getMemberList();
        try {
            FileWriter writer = new FileWriter(path, false);
            //Loops through list of members
            for (Member m: memberlist) {
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
            writer.close();
        } catch (IOException e) {
            System.out.println("Filen er blevet gemt.");
        }
    }

    public static String memberInfoToCSV(Member m){
        return m.getName() + "," + m.getID() + "," + m.getBirthdate() + "," +  m.getSignUpDate() + "," +  m.getContingent().getDebt();
    }

    public static String timeInfoToCSV(TrainTime t){
        return t.getDate() + "," + t.getDuration() + "," + t.getDiscipline() + "," + t.getDistance();
    }




}