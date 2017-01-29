package es.alruiz.networkinfoserver.network;

/**
 * Created by Alfonso on 29/01/2017.
 */

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import es.alruiz.networkinfoserver.ui.main.MainActivity;

public class Server {
    private MainActivity activity;
    private ServerSocket serverSocket;
    private String message = "";
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
        int client = 0;

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(serverSocketPort);
                while (true) {
                    Socket socket = serverSocket.accept();
                    client++;
                    message += "Client: " + client + " from "
                            + socket.getInetAddress() +"\n";

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.showMessage(message);
                        }
                    });

                    ServerSocketReplyThread serverSocketReplyThread = new ServerSocketReplyThread(socket, client);
                    serverSocketReplyThread.run();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ServerSocketReplyThread extends Thread {

        private Socket hostThreadSocket;
        private int client;

        ServerSocketReplyThread(Socket socket, int client) {
            hostThreadSocket = socket;
            this.client = client;
        }

        @Override
        public void run() {
            OutputStream outputStream;
            final String msgReply = "Hello from AndroidServer, you are " + client;

            try {
                outputStream = hostThreadSocket.getOutputStream();
                PrintStream printStream = new PrintStream(outputStream);
                printStream.print(msgReply);
                printStream.close();

                message += "Replayed: " + msgReply + "\n";

                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        activity.showMessage(message);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
                message += "Something wrong! " + e.toString() + "\n";
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
