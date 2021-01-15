package D3.models;


public class LoaderClass extends EmployeeClass implements Loader {
    private static final long serialVersionUID = 1L;

    public LoaderClass(String name, String permission) {
        this.name = name;
        this.permissions.add(permission);
    }
}
