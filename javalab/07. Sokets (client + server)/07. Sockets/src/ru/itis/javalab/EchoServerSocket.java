package ru.itis.javalab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerSocket {

    // два клиента, сообщение от первого клиента должно уходить второму клиенту
    // сообщение от второго клиента должно уходить первому
    public void start(int port) {
        ServerSocket server;

        try {
            server = new ServerSocket(port);
            // уводит приложение в ожидание, пока не подключится клиент
            // как только клиент подключился, поток продолжает выполнение и помещает
            // "клиента" в client
            Socket client = server.accept();

            // читаем сообщения от клиента
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter toClient = new PrintWriter(client.getOutputStream(), true);

            String messageFromClient = fromClient.readLine();
            while (messageFromClient != null) {
                System.out.println("Message from client: " + messageFromClient);
                toClient.println("Message from server: " + messageFromClient);
                messageFromClient = fromClient.readLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
