package D3.models;

public class ManagerClass extends EmployeeClass implements Manager {
    public ManagerClass(String name, String permission) {
        this.name = name;
        this.permissions.add(permission);
    }
}
