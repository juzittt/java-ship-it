package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Parcel> allParcels = new ArrayList<>();
    private static final List<Trackable> trackableParcels = new ArrayList<>();
    private static final ParcelBox<StandardParcel> standardBox = new ParcelBox<>(50);
    private static final ParcelBox<FragileParcel> fragileBox = new ParcelBox<>(25);
    private static final ParcelBox<PerishableParcel> perishableBox = new ParcelBox<>(35);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    trackParcels();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить посылку");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже
    private static void addParcel() {
        System.out.println("""
        Введите тип посылки:
        1 - Стандартная
        2 - Хрупкая
        3 - Скоропортящаяся""");
        int type = Integer.parseInt(scanner.nextLine());

        System.out.println("Описание:");
        String description = scanner.nextLine();

        System.out.println("Вес (в кг):");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.println("Адрес доставки:");
        String deliveryAddress = scanner.nextLine();

        System.out.println("День отправки:");
        int sendDay = Integer.parseInt(scanner.nextLine());

        switch (type) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                if (standardBox.addParcel(standardParcel)) {
                    allParcels.add(standardParcel);
                    System.out.println("Стандартная посылка добавлена.");
                }
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                if (fragileBox.addParcel(fragileParcel)) {
                    allParcels.add(fragileParcel);
                    trackableParcels.add(fragileParcel);
                    System.out.println("Хрупкая посылка добавлена.");
                }
                break;
            case 3:
                System.out.println("Срок хранения (в днях):");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishableParcel = new PerishableParcel(description, weight,
                        deliveryAddress, sendDay, timeToLive);
                if (perishableBox.addParcel(perishableParcel)) {
                    allParcels.add(perishableParcel);
                    System.out.println("Скоропортящаяся посылка добавлена.");
                }
                break;
            default:
                System.out.println("Неизвестный тип посылки.");
                break;
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
        // Пройти по allParcels, вызвать packageItem() и deliver()
    }

    private static void calculateCosts() {
        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost(parcel);
        }
        System.out.println("Общая стоимость доставки: " + totalCost);
    }

    private static void trackParcels() {
        if (trackableParcels.isEmpty()) {
            System.out.println("Нет посылок для трекинга.");
            return;
        }

        System.out.println("Введите новое местоположение:");
        String location = scanner.nextLine();

        for (Trackable parcel : trackableParcels) {
            parcel.reportStatus(location);
        }
    }

    private static void showBoxContents() {
        System.out.println("Выберите тип коробки для просмотра:");
        System.out.println("1. Стандартные посылки");
        System.out.println("2. Хрупкие посылки");
        System.out.println("3. Скоропортящиеся посылки");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                standardBox.showContents();
                break;
            case "2":
                fragileBox.showContents();
                break;
            case "3":
                perishableBox.showContents();
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }
}



