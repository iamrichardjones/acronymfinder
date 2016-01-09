package core;

import java.io.*;
import java.util.*;

import static core.Utils.DEFAULT_COLUMN_LENGTH;
import static core.Utils.fixedLengthString;

/**
 * Entry Point
 */
public class Runner {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("I18NMessages", Locale.getDefault());

    private static final List<String> FILE_LIST = Arrays.asList("Slogans.txt", "SongTitles.txt");

    private final MnemonicMap mnemonicMap;

    public static void main (String... args) throws Throwable {
        new Runner(args).startProgram();
    }

    public Runner(String... args) throws Throwable {

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
        System.out.println(BUNDLE.getString("welcomeStars"));
        System.out.println(BUNDLE.getString("welcomeMsg1"));
        System.out.println(BUNDLE.getString("welcomeMsg2"));
        System.out.println(BUNDLE.getString("welcomeMsg3"));
        System.out.println(BUNDLE.getString("welcomeMsg4"));
        System.out.println(BUNDLE.getString("welcomeStars"));
    }

    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.format(BUNDLE.getString("enterLetters"));
        return scanner.next();
    }

    private void outputAcronym(String userInput) {
        List<MatchingMnemonic> values = mnemonicMap.getValues(userInput);

        if (values.isEmpty()) {
            System.out.println(BUNDLE.getString("mnemonicNotFound"));
        }
        else {
            if (values.size() == 1) {
                System.out.format(BUNDLE.getString("matchingOneValues"), values.size());
            }
            else {
                System.out.format(BUNDLE.getString("matchingMoreThanOneValues"), values.size());
            }
            for (MatchingMnemonic value : values) {
                System.out.format("   \"%s\"\t\t%s \t%s\t%s: \t%s (%s)%n",
                        value.getMnemonic(),
                        BUNDLE.getString("canBeMapped"),
                        fixedLengthString(value.getDetail().getExpandedMnemonic(),
                        DEFAULT_COLUMN_LENGTH),
                        BUNDLE.getString("origin"),
                        value.getDetail().getOrigin(),
                        value.getDetail().getCategory());
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