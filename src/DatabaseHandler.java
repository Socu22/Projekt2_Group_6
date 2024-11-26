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
            while ((line = br.readLine()) != null) {
                String[] medlemmer = line.split(",");
                if (medlemmer[0].equalsIgnoreCase("aktivmedlem")) {
                    Member m = new Member(medlemmer[1], LocalDate.parse(medlemmer[3]), LocalDate.parse(medlemmer[4]), Integer.parseInt(medlemmer[2]));
                    Memberlist.add(m);
                }
                else if (medlemmer[0].equalsIgnoreCase("passivmedlem")){
                    Member m = new PassiveMember(medlemmer[1], LocalDate.parse(medlemmer[3]), LocalDate.parse(medlemmer[4]), Integer.parseInt(medlemmer[2]));
                    Memberlist.add(m);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Memberlist;
    }
    public static void save() {

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

        readFile(); //Eksempel på brug uden brugerinput

        Member newMember = new Member("Ben Dover", LocalDate.of(1990, 5, 20));
        writeToFile(newMember);

        readFile();

    }
}
