package ru.itis.inf.game;

import java.util.Scanner;

public class MainForConsoleGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConsoleGameService cgs = new ConsoleGameService();
        cgs.getGameBoard().printBoard();

        boolean isFirst = true;
        boolean isGameOver = false;
        MoveStatus moveStatus = MoveStatus.SIMPLE;
        MoveStatus oldMoveStatus = MoveStatus.SIMPLE;
        int oldX = 0;
        int oldY = 0;
        int newX = 0;
        int newY = 0;
        BotForConsoleGame bot = new BotForConsoleGame();
        while (true) {
            String player;
            if(isFirst)
                player = "первый";
            else
                player = "второй";

            System.out.println("Сейчас ходит " + player + " игрок.");

            //bot.printForAll(cgs.getGameBoard().getBoard());

            if(oldMoveStatus.equals(MoveStatus.DOUBLE_PLUS))
                moveStatus = MoveStatus.DOUBLE_PLUS;

            if(!oldMoveStatus.equals(MoveStatus.DOUBLE_PLUS)) {
                System.out.println("Пожалуйста, введите координаты старой шашки, затем новой (все числа через пробел)");
                oldX = sc.nextInt();
                oldY = sc.nextInt();
            }
            else {
                System.out.println("Вы ходите той же шашкой(" + newX + ";" + newY +"), введите только новые координаты");
                if (!moveStatus.equals(MoveStatus.WRONG_MOVE) && !moveStatus.equals(MoveStatus.NOT_YOUR_CHEKER) && !moveStatus.equals(MoveStatus.INCORRECT_INPUT)) {
                    oldX = newX;
                    oldY = newY;
                }
                System.out.println("Введите -1 -1 в качестве новых координат, если хотите пропустить ход");
                System.out.print(newX + " " + newY + " ");
            }
            newX = sc.nextInt();
            newY = sc.nextInt();

            if(oldMoveStatus.equals(MoveStatus.DOUBLE_PLUS))
                moveStatus = MoveStatus.DOUBLE_PLUS;

            if(moveStatus.equals(MoveStatus.DOUBLE_PLUS) && newX == -1 && newY == -1) {
                isFirst = !isFirst;
                oldMoveStatus = MoveStatus.SIMPLE;
                moveStatus = MoveStatus.SIMPLE;
            } else {
                moveStatus = cgs.move(oldX, oldY, newX, newY, isFirst, moveStatus);
                if (moveStatus.equals(MoveStatus.SIMPLE) || moveStatus.equals(MoveStatus.DOUBLE)) {
                    isFirst = !isFirst;
                    oldMoveStatus = moveStatus;
                } else if (moveStatus.equals(MoveStatus.DOUBLE_PLUS)) {
                    oldMoveStatus = MoveStatus.DOUBLE_PLUS;
                }
                System.out.println(cgs.moveStatusToString(moveStatus));
                cgs.getGameBoard().printBoard();

                if (moveStatus.equals(MoveStatus.SIMPLE) || moveStatus.equals(MoveStatus.DOUBLE) || moveStatus.equals(MoveStatus.DOUBLE_PLUS)) {
                    if (isFirst)
                        isGameOver = cgs.didWhiteWin();
                    else
                        isGameOver = cgs.didBlackWin();
                    if (isGameOver)
                        break;
                }
            }
        }

        String winner;
        if(isFirst)
            winner = "первый";
        else
            winner = "второй";
        System.out.println("Игра окончена. " + winner + " игрок победил.");
    }
}
