package D3.models;

import java.util.LinkedList;
import java.util.List;

public class EmployeeClass implements Employee {
    protected String name;
    protected List<String> permissions = new LinkedList<String>();
}
