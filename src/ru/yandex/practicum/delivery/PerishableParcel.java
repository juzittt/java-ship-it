package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel { // Скоропортящаяся посылка

    protected int timeToLive;

    protected static final int DELIVERY_PRICE = 3;
    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {
        return timeToLive + sendDay < currentDay;
    }

    @Override
    protected int getBaseCost() {
        return DELIVERY_PRICE;
    }
}

