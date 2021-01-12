package D3.controllers;

import D3.models.*;

import java.util.LinkedList;
import java.util.List;

public class TServiceClass implements TService{
    List<Client> clients = new LinkedList<Client>();
    List<Employee> employees = new LinkedList<Employee>();
    List<Local> locals = new LinkedList<Local>();



    @Override
    public boolean category_existent(String category) {
        return false;
    }

    @Override
    public boolean permission_existent(String category) {
        return false;
    }

    @Override
    public boolean has_employee_in_category(String name, String category) {
        return false;
    }

    @Override
    public int register_employee(String category, String permission, String name) {
        Employee newEmployee;
        if (category == "Condutor") {
            newEmployee = new DriverClass(name, permission);
        }
        else if (category == "Carregador") {
            newEmployee = new LoaderClass(name, permission);
        }
        else if (category == "Gestor") {
            newEmployee = new ManagerClass(name, permission);
        }
        this.employees.add(newEmployee);
        return this.employees.size();
    }

    @Override
    public boolean has_client(String name) {
        for(Client client : clients){
            if(client.get_name() == name){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean has_idClient(int idClient) {
        return clients.size() >= idClient;
    }

    @Override
    public int register_client(String name, int idEmployee) {
        Client newClient = new ClientClass(name, idEmployee);
        this.clients.add(newClient);
        return this.clients.size();
    }

    @Override
    public int[] register_item(String nameClient, String nameItem, String[] permissions) {
        return new int[0];
    }

    @Override
    public boolean has_local(String nameLocal) {
        return false;
    }

    @Override
    public boolean has_idLocal(int idLocal) {
        return false;
    }

    @Override
    public int register_local(String nameLocal) {
        Local newLocal = new LocalClass(nameLocal);
        this.locals.add(newLocal);
        return this.locals.size();
    }

    @Override
    public boolean has_idItem(int idClient, int idItem) {
        return false;
    }

    @Override
    public boolean has_employee(int employee) {
        return false;
    }

    @Override
    public boolean readFile(String nameFile) {
        return false;
    }

    @Override
    public void saveFile(String nameFile) {

    }

    @Override
    public boolean has_items(int idClient, String[][] items) {
        return false;
    }

    @Override
    public boolean has_emplyees(String[] idEmployees) {
        return false;
    }

    @Override
    public boolean drive_have_permission(String[] idEmployees, String[][] items) {
        return false;
    }

    @Override
    public boolean loaders_have_permissions(String[] idEmployees, String[][] items) {
        return false;
    }

    @Override
    public String register_deposit(int idClient, int idLocal, String[] idEmployees, String[][] items) {
        return null;
    }

    @Override
    public boolean have_quant_items(int idClient, String[][] items) {
        return false;
    }

    @Override
    public String register_delivery(int idClient, int idLocal, String[] idEmployees, String[][] items) {
        return null;
    }

    @Override
    public String[] info_client(int idClient) {
        return new String[0];
    }

    @Override
    public boolean has_item_client(int idClient, int idItem) {
        return false;
    }

    @Override
    public String[] info_item(int idClient, int idItem) {
        return new String[0];
    }

    @Override
    public boolean has_delivery_client(int idClient, int idDelivery) {
        return false;
    }

    @Override
    public String[] info_delivery(int idClient, int idDelivery) {
        return new String[0];
    }

    @Override
    public String[] info_employee(int idEmployee) {
        return new String[0];
    }

    @Override
    public boolean isManager(int idEmployee) {
        return this.employees.get(idEmployee - 1) instanceof ManagerClass; 
    }
}
