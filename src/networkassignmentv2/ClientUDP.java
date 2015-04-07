/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkassignmentv2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;

/**
 *
 * @author KRIT
 */
public class ClientUDP {
    private DatagramSocket socket;
    private String msg;
    private String answer;
    private byte[] byteMsg = new byte[1024];
    private byte[] byteAnswer = new byte[1024];
    private String host;
    private MainFrame frame;
    private InetAddress ip;
    private int port;

    public ClientUDP(String host, int port, String msg) {
        this.msg = msg;
        this.host = host;
        this.port = port;
        try {
            //instantiate socket
            socket = new DatagramSocket();
            //translate host to ip
            ip = InetAddress.getByName(host);
            //declare byte[] msg and answer
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public ClientUDP(String host, int port, String msg, MainFrame frame) {
        this.frame = frame;
        this.msg = msg;
        this.host = host;
        this.port = port;
        try {
            //instantiate socket
            socket = new DatagramSocket();
            //translate host to ip
            ip = InetAddress.getByName(host);
            //declare byte[] msg and answer
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void send(){
        //set value of msg and answer
        byteMsg = msg.getBytes();     
        try {
            //create packet
            DatagramPacket packetMsg = new DatagramPacket(byteMsg, byteMsg.length, ip, port);
            DatagramPacket packetAns = new DatagramPacket(byteAnswer, byteAnswer.length);
            //send packet
            socket.send(packetMsg);
            //receive packet
            socket.receive(packetAns);
            answer = new String(packetAns.getData());
            
            System.out.println("Sent "+msg+" to "+ip+":"+port+" via UDP");
            if(frame!=null){
                frame.setStatusTxtArea("Sent "+msg+" to "+ip+":"+port+" via UDP");
            }
            System.out.println(ip+" replied: "+answer);
            if(frame!=null){
                frame.setStatusTxtArea(frame.getStatusTxtArea()+ip+" replied: "+answer+"\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if(socket!=null) {
                    //close connection
                    this.socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAnswer() {
        return answer;
    }
    
}
