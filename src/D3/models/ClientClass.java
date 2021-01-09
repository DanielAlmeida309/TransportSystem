package D3.models;

import java.util.LinkedList;
import java.util.List;

public class ClientClass implements Client{
    private String name;
    private Employee manager = new ManagerClass();
    private List<Item> inventory = new LinkedList<Item>();
    private List<Deposit> deposits = new LinkedList<Deposit>();
    private List<Delivery> deliveries = new LinkedList<Delivery>();

}




