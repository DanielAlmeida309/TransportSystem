package D3.models;

import java.util.List;

public class DeliveryClass extends TravelClass implements Delivery {
    private static final long serialVersionUID = 1L;

    public DeliveryClass(int idLocal, Driver driver, List<Loader> loaders, List<Item> cargo) {
        super(idLocal, driver, loaders, cargo);
    }


}
