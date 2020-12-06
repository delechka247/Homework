package ru.itis.javalab;

import java.util.Scanner;

public class MainForClient {
    public static void main(String[] args) {

        SocketClient client = new SocketClient("127.0.0.1", 8080);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String message = scanner.nextLine();
            client.sendMessage(message);
        }
    }
}

