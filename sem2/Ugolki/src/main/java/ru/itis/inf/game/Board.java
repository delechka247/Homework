package ru.itis.inf.game;

public class Board implements CheckersMover {
    private static final int SIZE = 8;
    private OneChecker[][] board;


    public Board() {
        OneChecker[][] newBoard = new OneChecker[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Color color;
                if (i < 3 && j < 4)
                    color = Color.WHITE;
                else if (i > 4 && j > 3)
                    color = Color.BLACK;
                else
                    color = Color.NOCHECKER;
                newBoard[i][j] = new OneChecker(color, i, j);
            }
        }
        this.board = newBoard;
    }

    public void setBoard(OneChecker[][] board) {
        this.board = board;
    }

    public OneChecker[][] getBoard() {
        return this.board;
    }

    public void printBoard() {
        System.out.print("  ");
        for (int j = 0; j < SIZE; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j].toString() + " ");
            }
            System.out.println();
        }
    }

    @Override
    public void moveFromTo(int oldX, int oldY, int newX, int newY) {
        this.board[newX][newY].setColor(this.board[oldX][oldY].getColor());
        this.board[oldX][oldY].setColor(Color.NOCHECKER);
    }

    @Override
    public boolean isMoveAble(int oldX, int oldY, int newX, int newY, MoveStatus moveStatus) {
        if (this.board[newX][newY].getColor().equals(Color.NOCHECKER) && (this.isMoveDouble(oldX, oldY, newX, newY) || this.isMoveSimple(oldX, oldY, newX, newY))) {
            if (moveStatus.equals(MoveStatus.DOUBLE_PLUS) && this.isMoveSimple(oldX, oldY, newX, newY))
                return false;
            return true;
        }
        return false;
    }

    @Override
    public boolean isMoveDouble(int oldX, int oldY, int newX, int newY) {
        if (oldX == newX && Math.abs(oldY - newY) == 2 && !this.board[newX][Math.min(oldY, newY) + 1].getColor().equals(Color.NOCHECKER)
                || oldY == newY && Math.abs(oldX - newX) == 2 && !this.board[Math.min(oldX, newX) + 1][newY].getColor().equals(Color.NOCHECKER))
            return true;
        return false;
    }

    @Override
    public boolean isMoveSimple(int oldX, int oldY, int newX, int newY) {
        if (this.board[newX][newY].getColor().equals(Color.NOCHECKER) &&
                (Math.abs(newX - oldX) == 1 && newY == oldY || Math.abs(newY - oldY) == 1 && newX == oldX))
            return true;
        return false;
    }

    @Override
    public boolean isChekerWhite(int x, int y) {
        return this.board[x][y].getColor().equals(Color.WHITE);
    }

    @Override
    public boolean canDoDouble(int oldX, int oldY, int newX, int newY) {
       /* if ((newX - 2 >= 0 && newX - 2 < 8 && !this.board[newX - 1][newY].getColor().equals(Color.NOCHECKER) && this.board[newX - 2][newY].getColor().equals(Color.NOCHECKER) && newX - 2 != oldX
                || newY - 2 >= 0 && newY - 2 < 8 && !this.board[newX][newY - 1].getColor().equals(Color.NOCHECKER) && this.board[newX][newY - 2].getColor().equals(Color.NOCHECKER) && newY - 2 != oldY
                || newX + 2 >= 0 && newX + 2 < 8 && !this.board[newX + 1][newY].getColor().equals(Color.NOCHECKER) && this.board[newX + 2][newY].getColor().equals(Color.NOCHECKER) && newX + 2 != oldX
                || newY + 2 >= 0 && newY + 2 < 8 && !this.board[newX][newY + 1].getColor().equals(Color.NOCHECKER) && this.board[newX][newY + 2].getColor().equals(Color.NOCHECKER)) && newY + 2 != oldY
        ) {
            return true;
        }
        return false;
        */

        if (newX - 2 >= 0 && newX - 2 < 8) {
            System.out.println("inside 1.1");
            if (!this.board[newX - 1][newY].getColor().equals(Color.NOCHECKER)) {
                System.out.println("inside 1.2");
                if (this.board[newX - 2][newY].getColor().equals(Color.NOCHECKER)) {
                    System.out.println("inside 1.3");
                    if (newX - 2 != oldX) {
                        System.out.println("inside 1.4");
                        return true;
                    }
                }
            }
        }
        if (newY - 2 >= 0 && newY - 2 < 8) {
            System.out.println("inside 2.1");
            if (!this.board[newX][newY - 1].getColor().equals(Color.NOCHECKER)) {
                System.out.println("inside 2.2");
                if (this.board[newX][newY - 2].getColor().equals(Color.NOCHECKER)) {
                    System.out.println("inside 2.3");
                    if (newY - 2 != oldY) {
                        System.out.println("inside 2.4");
                        return true;
                    }
                }
            }
        }
        if (newX + 2 >= 0 && newX + 2 < 8) {
            System.out.println("inside 3.1");
            if (!this.board[newX + 1][newY].getColor().equals(Color.NOCHECKER)) {
                System.out.println("inside 3.2");
                if (this.board[newX + 2][newY].getColor().equals(Color.NOCHECKER)) {
                    System.out.println("inside 3.3");
                    if (newX + 2 != oldX) {
                        System.out.println("inside 3.4");
                        return true;
                    }
                }
            }
        }
        if (newY + 2 >= 0 && newY + 2 < 8) {
            System.out.println("inside 4.1");
            if (!this.board[newX][newY + 1].getColor().equals(Color.NOCHECKER)) {
                System.out.println("inside 4.2");
                if (this.board[newX][newY + 2].getColor().equals(Color.NOCHECKER)){
                    System.out.println("inside 4.3");
                    if (newY + 2 != oldY) {
                        System.out.println("inside 4.4");
                        return true;
                    }
                }
            }
        }
        System.out.println("outside");
        return false;
    }

    @Override
    public boolean isWinner(Color color) {
        boolean win = true;
        if (color.equals(Color.BLACK)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!this.board[i][j].getColor().equals(Color.BLACK)) {
                        win = false;
                        break;
                    }
                }
            }
        } else if (color.equals(Color.WHITE)) {
            for (int i = 5; i < 8; i++) {
                for (int j = 4; j < 8; j++) {
                    if (!this.board[i][j].getColor().equals(Color.WHITE)) {
                        win = false;
                        break;
                    }
                }
            }
        }
        return win;
    }
}
