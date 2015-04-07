package networkassignmentv2;

import java.awt.Component;
import java.io.*;
import java.net.*;

/**
 *
 * @author KRIT
 */
public class ServerTCP {
    private int port;
    private String msg;// client to server
    private String answer = "1234"; // answer from server
    private ServerSocket server;
    private MainFrame frame;
    
    public ServerTCP(int port) {
        try {
            this.port = port;
            server = new ServerSocket(this.port);
            System.out.println("Server can run on port "+this.port);
        }
        catch (IOException ioe){
            System.out.println("Port "+this.port+" is already used");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public ServerTCP(int port, MainFrame frame) {
        try {
            this.port = port;
            server = new ServerSocket(this.port);
            this.frame = frame;
            System.out.println("TCP server can run on port "+this.port);
            if(frame!=null) frame.setHostStatusTextArea(frame.getHostStatusTextArea()+"TCP server can run on port "+this.port+"\n");
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
            // socket connected from client
            Socket socket = server.accept();
            // input attach to packet
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // output attach to packet
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            //get msg from client
            msg = in.readLine();
            //set received msg to component
            if(frame!=null) {
                frame.setHostStatusTextArea(
                        frame.getHostStatusTextArea()+"server received:"+msg
                                +" from "+socket.getInetAddress()
                                +":"+socket.getPort()
                                +"\n"
                );
            }
            //print received
            System.out.println("server received:"+msg
                    +" from "+socket.getInetAddress()
                    +":"+socket.getPort()
            );
            //send answer to client
            out.writeBytes(answer);
            
            //close
            in.close();
            out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getMsg() {
        return msg;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
