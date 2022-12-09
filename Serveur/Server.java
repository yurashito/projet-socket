package server;
import affichage.*;
import fonction.*;
import relation.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import exception.*;
import traitement.*;
public class Server extends Thread{

        ServerSocket serverSocket ;

       public Server(int port) throws Exception
       {
            serverSocket = new ServerSocket(port);
       }
       public void run(){
            try{
                while(true){
                    Socket clientSocket = serverSocket.accept();
                    Traitement traitement= new Traitement(clientSocket);
                    traitement.start();
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }

    public static void main(String[] args)throws Exception{         
        Server server = new Server(2000);
        server.start();
   }
}
