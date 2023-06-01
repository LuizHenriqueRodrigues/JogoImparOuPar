package Util;

import java.io.Serializable;

public class MsgResponse implements Serializable {
    
    private Status status;
    private String resultado;

    public MsgResponse(Status status) {
        this.status = status;    
    }

    public Status getStatus() {
        return status;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}



