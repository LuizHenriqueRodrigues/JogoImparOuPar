package Util;

import java.io.Serializable;

public class MsgRequest implements Serializable{
    private double value1;
    private char escolha;
    private double value2;
    
    public MsgRequest(double value1, double value2, char escolha) {
        this.value1 = value1;
        this.value2 = value2;
        this.escolha = escolha;
    }

    public double getValue1() {
        return value1;
    }

    public double getvalue2() {
        return value2;
    }

    public char getEscolha() {
        return escolha;
    }

}