package core;

import java.util.Scanner;

/**
 * Entry Point
 */
public class Runner {

    private final AcronymMap acronymMap ;

    public static void main (String... args) {
        new Runner().startProgram();

    }

    public Runner() {
        //initialise
        acronymMap = new AcronymMap();
        HardCodedAcronymMapLoader acronymMapLoader = new HardCodedAcronymMapLoader();
        acronymMapLoader.load(acronymMap);
    }

    private void startProgram() {
        String userInput = getUserInput();
        outputAcronym(userInput.toUpperCase());
    }

    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Letters in Acronym:" );
        String userInput = scanner.next();
        System.out.println("You entered: " + userInput + System.lineSeparator());
        return userInput;
    }

    private void outputAcronym(String userInput) {
        AcronymDetail value = acronymMap.getValues(userInput);

        String acronym = value.getAcronym();
        if (Utils.NULL_STRING.equals(acronym)) {
            System.out.println("Acronym Not Found - sorry");
        }
        else {
            String answer = String.format("Here's some help: \"%s\"\t\tcan be mapped to\t\"%s\"", acronym, value.getExpandedAcronym());
            System.out.println(answer);
        }
    }
}