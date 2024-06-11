package gameLaby.laby;

import javafx.scene.paint.Color;

public class Bouclier extends Item {
    /**
     * vie du bouclier
     */
    int vie;
    public Bouclier(int abscisse, int ordonne, int v) {
        super(abscisse, ordonne);
        this. vie = v;
    }

    /**
     * methode qui fait subir des degats au bouclier
     * @param dgt
     */
    public void subirDegats(int dgt){
        this.vie-=dgt;
    }

    @Override
    public Color getCouleur() {
        return Color.GREEN;
    }
}
