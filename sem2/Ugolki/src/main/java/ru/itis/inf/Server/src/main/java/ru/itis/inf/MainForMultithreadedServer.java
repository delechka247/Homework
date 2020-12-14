package ru.itis.inf;

import ru.itis.inf.game.ConsoleGameService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class MainForMultithreadedServer {
    public static final int PORT = 4321;
    public static LinkedList<MultithreadedServer> serverList = new LinkedList<>();
    public static ConsoleGameService cgs = new ConsoleGameService();
    public static boolean isFirst = true;
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT, 2);
        System.out.println("Server started at " + PORT);
        
        try {
            while (serverList.size() < 2) {
                Socket socket = server.accept();
                try {
                    serverList.add(new MultithreadedServer(socket));
                    System.out.println("Новый клиент! Осталось ожидать игроков: " + (2 - serverList.size()));
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
            
        } finally {
            server.close();
        }
    }
}