package exception;
import java.io.Serializable;

public class GereException extends Exception implements Serializable{
    public  void table_not_exist(String exception){
        System.out.println(exception);
    }
}