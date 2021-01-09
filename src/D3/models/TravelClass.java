package D3.models;

import java.util.LinkedList;
import java.util.List;

public class TravelClass implements Travel {
    private int idLocal;
    private Driver driver;
    private List<Loader> loaders = new LinkedList<Loader>();
    private List<Item> cargo = new LinkedList<Item>();
}
