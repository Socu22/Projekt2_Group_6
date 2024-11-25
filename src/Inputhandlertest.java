import java.util.Scanner;

public class Inputhandlertest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
            try {
                double age = InputHandler.inputDouble("Indtast din alder: ");
                System.out.println("Din alder er: " + age);
                String navn = InputHandler.inputString("indtast navn ");

            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
