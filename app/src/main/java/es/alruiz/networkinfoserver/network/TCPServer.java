package es.alruiz.networkinfoserver.network;

/**
 * Created by Casa on 28/01/2017.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import timber.log.Timber;

public class TCPServer {

    private String clientMessage;
    private String clientIp = "";
    private int port = 8888;
    private OnMessageReceived mMessageListener = null;
    private boolean active = false;

    private PrintWriter out;
    private BufferedReader in;


    public TCPServer(String clientIp, OnMessageReceived listener) {
        mMessageListener = listener;
        this.clientIp = clientIp;
    }

    public void run() {
        active = true;
        try {
            InetAddress serverAddr = InetAddress.getByName(clientIp);
            Timber.i("Server", "Connecting");

            try (Socket socket = new Socket(serverAddr, port)) {
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                Timber.i("Message sent");
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (active) {
                    clientMessage = in.readLine();

                    if (clientMessage != null && mMessageListener != null) {
                        mMessageListener.messageReceived(clientMessage);
                    }
                    clientMessage = null;
                }
                Timber.i("Received Message:", clientMessage);

            } catch (Exception e) {
                Timber.e(e.getMessage());
            }

        } catch (Exception e) {
            Timber.e(e.getMessage());
        }
    }

    public void sendMessage(String message) {
        if (out != null && !out.checkError()) {
            out.println(message);
            out.flush();
        }
    }

    public void stopClient() {//TODO alf llamar
        active = false;
    }


    public interface OnMessageReceived {
        void messageReceived(String message);
    }
}
