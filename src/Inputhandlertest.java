import java.util.Scanner;

public class Inputhandlertest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
            try {
                int age = InputHandler.inputInt("Indtast din alder: ");
                System.out.println("Din alder er: " + age);

            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
