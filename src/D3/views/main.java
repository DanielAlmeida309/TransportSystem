package D3.views;

import D3.controllers.TService;
import D3.controllers.TServiceClass;

import java.util.*;

public class main {
    public static void main(String[] args){
        TService ts = new TServiceClass();
        Scanner sc = new Scanner(System.in);

        while(true){
            String comm = sc.nextLine();
            String[] comm2 = comm.split(" ", 8);

            switch (comm2[0]){  //verificar o código do comando inserido
                case "RF":
                    commandRF(ts, sc, comm2);
                    break;

                case "RC":
                    commandRC(ts, sc, comm2);
                    break;

                case "RI":
                    commandRI(ts, sc, comm2);
                    break;

                case "RL":
                    commandRL(ts, sc, comm2);
                    break;

                case "RD":
                    commandRD(ts, sc, comm2);
                    break;

                case "RE":
                    commandRE(ts, sc, comm2);
                    break;

                case "CC":
                    commandCC(ts, sc, comm2);
                    break;

                case "CI":
                    commandCI(ts, sc, comm2);
                    break;

                case "CE":
                    commandCE(ts, sc, comm2);
                    break;

                case "CF":
                    commandCF(ts, sc, comm2);
                    break;
                case "G":
                    commandG(ts, sc, comm2);
                    break;
                case "L":
                    ts = commandL(ts, sc, comm2);
                    break;

                case "0":
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Instrução inválida.\n");

            }

        }
    }


    static void commandRF(TService ts,Scanner sc,String[] comm2){
        String category = comm2[1], permission = comm2[2], name = comm2[3] + " " +  comm2[4];

        if( !ts.category_existent(category) ){

            System.out.println("Categoria inexistente.");

        }else if( !ts.permission_existent(permission) ){

            System.out.println("Permissão inexistente.");

        }else if( ts.has_employee_in_category(name, category)){

            System.out.println("Funcionário existente.");

        }else{

            System.out.println("Funcionário registado com o identificador " + ts.register_employee(category, permission, name) + ".");

        }
    }

    static void commandRC(TService ts,Scanner sc,String[] comm2){
        String name = comm2[2] + " " + comm2[3];
        int idEmployee = Integer.parseInt(comm2[1]);

        if (!ts.has_employee(idEmployee)) {
            System.out.println("Funcionario inexistente.");
        }
        else if (!ts.isManager(idEmployee)) {
            System.out.println("Funcionario incorreto.");
        }
        else if( ts.has_client(name) ){

            System.out.println("Cliente existente.");

        }else{

            System.out.println("Cliente registado com o identificador " + ts.register_client(name, idEmployee) + ".");

        }
    }

    private static void commandRI(TService ts, Scanner sc, String[] comm2) {
        String idClient = comm2[1];
        String nameItem = "";
        for(int i = 2; i < comm2.length; i ++) {
            if(i == 2) {
                nameItem += comm2[i];
            }
            else {
                nameItem += " " + comm2[i];
            }
        }
//        String nameItem = comm2[2] + " " + comm2[3];
        List<String> permissions = Arrays.asList(sc.nextLine().split(" ", 8));

        if( ! ts.has_idClient(Integer.parseInt(idClient)) ){

            System.out.println("Cliente inexistente.");

        }else{
            String nameClient = ts.get_nameClient(Integer.parseInt(idClient));



            int ids[] =ts.register_item(nameClient, nameItem, permissions);
            System.out.println("Item registado para o cliente " + ids[0] + " com o identificador " + ids[1]);
        }
    }

    private static void commandRL(TService ts, Scanner sc, String[] comm2) {
        String nameLocal = comm2[1];

        if( ts.has_local(nameLocal) ){

            System.out.println("Local existente.");

        }else{

            System.out.println("Local registado com o identificador "+ ts.register_local(nameLocal) +".");

        }
    }

    private static void commandRD(TService ts, Scanner sc, String[] comm2) {
        int idClient = Integer.parseInt(comm2[1]);
        int idLocal = Integer.parseInt(comm2[2]);

        if( ! ts.has_idClient(idClient) ){

            System.out.println("Cliente inexistente.");

        }else if( ! ts.has_idLocal(idLocal) ){

            System.out.println("Local inexistente.");

        }else{

            List<String> idEmployees = Arrays.asList(sc.nextLine().split(" ", 8));
            List<Integer> idItems = new ArrayList<>();
            List<Integer> quantidade = new ArrayList<>();

            while (true) {
                List<String> str = Arrays.asList(sc.nextLine().split(" ", 2));
                if (str.get(0).equals("")) break;
                idItems.add(Integer.parseInt(str.get(0)));
                quantidade.add(Integer.parseInt(str.get(1)));
            }

            if( !ts.has_items(idClient, idItems) ){

                System.out.println("Item inexistente.");

            }else if ( ! ts.has_employees(idEmployees) ){

                System.out.println("Funcionário inexistente.");

            }else if ( ! ts.driver_have_permission(idClient, Integer.parseInt(idEmployees.get(0)), idItems) ){

                System.out.println("Condutor sem permissões.");

            }else if ( ! ts.loaders_have_permissions(idClient, Integer.parseInt(idEmployees.get(0)), idItems) ){

                System.out.println("Carregador sem permissões.");

            }else{

                System.out.println("Depósito registado com o identificador "+ ts.register_deposit(idClient, idLocal, idEmployees, idItems, quantidade) +".");

            }
        }
    }

    private static void commandRE(TService ts, Scanner sc, String[] comm2) {
        int idClient = Integer.parseInt(comm2[1]);
        int idLocal = Integer.parseInt(comm2[2]);

        if( ! ts.has_idClient(idClient) ){

            System.out.println("Cliente inexistente.");

        }else if( ! ts.has_idLocal(idLocal) ){

            System.out.println("Local inexistente.");

        }else{

            List<String> idEmployees = Arrays.asList(sc.nextLine().split(" ", 8));
            List<Integer> idItems = new ArrayList<>();
            List<Integer> quantidade = new ArrayList<>();

            while (true) {
                List<String> str = Arrays.asList(sc.nextLine().split(" ", 2));
                if (str.get(0).equals("")) break;
                idItems.add(Integer.parseInt(str.get(0)));
                quantidade.add(Integer.parseInt(str.get(1)));
            }

            if( !ts.has_items(idClient, idItems) ) {

                System.out.println("Item inexistente.");

            }else if(!ts.have_quant_items(idClient, idItems, quantidade)){

                System.out.println("Quantidade insuficiente.");

            }else if ( ! ts.has_employees(idEmployees) ){

                System.out.println("Funcionário inexistente.");

            }else if ( ! ts.driver_have_permission(idClient, Integer.parseInt(idEmployees.get(0)), idItems) ){

                System.out.println("Condutor sem permissões.");

            }else if ( ! ts.loaders_have_permissions(idClient, Integer.parseInt(idEmployees.get(0)), idItems) ){

                System.out.println("Carregador sem permissões.");

            }else{

                System.out.println("Entrega registada o identificador "+ ts.register_delivery(idClient, idLocal, idEmployees, idItems, quantidade) +".");

            }
        }
    }

    private static void commandCC(TService ts, Scanner sc, String[] comm2) {
        int idClient = Integer.parseInt(comm2[1]);

        if( ! ts.has_idClient(idClient) ){

            System.out.println("Cliente inexistente.");

        }else{

            System.out.println(ts.get_nameClient(idClient));
            System.out.println(ts.get_nameManager(idClient));
            System.out.println("Items:");
            for(String text : ts.info_itens(idClient)){
                System.out.println("  "+text);
            }
            System.out.println("Depósitos:");
            for(String text : ts.info_depositsCC(idClient)){
                System.out.println("  "+text);
            }
            System.out.println("Entregas:");
            for(String text : ts.info_deliveriesCC(idClient)){
                System.out.println("  "+text);
            }

        }
    }

    private static void commandCI(TService ts, Scanner sc, String[] comm2) {
        int idClient = Integer.parseInt(comm2[1]);
        int idItem = Integer.parseInt(comm2[2]);

        if( ! ts.has_idClient(idClient) ){

            System.out.println("Cliente inexistente.");

        }else if( ! ts.has_item_client(idClient, idItem) ){

            System.out.println("Cliente inexistente.");

        }else{

            System.out.println(ts.info_item(idClient, idItem));
            System.out.println("Depósitos:");
            for(String text : ts.info_depositsCI(idClient, idItem)){
                System.out.println("  "+text);
            }
            System.out.println("Entregas:");
            for(String text : ts.info_deliveriesCI(idClient, idItem)){
                System.out.println("  "+text);
            }

        }

    }

    private static void commandCE(TService ts, Scanner sc, String[] comm2) {
        int idClient = Integer.parseInt(comm2[1]);
        int idDelivery = Integer.parseInt(comm2[2]);

        if( ! ts.has_idClient(idClient) ){

            System.out.println("Cliente inexistente.");

        }else if( ! ts.has_delivery_client(idClient, idDelivery) ){

            System.out.println("Entrega inexistente.");

        }else{

            List<String> info = ts.info_delivery(idClient, idDelivery);
            for(int i=0; i< info.size(); i++){
                System.out.println(info.get(i));
            }

        }
    }

    private static void commandCF(TService ts, Scanner sc, String[] comm2) {
        int idEmployee = Integer.parseInt(comm2[1]);

        if( ! ts.has_employee(idEmployee) ){

            System.out.println("Funcionário inexistente.");

        }else{

            System.out.println(ts.get_nameEmployee(idEmployee));
            System.out.println(ts.get_categoryEmployee(idEmployee));
            List<String> permissions = ts.get_permissionEmployee(idEmployee);
            for(String permission : permissions){
                System.out.print(permission);
            }
            System.out.println("");


            System.out.println("Depósitos:");
            for(String text : ts.info_depositsE(idEmployee)){
                System.out.println("  "+text);
            }
            System.out.println("Entregas:");
            for(String text : ts.info_deliveriesE(idEmployee)){
                System.out.println("  "+text);
            }
        }

    }

    private static void commandG(TService ts, Scanner sc, String[] comm2) {
        String nameFile = comm2[1];
        ts.saveFile(nameFile, ts);
        System.out.println("Ficheiro gravado com sucesso.");
    }


    private static TService commandL(TService ts, Scanner sc, String[] comm2) { //criar exception caso não consiga abrir file
        String nameFile = comm2[1];

        Object objectRetuned = ts.readFile(nameFile);
        if (objectRetuned instanceof Boolean) {
            System.out.println("Ficheiro inexistente.");

        }
        else if(objectRetuned instanceof TService) {
            System.out.println("Ficheiro lido com sucesso.");
            return (TService) objectRetuned;
        }
        return ts;
    }


}
