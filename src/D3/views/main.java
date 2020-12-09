package D3.views;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
        TService ts = new TService();
        Scanner sc = new Scanner(System.in);

        while(true){
            String comm = sc.nextLine();
            String[] comm2 = comm.split(" ", 8);

            switch (comm){
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

                case "0":
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Instrução inválida.\n");

            }

        }
    }

}
