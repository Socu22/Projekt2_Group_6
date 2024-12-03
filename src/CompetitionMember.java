import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitionMember extends Member {
    private String hold;
    private ArrayList<Competition> competitions = new ArrayList<>();

    public CompetitionMember(String name, LocalDate birthdate) {
        super(name, birthdate);
    }

    public void addCompetitionPerformance() {
        System.out.println("Tilføj stævne-performance for: " + getName());

        String competitionName = InputHandler.inputString("Indtast stævnets navn:");
        LocalDate competitionDate = InputHandler.inputDate("Indtast stævnets dato:");
        String discipline = InputHandler.inputString("Indtast disciplin:");
        double time = InputHandler.inputDouble("Indtast tid (sekunder):");
        int placement = InputHandler.inputInt("Indtast placering:");

        Competition competition = new Competition(competitionName, discipline, placement, competitionDate);
        competition.addCompetitionTime(time);
        competitions.add(competition);

        System.out.println("Performance gemt for stævnet: " + competitionName);
    }

    public void viewCompetitions() {
        System.out.println("Stævneinformation for: " + getName());
        if (competitions.isEmpty()) {
            System.out.println("Ingen registrerede stævner endnu.");
        } else {
            for (Competition competition : competitions) {
                System.out.println(competition);
            }
        }
    }

    public void assignHold() {
        int age = getAge();
        if (age < 18) {
            this.hold = "Juniorhold";
        } else if (age <= 60) {
            this.hold = "Seniorhold";
        } else {
            this.hold = "Ældrehold";
        }
        System.out.println(getName() + " er blevet tildelt til hold: " + hold);
    }

    public String getHold() {
        return hold;
    }
}
