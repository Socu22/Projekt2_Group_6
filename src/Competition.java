import java.time.LocalDate;

public class Competition {
    String name;
    double timeResult;
    String disciplin;
    int placement;
    LocalDate date;
    int distance;
    private String modtagetMemberName;

    public Competition(Member member, String name, String disciplin, int placement, LocalDate date, double timeResult, int distance) {
        this.name = name;
        this.disciplin = disciplin;
        this.placement = placement;
        this.date = date;
        this.timeResult = timeResult;
        this.distance = distance;
        this.modtagetMemberName= member.getName();
    }

    public void addCompetitionTime(double time) {
        this.timeResult = time;
    }

    public void showCompetitionTime() {
        if (timeResult == 0.0) {
            System.out.println("Der er ingen tid for denne disciplin");
        } else {
            System.out.println("Tid for: " + name + "i disciplinen: " + disciplin + ": " + timeResult);
        }
    }

    public String compToCSV(Competition c){
        return date + "," + timeResult + "," + disciplin + "," + distance + "," + name + "," + placement + "," + modtagetMemberName;
    }

    public String toString(){
        return "Tid for: " +name+ " i disciplinen: " +disciplin+ " " +distance+ " meter: " +timeResult+ " sekunder" + "\n" +modtagetMemberName+ " placerede nr: " +placement+ " den: "+date;
    }
}
