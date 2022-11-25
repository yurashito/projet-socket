package client;
import affichage.*;
import relation.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client  {
     public static void main(String[] args) throws Exception {
    try{
            Scanner scan=new Scanner(System.in);
            Socket socket = new Socket("192.168.43.62", 3000);
            PrintWriter out = new  PrintWriter(socket.getOutputStream());
            while(true){
                String msg;
                msg=scan.nextLine();
                out.println(msg);
                out.flush();
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Relation relation = (Relation) in.readObject();
                System.out.println(relation.getColonne()[0]);
                Affichage affichage = new Affichage(relation);
                if(1==2)
                {
                    break;
                }
            }
            socket.close();
          
      } catch( Exception e){
         System.out.println(e);
      }
    } 
}