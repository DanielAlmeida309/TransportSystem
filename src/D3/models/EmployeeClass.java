package D3.models;

import java.util.LinkedList;
import java.util.List;

public class EmployeeClass implements Employee {
    protected String name;
    protected List<String> permissions = new LinkedList<String>();

    @Override
    public String get_name() {
        return null;
    }

    @Override
    public List<String> getPermissions() {
        return null;
    }
}
