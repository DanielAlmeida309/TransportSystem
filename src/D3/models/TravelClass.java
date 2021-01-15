package D3.models;

import java.util.LinkedList;
import java.util.List;

public class TravelClass implements Travel {
    private int idLocal;
    private Driver driver;
    private List<Loader> loaders = new LinkedList<Loader>();
    private List<Item> cargo = new LinkedList<Item>();

    public TravelClass(int idLocal, Driver driver, List<Loader> loaders, List<Item> cargo){
        this.idLocal = idLocal;
        this.driver = driver;
        this.loaders = loaders;
        this.cargo = cargo;
    }

    @Override
    public int getIdLocal() {
        return 0;
    }

    @Override
    public Driver getDriver() {
        return null;
    }

    @Override
    public List<Loader> getLoaders() {
        return null;
    }

    @Override
    public List<Item> getItems() {
        return null;
    }
}
