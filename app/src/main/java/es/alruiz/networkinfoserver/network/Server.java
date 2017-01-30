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
        String operation = "";

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(serverSocketPort);
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    out.println("respuesta desde el movil");
                    operation = in.readLine();
                    message += operation + " from " + socket.getInetAddress() + "\n";

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            activity.showMessage(message);
                        }
                    });

//                    ServerSocketReplyThread serverSocketReplyThread = new ServerSocketReplyThread(socket);
//                    serverSocketReplyThread.run();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    private class ServerSocketReplyThread extends Thread {
//
//        private Socket hostThreadSocket;
//
//        ServerSocketReplyThread(Socket socket) {
//            hostThreadSocket = socket;
//        }
//
//        @Override
//        public void run() {
////            OutputStream outputStream;
//            final String msgReply = "Hello from AndroidServer";
//
//            try {
////                outputStream = hostThreadSocket.getOutputStream();
////                PrintStream printStream = new PrintStream(outputStream);
////                printStream.print(msgReply);
////                printStream.close();
//                PrintWriter out = new PrintWriter(hostThreadSocket.getOutputStream(), true);
//                out.println(msgReply);
//
//                message += "Replayed: " + msgReply + "\n";
//
//            } catch (IOException e) {
//                e.printStackTrace();
//                message += "Something wrong! " + e.toString() + "\n";
//            }
//
//            activity.runOnUiThread(new Runnable() {
//
//                @Override
//                public void run() {
//                    activity.showMessage(message);
//                }
//            });
//        }
//
//    }
}
