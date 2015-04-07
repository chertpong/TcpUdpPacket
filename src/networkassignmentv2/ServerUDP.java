/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkassignmentv2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author KRIT
 */
public class ServerUDP {
    private byte[] byteMsg;
    private byte[] byteAns;
    private String msg;
    private String answer;
    private DatagramSocket server;
    private int port;
    private MainFrame frame;

    
    public ServerUDP(int port) {
        try {
            answer = "";
            byteMsg = new byte[1024];
            byteAns = new byte[1024];
            this.port = port;
            server = new DatagramSocket(this.port);
            System.out.println("Server can run on port "+this.port);
            System.out.println("Local IP:"+new String(InetAddress.getLocalHost().getHostAddress()));
        }
        catch (IOException ioe){
            System.out.println("Port "+this.port+" is already used");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public ServerUDP(int port, MainFrame frame) {
        try {
            answer = "";
            byteMsg = new byte[1024];
            byteAns = new byte[1024];
            this.port = port;
            this.frame = frame;
            server = new DatagramSocket(this.port);
            System.out.println("UDP server can run on port "+this.port);
            System.out.println("Local IP:"+new String(InetAddress.getLocalHost().getHostAddress()));
            if(frame!=null){
                frame.setHostStatusTextArea(frame.getHostStatusTextArea()+"UDP server can run on port "+this.port+"\n");
                frame.setHostStatusTextArea(frame.getHostStatusTextArea()
                        +"Local IP:"+new String(InetAddress.getLocalHost().getHostAddress())
                        +"\n");
            }
        }
        catch (IOException ioe){
            System.out.println("Port "+this.port+" is already used");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void run(){ 
        while (true) {            
            try {
            //create packet to receive msg
            DatagramPacket msgPacket = new DatagramPacket(byteMsg, byteMsg.length);
            //receive msg packet from client
            server.receive(msgPacket);
            //load msg from packet
            msg = new String(msgPacket.getData());
            //print received msg
            System.out.println("UDP server received:"+msg+" from "+msgPacket.getAddress()+":"+ msgPacket.getPort());
            //set received to component
            if(frame!=null){ 
                frame.setHostStatusTextArea(
                        frame.getHostStatusTextArea()
                        +"UDP server received:"+msg+" from "+msgPacket.getAddress()+":"+ msgPacket.getPort()+"\n"
                );
           }
            
            //set answer
            byteAns = answer.getBytes();
            //create answer packet
            DatagramPacket ansPacket = new DatagramPacket(byteAns, byteAns.length, msgPacket.getAddress(), msgPacket.getPort());
            //send answer to client
            server.send(ansPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
