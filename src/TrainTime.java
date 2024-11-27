import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;

public class TrainTime {
    private String modtagetMemberName;
    private int modtagetMemberID;
    private LocalDate date;         //Remember which day the time was set
    private double duration;        //How fast was the trainging
    private String discipline;      //The swimming discipline
    private int distance;           //The Distance swam
    private String secOrMin = " sekunder";
    DecimalFormat df = new DecimalFormat("#.00");

    public TrainTime(Member member, LocalDate date, double timeSec, String discipline, int distance){
        this.date = date;
        this.duration = setDuration(timeSec);
        this.discipline = discipline.toUpperCase();
        this.distance = distance;
        this.modtagetMemberName= member.getName();
        this.modtagetMemberID= member.getID();
    }

    double setDuration(double durationInSec){
        if (durationInSec >= 60){
            this.secOrMin = " minutter";
            return durationInSec/60;
        }
        else{
            return durationInSec;
        }
    }

    public double getDuration() {
        return duration;
    }

    public String getDiscipline() {
        return discipline;
    }

    public int getDistance() {
        return distance;
    }

    public LocalDate getDate() {
        return date;
    }

    public String toString(){
        return "\t"+modtagetMemberName+"[id("+modtagetMemberID+")]"+date + " - " + discipline + " " + distance + "m - " + df.format(duration) + secOrMin;
    }

    public static void main(String[] args) {
       // TrainTime t1 = new TrainTime(LocalDate.now(), 80.4, "Crawl", 1000);
       // System.out.println(t1);
    }
}
