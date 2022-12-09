package client;
import affichage.*;
import relation.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import exception.*;

public class Client {
    public Client(){
        try{
            Scanner scan=new Scanner(System.in);
            Socket socket = new Socket("localhost", 2000);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            while(true){
                try {
                    String msg;
                    msg=scan.nextLine();
                    out.println(msg);
                    out.flush();
                    if(msg.equals("quit")){
                        break;
                    }
                    Object objet= in.readObject();
                    if(objet.getClass().getSimpleName().equals("Relation"))
                    {
                        Relation relation = (Relation)objet;
                        Affichage2 affichage = new Affichage2(relation);
                    }
                    if(objet.getClass().getSimpleName().equals("String")){
                        System.out.println(String.valueOf(objet));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            out.close();
            socket.close();
    
      } catch( Exception e){
         e.printStackTrace();
      }

    }


     public static void main(String[] args) throws Exception {
        Client client = new Client();

    
    } 
}