import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;

public class TrainTime {
    private LocalDate date;         //Remember which day the time was set
    private double duration;        //How fast was the trainging
    private String discipline;      //The swimming discipline
    private int distance;           //The Distance swam
    private String secOrMin = " sekunder";
    DecimalFormat df = new DecimalFormat("#.00");

    public TrainTime(LocalDate date, double timeSec, String discipline, int distance){
        this.date = date;
        this.duration = setDuration(timeSec);
        this.discipline = discipline;
        this.distance = distance;
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

    public String toString(){
        return date + " - " + discipline.toUpperCase() + " " + distance + "m - " + df.format(duration) + secOrMin;
    }

    public static void main(String[] args) {
        TrainTime t1 = new TrainTime(LocalDate.now(), 80.4, "Crawl", 1000);
        System.out.println(t1);
    }
}
