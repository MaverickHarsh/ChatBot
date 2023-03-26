import java.net.*;
import java.io.*;
public class Server {
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    // Constructor
    public Server()
    {
        try {
            server = new ServerSocket(777);
            System.out.println("Server is Ready to accept connection");
            System.out.println("Waiting....");
            socket= server.accept();

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        } catch (Exception e){
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
                        System.out.println("Client Terminated the chat");
                        break;
                    }
                    System.out.println("Client :"+msg);
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
        System.out.println("This is Server.. Going to start server");
        new Server();
    }
}

