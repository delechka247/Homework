package ru.itis.javalab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultithreadedServer extends Thread {
    private Socket socket;
    private BufferedReader fromClient;
    private PrintWriter toClient;

    public MultithreadedServer(Socket socket) throws IOException {
        this.socket = socket;
        fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toClient = new PrintWriter(socket.getOutputStream(), true);
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String newMessage = fromClient.readLine();
                for (MultithreadedServer server : MainForMultithreadedServer.serverList)
                    server.send("Message from Client-" + (Integer.valueOf(currentThread().getName().replaceAll("Thread-", "")) + 1) + ": " + newMessage);

            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private void send(String message) {
        toClient.write(message + "\n");
        toClient.flush();
    }
}
