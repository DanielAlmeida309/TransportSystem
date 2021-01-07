package D3.controllers;

public interface TService {
    boolean category_existent(String category);

    boolean permission_existent(String category);

    boolean has_employee_in_category(String name, String category);

    String register_employee(String category, String permission, String name);

    boolean has_client(String name);

    String register_client(String name);

    int[] register_item(String nameClient, String nameItem, String permissions); //register_item regista o item e retorna o id do cliente e o id do item   -- vetor posiçao 0- id cliente   1- id produto
}
