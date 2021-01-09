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
    public String register_employee(String category, String permission, String name) {
        return null;
    }

    @Override
    public boolean has_client(String name) {
        return false;
    }

    @Override
    public boolean has_idClient(int idClient) {
        return false;
    }

    @Override
    public String register_client(String name) {
        return null;
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
    public String register_local(String nameLocal) {
        return null;
    }

    @Override
    public boolean has_idItem(int idClient, int idItem) {
        return false;
    }

    @Override
    public boolean has_employee(String employee) {
        return false;
    }

    @Override
    public boolean readFile(String nameFile) {
        return false;
    }

    @Override
    public void saveFile(String nameFile) {

    }
}
