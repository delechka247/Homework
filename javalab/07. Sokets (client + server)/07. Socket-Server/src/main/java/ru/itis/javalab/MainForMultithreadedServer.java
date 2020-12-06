package ru.itis.javalab;

import com.beust.jcommander.JCommander;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class MainForMultithreadedServer {
    public static LinkedList<MultithreadedServer> serverList = new LinkedList<>();

    public static void main(String[] argv) throws IOException {
        Args args = new Args();
        JCommander.newBuilder().addObject(args).build().parse(argv);
        int port = args.port;

        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started at " + port);
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new MultithreadedServer(socket));
                    System.out.println("new client");
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        } finally {
            server.close();
        }
    }
}
