package fonction;
import relation.*;
import affichage.*;
import java.io.*;
import exception.*;
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
        File fichier= new File("tout_table/"+nom+".txt");
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
            throw new GereException("le table "+nom+" n'existe pas ");            
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

///-------------------------------------------------------------------------------------------------------------

    public boolean test_existence_colonne(Relation r1 , String nom_colonne) throws Exception{
        String[] colonne = r1.getColonne();
        boolean result=false;
        int indice=0;
        for(int i=0 ; i<colonne.length ; i++){
            if(colonne[i].equals(nom_colonne)){
                indice=i;
                result=true;
            }
        }
        return result;

    }

    public boolean verification_colonne(Relation r1 , Relation r2 )  throws Exception{      // on verifie si les 2 relations ont un colonne commun 
        boolean resultat=false;
       for(int i=0; i<r1.getColonne().length ; i++){
            if(test_existence_colonne(r2  , r1.getColonne()[i])){
                return true;
            }
       }
        return resultat;
    }
    public String colonne_commun(Relation r1 , Relation r2) throws Exception {
        String resultat = new String();
        if( verification_colonne(r1 ,r2 )){
            for(int i=0; i<r1.getColonne().length ; i++){
                if(test_existence_colonne(r2  , r1.getColonne()[i])){
                    resultat=r1.getColonne()[i];
                    break;
                }
            } 
        } else{ 
             GereException ex= new GereException("il n'y a pas de colonne commun entre les deux relations");
       }
        return resultat;

    }


    public boolean test_existence(Object[] ligne , Relation r2) throws Exception{
        int header_r2=r2.getColonne().length;               // colonne de la relation r2
        Object[][] valeur_r2 = r2.getTableau();             // tableau d'objet de r2
        int compte=0;
        if(ligne.length == header_r2 ){
            for( int j=0; j<valeur_r2.length ; j++){
                for(int i=0 ; i<header_r2 ; i++){
                    if(ligne[i].equals(valeur_r2[j][i])){
                        compte++;
                    }
                } 
                if(compte == header_r2){
                    return true;
                }
                compte=0;
            }
        } else{
            throw new GereException("les deux tables n'ont pas le mem nombre de colonne");
        }
        return false;
    }
    
    public int[] indice_des_colonnes(String[] les_colonnes , Relation r1) throws Exception{
        int[] resultat = new int[les_colonnes.length]; 
        for(int i=0; i<resultat.length ; i++)
        {
            resultat[i]=numero_colonne(r1 , les_colonnes[i]);
        }
        return resultat;
    }

     


//----------------utiliser dans union( String a , String b)
    public Object[][] tableau_union(Relation r1 , Relation r2)throws Exception{  
        int ligne_r1=r1.getTableau().length;
        int ligne_r2=r1.getTableau().length;
        int colonne_r1=r1.getColonne().length;
        int ligne=ligne_r1+ligne_r2;
        Object[][] resultat = new Object[ligne][colonne_r1]; 
        if(r1.getColonne().length== r2.getColonne().length)
        {
             resultat= new Object[ligne ][ colonne_r1];
             int j=0;
            for(int i=0 ; i<ligne_r1 ; i++){
                resultat[j]= r1.getTableau()[i];
                j++;
            }
            for(int i=0 ; i<ligne_r2 ; i++){
                resultat[j]= r2.getTableau()[i];
                j++;
            }

        } else{
            throw new GereException("les deux tables n'ont pas le meme nombre de colonne");
        }
        return resultat;
    }
    public int nombre_resultat_diff(Relation r1, Relation r2) throws Exception
    {
        int compte=0;
        Object[][] valeur_r1= r1.getTableau();
        for(int i=0; i<valeur_r1.length ; i++ )
        {
            if(test_existence(valeur_r1[i], r2)==false){
                compte++;
            }
        }
        return compte;
    }


    public int nombre_resultat_intersec(Relation r1, Relation r2) throws Exception
    {
        int compte=0;
        Object[][] valeur_r1= r1.getTableau();
        for(int i=0; i<valeur_r1.length ; i++ )
        {
            if(test_existence(valeur_r1[i], r2)==true){
                compte++;
            }
        }
        return compte;
    }

     public int numero_colonne(Relation r1,  String nom_colonne) throws Exception{
        String[] colonne = r1.getColonne();
        int indice=0;
         if(test_existence_colonne(r1 , nom_colonne)==false){
            throw new GereException("le colonne '"+nom_colonne+"' n'existe pas dans '"+r1.getNom()+"'");
        }
        for(int i=0 ; i<colonne.length ; i++){
            if(colonne[i].equals(nom_colonne)){
                indice=i;
            }
        }
       
        return indice;
    }


    public int nombre_resultat_condition(Relation r1 , String condition) throws Exception{
        int compte =0; 
        Object[][] valeur_r1= r1.getTableau();
        String[] separer= condition.split("\\=");
        int indice_colonne= numero_colonne(r1 , separer[0]);
        for(int i=0; i<valeur_r1.length ; i++){
            if( valeur_r1[i][indice_colonne].equals(separer[1])  ){
                compte++;
            }
        }
        return compte;
    }



    public Object[][] tableau_difference(Relation r1 , Relation r2)throws Exception{
        int compte=nombre_resultat_diff(r1, r2);
        Object[][] resultat= new Object[compte][r1.getColonne().length];
        Object[][] valeur_r1= r1.getTableau();
        int j=0;
        for(int i=0; i<valeur_r1.length ; i++ )
        {
            if(test_existence(valeur_r1[i], r2)==false){
                resultat[j] = valeur_r1[i];
                j++;
            }
        }
        return resultat;
    }
    public Object[][] tableau_intersection(Relation r1 , Relation r2) throws Exception{
        int compte=nombre_resultat_intersec(r1, r2);
        Object[][] resultat= new Object[compte][r1.getColonne().length];
        Object[][] valeur_r1= r1.getTableau();
        int j=0;
        for(int i=0; i<compte ; i++ )
        {
            if(test_existence(valeur_r1[i], r2)){
                resultat[j] = valeur_r1[i];
                j++;
            }
        }
        return resultat;
    }
    public Object[][] tableau_condition(Relation r1 , String condition) throws Exception{
        int ligne=nombre_resultat_condition(r1 , condition);
        String[] header=r1.getColonne(); 
        Object[][] resultat= new Object[ligne][header.length];
        Object[][] valeur_r1= r1.getTableau();
        String[] separer= condition.split("\\=");
        int indice_colonne= numero_colonne(r1 , separer[0]);
        int j=0;
        for(int i=0; i<valeur_r1.length ; i++){
            if( valeur_r1[i][indice_colonne].equals(String.valueOf(separer[1])) ){
                resultat[j] = valeur_r1[i];
                j++;
            }
        }
        return resultat;
    }
    public Object[][] tableau_pc( Relation r1 , Relation r2) throws Exception{   
        Object[][] valeur_r1 = r1.getTableau();
        Object[][] valeur_r2 = r2.getTableau();     
        int ligne_r1=r1.getTableau().length;
        int ligne_r2=r2.getTableau().length;
        int colonne = r1.getColonne().length + r2.getColonne().length;
        int ligne = ligne_r1 * ligne_r2;
        Object[][] resultat= new Object[ligne][colonne];
        int j=0; 
        for(int i=0; i< valeur_r1.length ; i++){
            for(int l=0 ; l<valeur_r2.length ; l++)
            {
                int m=0;
                for( int k=0; k<r1.getColonne().length ; k++ ){
                    resultat[j][m]=valeur_r1[i][k];
                    m++;
                }
                for( int k=0; k<r2.getColonne().length ; k++ ){
                    resultat[j][m]=valeur_r2[l][k];
                    m++;
                }
                j++;
            }
        }
        return resultat;
    }
    public int nombre_resultat_jointure(String a , String b , String condition) throws Exception{
        int compte=0;
        Relation r1 = selection(a);
        Relation r2 = selection(b);
        Relation produit_cart = produit_cartesienne(r1,r2);
        int numero1= numero_colonne(produit_cart,condition.split("\\=")[0]);
        int numero2= numero_colonne(produit_cart,condition.split("\\=")[1]); 
        Object[][] tab=produit_cart.getTableau();
        for(int i=0; i<tab.length ; i++){
            if(tab[i][numero1].equals(tab[i][numero2])){
                compte++;
            }
        }
        return compte;
    }

    public Object[][] tableau_jointure(String a , String b , String condition) throws Exception{
        Relation r1 = selection(a);
        Relation r2 = selection(b);
        Relation produit_cart = produit_cartesienne(r1,r2);
        int numero1= numero_colonne(produit_cart,condition.split("\\=")[0]);
        int numero2= numero_colonne(produit_cart,condition.split("\\=")[1]);   
        int ligne=nombre_resultat_jointure(a,b,condition);
        Object[][] resultat = new Object[ligne][produit_cart.getColonne().length];
        Object[][] tab=produit_cart.getTableau();
        int j=0; 
        for(int i=0; i<tab.length ; i++){
            if(tab[i][numero1].equals(tab[i][numero2])){
                resultat[j]=tab[i];
                j++;
            }
        }
        return resultat;
    }

    


// ------------------------------------------------------------------------------------------------------------------

    public Relation selection(String nom )throws Exception{
        Object[] fichier= lire(nom);

        String nom_table= nom;
        String[] colonne= String.valueOf(fichier[1]).split(",");
        Object[][] valeur= string_to_table(String.valueOf(fichier[2]));
        Relation relation = new Relation(nom_table , colonne , valeur);
        return relation;
    }


    public Relation union(Relation r1 , Relation r2) throws Exception{
        Relation resultat=new Relation("union", r1.getColonne() , tableau_union(r1, r2));
        return resultat;
    }

    public Relation difference(Relation r1 , Relation r2  ) throws Exception{
        Relation resultat=new Relation("difference", r1.getColonne() , tableau_difference(r1, r2));
        return resultat;
    }

    public Relation intersection(Relation r1 , Relation r2  ) throws Exception{
        Relation resultat=new Relation("intersection", r1.getColonne() , tableau_intersection(r1, r2));
        return resultat;
    }

    public Relation condition(Relation r1, String condition ) throws Exception{
        Relation resultat=new Relation("intersection", r1.getColonne() , tableau_condition(r1, condition));
        return resultat;
    }

    public Relation projection(Relation r1 , String colonne) throws Exception
    {
        String[] header= colonne.split(",");
        Object[][] valeur_r1 = r1.getTableau();
        Object[][] resultat =new Object[valeur_r1.length][header.length];
        int[] indice = indice_des_colonnes(header , r1);
        int j=0; 
        for(int i=0; i<valeur_r1.length ;i++){
            for( j=0; j<indice.length ; j++){
                resultat[i][j]=valeur_r1[i][indice[j]];
            }
        }
        Relation result= new Relation("project",header , resultat );
        return result;
    }

    public Relation produit_cartesienne( Relation r1 , Relation r2) throws Exception{
        String[] header=somme_tableau_string(r1, r2);
        Relation resultat= new Relation("produit cartesienne" , header , tableau_pc(r1 , r2));
        return resultat;
    }

    public Relation jointure(Relation r1 , Relation r2 , String c) throws Exception{
        String[] header=somme_tableau_string(r1,r2 );
        Relation resultat= new Relation("produit cartesienne" , header , tableau_jointure(r1.getNom() , r2.getNom() ,c));
        return resultat;
    }
    
    public Relation division(Relation a , Relation b) throws Exception{   // r1: diviseur // r2: quotient  // r3: dividende
        Relation r1= selection(a.getNom());
        Relation r2= selection(b.getNom());   
        Relation r3= selection("r3");
        Relation projection_r1;            
        Relation projection_r2;
        if(test_existence_colonne( r1, r3.getColonne()[0])){
            projection_r1=projection(r1 , r3.getColonne()[0]);
            projection_r2=projection(r2 , r3.getColonne()[1]);
        }else{
            projection_r2=projection(r1 , r3.getColonne()[0]);
            projection_r1=projection(r2 , r3.getColonne()[1]);
        }
        Relation produit_cart= produit_cartesienne(projection_r1 , projection_r2); 
        Relation reste= difference(produit_cart , r3);
        Affichage affichage= new Affichage(produit_cart);
        Affichage affichage1= new Affichage(r3);
        Affichage affichage2= new Affichage(reste);


//------------------ recupere la colonne commun entre les deux relations  reste et quotient
        String colonne_comm1= colonne_commun(reste , r2);
        Relation projection_reste= projection(reste , colonne_comm1);
        Relation projection_quotient= projection(r2 , colonne_comm1);
        Relation resultat= difference(projection_quotient , projection_reste);
        return resultat;
    }

    public String verification_table(String table) throws Exception {
        File fichier = new File("tout_table/"+table+".txt");
        String resultat =  new String();
        if( fichier.exists()){
            resultat= table;
        }else{
            GereException ex= new GereException("ce table n'existe pas");

        }
        return resultat;
    }




    public Relation requette_select( String req )throws Exception{
        String[] resultat = req.split(" ");
        Relation fin=new Relation();
       
        if( resultat.length==4)
        {       if( resultat[1].equals("*") &&  resultat[2].equals("from")){
                 fin= selection(verification_table(resultat[3]));
                }
                else if( resultat[1].equals("*")==false &&  resultat[2].equals("from") ){
                    Relation r1= selection(verification_table(resultat[3]));
                    fin= projection(r1 , resultat[1]);
                }else{
                   throw new GereException("verifier bien votre requette!");
                }
        }
        if( resultat.length > 4 ){
            if(resultat[4].equals("where") ){
                Relation r1= selection(verification_table(resultat[3]));
                fin=condition( r1, resultat[5]);
            }else{
                throw new GereException("verifier bien votre requette!");
            }
        }
       return fin;

    }

    public Relation requette_union(String requeste) throws Exception{
        String[] separer = requeste.split(" ");
        Relation fin=new Relation();
        
        if(separer.length !=5){
            throw new GereException("la requette doit etre de la forme 'union de nom_table1 a nom_table2'");
        }else{
            Relation r1= selection( verification_table(separer[2]));
            Relation r2= selection( verification_table(separer[4]));
            fin= union(r1, r2) ;
        }
        return fin;
    } 


    public Relation requette_jointure(String requeste) throws Exception{
        String[] separer = requeste.split(" ");
        Relation fin = new Relation();
        if(separer.length!=7)
        {
            throw new GereException("la requette doit etre de la forme 'jointure de nom_table1 a nom_table2 quand colonne1=colonne2'");
        }
        else{
            Relation r1= selection( verification_table(separer[2]));
            Relation r2= selection( verification_table(separer[4]));
            fin=jointure(r1, r2 , separer[6]) ;
        }
        return fin;
    }


    public Relation requette_difference(String requeste) throws Exception {
     String[] separer = requeste.split(" ");
        Relation fin=new Relation();
        if(separer.length!=5)
        {
            GereException ex= new GereException("la requette doit etre de la forme 'diference entre nom_table1 et nom_table2 '");
        }
        else{
            Relation r1= selection( verification_table(separer[2]));
            Relation r2= selection( verification_table(separer[4]));
            fin= difference(r1, r2 ) ;
        }   
        return fin;
    }

    public Relation create_table( String requette) throws Exception{
        String[] separer = requette.split(" ");
        Relation fin=new Relation();
        if(separer.length!=6)
        {
              throw new GereException("la requette doit etre de la forme 'creer table nomtable avec colonne: les_noms_colonnes'");
        }
        else{
            File fichier= new File("tout_table/"+separer[2]+".txt");
            if(fichier.exists()==false){
                try(BufferedWriter stylo=new BufferedWriter(new FileWriter(fichier,true))) 
                {
                    stylo.write(separer[2]);
                    stylo.newLine() ;
                    stylo.write(separer[5]);
                    fin=new Relation(separer[2],separer[5].split(",") , new Object[0][0]);
                    stylo.newLine();
                } catch(IOException huhu){
                    huhu.printStackTrace();
                }
            }else{
               throw new GereException("ce table existe deja");
            }  
        }   
        return fin;
    }
    public void insert(String requette) throws Exception{
        String[] separer = requette.split(" "); 
        if(separer.length!=5){
              throw new GereException("la requette doit etre de la forme 'insert into nomtable : x,x,x,....(meme nombre au colonne) '");
        }else{
             File fichier= new File("tout_table\\"+separer[2]+".txt");
            if(fichier.exists()){
                BufferedWriter stylo=new BufferedWriter(new FileWriter(fichier, true));
                BufferedReader br = new BufferedReader(new FileReader(fichier));
                String Nom_table=br.readLine();
                String les_colonnes=br.readLine();
                String ligne= br.readLine();
                if( ligne==null && les_colonnes.split(",").length==separer[4].split(",").length){
                    stylo.write(separer[4]);
                }
                else if(ligne!=null && les_colonnes.split(",").length==separer[4].split(",").length){
                    stylo.write(";"+separer[4]);
                }
                else{
                     throw new GereException("verifier bien votre requette");
                }
                stylo.close();
               throw new GereException("inserer avec succe");
            }else{
               throw new GereException("ce table n'existe pas");
            }  
        }
    }

    public void supprime_table(String requette)throws Exception{
        String[] separer = requette.split(" ");
        if(separer[0].equals("supprime") && separer[1].equals("table")){
            File fichier= new File("tout_table\\"+separer[2]+".txt");
            if(fichier.exists()){
                fichier.delete();
               throw new GereException("table "+separer[2]+" supprimer avec succes");
                
            }else{
               throw new GereException("ce table n'existe pas");
            }
        }else{
               throw new GereException("verifier bien votre ! la requette doit etre de la forme 'supprime table nomTable'");
        }
    }
    
    public int compte_fichier_txt() throws Exception {
        int compte = 0;
        File dossier= new File(System.getProperty("user.dir")+"/tout_table");    
        File[] liste= dossier.listFiles();
        for(int i=0 ; i<liste.length ; i++){
            if(liste[i].isFile() && liste[i].getName().contains(".txt") ){
                compte++;
            }
        }
        return compte;
    }

    public Relation afficher_les_tables() throws Exception{
        File dossier= new File(System.getProperty("user.dir")+"/tout_table");
        File[] liste= dossier.listFiles();
        Object[][] nom_fichier = new Object[compte_fichier_txt()][1];
        int j=0;
        for(int i=0 ; i<liste.length ; i++){
            if(liste[i].isFile() && liste[i].getName().contains(".txt") ){
                nom_fichier[j][0]=liste[i].getName().replace(".txt" , "");
                j++;
            }
        }
        String[] colonne = new String[1];
        colonne[0]="les tables";
        Relation relation = new Relation("tables" , colonne , nom_fichier);      
        return relation;
    }


    public Relation requette(String req) throws Exception{
        String[] resultat = req.split(" ");
        Relation fin=new Relation();
        if(resultat[0].equals("select")){
            fin=requette_select(req);
        }
        else if( resultat[0].equals("union") )
        {
            fin=requette_union(req);
        }
        else if(resultat[0].equals("jointure")){
            fin=requette_jointure(req);
        }
        else if(resultat[0].equals("difference")){
            fin=requette_difference(req);
        } else if(resultat[0].equals("creer")){
            fin=create_table(req);
        }
        else if(resultat[0].equals("insert")){
            insert(req);
        }else if(resultat[0].equals("supprime")){
            supprime_table(req);
        } 
        else if( req.equals("afficher les tables")){
            fin=afficher_les_tables();
        }else{
            throw new GereException("ce requette n'existe pas");
        }
        return fin;
    }

}