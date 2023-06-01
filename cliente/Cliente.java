package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import Util.MsgRequest;
import Util.MsgResponse;

public class Cliente {

    public static void main(String[] args) {
        Socket socket;
        int PORT = 12345;
        String HOST = "localhost";
        double value1;
        char escolha;

        Scanner entrada = new Scanner(System.in);

        
        try {
            socket = new Socket(HOST, PORT);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Digite uma operação (I para ímpar e P para par): ");
            escolha = entrada.nextLine().charAt(0); // pega apenas o primeiro caractere
            System.out.println("Digite o primeiro número: ");
            value1 = Double.parseDouble(entrada.nextLine());
            System.out.println("Digite o segundo número: ");
            Random nuRandom = new Random();
            int value2 = nuRandom.nextInt(5);
            int resposta = (int) (value2 + value1);

            System.out.println("Seu número: " + value1);
            System.out.println("Número do adversário: " + (resposta - value1));
            System.out.println("Resultado: " + resposta);

            MsgRequest request = new MsgRequest(value1, value2, escolha);
            out.writeObject(request);

            MsgResponse response = (MsgResponse) in.readObject();

            System.out.println("Resultado do jogo: " + response.getResultado());

        } catch (Exception e) {
            System.out.println("error" +e.getMessage());
        }
       entrada.close();
}
        
    }

