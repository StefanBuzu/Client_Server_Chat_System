package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        UDPBroadcaster udpBroadcaster = new UDPBroadcaster("230.0.0.0",8888);
        while (true) {
            System.out.println("Server is ready for input port 8080");
            Socket socket = serverSocket.accept();
            LoginCommunicator loginCommunicator = new LoginCommunicator(socket, udpBroadcaster);
            Thread communicatorThread = new Thread(loginCommunicator);
            communicatorThread.start();
        }
    }
}
