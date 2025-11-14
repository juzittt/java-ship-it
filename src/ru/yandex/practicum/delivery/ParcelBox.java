package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private final List<T> parcels;
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.parcels = new ArrayList<>();
        this.currentWeight = 0;
    }

    public boolean addParcel(T parcel) {
        if (currentWeight + parcel.weight > maxWeight) {
            System.out.println("Вес посылки " + parcel.description +
                    " превысит максимальный вес коробки. Добавление отменено.");
            return false;
        }
        parcels.add(parcel);
        currentWeight += parcel.weight;
        System.out.println("Посылка " + parcel.description + " добавлена в коробку.");
        return true;
    }


    public List<T> getAllParcels() {
        return new ArrayList<>(parcels);
    }

    public void showContents() {
        if (parcels.isEmpty()) {
            System.out.println("Коробка пуста.");
        } else {
            System.out.println("Содержимое коробки (общий вес: " + currentWeight + " кг):");
            for (T parcel : parcels) {
                System.out.println("- " + parcel.description + " (" + parcel.weight + " кг)");
            }
        }
    }
}

