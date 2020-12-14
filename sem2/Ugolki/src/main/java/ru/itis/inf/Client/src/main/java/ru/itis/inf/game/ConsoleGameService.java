package ru.itis.inf.game;

import java.util.Scanner;

public class ConsoleGameService {
    private Board gameBoard;
    private BotForConsoleGame bot = new BotForConsoleGame();

    public ConsoleGameService() {
        this.gameBoard = new Board();
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public boolean areNumbersCorrect(int oldX, int oldY, int newX, int newY) {
        if (oldX < 8 && oldY < 8 && newX < 8 && newY < 8 && oldX >= 0 && oldY >= 0 && newX >= 0 && newY >= 0) {
            return true;
        } else
            return false;
    }

    public MoveStatus move(int oldX, int oldY, int newX, int newY, boolean isFirst, MoveStatus moveStatus) {
        if (this.areNumbersCorrect(oldX, oldY, newX, newY)) {
            if (this.gameBoard.isChekerWhite(oldX, oldY) == isFirst) {
                if (gameBoard.isMoveAble(oldX, oldY, newX, newY, moveStatus)) {
                    gameBoard.moveFromTo(oldX, oldY, newX, newY);
                    if (gameBoard.isMoveDouble(oldX, oldY, newX, newY)) {
                        if (gameBoard.canDoDouble(oldX, oldY, newX, newY))
                            return MoveStatus.DOUBLE_PLUS;
                        else {
                            return MoveStatus.DOUBLE;
                        }
                    } else {
                        return MoveStatus.SIMPLE;
                    }
                } else
                    return MoveStatus.WRONG_MOVE;
            } else
                return MoveStatus.NOT_YOUR_CHEKER;
        } else
            return MoveStatus.INCORRECT_INPUT;
    }

    public String moveStatusToString(MoveStatus moveStatus) {
        if (moveStatus.equals(MoveStatus.SIMPLE)) {
            return "Ход успешно совершен";
        } else if (moveStatus.equals(MoveStatus.DOUBLE_PLUS)) {
            return "Вы совершили двойной ход. Можете сделать двойной ход еще раз.";
        } else if (moveStatus.equals(MoveStatus.DOUBLE)) {
            return "Вы совершили двойной ход. Но двойных ходов дальше нет.";
        } else if (moveStatus.equals(MoveStatus.INCORRECT_INPUT)) {
            return "Некорректный ввод координат. Введите еще раз.";
        } else if (moveStatus.equals(MoveStatus.NOT_YOUR_CHEKER)) {
            return "Это не ваша шашка. Введите еще раз.";
        } else if (moveStatus.equals(MoveStatus.NOT_YOUR_TURN)) {
            return "Сейчас не Ваша очередь. Подождите.";
        } else if (moveStatus.equals(MoveStatus.PASS)) {
            return "Вы пропускаете ход";
            //} else if (moveStatus.equals(MoveStatus.WRONG_MOVE)) {
        } else {
            return "Так ходить нельзя. Введите ещё раз.";
        }

    }


    public boolean didBlackWin(){
        return this.gameBoard.isWinner(Color.BLACK);
    }

    public boolean didWhiteWin(){
        return this.gameBoard.isWinner(Color.WHITE);
    }
}
