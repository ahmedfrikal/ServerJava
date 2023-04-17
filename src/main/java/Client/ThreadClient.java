package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadClient extends Thread {

    public static void main(String[] args) {
        new ThreadClient().start();
    }
    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Demmarage de client");
            while (true){
                new ThreadClient.Convesation(socket).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    class  Convesation  extends Thread{
        private Socket socket;
        public Convesation(Socket socket){
            this.socket=socket;
        }
        @Override
        public void run() {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput);
                    System.out.println("Message envoyé au serveur : " + userInput);
                    System.out.println("Réponse du serveur : " + in.readLine());
                }

                out.close();
                in.close();
                stdIn.close();
                socket.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
