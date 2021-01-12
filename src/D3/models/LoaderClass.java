package D3.models;

public class LoaderClass extends EmployeeClass implements Loader {
    public LoaderClass(String name, String permission) {
        this.name = name;
        this.permissions.add(permission);
    }
}
