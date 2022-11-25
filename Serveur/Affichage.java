package affichage;
import relation.*;
import fonction.*;
import javax.swing.JTable.*;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;
import javax.swing.*;
public class Affichage extends JFrame{
    public Affichage(){}
    public Affichage(Relation relation){
        setSize(500,500);
        Object[][] tableau = relation.getTableau();
        String[] header=relation.getColonne();
        JTable table= new JTable(tableau,header);
        getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
        getContentPane().add(table,BorderLayout.CENTER);
        this.setVisible(true);
    }

}