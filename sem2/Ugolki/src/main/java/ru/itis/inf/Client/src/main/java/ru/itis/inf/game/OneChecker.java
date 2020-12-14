package ru.itis.inf.game;

public class OneChecker {
    private Color color;
    private int x;
    private int y;

    public OneChecker(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        if (this.getColor().equals(Color.BLACK))
            return "B";
        else if (this.getColor().equals(Color.WHITE))
            return "W";
        else
            return ".";
    }
}
