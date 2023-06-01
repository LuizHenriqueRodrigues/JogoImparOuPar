package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Util.MsgRequest;
import Util.MsgResponse;

public class ThreadImparOuPar extends Thread {
    private int ganhou;
    private int perdeu;
    private Socket cliente;

    public ThreadImparOuPar(Socket cliente) {
        this.cliente = cliente;
        ganhou = 0;
        perdeu = 0;
    }

    @Override
    public void run() {
        System.out.println("Conectado ao cliente: " + cliente.getInetAddress().getHostAddress());

        try {
            ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

            MsgRequest request = (MsgRequest) in.readObject();

            char escolha = request.getEscolha();
            double valor1 = request.getValue1();
            double value2 = request.getvalue2();
            boolean resp;
            MsgResponse response;

            switch (escolha) {
                case 'P':
                    resp = jogarPar(value2, valor1);
                    response = new MsgResponse(null);
                    response.setResultado(resp  ? "Par" : "Ímpar");
                    out.writeObject(response);
                    break;

                case 'I':
                    resp = jogarImpar(value2, valor1);
                    response = new MsgResponse(null);
                    response.setResultado(resp  ? "Ímpar" : "Par");
                    out.writeObject(response);
                    break;

                default:
                    System.out.println("Escolha inválida.");
                    break;
            }

            in.close();
            out.close();
            cliente.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        synchronized (ThreadImparOuPar.class) {
            System.out.println("Contador de vitórias: " + ganhou);
            System.out.println("Contador de derrotas: " + perdeu);

            if (ganhou > perdeu) {
                System.out.println("O jogador ganhou!");
            } else if (ganhou < perdeu) {
                System.out.println("O computador ganhou!");
            } else {
                System.out.println("Empate!");
            }
        }
    }

    private boolean jogarPar(double value2, double valor1) {
        double valorComputador = value2;  // Gera um número aleatório para o computador
        double soma = valor1 + valorComputador;

        if ((int) soma % 2 == 0) {
            System.out.println("A soma é par.");
            ganhou++; // Incrementar o contador de vitórias
            return true;
        } else {
            System.out.println("A soma é ímpar.");
            perdeu++; // Incrementar o contador de derrotas
            return false;
        }
    }

    private boolean jogarImpar(double value2, double valor1) {
        double valorComputador = value2;  // Gera um número aleatório para o computador
        double soma = valor1 + valorComputador;
        
        if ((int) soma % 2 != 0) {
            System.out.println("A soma é ímpar.");
            ganhou++; // Incrementar o contador de vitórias
            return true;
        } else {
            System.out.println("A soma é par.");
            perdeu++; // Incrementar o contador de derrotas
            return false;
        }
    }

    public int getGanhou() {
        return ganhou;
    }

    public int getPerdeu() {
        return perdeu;
    }
}
