import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;

public class TrainTime implements Comparable<TrainTime>{
    private String modtagetMemberName;
    private int modtagetMemberID;
    private LocalDate date;         //Remembers which day the time was set
    private double duration;        //How fast was the training
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
        this.duration = timeSec;
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
        double timeInMilliSecs = sec * 100;
        int minutes = 0;
        int seconds = 0;
        int milliseconds = 0;

        for (int i = 0; i<timeInMilliSecs; i++){
            milliseconds++;
            if (milliseconds == 100){
                seconds++;
                if(seconds == 60){
                    minutes++;
                    seconds = 0;
                }
                milliseconds=0;
            }
        }

        return minutes + ":" + seconds + ":" + milliseconds;
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
        //return "\t"+modtagetMemberName+" - (ID: "+modtagetMemberID+") \t\t\t=\t\t "+date + " - " + discipline + " " + distance + "m - " + durationInTime + " minutter";
        return String.format("\uD83D\uDD14: %10s %10s %4s meter - %s - %s (ID: %s)", durationInTime, discipline, distance, date, modtagetMemberName, modtagetMemberID);
    }

    public static void main(String[] args) {
        Member m1 = new Member("Rasmus Falster Jørgensen Skibidi", LocalDate.of(2001,01,02));
        TrainTime t1 = new TrainTime(m1, LocalDate.now(), 200.13, "Crawl",100);
        Member m2 = new Member("Louise Lækkerlæber", LocalDate.of(1998,01,01));
        TrainTime t2 = new TrainTime(m2, LocalDate.now(), 67.91, "RygCrawl", 200);
        System.out.println(t1);
        System.out.println(t2);
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
