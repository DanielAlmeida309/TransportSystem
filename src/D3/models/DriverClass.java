package D3.models;

import java.io.Serializable;

public class DriverClass extends EmployeeClass implements Driver {
    private static final long serialVersionUID = 1L;

    public DriverClass(String name, String permission) {
       this.name = name;
       this.permissions.add(permission);
    }
}
