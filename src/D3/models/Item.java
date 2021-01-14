package D3.models;

import java.util.List;

public interface Item {
    int get_quantity();

    String getName();

    List<String> getPermissions();
}
