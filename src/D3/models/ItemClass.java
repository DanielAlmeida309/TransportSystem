package D3.models;

import java.util.LinkedList;
import java.util.List;

public class ItemClass implements Item {
    private String name;
    private int quantity;
    private String[] permissions;

    public ItemClass(String nameItem, String[] permissions) {
        this.name = nameItem;
        this.quantity = 0;
        this.permissions = permissions;
    }
    public ItemClass(String nameItem, int quantity){
        this.name = nameItem;
        this.quantity = quantity;
    }

    @Override
    public int get_quantity() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<String> getPermissions() {
        return null;
    }

    @Override
    public void add_quantity(int s) {

    }

    @Override
    public String get_name() {
        return null;
    }

    @Override
    public void remove_quantity(int i) {

    }
}
