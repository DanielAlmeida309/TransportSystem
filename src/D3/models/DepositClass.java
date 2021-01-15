package D3.models;

import java.util.List;

public class DepositClass  extends TravelClass implements Deposit {
    private static final long serialVersionUID = 1L;

    public DepositClass(int idLocal, Driver driver, List<Loader> loaders, List<Item> cargo) {
        super(idLocal, driver, loaders, cargo);
    }
}
