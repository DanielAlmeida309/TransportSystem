package D3.models;

public class DriverClass extends EmployeeClass implements Driver {
    public DriverClass(String name, String permission) {
       this.name = name;
       this.permissions.add(permission);
    }
}
