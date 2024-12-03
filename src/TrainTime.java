import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;

public class TrainTime implements Comparable<TrainTime>{
    private String modtagetMemberName;
    private int modtagetMemberID;
    private LocalDate date;         //Remember which day the time was set
    private double duration;        //How fast was the trainging
    private double durationinSec;
    private String durationInTime;
    private String discipline;      //The swimming discipline
    private int distance;           //The Distance swam
    private double performance;     //The ratio of Time to distance to measure performance
    private String secOrMin = " sekunder";
    DecimalFormat df = new DecimalFormat("#.00");

    public TrainTime(Member member, LocalDate date, double timeSec, String discipline, int distance){
        this.date = date;
        this.performance = distance/timeSec;
        this.duration = setDuration(timeSec);
        this.durationinSec = timeSec;
        this.discipline = discipline.toUpperCase();
        this.distance = distance;
        this.modtagetMemberName= member.getName();
        this.modtagetMemberID= member.getID();
        this.durationInTime = calTimeFromSec(timeSec);
    }

    double getDurationinSec(){
        return durationinSec;
    }

    String calTimeFromSec(double sec){
        int minutes = 0;
        int seconds = 0;

        for (int i = 0; i<sec; i++){
            seconds++;
            if (seconds == 60){
                minutes++;
                seconds=0;
            }
        }
        return minutes + ":" + seconds;
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

    public double getPerformance(){
        return performance;
    }

    public LocalDate getDate() {
        return date;
    }

    public String toString(){
        return "\t"+modtagetMemberName+"[id("+modtagetMemberID+")]"+date + " - " + discipline + " " + distance + "m - " + durationInTime;
    }

    public static void main(String[] args) {
        Member m1 = new Member("Rasmus", LocalDate.of(2001,01,01));
        TrainTime t1 = new TrainTime(m1, LocalDate.now(), 200, "Crawl",100);
        System.out.println(t1);
    }

    @Override
    public int compareTo(TrainTime o){
        if(this.performance<o.performance)
            return 1;
        else if(o.performance<this.performance)
            return -1;
        return 0;
    }
}
