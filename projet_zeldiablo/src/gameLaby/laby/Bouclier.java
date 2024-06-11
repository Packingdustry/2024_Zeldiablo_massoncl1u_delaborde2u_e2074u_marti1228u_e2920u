package gameLaby.laby;

import javafx.scene.paint.Color;

public class Bouclier extends Item {
    int vie;
    public Bouclier(int abscisse, int ordonne, int v) {
        super(abscisse, ordonne);
        this. vie = v;
    }

    @Override
    public Color getCouleur() {
        return Color.GREEN;
    }
}
