package core;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Entry Point
 */
public class Runner {

    private final MnemonicMap mnemonicMap;

    public static void main (String... args) throws IOException {
        new Runner().startProgram();

    }

    public Runner() throws IOException {
        //initialise
        mnemonicMap = new MnemonicMap();
//        MnemonicMapLoader acronymMapLoader = new HardCodedMnemonicMapLoader();
        String root = "src/main/resources/";
        MnemonicMapLoader acronymMapLoader = new FileMnemonicMapLoader(new File(root, "Slogans.txt"), new File(root, "SongTitles.txt"));
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
            System.out.format("There %s %s matching values%n", values.size() >1 ? "are" : "is", values.size());
            System.out.format("Here's some help%n");
            for (MatchingMnemonic value : values) {
                System.out.format("\"%s\"\t\tcan be mapped to\t\"%s\"\tOrigin: \t%s (%s)%n", value.getAcronym(), value.getDetail().getExpandedAcronym(), value.getDetail().getOrigin(), value.getDetail().getCategory());
            }
        }
    }
}