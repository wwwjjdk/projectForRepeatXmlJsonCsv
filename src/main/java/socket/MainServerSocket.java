package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServerSocket {
    private static final int PORT = 8089;

    public static void main(String[] args) throws IOException {

        //ServerSocket serverSocket = new java.net.ServerSocket(PORT);
        System.out.println("server started");

        while (true) {
            ServerSocket serverSocket = new java.net.ServerSocket(PORT);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.printf("client accepted, port: %s\n", clientSocket.getPort());
            String str = in.readLine();

            out.println(String.format("answer from server: %s", str));
            serverSocket.close();
        }
    }

}
