package D3.controllers;

public interface TService {
    boolean category_existent(String category);

    boolean permission_existent(String category);

    boolean has_employee_in_category(String name, String category);

    String register_employee(String category, String permission, String name);

    boolean has_client(String name); //has_client deve retornar true se já existir um cliente com o mesmo nome inserido

    boolean has_idClient(int idClient);  //has_idclient  deve retornar true se já existir o id do cliente associado a um cliente ou false se nao existir

    String register_client(String name);

    int[] register_item(String nameClient, String nameItem, String[] permissions); //register_item regista o item e retorna o id do cliente e o id do item   -- vetor posiçao 0- id cliente   1- id produto

    boolean has_local(String nameLocal);    //has_local deve retornar true se já existir um local com o mesmo nome inserido

    boolean has_idLocal(int idLocal); //has_idLocal  deve retornar true se já existir o id do local associado a um local ou false se nao existir

    String register_local(String nameLocal);     //register_local regista o local e retorna o id do local

}
