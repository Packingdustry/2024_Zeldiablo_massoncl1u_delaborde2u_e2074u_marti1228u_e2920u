package gameLaby.laby;

import javafx.scene.paint.Color;

public class Monstre extends Perso {
    private int degats;

    /**
     * constructeur
     *
     * @param vie
     * @param dx  position selon x
     * @param dy  position selon y
     */
    public Monstre(int degats, int vie, int dx, int dy) {
        super(dx, dy, vie);
        this.degats = degats;
    }

    public int infligerDegat() {
        if(this.vie>0){
            return this.degats;
        }
        else{
            return 0;
        }
    }

    public Color getCouleur() {
        return Color.RED;
    }

    public void deplacerMonstre(boolean[][] murs) {
        double direction = Math.random();
        int[] suivante = new int[2];
        if (direction < 0.25) {
            suivante = getSuivant(x, y, Labyrinthe.HAUT);
        } else if (direction >= 0.25 && direction < 0.5) {
            suivante = getSuivant(x, y, Labyrinthe.DROITE);
        } else if (direction >= 0.5 && direction < 0.75) {
            suivante = getSuivant(x, y, Labyrinthe.BAS);
        } else {
            suivante = getSuivant(x, y, Labyrinthe.GAUCHE);
        }
        majPos(suivante, murs);
    }
}
