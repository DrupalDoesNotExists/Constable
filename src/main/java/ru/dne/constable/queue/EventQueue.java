package ru.dne.constable.queue;

import lombok.Getter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Queue for events
 */
@Getter
public class EventQueue {

    /*
    Current merged event container
     */
    private final @NotNull MergedEventsContainer current = new MergedEventsContainer();

    /**
     * Merge event into suitable container
     * @param event Event
     */
    public void add(@NotNull Event event) { current.add(event); }

    /**
     * Switch merged events
     * @param shuffle Should shuffle
     * @return Current list of events
     */
    public @NotNull List<Event> tick(boolean shuffle) {
        if (shuffle) current.shuffle();
        List<Event> eventList = new ArrayList<>(current.getChildren());
        current.getChildren().clear();
        return eventList;
    }

}
