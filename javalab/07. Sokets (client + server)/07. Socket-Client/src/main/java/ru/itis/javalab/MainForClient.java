package ru.itis.javalab;

import com.beust.jcommander.JCommander;

import java.io.IOException;
import java.util.Scanner;

public class MainForClient {

    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder().addObject(args).build().parse(argv);
        int port = args.port;
        String ip = args.ip;

        SocketClient client = new SocketClient(ip, port);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String message = scanner.nextLine();
            client.sendMessage(message);
        }
    }
}

