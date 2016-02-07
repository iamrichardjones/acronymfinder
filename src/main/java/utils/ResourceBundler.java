package utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundler {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("I18NMessages", Locale.getDefault());

    public static String get(String key) {
        return BUNDLE.getString(key);
    }
}