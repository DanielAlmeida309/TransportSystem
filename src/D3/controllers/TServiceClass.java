package D3.controllers;

import D3.models.*;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class TServiceClass implements TService, Serializable {
    private static final long serialVersionUID = 1L;
    List<Client> clients = new LinkedList<Client>();
    List<Employee> employees = new LinkedList<Employee>();
    List<Local> locals = new LinkedList<Local>();



    @Override
    public boolean category_existent(String category) {
        return category.equals("Condutor") ||
                category.equals("Carregador") ||
                category.equals("Gestor");
    }

    @Override
    public boolean permission_existent(String category) {
        return category.equals("N") || category.equals("S") ||  category.equals("P");
    }

    @Override
    public boolean has_employee_in_category(String name, String category) {
        for( Employee atual_employee : employees){
            if(atual_employee.get_name().equals(name)){
                if (category.equals("Condutor") && atual_employee instanceof Driver) {
                    return true;
                }
                else if (category.equals("Carregador") && atual_employee instanceof Loader) {
                    return true;
                }
                else if (category.equals("Gestor") && atual_employee instanceof Manager) {
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
            if(client.get_name().equals(name)){
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
    public int[] register_item(String nameClient, String nameItem, List<String> permissions) {
        for(int i=0; i<clients.size();i++){
            if(clients.get(i).get_name().equals(nameClient)){
                List<Item> inventory = clients.get(i).get_inventory();
                Item newItem = new ItemClass(nameItem, permissions);
                inventory.add(newItem);
                int[] ids = new int[2]; ids[0]= i+1; ids[1] = inventory.size();
                return ids;
            }
        }
        return null;
    }

    @Override
    public boolean has_local(String nameLocal) {
        for(Local local: this.locals) {
            if(local.getName().equals(nameLocal)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean has_idLocal(int idLocal) {
        return this.locals.size() >= idLocal;
    }

    @Override
    public int register_local(String nameLocal) {
        Local newLocal = new LocalClass(nameLocal);
        this.locals.add(newLocal);
        return this.locals.size();
    }

    @Override
    public boolean has_idItem(int idClient, int idItem) {
        return this.clients.get(idClient).get_inventory().size() >= idItem;
    }

    @Override
    public boolean has_employee(int idEmployee) {
        return employees.size() >= idEmployee;
    }

    @Override
    public Object readFile(String nameFile) {
        Object ts;
        try {
            FileInputStream fileInputStream = new FileInputStream(nameFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ts = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return ts;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void saveFile(String nameFile, TService ts) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(nameFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(ts);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public boolean has_items(int idClient, List<Integer> idItems) {
        int inventorySize = this.clients.get(idClient-1).get_inventory().size();
        for(Integer id: idItems) {
            if (inventorySize < id) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean has_employees(List<String> idEmployees) {
        for(String id : idEmployees){
            if(!has_employee(Integer.parseInt(id))){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean driver_have_permission(int idClient, int idEmployee, List<Integer> idItems) {
        List<String> driverPermissions = this.employees.get(idEmployee-1).getPermissions();
        for(Integer idItem: idItems) {
            List<String> itemPermissions = this.clients.get(idClient-1).get_inventory().get(idItem-1).getPermissions();
            for(String p: itemPermissions) {
                System.out.println(p);
                if(!driverPermissions.contains(p)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean loaders_have_permissions(int idClient, int idEmployee, List<Integer> idItems) {
        List<String> driverPermissions = this.employees.get(idEmployee-1).getPermissions();
        for(Integer idItem: idItems) {
            List<String> itemPermissions = this.clients.get(idClient-1).get_inventory().get(idItem-1).getPermissions();
            for(String p: itemPermissions) {
                if(driverPermissions.contains(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int register_deposit(int idClient, int idLocal, List<String> idEmployees, List<Integer> idItems, List<Integer> quantidade) {
        int i, j;
        Driver driver = null;
        Client client;
        List<Loader> loaders = new LinkedList<Loader>();
        List<Item> cargo = new LinkedList<Item>();

        List<Item> inventory = new LinkedList<Item>();
        List<Deposit> deposits = new LinkedList<Deposit>();

//        for(i=0; i<clients.size();i++){ //vai buscar o objeto do cliente
//            if(idClient == i){
//                client = clients.get(i);
//                inventory = client.get_inventory();
//                deposits = client.get_deposits();
//            }
//        }

        inventory = this.clients.get(idClient-1).get_inventory();
        deposits = this.clients.get(idClient-1).get_deposits();

//        for(i=0; i<inventory.size(); i++){
//            for(j=0; j<idItems.size(); j++){
//                int quantItem = quandidade.get(j);
//                if( i == idItems.get(i) ){
//                    Item atualItem = inventory.get(i);
//
//                    System.out.println(this.clients.get(idClient-1).get_inventory().get(i).get_quantity());
//                    this.clients.get(idClient-1).get_inventory().get(i).add_quantity(quantItem);
//                    System.out.println(this.clients.get(idClient-1).get_inventory().get(i).get_quantity());
//
//                    Item new_item = new ItemClass(atualItem.get_name(), quantItem);
//                    cargo.add(new_item);
//                }
//            }
//        }

        for(i = 0; i < idItems.size(); i++) {
            int quantItem = quantidade.get(i);
            Item atualItem = inventory.get(i);

//            System.out.println(this.clients.get(idClient-1).get_inventory().get(i).get_quantity());
            this.clients.get(idClient-1).get_inventory().get(i).add_quantity(quantItem);
//            System.out.println(this.clients.get(idClient-1).get_inventory().get(i).get_quantity());

            Item new_item = new ItemClass(atualItem.get_name(), quantItem);
            cargo.add(new_item);
        }

//        for(i=0; i<employees.size(); i++){   //vai buscar o objeto do driver inserido
//            if( Integer.parseInt(idEmployees.get(0)) == i+1){
//                driver = (Driver) employees.get(i);
//            }
//        }
//        driver = (Driver) this.employees.get(Integer.parseInt(idEmployees.get(0)));
//
//        for(i=1; i<idEmployees.size(); i++){  //vai buscar os objetos dos loaders inseridos
//            for(j=0; j<employees.size(); j++){
//                if( Integer.parseInt(idEmployees.get(0)) == j+1){
//                    loaders.add( (Loader) employees.get(j) );
//                }
//            }
//        }

        for (i = 0; i < idEmployees.size(); i++) {
            if (this.employees.get(Integer.parseInt(idEmployees.get(i))-1) instanceof Driver) {
                driver = (Driver) this.employees.get(Integer.parseInt(idEmployees.get(i))-1);
            }
            else if (this.employees.get(Integer.parseInt(idEmployees.get(i))-1) instanceof Loader) {
                loaders.add( (Loader) this.employees.get(Integer.parseInt(idEmployees.get(i))-1) );
            }
        }


        Deposit new_deposit = new DepositClass(idLocal, driver, loaders, cargo);
        deposits.add(new_deposit);
        return deposits.size();

    }

    @Override
    public boolean have_quant_items(int idClient, List<Integer> idItems, List<Integer> quantidade) {
        for(int i = 0; i<idItems.size(); i++){
            if (quantidade.get(i) > this.clients.get(idClient-1).get_inventory().get(idItems.get(i)-1).get_quantity()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int register_delivery(int idClient, int idLocal, List<String> idEmployees, List<Integer> idItems, List<Integer> quantidade) {
        int i, j;
        Driver driver = null;
        Client client;
        List<Loader> loaders = new LinkedList<Loader>();
        List<Item> cargo = new LinkedList<Item>();

        List<Item> inventory = new LinkedList<Item>();
        List<Delivery> deliveries = new LinkedList<Delivery>();

//        for(i=0; i<clients.size();i++){ //vai buscar o objeto do cliente
//            if(idClient == i){
//                client = clients.get(i);
//                inventory = client.get_inventory();
//                deliveries = client.get_deliveries();
//            }
//        }

        inventory = this.clients.get(idClient-1).get_inventory();
        deliveries = this.clients.get(idClient-1).get_deliveries();

//        for(i=0; i<=inventory.size();i++){
//            for(j=0;j<idItems.size();j++){
//                int quantItem = quantidade.get(j);
//                if( i == idItems.get(j) ){
//                    Item atualItem = inventory.get(i);
//                    atualItem.remove_quantity( quantItem);
//
//                    Item new_item = new ItemClass(atualItem.get_name(), quantItem);
//                    cargo.add(new_item);
//                }
//            }
//        }

        for(i = 0; i < idItems.size(); i++) {
            int quantItem = quantidade.get(i);
            Item atualItem = inventory.get(i);

//            System.out.println(this.clients.get(idClient-1).get_inventory().get(i).get_quantity());
            this.clients.get(idClient-1).get_inventory().get(i).remove_quantity(quantItem);
//            System.out.println(this.clients.get(idClient-1).get_inventory().get(i).get_quantity());

            Item new_item = new ItemClass(atualItem.get_name(), quantItem);
            cargo.add(new_item);
        }

//        for(i=0; i<employees.size(); i++){   //vai buscar o objeto do driver inserido
//            if( Integer.parseInt(idEmployees.get(0)) == i+1){
//                driver = (Driver) employees.get(i);
//            }
//        }
//        for(i=1; i<idEmployees.size(); i++){  //vai buscar os objetos dos loaders inseridos
//            for(j=0; j<employees.size(); j++){
//                if( Integer.parseInt(idEmployees.get(0)) == j+1){
//                    loaders.add( (Loader) employees.get(j) );
//                }
//            }
//        }

        for (i = 0; i < idEmployees.size(); i++) {
            if (this.employees.get(Integer.parseInt(idEmployees.get(i))-1) instanceof Driver) {
                driver = (Driver) this.employees.get(Integer.parseInt(idEmployees.get(i))-1);
            }
            else if (this.employees.get(Integer.parseInt(idEmployees.get(i))-1) instanceof Loader) {
                loaders.add( (Loader) this.employees.get(Integer.parseInt(idEmployees.get(i))-1) );
            }
        }

        Delivery new_delivery = new DeliveryClass(idLocal, driver, loaders, cargo);
        deliveries.add(new_delivery);
        return deliveries.size();
    }

    @Override
    public boolean has_item_client(int idClient, int idItem) {
        List<Item> inventory = clients.get(idClient-1).get_inventory();
        return inventory.size() >= idItem;
    }

    @Override
    public boolean has_delivery_client(int idClient, int idDelivery) {
        List<Delivery> deliveries = clients.get(idClient-1).get_deliveries();
        return deliveries.size() >= idDelivery;
    }

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
            int identificarItem = this.clients.get(idClient).get_inventory().indexOf(item) + 1;
            stringList.add("" + identificarItem + " " + item.get_quantity() + " " + item.get_name());
        }


        return stringList;
    }

    @Override
    public String get_nameEmployee(int idEmployee) {
        return this.employees.get(idEmployee-1).get_name();
    }

    @Override
    public String get_categoryEmployee(int idEmployee) {
        if (this.employees.get(idEmployee-1) instanceof Driver) {
            return "Condutor";
        }
        else if (this.employees.get(idEmployee-1) instanceof Loader) {
            return "Carregador";
        }
        else if (this.employees.get(idEmployee-1) instanceof Manager) {
            return "Gestor";
        }
        return null;
    }

    @Override
    public List<String> get_permissionEmployee(int idEmployee) {
        List<String> permissions = new LinkedList<String>();
        Employee employee = employees.get(idEmployee-1);
        for( String permission : employee.getPermissions()){
            if(permission.equals("N")) permissions.add("Normal");
            else if (permission.equals("S")) permissions.add("Seguro");
            else if (permission.equals("P")) permissions.add("Perigoso");
        }
        return permissions;
    }

    @Override
    public List<String> info_depositsE(int idEmployee) {
        List<String> inf = new LinkedList<String>();
        if (this.employees.get(idEmployee - 1) instanceof Manager) {
            for(int i = 0; i < this.clients.size(); i++) {
                if (this.clients.get(i).getIdManager() == idEmployee) {
                    for(int j = 0; j < this.clients.get(i).get_deposits().size(); j++) {
                        String nameLocal = this.locals.get(this.clients.get(i).get_deposits().get(j).getIdLocal()-1).getName();
                        String info = (i + 1) + " " + (j + 1) + " (" + nameLocal + ") " + this.clients.get(i).get_name();
                        inf.add(info);
                    }
                }
            }
        }
        else if (this.employees.get(idEmployee - 1) instanceof Driver) {
            for(int i = 0; i < this.clients.size(); i++) {
                for(int j = 0; j < this.clients.get(i).get_deposits().size(); j++) {
                    int idDriver = this.employees.indexOf(this.clients.get(i).get_deposits().get(j).getDriver());
                    if(idDriver == idEmployee-1) {
                        String nameLocal = this.locals.get(this.clients.get(i).get_deposits().get(j).getIdLocal()-1).getName();
                        String info = (i + 1) + " " + (j + 1) + " (" + nameLocal + ") " + this.clients.get(i).get_name();
                        inf.add(info);
                    }
                }
            }
        }
        else if (this.employees.get(idEmployee - 1) instanceof Loader) {
            for(int i = 0; i < this.clients.size(); i++) {
                for(int j = 0; j < this.clients.get(i).get_deposits().size(); j++) {
                    for(int k = 0; k <  this.clients.get(i).get_deposits().get(j).getLoaders().size(); k++) {
                        int idLoader = this.employees.indexOf(this.clients.get(i).get_deposits().get(j).getLoaders().get(k));
                        if (idLoader == idEmployee-1) {
                            String nameLocal = this.locals.get(this.clients.get(i).get_deposits().get(j).getIdLocal()-1).getName();
                            String info = (i + 1) + " " + (j + 1) + " (" + nameLocal + ") " + this.clients.get(i).get_name();
                            inf.add(info);
                        }
                    }
                }
            }
        }

//        int i,j,k;
//        Employee atualEmployee = employees.get(idEmployee - 1);
//        String name = atualEmployee.get_name();
//        List<String> inf = new LinkedList<String>();
//        for(i=0; i<clients.size(); i++){
//            for(k=0; k<clients.get(i).get_deposits().size(); k++){
//                Deposit atualDeposit = clients.get(i).get_deposits().get(k);
//                List<String> nameEmployees = new LinkedList<String>();
//                for(j=0; j< atualDeposit.getLoaders().size(); j++){
//                    nameEmployees.add( atualDeposit.getLoaders().get(j).get_name() );
//                }
//                if( atualDeposit.getDriver().get_name().equals(name) || nameEmployees.contains(name) ){
//                    String infDeposit = i + " " + k + " " + locals.get( atualDeposit.getIdLocal()-1 ) + " " + clients.get(i).get_name();
//                    inf.add(infDeposit);
//                }
//            }
//        }
        return inf;
    }

    @Override
    public List<String> info_deliveriesE(int idEmployee) {
        List<String> inf = new LinkedList<String>();
        if (this.employees.get(idEmployee - 1) instanceof Manager) {
            for(int i = 0; i < this.clients.size(); i++) {
                if (this.clients.get(i).getIdManager() == idEmployee) {
                    for(int j = 0; j < this.clients.get(i).get_deliveries().size(); j++) {
                        String nameLocal = this.locals.get(this.clients.get(i).get_deliveries().get(j).getIdLocal()-1).getName();
                        String info = (i + 1) + " " + (j + 1) + " (" + nameLocal + ") " + this.clients.get(i).get_name();
                        inf.add(info);
                    }
                }
            }
        }
        else if (this.employees.get(idEmployee - 1) instanceof Driver) {
            for(int i = 0; i < this.clients.size(); i++) {
                for(int j = 0; j < this.clients.get(i).get_deliveries().size(); j++) {
                    int idDriver = this.employees.indexOf(this.clients.get(i).get_deliveries().get(j).getDriver());
                    if(idDriver == idEmployee-1) {
                        String nameLocal = this.locals.get(this.clients.get(i).get_deliveries().get(j).getIdLocal()-1).getName();
                        String info = (i + 1) + " " + (j + 1) + " (" + nameLocal + ") " + this.clients.get(i).get_name();
                        inf.add(info);
                    }
                }
            }
        }
        else if (this.employees.get(idEmployee - 1) instanceof Loader) {
            for(int i = 0; i < this.clients.size(); i++) {
                for(int j = 0; j < this.clients.get(i).get_deliveries().size(); j++) {
                    for(int k = 0; k <  this.clients.get(i).get_deliveries().get(j).getLoaders().size(); k++) {
                        int idLoader = this.employees.indexOf(this.clients.get(i).get_deliveries().get(j).getLoaders().get(k));
                        if (idLoader == idEmployee-1) {
                            String nameLocal = this.locals.get(this.clients.get(i).get_deliveries().get(j).getIdLocal()-1).getName();
                            String info = (i + 1) + " " + (j + 1) + " (" + nameLocal + ") " + this.clients.get(i).get_name();
                            inf.add(info);
                        }
                    }
                }
            }
        }
//        int i,j,k;
//        Employee atualEmployee = employees.get(idEmployee - 1);
//        String name = atualEmployee.get_name();
//        String[] inf = new String[0];
//        for(i=0; i<clients.size(); i++){
//            for(k=0; k<clients.get(i).get_deliveries().size(); k++){
//                Delivery atualDelivery = clients.get(i).get_deliveries().get(k);
//                List<String> nameEmployees = new LinkedList<String>();
//                for(j=0; j< atualDelivery.getLoaders().size(); j++){
//                    nameEmployees.add( atualDelivery.getLoaders().get(j).get_name() );
//                }
//                if( atualDelivery.getDriver().get_name().equals(name) || nameEmployees.contains(name) ){
//                    String infDelivery = i + " " + k + " " + locals.get( atualDelivery.getIdLocal()-1 ) + " " + clients.get(i).get_name();
//                    inf[i] = infDelivery;
//                }
//            }
//        }
        return inf;
    }

    @Override
    public boolean isManager(int idEmployee) {
        return this.employees.get(idEmployee - 1) instanceof ManagerClass; 
    }

    @Override
    public String get_nameClient(int idClient) {
        return this.clients.get(idClient-1).get_name();
    }

    @Override
    public String get_nameManager(int idClient) {
        int idManager =  this.clients.get(idClient-1).getIdManager();
        return this.employees.get(idManager-1).get_name();
    }

    @Override
    public List<String> info_itens(int idClient) {
        List<String> strList = new LinkedList<String>();
        for(int i = 0; i < this.clients.get(idClient-1).get_inventory().size(); i++) {
            strList.add(this.info_item2(idClient,i + 1 ));
        }
        return strList;
    }

    @Override
    public List<String> info_depositsCC(int idClient) {
        List<String> info = new LinkedList<String>();
        Client atualClient = clients.get(idClient-1);
        List<Deposit> deposits = atualClient.get_deposits();
        for(int i=0; i<deposits.size(); i++){
            int idLocal = deposits.get(i).getIdLocal();
            String infDeposit = (i + 1) + " (" + locals.get(idLocal-1).getName() + ")";
            info.add(infDeposit);
        }
        return info;
    }

    @Override
    public List<String> info_deliveriesCC(int idClient) {
        List<String> info = new LinkedList<String>();
        Client atualClient = clients.get(idClient-1);
        List<Delivery> deliveries = atualClient.get_deliveries();
        for(int i=0; i<deliveries.size(); i++){
            int idLocal = deliveries.get(i).getIdLocal();
            String infDelivery = (i + 1) + " (" + locals.get(idLocal-1).getName() + ")";
            info.add(infDelivery);
        }
        return info;
    }

    @Override
    public List<String> info_depositsCI(int idClient, int idItem) {
        Client atualClient = clients.get(idClient-1);
        String nameItem = atualClient.get_inventory().get(idItem-1).get_name();
        List<Deposit> deposits = atualClient.get_deposits();
        List<String> inf = new LinkedList<String>();
        for(int i=0; i<deposits.size(); i++){
            List<Item> cargo = deposits.get(i).getItems();
            for(int j=0; j<cargo.size(); j++){
                if(cargo.get(j).get_name().equals(nameItem)){
                    String infDeposit = (i + 1)  + " " + cargo.get(j).get_quantity();
                    inf.add(infDeposit);
                }
            }
        }
        return inf;
    }

    @Override
    public List<String> info_deliveriesCI(int idClient, int idItem) {
        Client atualClient = clients.get(idClient-1);
        String nameItem = atualClient.get_inventory().get(idItem-1).get_name();
        List<Delivery> deliveries = atualClient.get_deliveries();
        List<String> inf = new LinkedList<String>();
        for(int i=0; i<deliveries.size(); i++){
            List<Item> cargo = deliveries.get(i).getItems();
            for(int j=0; j<cargo.size(); j++){
                if(cargo.get(j).get_name().equals(nameItem)){
                    String infDelivery = (i + 1) + " " + cargo.get(j).get_quantity();
                    inf.add(infDelivery);
                }
            }
        }
        return inf;
    }

    @Override
    public String info_item(int idClient, int idItem) {
        Item item = this.clients.get(idClient-1).get_inventory().get(idItem-1);

        List<String> namePermissions = new LinkedList<String>();
        for(String p: item.getPermissions()){
            if(p.equals("N")) namePermissions.add("Normal");
            else if (p.equals("S")) namePermissions.add("Seguro");
            else if (p.equals("P")) namePermissions.add("Perigoso");
        }

        String permissions = String.join(", ", namePermissions);
        String str = "" + item.get_quantity() + " [" + permissions + "] " + item.get_name();
        return str;
    }

    private String info_item2(int idClient, int idItem) {
        Item item = this.clients.get(idClient-1).get_inventory().get(idItem-1);

        String permissions = String.join(", ", item.getPermissions());
        String str = idItem + " (" + item.get_quantity() + ") [" + permissions + "] " + item.get_name();
        return str;
    }

}
