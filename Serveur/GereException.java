package exception;
import java.io.Serializable;

public class GereException extends Exception implements Serializable{
    String Exception;
    public String getException(){
        return Exception;
    }
    public GereException(String exception){
        Exception=exception;
    }
}