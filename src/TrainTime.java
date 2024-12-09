import java.time.LocalDate;

public class TrainTime implements Comparable<TrainTime>{
    private final String modtagetMemberName;
    private final int modtagetMemberID;
    private final LocalDate date;         //Remembers which day the time was set
    private final double duration;        //How fast was the training
    private final String durationInTime;
    private final String discipline;      //The swimming discipline
    private final int distance;           //The Distance swam
    private final double performance;     //The ratio of Time to distance to measure performance

    public TrainTime(Member member, LocalDate date, double timeSec, String discipline, int distance){
        this.date = date;
        this.performance = distance/timeSec;
        this.duration = timeSec;
        this.discipline = discipline.toUpperCase();
        this.distance = distance;
        this.modtagetMemberName= member.getName();
        this.modtagetMemberID= member.getID();
        this.durationInTime = calTimeFromSec(timeSec);
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

    public LocalDate getDate() {
        return date;
    }

    public String toString(){
        //return "\t"+modtagetMemberName+" - (ID: "+modtagetMemberID+") \t\t\t=\t\t "+date + " - " + discipline + " " + distance + "m - " + durationInTime + " minutter";
        return String.format("\uD83D\uDD14: %10s %10s \uD83C\uDFCA\u200D♀\uFE0F%4s Ⓜ\uFE0F  %s \uD83D\uDCC5 - %s (ID: %s)", durationInTime, discipline, distance, date, modtagetMemberName, modtagetMemberID);
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
