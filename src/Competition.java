import java.time.LocalDate;

public class Competition {
    String name;
    String timeResult;
    String disciplin;
    int placement; // i forhold til andre
    LocalDate date;

    public Competition(String name, String disciplin, int placement, LocalDate date) {
        this.name = name;
        this.disciplin = disciplin;
        this.placement = placement;
        this.date = date;
    }

    public void addCompetitionTime(String time) {
        this.timeResult = time;
    }

    public void showCompetitionTime() {
        if (timeResult == null) {
            System.out.println("Der er ingen tid for denne disciplin");
        } else {
            System.out.println("Tid for: " + name + "i disciplinen: " + disciplin + ": " + timeResult);
        }
    }
}

