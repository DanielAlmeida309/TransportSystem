package D3.models;

import java.io.Serializable;

public class LocalClass implements Local, Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;

    public LocalClass(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
