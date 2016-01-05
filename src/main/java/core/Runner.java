package core;

import java.util.List;
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
        List<MatchingMnemonic> values = mnemonicMap.getValues(userInput);

        if (values.isEmpty()) {
            System.out.println("Acronym Not Found - sorry");
        }
        else {
            System.out.format("There are %s matching values%n", values.size());
            System.out.format("Here's some help%n");
            for (MatchingMnemonic value : values) {
                System.out.format("\"%s\"\t\tcan be mapped to\t\"%s\"\tOrigin: \t%s%n", value.getAcronym(), value.getDetail().getExpandedAcronym(), value.getDetail().getOrigin());
            }
        }
    }
}