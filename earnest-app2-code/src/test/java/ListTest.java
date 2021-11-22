import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {
    @Test
    public void test_Clear() {
        List list = new List();
        ObservableList<Item> inventoryList = FXCollections.observableArrayList();
        inventoryList.add(0,new Item("A", "Test", "Here"));

        list.clear(inventoryList);

        assertTrue(inventoryList.isEmpty());
    }

}