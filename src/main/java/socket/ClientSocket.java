package socket;

import java.io.*;
import java.net.Socket;

public class ClientSocket {
    private static final String LOCAL_HOST = "127.0.0.1";
    private static final int PORT = 8089;

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(LOCAL_HOST, PORT)) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("gosha");

            String str =in.readLine();
            System.out.println(str);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
