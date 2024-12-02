import java.util.*;
import java.util.stream.Collectors;

public class TimeSort {
    static List<TrainTime> trainTimes = new ArrayList<>();
    static Map<String, List<TrainTime>> grouped;
    static String inputDiscipline;
    static String inputDistance;
    static String keyInputSearch;

//Sorts everything about the Member and TrainTimes
    public static void sorter(){
            //adds all traintimes to traintimes Arraylist
        for (Member m : MemberHandler.loadFromDatabase()){
            trainTimes.addAll(m.getTrainTimeList());

        }

        // Group by discipline and distance
        grouped = trainTimes.stream()
                .collect(Collectors.groupingBy(
                        tt -> tt.getDiscipline() + " " + tt.getDistance() + "m" //this is the name of the group and one of the keys value
                ));

        // Sort each group based on duration comparred to all dates
        grouped.forEach((key, list) -> {
            list.sort(Comparator.comparingDouble(TrainTime::getDuration)
                    .thenComparing(TrainTime::getDate));
        });




    }
//prints all options out
    public static void selectDicipline(){
        ArrayList<String> hsukshit=new ArrayList<>();

        // Print Choices:
        System.out.println("Alle discipliner:  ");
        grouped.forEach((key,list)->{
            String[] discipline =(String[]) key.split(" ");
            List<String> disciplineSplit = new ArrayList<>();
            disciplineSplit
                    .add(Arrays.asList(discipline[0]).toString());
            int x;
            boolean control=false;

            hsukshit.add(discipline[0]);
            for (int i = 0; i < hsukshit.size() ; i++) {
                if(!hsukshit.get(i).equals(discipline[0])){
                    control=true;

                }
                if (!control){
                    hsukshit.add(discipline[0]);


                }

            }





            System.out.println(hsukshit);


        });

        //your choice as a key
         inputDiscipline = InputHandler.inputString("Vælg Disciplin: ").toUpperCase();
         inputDistance = InputHandler.inputString("Skriv Distance: ");
         keyInputSearch=inputDiscipline + " " + inputDistance + "m";


    }
    public static void topFiveForEachDiscipline(){
       sorter();
       selectDicipline();

        //Sort Based on discipline and only top5

        grouped.forEach((key, list) -> {
            if(key.equals(keyInputSearch)){
                System.out.println("\nGroup: " + key);
                for (int i = 0; i < 5; i++) {
                    System.out.println(list.get(i));
                }

            }
        });


    }


}
