package core;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Entry Point
 */
public class Runner {

    private static final List<String> FILE_LIST = Arrays.asList("Slogans.txt", "SongTitles.txt");

    private final MnemonicMap mnemonicMap;

    public static void main (String... args) throws Throwable {
        new Runner(args).startProgram();
    }

    public Runner(String... args) throws Throwable{
        //initialise
        mnemonicMap = new MnemonicMap();
        MnemonicMapLoader acronymMapLoader = new FileMnemonicMapLoader(getBufferedReaders(args));
        acronymMapLoader.load(mnemonicMap);
    }

    private BufferedReader[] getBufferedReaders(String[] args) throws FileNotFoundException {
        return isDebug(args) ? getTestReaders() : getReaders();
    }

    private boolean isDebug(String[] args) {
        return args.length > 0 && args[0].equals(Utils.DEBUG_SET);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void startProgram() {
        header();
        while (true) {
            String userInput = getUserInput();
            outputAcronym(userInput.toUpperCase());
        }
    }

    private void header() {
        System.out.println("************************************************************************");
        System.out.println("** Welcome to my Mnemonic Revision Helper                             **");
        System.out.println("** Please see my GITHUB account at https://github.com/iamrichardjones **");
        System.out.println("** Please enter mnemonics that you want to remember below. To exit    **");
        System.out.println("** press cntrl-C or cmd-c                                             **");
        System.out.println("************************************************************************");
    }

    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.format("%nEnter Letters in Mnemonic:" );
        return scanner.next();
    }

    private void outputAcronym(String userInput) {
        List<MatchingMnemonic> values = mnemonicMap.getValues(userInput);

        if (values.isEmpty()) {
            System.out.println("Mnemonic Not Found - sorry");
        }
        else {
            System.out.format("There %s %s matching values%n", values.size() >1 ? "are" : "is", values.size());
            for (MatchingMnemonic value : values) {
                System.out.format("   \"%s\"\t\tcan be mapped to\t\"%s\"\tOrigin: \t%s (%s)%n", value.getAcronym(), value.getDetail().getExpandedAcronym(), value.getDetail().getOrigin(), value.getDetail().getCategory());
            }
        }
    }

    private BufferedReader[] getReaders() {
        List<BufferedReader> res = new ArrayList<>();
        for (String file: FILE_LIST) {
            InputStream in = getClass().getResourceAsStream("/" + file);
            res.add(new BufferedReader(new InputStreamReader(in)));
        }
        return res.toArray(new BufferedReader[res.size()]);
    }

    private BufferedReader[] getTestReaders() throws FileNotFoundException {
        final String root = "src/main/resources/";
        List<BufferedReader> res = new ArrayList<>();
        for (String file: FILE_LIST) {
            res.add(new BufferedReader(new FileReader(new File(root, file))));
        }
        return res.toArray(new BufferedReader[res.size()]);
    }
}