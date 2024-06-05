package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import javax.swing.plaf.LabelUI;
import java.io.IOException;

public class LabyJeu implements Jeu {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    public static final String FICHIER = "labySimple/laby2.txt";

    private final Labyrinthe labyrinthe;

    public LabyJeu() throws IOException {
        labyrinthe = new Labyrinthe(FICHIER);
    }

    @Override
    public void update(double secondes, Clavier clavier) {
        if (clavier.droite) {
            labyrinthe.deplacerPerso(Labyrinthe.DROITE);
        }else if (clavier.gauche) {
            labyrinthe.deplacerPerso(Labyrinthe.GAUCHE);
        }else if (clavier.bas) {
            labyrinthe.deplacerPerso(Labyrinthe.BAS);
        }else if (clavier.haut) {
            labyrinthe.deplacerPerso(Labyrinthe.HAUT);
        }
        labyrinthe.verifierMonstre();
    }

    @Override
    public void init() {}

    @Override
    public boolean etreFini() {
        return false;
    }

    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }
}
