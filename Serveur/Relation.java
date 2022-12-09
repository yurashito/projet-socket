package relation; 
import java.io.*;  
import fonction.*;
import java.io.Serializable;

public class Relation implements Serializable{
    String Nom;
    String[] Colonne;
    Object[][] Tableau;
    public Relation(String nom , String[] colonne , Object[][] tableau){
        this.Nom= nom;
        this.Colonne= colonne;
        this.Tableau= tableau;
    }
    public Relation(){}
    public String getNom(){
        return Nom;
    }
    public void setNom(String nom){
        Nom= nom ; 
    }
    public String[] getColonne(){
        return Colonne;
    }
     public void setColonne(String[] colonne){
        Colonne= colonne ; 
    }
    public Object[][] getTableau(){
        return Tableau;
    }
     public void setTableau(Object[][] tableau){
        Tableau = tableau;
    }
    public void ecrire(){
        File fichier= new File("tout_table/"+this.Nom+".txt") ;
        Fonction function = new Fonction();
        if(fichier.exists()==false){
            try(BufferedWriter stylo=new BufferedWriter(new FileWriter(fichier,true))) 
            {
                stylo.write(this.Nom);
                stylo.newLine() ;
                stylo.write(function.somme_string(this.Colonne));
                stylo.newLine();
                stylo.write(function.table_to_string(this.Tableau));
            } catch(IOException huhu){
                huhu.printStackTrace();
            }
        }
    }
}