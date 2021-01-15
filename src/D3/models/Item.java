package D3.models;

import java.util.List;

public interface Item {
    int get_quantity();

    String getName();

    List<String> getPermissions();

    void add_quantity(int s);

    String get_name();

    void remove_quantity(int i);
}
