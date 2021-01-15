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
        return this.quantity;
    }

    @Override
    public List<String> getPermissions() {
        List<String> perm = new LinkedList<String>();
        for(int i=0; i< permissions.length; i++){
            perm.add(permissions[i]);
        }
        return perm;
    }

    @Override
    public void add_quantity(int s) {
        this.quantity =  this.quantity + s;
    }

    @Override
    public String get_name() {
        return this.name;
    }

    @Override
    public void remove_quantity(int i) {
        this.quantity = this.quantity - i;
    }
}
