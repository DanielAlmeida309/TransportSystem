package D3.views;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
        TService ts = new TService();
        Scanner sc = new Scanner(System.in);

        while(true){
            String comm = sc.next();
            // String[] comm2 = comm.split(" ", 8);

            switch (comm){
                case "RF":
                    commandRF(ts, sc);
                    break;

                case "RC":
                    commandRC(ts, sc);
                    break;

                case "RI":
                    commandRI(ts, sc);
                    break;

                case "RL":
                    commandRL(ts, sc);
                    break;

                case "RD":
                    commandRD(ts, sc);
                    break;

                case "RE":
                    commandRE(ts, sc);
                    break;

                case "CC":
                    commandCC(ts, sc);
                    break;

                case "CI":
                    commandCI(ts, sc);
                    break;

                case "CE":
                    commandCE(ts, sc);
                    break;

                case "CF":
                    commandCF(ts, sc);
                    break;

                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Instrução inválida.\n");

            }

        }
    }

}
