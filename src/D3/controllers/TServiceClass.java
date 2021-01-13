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
        if(category.get_name()==category) {
            return true;
        }
        return false;
    }

    @Override
    public boolean permission_existent(String category) {
        return false;
    }

    @Override
    public boolean has_employee_in_category(String name, String category) {
        for( Employee atual_employee : employees){
            if(atual_employee.get_name()==name){
                if (category == "Condutor" && atual_employee instanceof Driver) {
                    return true;
                }
                else if (category == "Carregador" && atual_employee instanceof Loader) {
                    return true;
                }
                else if (category == "Gestor" && atual_employee instanceof Manager) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int register_employee(String category, String permission, String name) {
        Employee newEmployee;
        if (category.equals("Condutor")) {
            newEmployee = new DriverClass(name, permission);
            this.employees.add(newEmployee);
        }
        else if (category.equals("Carregador")) {
            newEmployee = new LoaderClass(name, permission);
            this.employees.add(newEmployee);
        }
        else if (category.equals("Gestor")) {
            newEmployee = new ManagerClass(name, permission);
            this.employees.add(newEmployee);
        }
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
        item newItem = new ItemClass(nameClient, nameItem);
        this.items.add(newItem);
        return new int[0];
    }

    @Override
    public boolean has_local(String[][], nameLocal) {
        local atual_local = locals.get(idLocal);
        int tam_locals = atual_local.get_locals().size();
        for(int i=0;i<locals.length; i++){
            int idlocal = Integer.parseInt(nameLocal[i][1]);
            if(! (idlocal <= tam_locals) ){
                return false;
        }
        return true;
    }

    @Override
    public boolean has_idLocal(int idLocal) {
            return clients.size() >= idClient;
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
    public boolean has_employee(int idEmployee) {
        return employees.size() >= idEmployee;
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
        Client atual_client = clients.get(idClient - 1);
        int tam_inventory = atual_client.get_inventory().size();
        for(int i=0; i<items.length; i++){
            int idItem = Integer.parseInt(items[i][1]);
            if(! (idItem <= tam_inventory) ){
               return false;
            }
        }
        return true;

    }

    @Override
    public boolean has_emplyees(String[][] idEmployees) {
        employee atual_employee = employees.get(idEmployee -1);
        int tam_employees = atual_employee.get_idEmployees().size();
        for(int i=0; i<employees.lenght; i++){
            int idEmployees = Integer.parseInt(employees[i][1]);
            if(! (idEmployees <= tam_employees) ){
                return false;
            }
        }
        return true;
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
        Client atual_client = clients.get(idClient - 1);
        List<Item> inventory = atual_client.get_inventory();
        for(int i=0; i<items.length; i++){
            int quantItem = Integer.parseInt(items[i][2]);
            int codItem = Integer.parseInt(items[i][1]);
            int quantity = inventory.get(codItem).get_quantity();
            if( quantItem <= quantity ){
                return false;
            }
        }
        return true;
    }

    @Override
    public String register_delivery(int idClient, int idLocal, String[] idEmployees, String[][] items) {
        return null;
    }

    @Override
    public boolean has_item_client(int idClient, int idItem) {
        return false;
    }

    @Override
<<<<<<< Updated upstream
    public boolean has_delivery_client(int idClient, int idDelivery) {
        return false;
    }
=======
    public boolean has_item_client(int idClient, int idItem) {
            Client atual_client = clients.get(idClient - 1);
            list<Item> inventory = atual_client.get_inventory();
            for(int i=0; i<Item().lenght; i++){
                int quantItem = Integer.parseInt(Item[i][2])
                int codItem = Integer.parseInt(Item[i][1]);
                int quantity = inventory.get(idItem).get_item();
                if (quantItem <= quantity){
                    return false;
                }
            }
            return false;
        }
>>>>>>> Stashed changes

    @Override
    public List<String> info_delivery(int idClient, int idDelivery) {
        Delivery delivery = this.clients.get(idClient - 1).getDelivery(idDelivery);
        List<String> stringList = new LinkedList<String>();

        String localName = this.locals.get(delivery.getIdLocal()).getName();
        String driverInfo = "" + delivery.getDriver().getPermissions() + " " + delivery.getDriver().get_name();

        stringList.add(localName);
        stringList.add(driverInfo);

        List<Loader> listLoader = delivery.getLoaders();
        for(Loader loader: listLoader) {
            stringList.add("" + loader.getPermissions() + " " + loader.get_name());
        }

        List<Item> itemList = delivery.getItems();
        for(Item item: itemList) {
            int identificarItem = this.clients.get(idClient).get_inventory().indexOf(item);
            stringList.add("" + identificarItem + " " + item.get_quantity() + " " + item.getName());
        }


        return stringList;
    }

    @Override
<<<<<<< Updated upstream
    public String get_nameEmployee(int idEmployee) {
        return null;
    }

    @Override
    public String get_categoryEmployee(int idEmployee) {
        return null;
    }

    @Override
    public String get_permissionEmployee(int idEmployee) {
        return null;
=======
    public boolean has_delivery_client(int idClient, string[], int idDelivery){
        Client atual_client = clients.get(idClient - 1);
        List<delivery> inventory = atual_client.get_inventory();
        for(int i=0; i< delivery().length; i++){
            int quantDelivery = Integer.parseInt(delivery[i][2]);
            int codDelivery = Integer.parseInt(delivery[i][1]);
            int quantity = inventory.get(idDelivery).get_delivery();
            if( quantDelivery <= quantity ){
                return false;
            }
        }
        return true;
>>>>>>> Stashed changes
    }

    @Override
    public String[] info_depositsE(int idEmployee) {
        return new String[0];
    }

    @Override
    public String[] info_deliveriesE(int idEmployee) {
        return new String[0];
    }

    @Override
    public boolean isManager(int idEmployee) {
        return this.employees.get(idEmployee - 1) instanceof ManagerClass; 
    }

    @Override
    public String get_nameClient(int idClient) {
        return null;
    }

    @Override
    public String get_nameManager(int idClient) {
        return null;
    }

    @Override
    public String[] info_itens(int idClient) {
        return new String[0];
    }

    @Override
    public String[] info_deposits(int idClient) {
        return new String[0];
    }

    @Override
    public String[] info_deliveries(int idClient) {
        return new String[0];
    }

    @Override
    public String[] info_item(int idClient, int idItem) {
        return new String[0];
    }
}
