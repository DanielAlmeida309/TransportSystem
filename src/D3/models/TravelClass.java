package D3.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TravelClass implements Travel, Serializable {
    private static final long serialVersionUID = 1L;
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
        return this.idLocal;
    }

    @Override
    public Driver getDriver() {
        return this.driver;
    }

    @Override
    public List<Loader> getLoaders() {
        return this.loaders;
    }

    @Override
    public List<Item> getItems() {
        return this.cargo;
    }

}
