package ru.yandex.practicum.delivery;

public interface Trackable {
    default void reportStatus(String newLocation) {
    }
}

