import java.util.*;
import java.util.stream.Collectors;

public class TimeSort {

    private static List<TrainTime> trainTimes = new ArrayList<>();
    private static Map<String, List<TrainTime>> grouped;
    private static String inputDiscipline;
    private static String inputDistance;

    public static void groupByDisciplines() {
        trainTimes.clear();
        for (Member m : MemberHandler.loadFromDatabase()) {
            trainTimes.addAll(m.getTrainTimeList());
        }

        grouped = trainTimes.stream()
                .collect(Collectors.groupingBy(TrainTime::getDiscipline));

        grouped.forEach((key, list) -> list.sort(
                Comparator.comparingDouble(TrainTime::getDuration)
                        .thenComparing(TrainTime::getDate)
        ));
    }

    public static void selectDiscipline() {
        System.out.println("Available Disciplines: ");
        List<String> sortedKeys = new ArrayList<>(grouped.keySet());
        Collections.sort(sortedKeys);
        System.out.println(sortedKeys);

        inputDiscipline = InputHandler.inputString("Skriv din discipline").toUpperCase();
        System.out.println("Selected Discipline: " + inputDiscipline);

        Map<String, List<TrainTime>> groupedByDistance = trainTimes.stream()
                .collect(Collectors.groupingBy(
                        tt -> tt.getDiscipline() + " " + tt.getDistance() + "m"
                ));

        System.out.println("Available Distances: ");
        List<String> distanceKeys = new ArrayList<>(groupedByDistance.keySet());
        distanceKeys.stream()
                .filter(key -> key.startsWith(inputDiscipline))
                .forEach(System.out::println);

        inputDistance = InputHandler.inputString("Skriv distancen der skal sorters efter:");
        System.out.println("Selected Distance: " + inputDistance);
    }

    public static void topFiveForEachDiscipline() {
        groupByDisciplines();
        selectDiscipline();

        String keyInputSearch = inputDiscipline + " " + inputDistance + "m";
        if (grouped.containsKey(keyInputSearch)) {
            List<TrainTime> list = grouped.get(keyInputSearch);
            System.out.println("\nTop 5 for Group: " + keyInputSearch);
            for (int i = 0; i < Math.min(5, list.size()); i++) {
                System.out.println(list.get(i));
            }
        } else {
            System.out.println("No results found for group: " + keyInputSearch);
        }
    }


    public static void main(String[] args) {

    }
}
