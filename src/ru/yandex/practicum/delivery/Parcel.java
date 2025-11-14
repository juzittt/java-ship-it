package ru.yandex.practicum.delivery;

public abstract class Parcel {
    public String description;
    public int weight;
    String deliveryAddress;
    int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem(){
        System.out.println("Посылка " + description + " упакована");
    }

    public void deliver(){
        System.out.println("Посылка " + description + " доставлена по адресу " + deliveryAddress);
    }

    public <T extends Parcel> int calculateDeliveryCost(T parcel) {
        int deliveryCost = 0;
        if (parcel.getClass() == StandardParcel.class) {
            deliveryCost =  weight * StandardParcel.DELIVERY_PRICE;
        } else if (parcel.getClass() == PerishableParcel.class) {
            deliveryCost = weight * PerishableParcel.DELIVERY_PRICE;
        } else if (parcel.getClass() == FragileParcel.class) {
            deliveryCost = weight * FragileParcel.DELIVERY_PRICE;
        }
        return deliveryCost;
    }
}

