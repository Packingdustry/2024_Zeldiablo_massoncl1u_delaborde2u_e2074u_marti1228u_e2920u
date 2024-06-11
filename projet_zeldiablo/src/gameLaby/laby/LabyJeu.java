package gameLaby.laby;

import javafx.application.Platform;
import moteurJeu.Clavier;
import moteurJeu.Jeu;

import javax.swing.plaf.LabelUI;
import java.io.IOException;

public class LabyJeu implements Jeu {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    public static final String FICHIER = "labySimple/laby2.txt";

    private final Labyrinthe labyrinthe;

    private boolean finMonstre = false;

    public LabyJeu() throws IOException {
        labyrinthe = new Labyrinthe(FICHIER);
    }

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

    @Override
    public boolean etreFini() {
        boolean fin = false;
        int nbMorts = 0;
        if(labyrinthe.pj.getVie() <= 0) {
            fin = true;
        }
        else{
            for(int i=0;i<labyrinthe.getNbMonstres();i++){
                if(labyrinthe.monstres.get(i).getVie()<=0){
                    labyrinthe.monstres.remove(i);
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
