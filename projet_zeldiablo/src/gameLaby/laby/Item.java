package gameLaby.laby;

public abstract class Item extends Entite {

    public Item (int abscisse, int ordonne) {
        super(abscisse, ordonne);

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
