package server;
import affichage.*;
import fonction.*;
import relation.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Server {
      public static void main(String[] args) throws Exception { 
        ServerSocket serverSocket = new ServerSocket(3000);
        Socket clientSocket = serverSocket.accept();
        BufferedReader  in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));  
        Fonction function = new Fonction();
        while(true){
            String requette=in.readLine();
            System.out.println(serverSocket.getInetAddress() +" : "+ requette );
            Relation relation=function.requette(requette) ;
            System.out.println(relation.getNom());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(relation);
            if(1==2){

                break;
            }
      }
      clientSocket.close();
      serverSocket.close();
   }
}
