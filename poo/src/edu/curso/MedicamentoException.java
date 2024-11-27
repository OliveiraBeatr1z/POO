package src.edu.curso;

public class MedicamentoException extends Exception{
    public MedicamentoException(String message){
        super(message);
    }

    public MedicamentoException(){
        super();
    }

    public MedicamentoException(Throwable t){
        super(t);
    }
}
