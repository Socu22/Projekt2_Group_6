import java.util.*;
import java.util.stream.Collectors;

public class TimeSort {

    private static List<TrainTime> trainTimes = new ArrayList<>(); //List with all traintimes
    private static List<String> distanceKeys;
    private static TimeSort instance= new TimeSort();
    private static Map<String, List<TrainTime>> grouped; // so the grouped keyes can printed out easier
    private static Map<String, List<TrainTime>> groupedByDistance; //this one is here so it can search with distance and not just group like grouped map
    private static String inputDiscipline; //straight forward
    private static String inputDistance; // straight forward

    public static TimeSort getInstance() {
        return instance;
    }

    private static void groupByDisciplines() {

        trainTimes.clear(); // clears if somehow there is something in it before anything else
        for (Member m : MemberHandler.getMemberList()) {
            trainTimes.addAll(m.getTrainTimeList()); // adds all traintimes
        }

        grouped = trainTimes.stream()
                .collect(Collectors.groupingBy(TrainTime::getDiscipline)); //Collect every discipline name/ is the key

        groupedByDistance = trainTimes.stream()//Makes GroupedByDistance Keys
                .collect(Collectors.groupingBy(
                        tt -> tt.getDiscipline() + " " + tt.getDistance() + "m"
                ));

        groupedByDistance.forEach((key, trainTimes) -> trainTimes.sort(// sorts based on Duration, & date
                Comparator.comparingDouble(TrainTime::getDuration)
                        .thenComparing(TrainTime::getDate)
        ));


    }

    private static void selectDiscipline() { //select discipline, and then distance
        System.out.println("Available Disciplines: ");
        List<String> sortedKeys = new ArrayList<>(grouped.keySet());
        Collections.sort(sortedKeys);
        System.out.println(sortedKeys);//prints the grouped keys

        inputDiscipline = InputHandler.inputString("Skriv din discipline").toUpperCase(); // makes sure than distance is in upper case so it follows syntax
        System.out.println("Selected Discipline: " + inputDiscipline);


        System.out.println("Available Distances: ");
        distanceKeys = new ArrayList<>(groupedByDistance.keySet());// it sends all the keys with distance
        distanceKeys.stream()
                .filter(key -> key.startsWith(inputDiscipline))// custom key with input
                .forEach(System.out::println); //and then fancy System.out.Println()

        inputDistance = InputHandler.inputString("Skriv distancen der skal sorters efter:"); //
        System.out.println("Selected Distance: " + inputDistance);
    }

    public static void topFiveForEachDiscipline() {
        groupByDisciplines();
        selectDiscipline();

        String keyInputSearch = inputDiscipline.toUpperCase() + " " + inputDistance + "m";// custom key from input will match with custom key from ealier
        if (groupedByDistance.containsKey(keyInputSearch)) {
            trainTimes = groupedByDistance.get(keyInputSearch);
            System.out.println("\nTop 5 for Group: " + keyInputSearch);
            for (int i = 0; i < Math.min(5, trainTimes.size()); i++) {
                System.out.println(trainTimes.get(i)); //then print
            }
        } else {
            System.out.println("No results found for group: " + keyInputSearch);//if not this happens
        }
    }
    public static List<String> printKnown_DisciplineOrDistance (){
        groupByDisciplines();
        selectDiscipline();
        List<String> strings = Arrays.asList(inputDiscipline,inputDistance);


        return strings;
    }


    public static void main(String[] args) {

    }
}
