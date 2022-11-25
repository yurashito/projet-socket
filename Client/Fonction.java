package fonction;
import relation.*;
import affichage.*;
import java.io.*;
public class Fonction {
    public String somme_string(Object[] tableau){
        String somme="";
        for(int i=0 ; i<tableau.length ; i++){
            if(i<tableau.length-1)
            {
                somme=somme+tableau[i]+",";
            }else{
                somme=somme+tableau[i];   
            }
        }
        return somme;
    }   
    public String table_to_string(Object[][] tableau){
        String somme="";
        for(int i=0; i<tableau.length ; i++){
            if(i<tableau.length-1)
            {
                somme= somme+somme_string(tableau[i])+";";
            } else{
                 somme= somme+somme_string(tableau[i]);
            }
        }
        return somme;
    }
    public Object[][] string_to_table(String ray){
        String[] line= ray.split(";");
        Object[][] resultat= new Object[line.length][];
        for(int i=0 ; i<line.length ; i++){
            resultat[i]= line[i].split(",");
        }
        return resultat;
    }

    public Object[] lire(String nom) throws Exception
    {
        File fichier= new File(nom+".txt");
        Object[] tableau= new Object[3];
        if(fichier.exists()){
            BufferedReader br = br = new BufferedReader(new FileReader(fichier));
            String ligne;
            int i = 0 ; 
            while ((ligne = br.readLine()) != null)
            {
                tableau[i]=ligne;
                i++;
            }
            br.close();
        } else{
            throw new Exception("le table "+nom+" n'existe pas ");
        }
        return tableau;
        
    }

     public String[] somme_tableau_string(Relation r1 , Relation r2) throws Exception{
        String[] header_r1 =r1.getColonne();
        String[] header_r2 =r2.getColonne();
        String[] somme= new String[header_r1.length + header_r2.length]  ;
        int j=0; 
        for(int i=0; i<header_r1.length ; i++){
            somme[j]=header_r1[i];
            j++;
        }
        for(int i=0; i<header_r2.length ; i++){
            somme[j]=header_r2[i];
            j++;
        }

        return somme;
    }   

}