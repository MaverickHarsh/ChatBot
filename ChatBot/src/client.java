import jdk.swing.interop.SwingInterOpUtils;

import java.net.*;
import java.io.*;
public class client {
    BufferedReader br;
    PrintWriter out;
    Socket socket;
    public client() {
        try {
            System.out.println("Sending Request to the server");
            socket = new Socket("127.0.0.1", 777);
            System.out.println("Connection Done");


            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void startReading(){
        // creating a thread
        Runnable r1=()->{
            System.out.println("Reader Started...");
            while(true){
                try {
                    String msg= br.readLine();
                    if(msg.equals("Exit"))
                    {
                        System.out.println("Server Terminated the chat");
                        break;
                    }
                    System.out.println("Server :"+msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(r1).start();
    }
    public void startWriting(){
        Runnable r2=()->{
            System.out.println("Writer Started..");
            while(true){

                try{
                    BufferedReader b1=new BufferedReader(new InputStreamReader(System.in));
                    String content=b1.readLine();
                    System.out.println(content);
                    System.out.flush();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        new Thread(r2).start();
    }
    public static void main(String[] args) {
        System.out.println("This is client....");
    }
}