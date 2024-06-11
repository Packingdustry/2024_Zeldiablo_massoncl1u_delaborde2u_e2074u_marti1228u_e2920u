package gameLaby.laby;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Joueur extends Perso{
    private boolean attaqueEnCours;
    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Joueur(int dx, int dy, int pv) {
        super(dx, dy, pv);
        this.attaqueEnCours = false;
    }
    public void reinitialiserAttaque() {
        this.attaqueEnCours = false;
    }
    public boolean getAttaqueEnCours(){
        return attaqueEnCours;
    }
    public void setAttaqueEnCours(){
        this.attaqueEnCours=true;
    }

    public Color getCouleur() {
        return Color.BLUE;
    }



    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action, boolean[][] murs, ArrayList<Monstre> monstres) {
        // case courante
        int[] courante = {x, y};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        majPos(suivante, murs);
        reinitialiserAttaque();
        for (Monstre m : monstres) {
            m.deplacerMonstre(murs);
        }
    }

    public void verifierMonstre(Labyrinthe laby, ArrayList<Monstre> monstres, Bombe bombe) {
        if (getMonstre(x, y, monstres) != null && !getAttaqueEnCours()){
            Monstre m = getMonstre(x, y, monstres);
            subirDegats(m.infligerDegat());
            reinitialiserAttaque();
            setAttaqueEnCours();
        } else if (getBombe(x, y, bombe) && !getAttaqueEnCours()) {
            bombe.explosion(this, laby);
        }
    }

    public Monstre getMonstre(int x, int y, ArrayList<Monstre> monstres) {
        for (Monstre m : monstres) {
            if (m.getX() == x && m.getY() == y) {
                return m;
            }
        }
        return null;
    }

    public boolean getBombe(int x, int y, Bombe bombe) {
        if (bombe.getX() == x && bombe.getY() == y) {
            return true;
        }
        return false;
    }
}
