package ru.dne.constable.configuration.duration;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parser for custom duration format
 */
public class DurationParser {

    /*
    Custom format RegEx pattern
     */
    private final @NotNull Pattern pattern = Pattern.compile("(\\d+)(ms|h|m|s)");

    /**
     * Parse formatted string to {@link Duration}
     * @param formatted {@link String}
     * @return {@link Duration} or null
     */
    public @Nullable Duration parse(@Nullable String formatted) {
        if (formatted == null) return null;

        // Match with regexp
        Matcher matcher = pattern.matcher(formatted);
        Duration duration = Duration.ZERO;

        while (matcher.find()) {
            duration = duration.plus(parseSingle(matcher));
        }

        return duration;
    }

    /**
     * Parse single pair
     * @param matcher {@link Matcher}
     * @return {@link Duration}
     */
    private Duration parseSingle(Matcher matcher) {
        long val = Long.parseLong(matcher.group(1));
        String unit = matcher.group(2);

        switch (unit) {
            case "h":
                return Duration.ofHours(val);
            case "m":
                return Duration.ofMinutes(val);
            case "s":
                return Duration.ofSeconds(val);
            default:
                return Duration.ofMillis(val);
        }
    }

}
