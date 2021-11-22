import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    public void test_EventCreate() {
        Item i = new Item("A-111-111-111", "Test" , "$00.00");

        assertEquals(i.getSerialNumber(), "A-111-111-111");
        assertEquals(i.getName(), "Test");
        assertEquals(i.getValue(), "$00.00");
    }

    @Test
    public void test_toString() {
        Item i = new Item("A-111-111-111", "Test" , "$00.00");
        String temp = i.toString();
        assertEquals(i.getSerialNumber() + " " + i.getName() + " " + i.getValue(), temp);
    }

    @Test
    public void test_toStringTab() {
        Item i = new Item("A-111-111-111", "Test" , "$00.00");
        String temp = i.toStringTab();
        assertEquals(i.getSerialNumber() + "\t" + i.getName() + "\t" + i.getValue(), temp);
    }
}