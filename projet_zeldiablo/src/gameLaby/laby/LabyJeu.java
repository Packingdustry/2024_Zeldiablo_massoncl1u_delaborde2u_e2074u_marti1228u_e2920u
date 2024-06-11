package gameLaby.laby;

import javafx.application.Platform;
import moteurJeu.Clavier;
import moteurJeu.Jeu;

import javax.swing.plaf.LabelUI;
import java.io.IOException;

public class LabyJeu implements Jeu {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 1200;
    public static final String FICHIER = "labySimple/laby2.txt";

    private final Labyrinthe labyrinthe;

    private boolean finMonstre = false;

    public LabyJeu() throws IOException {
        labyrinthe = new Labyrinthe(FICHIER);
    }

    /**
     * met a jour le labyrinthe avec les nouvelles positions du joueur, des bombes, des monstres
     * @param secondes temps ecoule depuis la derniere mise a jour
     * @param clavier objet contenant l'état du clavier'
     */
    @Override
    public void update(double secondes, Clavier clavier) {
        labyrinthe.actualiser(clavier);
        if (etreFini()) {
            System.out.println("Game Over");
            Platform.exit();
        }else if (finMonstre) {
            System.out.println("bien joué");
            Platform.exit();
        }
    }

    @Override
    public void init() {}

    /**
     * verifier si le labyrinthe est fini
     * @return booleen indiquant si le labyrinthe est fini
     */
    @Override
    public boolean etreFini() {
        boolean fin = false;
        int nbMorts = 0;
        if(labyrinthe.pj.getVie() <= 0) {
            fin = true;
        }
        else{
            for(Monstre m : labyrinthe.monstres){
                if(m.getVie()<=0){
                    labyrinthe.monstres.remove(m);
                    labyrinthe.entites.remove(m);
                }
            }
        }
        if(nbMorts==labyrinthe.getNbMonstres()){
            finMonstre = true;
        }
        return fin;
    }

    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }
}
