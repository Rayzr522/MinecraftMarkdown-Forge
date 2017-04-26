package me.rayzr522.minecraftmarkdown;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rayzr522 on 4/25/17.
 */
public class CharReplacer {
    private static final String PATTERN_BASE = "%1$s(.*?)%1$s";

    private Map<Pattern, Character> replacers = new LinkedHashMap<Pattern, Character>();

    public void addReplacer(String sequence, char chatColor) {
        Pattern pattern = Pattern.compile(String.format(PATTERN_BASE, Pattern.quote(sequence)));

        replacers.put(pattern, chatColor);
    }

    public String translate(String input) {
        String output = input;

        for (Map.Entry<Pattern, Character> entry : replacers.entrySet()) {
            Matcher matcher = entry.getKey().matcher(output);
            output = matcher.replaceAll("\u00A7" + entry.getValue() + "$1\u00A7r");
        }

        return output;
    }
}
