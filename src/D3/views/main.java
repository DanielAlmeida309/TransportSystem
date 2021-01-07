package D3.views;

import D3.controllers.TService;
import D3.controllers.TServiceClass;

import java.util.ArrayList;
import java.util.Scanner;

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
                    commandL(ts, sc, comm2);
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
        String category = comm2[1], permission = comm2[2], name = comm2[3];

        if( !ts.category_existent(category) ){  //category_existent deve retornar true se existir categoria

            System.out.println("Categoria inexistente.");

        }else if( !ts.permission_existent(category) ){ //permission_existent deve retornar true se existir permissão

            System.out.println("Permissão inexistente.");

        }else if( ts.has_employee_in_category(name, category)){  //has_employee_in_category retorna true se existir um empregado com o mesmo nome na mesma categoria

            System.out.println("Funcionário existente.");

        }else{

            System.out.println("Funcionário registado com o identificador " + ts.register_employee(category, permission, name) + "."); //register_employee regista o empregado e retorna o id atribuido a ele pelo sistema

        }
    }

    static void commandRC(TService ts,Scanner sc,String[] comm2){
        String name = comm2[1];

        if( ts.has_client(name) ){  //has_client deve retornar true se já existir um cliente com o mesmo nome inserido

            System.out.println("Cliente existente.");

        }else{

            System.out.println("Cliente registado com o identificador " + ts.register_client(name) + "."); //register_client regista o cliente e retorna o id atribuido a ele pelo sistema

        }
    }

    private static void commandRI(TService ts, Scanner sc, String[] comm2) {
        String nameClient = comm2[1];
        String nameItem = comm2[2];

        if( ! ts.has_client(nameClient) ){

            System.out.println("Cliente inexistente.");

        }else{
            String permissions[] = sc.nextLine().split(" ", 8);
            int ids[] =ts.register_item(nameClient, nameItem, permissions);
            System.out.println("Item registado para o cliente " + ids[0] + " com o identificador " + ids[1]);
        }
    }

    private static void commandRL(TService ts, Scanner sc, String[] comm2) {
        String nameLocal = comm2[1];

        if( ts.has_local(nameLocal) ){

            System.out.println("Local existente.");

        }else{

            System.out.println("Local registado com o identificador"+ ts.register_local(nameLocal) +".");

        }
    }


}
