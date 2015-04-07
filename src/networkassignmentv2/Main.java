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
//        Thread t1 = new Thread(new Runnable() {
//
//               @Override
//               public void run() {
//                   ServerTCP server = new ServerTCP(12345);
//                   while(true){
//                       server.run();
//                   }
//               }
//           });
//
//        Thread t2 = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                ClientTCP client = new ClientTCP("192.168.1.40", 1234, "1212123");
//                client.setMsg("Hello from client");
//                client.send();
//            }
//        });
//   // t1.start();
//    //t2.start();
//        Thread t3 = new Thread(new Runnable() {
//
//               @Override
//               public void run() {
//                   ServerUDP server = new ServerUDP(1234);
//                   while(true){
//                       server.run();
//                   }
//               }
//           }); 
//        //t3.start();
//        
//         Thread t4 = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                ClientUDP client = new ClientUDP("127.0.0.1", 1234, "1212123");
//                client.setMsg("Hello from client");
//                client.send();
//            }
//        });
//         t4.start();
    }
}
