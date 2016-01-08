package core;

import java.io.*;

public class FileMnemonicMapLoader implements MnemonicMapLoader {

    private static final String DELIMETER = "\\|";
    private final BufferedReader reader;

    public FileMnemonicMapLoader(File dataFile) throws FileNotFoundException {
        this(new BufferedReader(new FileReader(dataFile)));
    }

    public FileMnemonicMapLoader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void load(MnemonicMap map) throws IOException {
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] split = line.trim().split(DELIMETER);
            System.out.println(split.length);
            if (split.length == 3) {
                map.add(new MatchingMnemonic(split[2], new MatchingMnemonicDetail(split[1], split[0])));
            }
        }
    }
}
