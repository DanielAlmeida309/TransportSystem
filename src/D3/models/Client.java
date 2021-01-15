package D3.models;

import java.util.List;

public interface Client {

    String get_name();  // retorna nome do cliente

    List<Item> get_inventory();


    Delivery getDelivery(int idDelivery);

    int getIdManager();


    List<Deposit> get_deposits();

    List<Delivery> get_deliveries();
}
