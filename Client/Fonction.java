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
    public int nombre_caractere_max(Relation relation){
        Object[][] tableau= relation.getTableau();
        
        int max= 0;
        if(tableau.length!=0)
        { 
            max=String.valueOf(tableau[0][0]).length();
        }
        for(int i=0; i<tableau.length ; i++){
            for(int j=0; j<tableau[0].length ; j++){
                String ray=String.valueOf(tableau[i][j]);
                int nombre= ray.length();
                if(nombre>max){
                    max= nombre;
                }
            }
        }
        return max;
    }
    public int nombre_max_colonne(Relation relation ,int indice){
        Object[][] tableau= relation.getTableau();

        int max=0;
        if(tableau.length!=0)
        { 
            max= String.valueOf(tableau[0][indice]).length();
        }
        for(int i=0; i<tableau.length ; i++){
            String ray=String.valueOf(tableau[i][indice]);
            int nombre= ray.length();
            if(nombre>max){
                max= nombre;
            }
        }
        return max;
    }

    
    public String mameno_par_esapce(String mot  , int max){
        String somme= mot;
        if(mot.length()<max){
            int nombre=mot.length();
            while(nombre<max){
                somme=somme+" ";
                nombre++;
            }
        }
        somme=somme+"|";
        return somme;
    }

}