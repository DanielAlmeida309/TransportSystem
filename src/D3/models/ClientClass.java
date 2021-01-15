package D3.models;

import java.util.LinkedList;
import java.util.List;

public class ClientClass implements Client{
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
        return null;
    }

    @Override
    public Delivery getDelivery(int idDelivery) {
        return null;
    }

    @Override
    public int getIdManager() {
        return 0;
    }

    @Override
    public List<Delivery> get_delivery() {
        return null;
    }
}




