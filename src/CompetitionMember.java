import java.time.LocalDate;
import java.util.ArrayList;

public class CompetitionMember extends Member {
    private String hold;
    private final ArrayList<Competition> competitionList = new ArrayList<>();

    public CompetitionMember(String name, LocalDate birthdate,LocalDate signUpDate, int id, boolean isPaid) {
        super(name, birthdate, signUpDate, id, isPaid);
        assignHold();
    }

    public CompetitionMember(String chosenName, LocalDate chosenDate) {
        super(chosenName, chosenDate);
        assignHold();
    }

    public void addCompetitionPerformance() {
        System.out.println("Tilføj stævne-performance for: " + getName());

        String competitionName = InputHandler.inputString("Indtast stævnets navn:");
        LocalDate competitionDate = InputHandler.inputDate("Indtast stævnets dato:");
        String discipline = InputHandler.inputString("Indtast disciplin:");
        double time = InputHandler.inputDouble("Indtast tid (sekunder):");
        int placement = InputHandler.inputInt("Indtast placering:");
        int distance = InputHandler.inputInt("Indtast distance");

        Competition competition = new Competition(this, competitionName, discipline, placement, competitionDate, time, distance);
        competition.addCompetitionTime(time);
        competitionList.add(competition);

        System.out.println("Performance gemt for stævnet: " + competitionName);
    }

    public void viewCompetitions() {
        System.out.println("Stævneinformation for: " + getName());
        if (competitionList.isEmpty()) {
            System.out.println("Ingen registrerede stævner endnu.");
        } else {
            for (Competition competition : competitionList) {
                System.out.println(competition);
            }
        }
    }

    public void assignHold() {
        if (super.getAge() < 18) {
            this.hold = "Juniorhold";
        } else if (super.getAge() <= 60) {
            this.hold = "Seniorhold";
        } else {
            this.hold = "Ældrehold";
        }
    }

    public ArrayList<Competition> getComepetitionList() {
        return competitionList;
    }


    @Override
    public String toString(){
        return "Velkommen \uD83D\uDC4B\uD83D\uDC4B\uD83D\uDC4B " + super.getName() + " ID: " + super.getID() + "\n\tAlder: " + super.getAge() + "\n\tHold: " + hold + "\n\tMedlemstype: " + super.getMemberType() + "\n" + super.getContingent() + "\n\tMeldt ind den: " + super.getSignUpDate();
    }
}
