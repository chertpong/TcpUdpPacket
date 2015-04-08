/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkassignmentv2;

/**
 *
 * @author KRIT
 */
public class Main {

        //Threads
        public static Thread clientThread;
        public static Thread clientThread2;
        public static Thread hostThread;
        public static Thread hostThread2;
        
    public static void main(String[] args) {
        if( args.length == 0 || args[0].equals("h") || args[0].equals("-help") || args[0].equals("--help")){
            System.out.println("Help option (can access by h, -help, --help)");
            System.out.println("There are 2 options for using this program");
            System.out.println(" -h -t <protocol> -p <port> // start server");
            System.out.println("    -t <protocol> can be 'tcp' or 'udp' or 'both'");
            System.out.println("    -p <port> any number from 0-65535[ 0 for auto port] //prefer 1000-65535");
            System.out.println(" -c -x <message> -t <protocol> -s <target> -p <port> // create client and send data to server");
            System.out.println("    -x <message> can only be integer number which unsigned and not bigger than 2^32");
            System.out.println("    -t <protocol> can be 'tcp' or 'udp'");
            System.out.println("    -s <target> is server ip (in udp can be domain name)");
            System.out.println("    -p <port> is server's port");
        }
        else if(args[0].equals("-h")){ // start host
            if(args[1].equals("-t") && args[3].equals("-p")){ //select protocol
                if(args[2].equals("tcp")){
                    hostThread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            ServerTCP s = new ServerTCP(Integer.parseInt(args[4]));
                            s.setAnswer("hello from TCP server");
                            s.run();
                        }
                    });
                    hostThread.start();
                }
                else if (args[2].equals("udp")){
                    hostThread2 = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            ServerUDP s = new ServerUDP(Integer.parseInt(args[4]));
                            s.setAnswer("hello from UDP server");
                            s.run();
                        }
                    });
                    hostThread2.start();
                }
                else{
                    System.out.println("Invalid protocol");
                    System.exit(0);
                }
            }
            else{
                System.out.println("Invalid syntax, type h, -help, --help for more information");
                System.exit(0);
            }
        }
        else if (args[0].equals("-c")) {

        }
        else{
            System.out.println("Invalid");
            System.exit(0);
        }
    }
}
