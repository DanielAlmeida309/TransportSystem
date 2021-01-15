package D3.models;

import java.util.LinkedList;
import java.util.List;

public class ItemClass implements Item {
    private String name;
    private int quantity;
    private String[] permissions;

    public ItemClass(String nameItem, String[] permissions) {
        this.name = nameItem;
        this.permissions = permissions;
    }
}
