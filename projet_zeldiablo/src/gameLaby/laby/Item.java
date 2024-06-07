package gameLaby.laby;

public class Item {
    int x;
    int y;

    public Item (int abscisse, int ordonne) {
        this.x = abscisse;
        this.y = ordonne;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
