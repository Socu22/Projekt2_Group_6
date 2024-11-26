import java.time.LocalDate;
import java.util.Scanner;

public class Inputhandlertest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int age = InputHandler.inputInt("Indtast din alder: ");
        System.out.println("Din alder er: " + age);
        String navn = InputHandler.inputString("indtast navn ");
        System.out.println("Dit navn er " + navn);
        double pengePåKonto = InputHandler.inputDouble("Indtast hvor mange penge du har");
        System.out.println("Du har så mange penge: " + pengePåKonto);
        LocalDate birthdate = InputHandler.inputDate("Indtast din fødselsdato");

    }
    }
