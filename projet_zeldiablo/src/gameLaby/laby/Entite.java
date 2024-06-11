package gameLaby.laby;

import javafx.scene.paint.Color;

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

    public abstract Color getCouleur();
}
