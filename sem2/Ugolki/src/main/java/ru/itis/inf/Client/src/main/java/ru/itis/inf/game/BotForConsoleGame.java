package ru.itis.inf.game;

import java.util.ArrayList;
import java.util.HashMap;

public class BotForConsoleGame {
    OneChecker[] botsChekers = new OneChecker[12];

    public BotForConsoleGame() {
    }

    public void moveByBot(OneChecker[][] board) {
    }

    public void printForAll(OneChecker[][] board) {
        this.setBotsChekers(board);
        for (int i = 0; i < 12; i++) {
            System.out.println("Лучший ход для шашки (" + botsChekers[i].getX() + ";" + botsChekers[i].getY() + "): " +
                    getLongestMoveForOneCheker(board, botsChekers[i].getX(), botsChekers[i].getY()).toString());
        }
    }

    public void setBotsChekers (OneChecker[][] board) {
        int num = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getColor().equals(Color.WHITE)) {
                    this.botsChekers[num] = new OneChecker(Color.WHITE, i, j);
                    num++;
                }
            }
        }
    }

    public boolean canBotDoDoubleDown(OneChecker[][] board, int x, int y) {
        if (x + 2 >= 0 && x + 2 < 8) {
            //System.out.println("bot inside 3.1");
            if (!board[x + 1][y].getColor().equals(Color.NOCHECKER)) {
                //System.out.println("bot inside 3.2");
                if (board[x + 2][y].getColor().equals(Color.NOCHECKER)) {
                    //System.out.println("bot inside 3.3");
                    return true;
                }
            }
        }
        //System.out.println("bot outside");
        return false;
    }

    public boolean canBotDoDoubleRight(OneChecker[][] board, int x, int y) {
        if (y + 2 >= 0 && y + 2 < 8) {
            //System.out.println("bot inside 4.1");
            if (!board[x][y + 1].getColor().equals(Color.NOCHECKER)) {
                //System.out.println("bot inside 4.2");
                if (board[x][y + 2].getColor().equals(Color.NOCHECKER)) {
                    //System.out.println("bot inside 4.3");
                    return true;
                }
            }
        }
        //System.out.println("bot outside");
        return false;
    }

    public StringBuffer getLongestMoveForOneCheker(OneChecker[][] board, int x, int y) {
        StringBuffer[] moves = new StringBuffer[16];
        for (int i = 0; i < 16; i++) {
            moves[i] = new StringBuffer("0");
        }
        for (int i = 0; i < 8; i++) {
            //x
            if (this.canBotDoDoubleDown(board,x,y)) {
                moves[i].append("X");
                //xx
                if (i < 4){
                    if (this.canBotDoDoubleDown(board, x + 2, y)) {
                        moves[i].append("X");
                        //xxx
                        if (i < 2) {
                            if (this.canBotDoDoubleDown(board, x + 4, y)) {
                                moves[i].append("X");
                                //xxxy
                                if (i == 1) {
                                    if (this.canBotDoDoubleRight(board, x + 6, y)) {
                                        moves[i].append("Y");

                                    }
                                }
                            }
                        }
                        //xxy
                        if (i > 1 ) {
                            if (this.canBotDoDoubleRight(board, x + 4, y)) {
                                moves[i].append("Y");
                                //xxyx
                                if (i == 2 ) {
                                    if (this.canBotDoDoubleDown(board, x + 4, y + 2)) {
                                        moves[i].append("X");
                                    }
                                }
                                //xxyy
                                if (i == 3 ) {
                                    if (this.canBotDoDoubleRight(board, x + 4, y + 2)) {
                                        moves[i].append("Y");
                                    }
                                }
                            }
                        }
                    }
                }
                //xy
                if (i > 3 ){
                    if (this.canBotDoDoubleRight(board, x + 2, y)) {
                        moves[i].append("Y");
                        //xyx
                        if (i < 6 ) {
                            if (this.canBotDoDoubleDown(board, x + 2, y + 2)) {
                                moves[i].append("X");
                                //xyxx
                                if (i == 4 ) {
                                    if (this.canBotDoDoubleDown(board, x + 4, y + 2)) {
                                        moves[i].append("X");
                                    }
                                }
                                //xyxy
                                if (i == 5) {
                                    if (this.canBotDoDoubleRight(board, x + 4, y + 2)) {
                                        moves[i].append("Y");
                                    }
                                }
                            }
                        }
                        //xyy
                        if (i > 5 ) {
                            if (this.canBotDoDoubleRight(board, x + 2, y + 2)) {
                                moves[i].append("Y");
                                //xyyx
                                if (i == 6 ) {
                                    if (this.canBotDoDoubleDown(board, x + 2, y + 4)) {
                                        moves[i].append("X");
                                    }
                                }
                                //xyyy
                                if (i == 7 ) {
                                    if (this.canBotDoDoubleRight(board, x + 2, y + 4)) {
                                        moves[i].append("Y");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 8; i < 16; i++) {
            //y
            if (this.canBotDoDoubleRight(board,x,y)) {
                moves[i].append("Y");
                //yx
                if (i < 12){
                    if (this.canBotDoDoubleDown(board, x, y + 2)) {
                        moves[i].append("X");
                        //yxx
                        if (i < 10) {
                            if (this.canBotDoDoubleDown(board, x + 2, y + 2)) {
                                moves[i].append("X");
                                //yxxx
                                if (i == 8 ) {
                                    if (this.canBotDoDoubleDown(board, x + 4, y + 2)) {
                                        moves[i].append("X");
                                    }
                                }
                                //yxxy
                                if (i == 9 ) {
                                    if (this.canBotDoDoubleRight(board, x + 4, y + 2)) {
                                        moves[i].append("Y");
                                    }
                                }
                            }
                        }
                        //yxy
                        if (i > 9) {
                            if (this.canBotDoDoubleRight(board, x + 2, y + 2)) {
                                moves[i].append("Y");
                                //yxyx
                                if (i == 10) {
                                    if (this.canBotDoDoubleDown(board, x + 2, y + 4)) {
                                        moves[i].append("X");
                                    }
                                }
                                //yxyy
                                if (i == 11) {
                                    if (this.canBotDoDoubleRight(board, x + 2, y + 4)) {
                                        moves[i].append("Y");
                                    }
                                }
                            }
                        }
                    }
                }
                //yy
                if (i > 11){
                    if (this.canBotDoDoubleRight(board, x, y + 2)) {
                        moves[i].append("Y");
                        //yyx
                        if (i < 14) {
                            if (this.canBotDoDoubleDown(board, x, y + 4)) {
                                moves[i].append("X");
                                //yyxx
                                if (i == 12 ) {
                                    if (this.canBotDoDoubleDown(board, x + 2, y + 4)) {
                                        moves[i].append("X");
                                    }
                                }
                                //yyxy
                                if (i == 13 ) {
                                    if (this.canBotDoDoubleRight(board, x + 2, y + 4)) {
                                        moves[i].append("Y");
                                    }
                                }
                            }
                        }
                        //yyy
                        if (i > 13) {
                            if (this.canBotDoDoubleRight(board, x, y + 4)) {
                                moves[i].append("Y");
                                //yyxx
                                if (i == 14 ) {
                                    if (this.canBotDoDoubleDown(board, x + 2, y + 4)) {
                                        moves[i].append("X");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        /*
        System.out.println(x + " " + y);
        for (StringBuffer str : moves) {
            System.out.println(str);
        }
         */
        return this.findMax(moves);
    }

    public StringBuffer findMax(StringBuffer[] array) {
        int imax = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i].length() > array[imax].length())
                imax = i;
        }
        return array[imax];
    }
}
