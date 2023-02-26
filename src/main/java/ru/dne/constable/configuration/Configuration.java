package ru.dne.constable.configuration;

import org.jetbrains.annotations.NotNull;

import lombok.RequiredArgsConstructor;

import org.jetbrains.annotations.Nullable;
import ru.dne.constable.Constable;
import ru.dne.constable.configuration.duration.DurationParser;

import java.time.Duration;

/**
 * Global configuration implementation
 *
 * @author DNE
 */
@RequiredArgsConstructor
public enum Configuration {

    // MERGING
    ENABLE_MERGING("merging.enable", true),
    MERGING_THRESHOLD("merging.threshold", "125ms"),

    // SHUFFLING
    ENABLE_SHUFFLING("shuffling.enable", true)
    ;

    /*
    Custom duration format parser
     */
    private final static @NotNull DurationParser durationParser = new DurationParser();

    /*
    Yaml configuration path
     */
    private final @NotNull String path;

    /*
    Default value
     */
    private final @Nullable Object def;

    /**
     * Raw {@link Object} value
     * @return {@link Object} or null
     */
    public @Nullable Object raw() {
        return Constable.getConstable().getConfig().get(path, def);
    }

    /**
     * {@link Boolean} value
     * @return {@link Boolean} or null
     */
    public @Nullable Boolean bool() { return (Boolean) raw(); }

    /**
     * {@link String} value
     * @return {@link String} or null
     */
    public @Nullable String string() { return (String) raw(); }

    /**
     * {@link Integer} value
     * @return {@link Integer} or null
     */
    public @Nullable Integer integer() { return (Integer) raw(); }

    /**
     * {@link Duration} value
     * @return {@link Duration} or null
     */
    public @Nullable Duration duration() { return durationParser.parse(string()); }

}
