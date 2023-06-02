package Util;

import java.io.Serializable;

public class MsgResponse implements Serializable {
    

    private String resultado;


    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}



