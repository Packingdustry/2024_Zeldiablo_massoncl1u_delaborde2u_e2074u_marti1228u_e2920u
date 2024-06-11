package gameLaby.laby;

import javafx.scene.paint.Color;

/**
 * classe abstraire Entite
 */
public abstract class Entite {

    /**
     * coordonée d'une entité
     */
    int x, y;

    /**
     * constructeur d'une entité
     * @param x abscisse
     * @param y ordonnée
     */
    public Entite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter
     * @return l'abscisse
     */
    public int getX() {
        return this.x;
    }

    /**
     * getter
     * @return l'ordonnée
     */
    public int getY() {
        return this.y;
    }

    /**
     * getter
     * @return la couleur de l'entitée
     */
    public abstract Color getCouleur();
}
