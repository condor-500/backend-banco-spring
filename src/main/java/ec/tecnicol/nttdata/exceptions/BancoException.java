package ec.tecnicol.nttdata.exceptions;

public class BancoException extends  Exception{
    private String codeError ;
    private String nombreError;





    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }

    public String getNombreError() {
        return nombreError;
    }

    public void setNombreError(String nombreError) {
        this.nombreError = nombreError;
    }
}
