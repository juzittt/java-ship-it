package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel { // Стандартная посылка

    public static final int DELIVERY_PRICE = 2;
    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }
}

