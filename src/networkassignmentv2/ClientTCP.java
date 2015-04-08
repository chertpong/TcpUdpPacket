package networkassignmentv2;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author KRIT
 */
public class ClientTCP {
    private String msg;
    private String answer = "hello";
    private Socket socket;
    private MainFrame frame;
    private String host;
    private int port;
    public ClientTCP(String host, int port, String msg) {
        this.msg = msg;
        this.host = host;
        this.port = port;
        try {
            this.socket = new Socket(host, port);
            this.socket.setSoTimeout(3000); // set connection timeout
        } 
        catch (Exception e) {
          e.printStackTrace();
        }
        
    }
    public ClientTCP(String host, int port, String msg, MainFrame frame) {
        this.frame = frame;
        this.msg = msg;
        this.host = host;
        this.port = port;
        try {
            this.socket = new Socket(host, port);
            this.socket.setSoTimeout(3000); // set connection timeout
        } 
        catch (Exception e) {
          e.printStackTrace();
        }
        
    }
    public void send(){
        try {
            // output attach to packet (output to server)
            DataOutputStream out = new DataOutputStream(this.socket.getOutputStream()); 
            // input attach to packet (input from server)
            // use buffer to make it faster
            // use InputStreamReader to turn byte to character
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            //send
            out.writeBytes(msg+'\n'); // use \n or new line as the end point of msg according to readLine()
            //receive and assign to this.answer
            this.answer = in.readLine();
            //close stream
            out.close();
            in.close();
            System.out.println("Sent "+msg+" to "+host+":"+port+" via TCP");
            if(frame!=null){
                frame.setStatusTxtArea(frame.getStatusTxtArea()+"Sent "+msg+" to "+host+":"+port+" via TCP"+"\n");
            }
            System.out.println(host+" replied: "+answer);
            if(frame!=null){
                frame.setStatusTxtArea(frame.getStatusTxtArea()+host+" replied: "+answer+"\n");
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAnswer() {
        return answer;
    }   
}
