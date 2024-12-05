import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHandler {

    private static String path = "src//Database.csv";
    private static String line = "";

    public static void readFile() { //Method for reading .csv file
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] medlemmer = line.split(",");
                System.out.println(Arrays.toString(medlemmer));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Member> load() { //Method for converting .csv file to arraylist
        ArrayList<Member> Memberlist = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            Member currentMember = null;
            CompetitionMember currentCompetitionMember = null;

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
                    } else if (type.equalsIgnoreCase("comptetitionmember")) {
                        currentCompetitionMember = new CompetitionMember(name, birthdate, signUpDate, id, isPaid);
                        
                    }

                    if (currentMember != null) {
                        Memberlist.add(currentMember);
                    } else if (currentCompetitionMember != null) {
                        Memberlist.add(currentCompetitionMember);

                    }
                }// Checks if is reading a time value
                else if (fields.length == 4 && currentMember != null) {
                    LocalDate date = LocalDate.parse(fields[0]); //Local variables are added from .csv
                    double duration = Double.parseDouble(fields[1]);
                    String discipline = fields[2];
                    int distance = Integer.parseInt(fields[3]);
                        // Local values are added to traintime object
                    TrainTime trainTime = new TrainTime(currentMember,date, duration, discipline, distance);
                    currentMember.getTrainTimeList().add(trainTime); //Traintime object is added to currentMember object
                } // Checks if it is a Comepetion
                else if (fields.length == 5 && currentCompetitionMember != null) {
                    LocalDate date = LocalDate.parse(fields[0]);
                    String competitionName = fields[1];
                    int placement = Integer.parseInt(fields[2]);
                    double timeResult = Double.parseDouble(fields[4]);
                    String disciplin = fields[3];
                    Competition competition = new Competition(currentMember, competitionName, disciplin, placement, date, timeResult);
                    currentCompetitionMember.getComepetitionList().add(competition);
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
                if (m instanceof Member && !(m instanceof PassiveMember)) {
                    writer.write("Aktivmedlem," + memberInfoToCSV(m));
                }
                writer.write("\n");
                for (TrainTime t: m.getTrainTimeList()){
                    writer.write(timeInfoToCSV(t));
                    writer.write("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String memberInfoToCSV(Member m){
        return m.getName() + "," + m.getID() + "," + m.getBirthdate() + "," +  m.getSignUpDate() + "," +  m.getContingent().getDebt();
    }

    public static String timeInfoToCSV(TrainTime t){
        return t.getDate() + "," + t.getDuration() + "," + t.getDiscipline() + "," + t.getDistance();
    }

    public static void writeToFile(Member member) { // Method for adding Member to Database.csv file
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
            bw.write(member.toCSV());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MemberHandler.loadFromDatabase();
        save();
        readFile();
    }
}