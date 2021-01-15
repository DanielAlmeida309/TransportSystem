package D3.models;


public class ManagerClass extends EmployeeClass implements Manager {
    private static final long serialVersionUID = 1L;

    public ManagerClass(String name, String permission) {
        this.name = name;
        this.permissions.add(permission);
    }
}
