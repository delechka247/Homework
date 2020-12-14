package ru.itis.inf.game;

public interface CheckersMover{
    public void moveFromTo(int oldX, int oldY, int newX, int newY);
    public boolean isMoveAble(int oldX, int oldY, int newX, int newY, MoveStatus moveStatus);
    public boolean isMoveDouble(int oldX, int oldY, int newX, int newY);
    public boolean isMoveSimple(int oldX, int oldY, int newX, int newY);
    public boolean isChekerWhite(int x, int y);
    public boolean canDoDouble(int oldX, int oldY, int newX, int newY);
    public boolean isWinner(Color color);
}
