package traitement;
import java.io.*;
import java.net.*;
import exception.*;
import affichage.*;
import fonction.*;
import relation.*;

public class Traitement extends Thread{
     Socket clientSocket;
    public Traitement( Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    public void run(){
        try {
            BufferedReader  in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));  
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream()); 
            out.flush();
            Fonction function = new Fonction();
            try{
                while(true){
                    try {        
                        String requette=in.readLine();

                        if(requette.equals("quit"))
                        {
                            out.close();
                            clientSocket.close();
                        }else{
                            Relation relation = function.requette(requette);  
                            out.writeObject(relation);
                            out.flush();
                        }
                    }
                    catch(GereException h){
                        System.out.println(h.getException());
                        out.writeObject(String.valueOf(h.getException()));
                        out.flush();         
                    }
                }
            } catch(Exception j){
                System.out.println(j);
            }
            finally{
                out.close();
                clientSocket.close();
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
    }

}