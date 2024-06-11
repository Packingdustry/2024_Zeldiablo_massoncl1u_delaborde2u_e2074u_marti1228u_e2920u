package gameLaby.laby;

public abstract class Entite {
    int x, y;

    public Entite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
