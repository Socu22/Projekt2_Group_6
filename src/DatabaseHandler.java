import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseHandler {

    private static String path = "src//Database.csv";
    private static String line = "";

    public static void readFile() { //Metode til at læse hele Databse.csv filen
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
    public static ArrayList<Member> load() { //Metode til at loade hele Databse.csv filen og gør det til en Arraylist
        ArrayList<Member> Memberlist = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            Member currentMember = null;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                // Den tjekker hvor mange felter der er i csv filen for at se om det er et medlem eller en tid
                if (fields.length == 6) {
                    String type = fields[0];
                    String name = fields[1]; //Den tilføjer alle værdierne fra csv filen ind i lokale variabler
                    int id = Integer.parseInt(fields[2]);
                    LocalDate birthdate = LocalDate.parse(fields[3]);
                    LocalDate signUpDate = LocalDate.parse(fields[4]);

                    //Den tjekker medlemstype og tilføjer derefter lokalevariabler til Medlemobjektets attributter
                    if (type.equalsIgnoreCase("aktivmedlem")) {
                        currentMember = new Member(name, birthdate, signUpDate, id);
                    } else if (type.equalsIgnoreCase("passivmedlem")) {
                        currentMember = new PassiveMember(name, birthdate, signUpDate, id);
                    }

                    if (currentMember != null) {
                        Memberlist.add(currentMember);
                    }
                }// Den tjekker om det er en tid den læser
                else if (fields.length == 4 && currentMember != null) {
                    LocalDate date = LocalDate.parse(fields[0]); //Lokale variabler bliver tilføjet en værdi ud fra felterne i CSV
                    double duration = Double.parseDouble(fields[1]);
                    String discipline = fields[2];
                    int distance = Integer.parseInt(fields[3]);
                        // Lokale variabler bliver tilføjet til traintime objektet
                    TrainTime trainTime = new TrainTime(currentMember,date, duration, discipline, distance);
                    currentMember.getTrainTimeList().add(trainTime); //Traintime objektet bliver tilføjer til current Member objektet
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Memberlist;
    }
    public static void save() {
      /*  String line = "";
        ArrayList<Member> gemtArrayList = new ArrayList<>();
        gemtArrayList = MemberHandler.memberList;
        String[] gemtArray;
        gemtArray = gemtArrayList.spliterator(line, ",");
        if (MemberList)*/
    }



    public static void writeToFile(Member member) { // Metode til at tilføje et Member til Database.csv filen
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
            bw.write(member.toCSV());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ArrayList<Member> members = load();
        for (Member member : members) {
            System.out.println(member.getName() + " - " + member.getTrainTimeList().size() + " training times");
            for (TrainTime time : member.getTrainTimeList()) {
                System.out.println("  " + time);
            }
        }


    }
}
