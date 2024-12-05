import java.time.LocalDate;

public class Competition {
    String name;
    double timeResult;
    String disciplin;
    int placement;
    LocalDate date;
    private String modtagetMemberName;
    private int modtagetMemberID;

    public Competition(Member member, String name, String disciplin, int placement, LocalDate date, double timeResult) {
        this.name = name;
        this.disciplin = disciplin;
        this.placement = placement;
        this.date = date;
        this.timeResult = timeResult;
        this.modtagetMemberName= member.getName();
        this.modtagetMemberID= member.getID();
    }
    public Competition(String name, String disciplin, int placement, LocalDate date, double timeResult){
        this.name = name;
        this.disciplin = disciplin;
        this.placement = placement;
        this.date = date;
        this.timeResult = timeResult;
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

    public String toString(){
        return "Tid for: " +name+ " i disciplinen: " +disciplin+ ": " +timeResult+ "\n" +modtagetMemberName+ " placerede nr: " +placement+ " den: "+date;
    }
}
