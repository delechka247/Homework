package ru.itis.inf;

import ru.itis.inf.game.ConsoleGameService;
import ru.itis.inf.game.MoveStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    private Socket client;

    private PrintWriter toServer;
    private BufferedReader fromServer;
    private ConsoleGameService cgs;
    private int x = -1;
    private int y = -1;


    public SocketClient(String host, int port) {
        try {
            client = new Socket(host, port);
            toServer = new PrintWriter(client.getOutputStream(), true);
            fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            cgs = new ConsoleGameService();
            new Thread(receiverMessagesTask).start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public ConsoleGameService getCgs() {
        return cgs;
    }

    public void sendMessage(String message) {
        toServer.println(message);
    }

    private Runnable receiverMessagesTask = () -> {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                String messageFromServer = fromServer.readLine();
                if (messageFromServer != null) {
                    //если это кодовая строка для игры
                    if (messageFromServer.startsWith("Player")) {
                        String[] msg = messageFromServer.split(",");
                        String player = msg[0];
                        MoveStatus moveStatus = MoveStatus.valueOf(msg[1]);
                        System.out.println(player + ", " + cgs.moveStatusToString(moveStatus));

                        if(!moveStatus.equals(MoveStatus.PASS)) {
                            String[] coor = msg[2].split(" ");
                            int[] coordinates = new int[4];
                            for (int i = 0; i < 4; i++) {
                                coordinates[i] = Integer.valueOf(coor[i]);
                            }
                            //ход и доска после хода
                            if (moveStatus.equals(MoveStatus.SIMPLE) || moveStatus.equals(MoveStatus.DOUBLE) || moveStatus.equals(MoveStatus.DOUBLE_PLUS)) {
                                cgs.getGameBoard().moveFromTo(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                                System.out.println("Так выглядит доска после хода " + player + ":");
                                cgs.getGameBoard().printBoard();
                            }
                        }

                        if(moveStatus.equals(MoveStatus.SIMPLE) || moveStatus.equals(MoveStatus.DOUBLE) || moveStatus.equals(MoveStatus.PASS)) {
                            System.out.println("Ход передается следующему игроку.");
                        }
                        if(moveStatus.equals(MoveStatus.DOUBLE_PLUS)) {
                            System.out.println("Вы ходите той же шашкой. Вводите только новые координаты");
                            System.out.println("Если хотите передать ход следующему игроку, введите -1 -1");
                        }
                    } else
                        System.out.println(messageFromServer);
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    };
}