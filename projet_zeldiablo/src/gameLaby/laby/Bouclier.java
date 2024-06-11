package gameLaby.laby;

import javafx.scene.paint.Color;

public class Bouclier extends Item {
    /**
     * vie du bouclier
     */
    int vie;

    /**
     * constructeur
     * @param abscisse abscisse dans le labyrinthe
     * @param ordonne ordonne dans le labyrinthe
     * @param v vie du bouclier
     */
    public Bouclier(int abscisse, int ordonne, int v) {
        super(abscisse, ordonne);
        this. vie = v;
    }

    /**
     * methode qui permet de recuperer la couleur du bouclier
     * @return la couleur du bouclier
     */
    @Override
    public Color getCouleur() {
        return Color.GREEN;
    }
}
