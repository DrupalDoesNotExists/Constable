package ru.dne.constable.queue;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Container for merged events
 */
@Getter
public class MergedEventsContainer {

    /*
    Child events
     */
    private final @NotNull List<Event> children = new ArrayList<>();

    /**
     * Merge new {@link Event} to this
     * @param event {@link Event}
     */
    public void add(@NotNull Event event) { children.add(event); }

    /**
     * Shuffle internal event order and return then
     * @return shuffled {@link List<Event>}
     */
    public @NotNull List<Event> shuffle() {
        Collections.shuffle(children);
        return children;
    }

}
