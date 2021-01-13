package D3.models;

import java.util.List;

public interface Travel {
    int getIdLocal();

    Driver getDriver();

    List<Loader> getLoaders();

    List<Item> getItems();
}
