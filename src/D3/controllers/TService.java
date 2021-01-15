package D3.controllers;

import java.util.List;

public interface TService {
    boolean category_existent(String category); //category_existent deve retornar true se existir categoria

    boolean permission_existent(String category); //permission_existent deve retornar true se existir permissão

    boolean has_employee_in_category(String name, String category); //has_employee_in_category retorna true se existir um empregado com o mesmo nome na mesma categoria

    int register_employee(String category, String permission, String name);  //register_employee regista o empregado e retorna o id atribuido a ele pelo sistema

    boolean has_client(String name); //has_client deve retornar true se já existir um cliente com o mesmo nome inserido senao retorna false

    boolean has_idClient(int idClient);  //has_idclient  deve retornar true se já existir o id do cliente associado a um cliente ou false se nao existir

    int register_client(String name, int idEmployee); //register_client regista o cliente e retorna o id atribuido a ele pelo sistema

    int[] register_item(String nameClient, String nameItem, String[] permissions); //register_item regista o item e retorna o id do cliente e o id do item   -- vetor posiçao 0- id cliente   1- id produto

    boolean has_local(String nameLocal);    //has_local deve retornar true se já existir um local com o mesmo nome inserido

    boolean has_idLocal(int idLocal); //has_idLocal  deve retornar true se já existir o id do local associado a um local ou false se nao existir

    int register_local(String nameLocal);     //register_local regista o local e retorna o id do local

    boolean has_idItem(int idClient, int idItem); // has_idItem se o cliente tiver o item com o idItem retorna true senao retorna false

    boolean has_employee(int idEmployee); // has_employee retorna true se já existir empregado senao retorna false

    boolean readFile(String nameFile);

    void saveFile(String nameFile);

    boolean has_items(int idClient, String[][] items); // has_items recebe uma matriz de items (idItem, Quantidade), verificar cada item se já existe esse item registado, se existir retornar true logo, se nenhum existir retornar false

    boolean has_employees(String[] idEmployees); // has_emplyees recebe um vetor de id de empregados, verificar se todos os ids existem caso não existe um retorna false se existir retorna true

    boolean driver_have_permission(String idEmployee, String[][] items); // drive_have_permission vai verificar as permissoes dos items e depois verifica qual é o empregado da lista de empregados que é o condutor e verifica se este tem as devidas permissões para transpostar os items, caso tenha retorna true, caso nao tenha retorna false

    boolean loaders_have_permissions(String[] idEmployees, String[][] items); // loaders_have_permissions vai verificar as permissoes dos items e depois verifica quais são os empregados da lista de empregados que são os carregadores e verifica se este tem as devidas permissões para carregar os items, caso tenha retorna true, caso nao tenha retorna false

    int register_deposit(int idClient, int idLocal, String[] idEmployees, String[][] items); //register_deposit vai registar o deposito no cliente e retorna o id atribuido do depósito

    boolean have_quant_items(int idClient, String[][] items); // have_quant_items se no deposito existir toda a quantidade de items retorna true senao retorna false

    int register_delivery(int idClient, int idLocal, String[] idEmployees, String[][] items); //register_delivery vai registar a entrega no cliente e retorna o id atribuido da entrega

    boolean has_item_client(int idClient, int idItem); // has_item_client verifica se o cliente tem o item se sim retorna true senao retorna false

    boolean has_delivery_client(int idClient, int idDelivery); // has_delivery_client verifica se o cliente tem a entrega se sim retorna true senao retorna false

    boolean isManager(int idEmployee); // isManager retorna true se o funcionario for manager

    String get_nameClient(int idClient); //commandCC

    String get_nameManager(int idClient); //commandCC

    List<String> info_itens(int idClient); //commandCC

    String info_item(int idClient, int idItem); //commandCI

    List<String> info_delivery(int idClient, int idDelivery); //commandCE

    String get_nameEmployee(int idEmployee);

    String get_categoryEmployee(int idEmployee);

    List<String> get_permissionEmployee(int idEmployee);

    String[] info_depositsE(int idEmployee);

    String[] info_deliveriesE(int idEmployee);

    String[] info_depositsCC(int idClient);

    String[] info_deliveriesCC(int idClient);

    String[] info_depositsCI(int idClient, int idItem);

    String[] info_deliveriesCI(int idClient, int idItem);
}
