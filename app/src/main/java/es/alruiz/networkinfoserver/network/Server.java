package es.alruiz.networkinfoserver.network;

/**
 * Created by Alfonso on 29/01/2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import es.alruiz.networkinfoserver.ui.main.MainActivity;

public class Server {
    private MainActivity activity;
    private ServerSocket serverSocket;
    private String message = "";
    private String line = "";
    private static final int serverSocketPort = 8080;

    public Server(MainActivity activity) {
        this.activity = activity;
        Thread socketServerThread = new Thread(new ServerSocketThread());
        socketServerThread.start();
    }

    public void stopServer() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ServerSocketThread extends Thread {

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(serverSocketPort);
                while (true) {
                    Socket socket = serverSocket.accept();

                    ServerSocketReplyThread serverSocketReplyThread = new ServerSocketReplyThread(socket);
                    serverSocketReplyThread.run();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ServerSocketReplyThread extends Thread {

        private Socket socket;

        ServerSocketReplyThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            final String msgReply = "JSON";

            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                line = in.readLine();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.showMessage(line);
                    }
                });

                out.println(msgReply);
                out.close();
                in.close();

                message += "Replayed JSON: " + msgReply + "\n";

            } catch (IOException e) {
                e.printStackTrace();
                message += "Server problem...  " + e.toString() + "\n";
            }

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.showMessage(message);
                }
            });
        }

    }
}