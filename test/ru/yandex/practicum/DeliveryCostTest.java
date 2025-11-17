package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {
    @Test
    void calculateDeliveryCost_StandardParcel_NormalWeight_CorrectCost() {
        StandardParcel parcel = new StandardParcel("Book", 5, "Moscow", 1);
        int cost = parcel.calculateDeliveryCost();
        assertEquals(10, cost);
    }

    @Test
    void calculateDeliveryCost_FragileParcel_NormalWeight_CorrectCost() {
        FragileParcel parcel = new FragileParcel("Vase", 3, "SPb", 2);
        int cost = parcel.calculateDeliveryCost();
        assertEquals(12, cost);
    }

    @Test
    void calculateDeliveryCost_PerishableParcel_NormalWeight_CorrectCost() {
        PerishableParcel parcel = new PerishableParcel("Cheese", 4,
                "Kazan", 3, 5);
        int cost = parcel.calculateDeliveryCost();
        assertEquals(12, cost);
    }

    @Test
    void calculateDeliveryCost_StandardParcel_ZeroWeight_ZeroCost() {
        StandardParcel parcel = new StandardParcel("Empty box", 0, "Klin", 1);
        int cost = parcel.calculateDeliveryCost();
        assertEquals(0, cost);
    }

    @Test
    void isExpired_PerishableParcel_NotExpired() {
        PerishableParcel parcel = new PerishableParcel("Milk", 1,
                "Kaliningrad", 1, 5);
        boolean expired = parcel.isExpired(5);
        assertFalse(expired);
    }

    @Test
    void isExpired_PerishableParcel_Expired() {
        PerishableParcel parcel = new PerishableParcel("Ice cream", 1,
                "Tver", 2, 3);
        boolean expired = parcel.isExpired(6);
        assertTrue(expired);
    }

    @Test
    void isExpired_PerishableParcel_ExpiresToday() {
        PerishableParcel parcel = new PerishableParcel("Yoghurt", 1,
                "Mytishi", 1, 4);
        boolean expired = parcel.isExpired(5);
        assertFalse(expired);
    }

    @Test
    void addParcel_WithinWeight_LetterAdded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel parcel = new StandardParcel("Document", 3, "Office", 1);

        boolean added = box.addParcel(parcel);

        assertTrue(added);
        assertEquals(1, box.getAllParcels().size());
        assertEquals(3, box.getAllParcels().getFirst().getWeight());
    }

    @Test
    void addParcel_ExactWeight_LetterAdded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(5);
        StandardParcel parcel = new StandardParcel("Book", 5, "Home", 1);

        boolean added = box.addParcel(parcel);

        assertTrue(added);
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void addParcel_OverWeight_LetterNotAdded() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(7);
        StandardParcel parcel = new StandardParcel("Box", 8, "Warehouse", 1);

        boolean added = box.addParcel(parcel);

        assertFalse(added);
        assertTrue(box.getAllParcels().isEmpty());
    }

    // Могу заменить stream().mapToInt на цикл for, но не очень хочется
    @Test
    void addParcel_AddTwoParcelsWithinLimit_SuccessiveAdd() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(10);
        StandardParcel p1 = new StandardParcel("P1", 4, "A", 1);
        StandardParcel p2 = new StandardParcel("P2", 3, "B", 1);

        assertTrue(box.addParcel(p1));
        assertTrue(box.addParcel(p2));
        assertEquals(2, box.getAllParcels().size());
        assertEquals(7, box.getAllParcels().stream().mapToInt(p -> p.getWeight()).sum());
    }

    @Test
    void addParcel_SecondParcelOverWeight_FirstStillThere() {
        ParcelBox<StandardParcel> box = new ParcelBox<>(6);
        StandardParcel p1 = new StandardParcel("P1", 4, "A", 1);
        StandardParcel p2 = new StandardParcel("P2", 3, "B", 1);

        assertTrue(box.addParcel(p1));
        assertFalse(box.addParcel(p2));

        assertEquals(1, box.getAllParcels().size());
        assertEquals("P1", box.getAllParcels().getFirst().getDescription());
    }
}
