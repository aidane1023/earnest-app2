import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorMessageTest {
    @Test
    public void test_serialFormat() {
        ErrorMessage errorMessage = new ErrorMessage();
        ObservableList<Item> list = FXCollections.observableArrayList();
        Item item1 = new Item("A-111-111-111", "Test", "$00.00");
        Item item2 = new Item("A-121-111-111", "Test", "$00.00");
        Item item3 = new Item("A-131-111-111", "Test", "$00.00");

        list.add(item1);

        assertFalse(errorMessage.serialFormat("A", list));
        assertFalse(errorMessage.serialFormat("A-111-111-111", list));
        assertTrue(errorMessage.serialFormat("A-141-111-111", list));
    }

}