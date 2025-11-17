package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel { // Стандартная посылка

    protected static final int DELIVERY_PRICE = 2;

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    protected int getBaseCost() {
        return DELIVERY_PRICE;
    }
}

