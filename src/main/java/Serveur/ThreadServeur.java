package Serveur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadServeur extends Thread {
    private int nbrClient;

    public static void main(String[] args) {
        ThreadServeur serveur=new ThreadServeur();
        serveur.start();
    }
    @Override
    public void run() {
        try {
            ServerSocket ss=new ServerSocket(1234);
            System.out.println("Demmarage de serveur");
            while (true){
                Socket s=ss.accept();

                InputStream is=s.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader b=new BufferedReader(isr);
                OutputStream os=s.getOutputStream();
                PrintWriter pw=new PrintWriter(os,true);
                String Ip=s.getRemoteSocketAddress().toString();
                this.nbrClient++;
                System.out.println("Connexion client num"+this.nbrClient+" IP "+Ip);

                pw.println("Bienvenue Vous etes le client num :"+this.nbrClient);
                while (true){
                    String req=b.readLine();
                    System.out.println("Client "+this.nbrClient +" "+req);
                    pw.println(req.length());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
