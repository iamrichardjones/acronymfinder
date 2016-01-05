package core;

import java.util.Scanner;

/**
 * Entry Point
 */
public class Runner {

    private final MnemonicMap mnemonicMap;

    public static void main (String... args) {
        new Runner().startProgram();

    }

    public Runner() {
        //initialise
        mnemonicMap = new MnemonicMap();
        HardCodedMnemonicMapLoader acronymMapLoader = new HardCodedMnemonicMapLoader();
        acronymMapLoader.load(mnemonicMap);
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
        MatchingMnemonic value = mnemonicMap.getValues(userInput);

        String acronym = value.getAcronym();
        if (Utils.EMPTY_STRING.equals(acronym)) {
            System.out.println("Acronym Not Found - sorry");
        }
        else {
            String answer = String.format("Here's some help: \"%s\"\t\tcan be mapped to\t\"%s\"\tOrigin: \t%s", acronym, value.getDetail().getExpandedAcronym(), value.getDetail().getOrigin());
            System.out.println(answer);
        }
    }
}