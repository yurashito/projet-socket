package affichage;
import relation.*;
import fonction.*;
import javax.swing.JTable.*;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;
import javax.swing.*;
public class Affichage2{
    public Affichage2(){}
    public Affichage2(Relation relation){
        Object[][] tableau = relation.getTableau();
        String[] header=relation.getColonne();
        int caractere_max=new Fonction().nombre_caractere_max(relation);
        String tire1= new String();
        int width_colonne= caractere_max ;
        int[] taille_colonne= new int[header.length];
        Fonction fonction = new Fonction();
        String tete= new String();
        int k=0;
        
         for(int i=0 ; i<header.length ; i++){
            int nbr_caractere_max=0;
            if( fonction.nombre_max_colonne(relation , i) < header[i].length() ){
                nbr_caractere_max=header[i].length();
            } else{
                nbr_caractere_max= fonction.nombre_max_colonne(relation , i);
            }
            taille_colonne[i]= nbr_caractere_max+5;
            for(int j=0 ; j<taille_colonne[i]+1 ; j++){
                tire1=tire1+"-";
            }
        }
        
        for(int i=0 ; i<header.length ; i++){
            tete=tete+fonction.mameno_par_esapce(header[i] , taille_colonne[i]);
        }
        
        System.out.println(tire1);
        System.out.println(tete);
        System.out.println(tire1);
         for( int i=0; i<tableau.length ; i++){
            String body= new String();
            for(int j=0 ; j<header.length ; j++){
                body= body+fonction.mameno_par_esapce(String.valueOf(tableau[i][j]) , taille_colonne[j]);
            }
            System.out.println(body);
        }
        System.out.println(tire1);
    
        
    }

}