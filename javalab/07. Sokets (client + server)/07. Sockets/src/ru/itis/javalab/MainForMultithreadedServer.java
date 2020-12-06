package ru.itis.javalab;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class MainForMultithreadedServer {
    public static final int PORT = 8080;
    public static LinkedList<MultithreadedServer> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new MultithreadedServer(socket));
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        } finally {
            server.close();
        }
    }
}
