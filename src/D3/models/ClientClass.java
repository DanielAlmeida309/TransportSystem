package D3.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ClientClass implements Client, Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int idManager;
    private List<Item> inventory = new LinkedList<Item>();
    private List<Deposit> deposits = new LinkedList<Deposit>();
    private List<Delivery> deliveries = new LinkedList<Delivery>();

    public ClientClass(String name, int identificadorFuncionario) {
        this.name = name;
        this.idManager = identificadorFuncionario;
    }

    @Override
    public String get_name() {
        return this.name;
    }

    @Override
    public List<Item> get_inventory() {
        return inventory;
    }

    @Override
    public Delivery getDelivery(int idDelivery) {
        return deliveries.get(idDelivery-1);
    }

    @Override
    public int getIdManager() {
        return idManager;
    }


    @Override
    public List<Deposit> get_deposits() {
        return deposits;
    }

    @Override
    public List<Delivery> get_deliveries() {
        return deliveries;
    }
}




