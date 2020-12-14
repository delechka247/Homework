package ru.itis.inf;

import ru.itis.inf.game.BotForConsoleGame;
import ru.itis.inf.game.ConsoleGameService;
import ru.itis.inf.game.MoveStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultithreadedServer extends Thread {
    private Socket socket;
    private BufferedReader fromClient;
    private PrintWriter toClient;
    boolean isGameOver = false;
    MoveStatus moveStatus = MoveStatus.SIMPLE;
    MoveStatus oldMoveStatus = MoveStatus.SIMPLE;
    int oldX = 0;
    int oldY = 0;
    int newX = 0;
    int newY = 0;
    BotForConsoleGame bot = new BotForConsoleGame();

    public MultithreadedServer(Socket socket) throws IOException {
        this.socket = socket;
        fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toClient = new PrintWriter(socket.getOutputStream(), true);
        this.start();
    }

    @Override
    public void run() {
        this.send("Ваше имя: Player-" + (Integer.valueOf(currentThread().getName().replaceAll("Thread-", "")) + 1));
        while (true) {
            try {
                //получает
                String newMessage = fromClient.readLine();
                if (Integer.valueOf(currentThread().getName().replaceAll("Thread-", "")) + 1 == 1 && MainForMultithreadedServer.isFirst ||
                        Integer.valueOf(currentThread().getName().replaceAll("Thread-", "")) + 1 == 2 && !MainForMultithreadedServer.isFirst) {
                    String[] coordinates = newMessage.split(" ");

                    if (oldMoveStatus.equals(MoveStatus.DOUBLE_PLUS))
                        moveStatus = MoveStatus.DOUBLE_PLUS;

                    if (!oldMoveStatus.equals(MoveStatus.DOUBLE_PLUS)) {
                        oldX = Integer.valueOf(coordinates[0]);
                        oldY = Integer.valueOf(coordinates[1]);
                        newX = Integer.valueOf(coordinates[2]);
                        newY = Integer.valueOf(coordinates[3]);
                    } else {
                       if (!moveStatus.equals(MoveStatus.WRONG_MOVE) && !moveStatus.equals(MoveStatus.NOT_YOUR_CHEKER) && !moveStatus.equals(MoveStatus.INCORRECT_INPUT)) {
                            oldX = newX;
                            oldY = newY;
                            newX = Integer.valueOf(coordinates[0]);
                            newY = Integer.valueOf(coordinates[1]);
                        }
                    }


                    if (oldMoveStatus.equals(MoveStatus.DOUBLE_PLUS))
                        moveStatus = MoveStatus.DOUBLE_PLUS;

                    if (oldMoveStatus.equals(MoveStatus.DOUBLE_PLUS) && newX == -1 && newY == -1) {
                        MainForMultithreadedServer.isFirst = !MainForMultithreadedServer.isFirst;
                        oldMoveStatus = MoveStatus.SIMPLE;
                        moveStatus = MoveStatus.PASS;
                    } else {
                        moveStatus = MainForMultithreadedServer.cgs.move(oldX, oldY, newX, newY, MainForMultithreadedServer.isFirst, moveStatus);
                        if (moveStatus.equals(MoveStatus.SIMPLE) || moveStatus.equals(MoveStatus.DOUBLE)) {
                            MainForMultithreadedServer.isFirst = !MainForMultithreadedServer.isFirst;
                            oldMoveStatus = moveStatus;
                        } else if (moveStatus.equals(MoveStatus.DOUBLE_PLUS)) {
                            oldMoveStatus = MoveStatus.DOUBLE_PLUS;
                        }
                        System.out.println(MainForMultithreadedServer.cgs.moveStatusToString(moveStatus));
                        MainForMultithreadedServer.cgs.getGameBoard().printBoard();

                        if (moveStatus.equals(MoveStatus.SIMPLE) || moveStatus.equals(MoveStatus.DOUBLE) || moveStatus.equals(MoveStatus.DOUBLE_PLUS)) {
                            if (MainForMultithreadedServer.isFirst)
                                isGameOver = MainForMultithreadedServer.cgs.didWhiteWin();
                            else
                                isGameOver = MainForMultithreadedServer.cgs.didBlackWin();
                            if (isGameOver)
                                break;
                        }
                    }

                    //отправляет
                    if (moveStatus.equals(MoveStatus.SIMPLE) || moveStatus.equals(MoveStatus.DOUBLE) || moveStatus.equals(MoveStatus.DOUBLE_PLUS) || moveStatus.equals(MoveStatus.PASS)) {
                        for (MultithreadedServer server : MainForMultithreadedServer.serverList) {
                            server.send("Player-" + (Integer.valueOf(currentThread().getName().replaceAll("Thread-", "")) + 1) + "," + moveStatus + "," + oldX + " " + oldY + " " + newX + " " + newY);
                        }
                    } else {
                        this.send("Player-" + (Integer.valueOf(currentThread().getName().replaceAll("Thread-", "")) + 1) + "," + moveStatus + "," + newMessage);
                    }
                    
                } else {
                    for (MultithreadedServer server : MainForMultithreadedServer.serverList) {
                        server.send("Player-" + (Integer.valueOf(currentThread().getName().replaceAll("Thread-", "")) + 1) + ",NOT_YOUR_TURN," + newMessage);
                    }
                }
                
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
