package eu.yeger.komi.util;

import javafx.event.EventHandler;

public class ThrowingEventHandlerWrapper {

    public static <T extends javafx.event.Event> EventHandler<T> throwingEventHandlerWrapper(ThrowingEventHandler<T, Exception> throwingEventHandler) {
        return i -> {
            try {
                throwingEventHandler.accept(i);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        };
    }
}
