package gameLaby.laby;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Joueur extends Perso{
    /**
     * permet de verifier si un monstre a deja attaqué le joueur ou non
     */
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

    /**
     * permet de mettre l'attaque en cours a false afin qu'un monstre n'attaque pas a l'infini
     */
    public void reinitialiserAttaque() {
        this.attaqueEnCours = false;
    }
    public boolean getAttaqueEnCours(){
        return attaqueEnCours;
    }

    /**
     * permet de mettre l'attaque en cours a true afin qu'un monstre puisse de nouveau attaquer
     */
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
        System.out.println("x : " + x + "\ny : " + y);
        this.majPos(suivante, murs);
        System.out.println("x : " + x + "\ny : " + y);
        reinitialiserAttaque();
        for (Monstre m : monstres) {
            m.deplacerMonstre(murs);
        }
    }

    public void verifierMonstre(Labyrinthe laby, ArrayList<Monstre> monstres, ArrayList<Bombe> bombes) {
        if (getMonstre(x, y, monstres) != null && !getAttaqueEnCours()) {
            Monstre m = getMonstre(x, y, monstres);
            subirDegats(m.infligerDegat());
            reinitialiserAttaque();
            setAttaqueEnCours();
        } else if (getBombe(x, y, bombes) != null && !getAttaqueEnCours()) {
            getBombe(x, y, bombes).explosion(this, laby);
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

    public Bombe getBombe(int x, int y, ArrayList<Bombe> bombes) {
        for (Bombe bombe : bombes) {
            if (bombe.getX() == x && bombe.getY() == y) {
                return bombe;
            }
        }
        return null;
    }
}
