package D3.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class EmployeeClass implements Employee, Serializable {
    private static final long serialVersionUID = 1L;
    protected String name;
    protected List<String> permissions = new LinkedList<String>();

    @Override
    public String get_name() {
        return this.name;
    }

    @Override
    public List<String> getPermissions() {
        return permissions;
    }
}
