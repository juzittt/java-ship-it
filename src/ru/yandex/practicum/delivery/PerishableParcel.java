package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel { // Скоропортящаяся посылка

    int timeToLive;
    int expired;

    public static final int DELIVERY_PRICE = 3;
    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        if (timeToLive + sendDay >= currentDay){
            return false;
        }
        return true;
    }
}

