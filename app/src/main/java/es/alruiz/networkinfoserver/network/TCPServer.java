package es.alruiz.networkinfoserver.network;

/**
 * Created by Casa on 28/01/2017.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread {

    public static final int SERVERPORT = 4444;
    private boolean running = false;
    private PrintWriter printWriter;
    private OnMessageReceived messageListener;

    public TCPServer(OnMessageReceived messageListener) {
        this.messageListener = messageListener;
    }

    public void sendMessage(String message) {
        if (printWriter != null && !printWriter.checkError()) {
            printWriter.println(message);
            printWriter.flush();
        }
    }

    @Override
    public void run() {
        super.run();

        running = true;

        try {
            System.out.println("S: Connecting...");

            ServerSocket serverSocket = new ServerSocket(SERVERPORT);

            Socket client = serverSocket.accept();
            System.out.println("S: Receiving...");

            try {

                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                while (running) {
                    String message = in.readLine();

                    if (message != null && messageListener != null) {
                        messageListener.messageReceived(message);
                    }
                }

            } catch (Exception e) {
                System.out.println("S: Error");
                e.printStackTrace();
            } finally {
                client.close();
                System.out.println("S: Done.");
            }

        } catch (Exception e) {
            System.out.println("S: Error");
            e.printStackTrace();
        }

    }

    public interface OnMessageReceived {
        public void messageReceived(String message);
    }

}
