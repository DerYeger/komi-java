package eu.yeger.komi.util;

@FunctionalInterface
public interface ThrowingEventHandler<T extends javafx.event.Event, E extends Exception> {
    void accept(T t) throws E;
}
