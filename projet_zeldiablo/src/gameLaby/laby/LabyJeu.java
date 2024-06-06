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
    private boolean fin = false;

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
        else if(clavier.space){
            for(int i=1;i<labyrinthe.persos.size();i++){
                if(labyrinthe.persos.get(i).getX()-1==labyrinthe.persos.get(0).getX() && labyrinthe.persos.get(i).getY()==labyrinthe.persos.get(0).getY()
                || labyrinthe.persos.get(i).getX()+1==labyrinthe.persos.get(0).getX() && labyrinthe.persos.get(i).getY()==labyrinthe.persos.get(0).getY()
                ){
                   labyrinthe.persos.get(i).subirDegats(Perso.degats);
                }
            }
        }
        labyrinthe.verifierMonstre();
        etreFini();
        if (etreFini()&&!fin) {
            fin = true;
            System.out.println("Game Over");
            Platform.exit();
        }
    }

    @Override
    public void init() {}

    @Override
    public boolean etreFini() {
        boolean fin = false;
        int nbMorts=0;
        if(labyrinthe.persos.get(0).getVie() <= 0) {
            fin = true;
        }
        else{
            for(int i=1;i<labyrinthe.persos.size();i++){
                if(labyrinthe.persos.get(i).getVie()<=0){
                    nbMorts+=1;
                }
            }
        }
        if(nbMorts==labyrinthe.persos.size()-1){
            fin = true;
        }
        return fin;
    }

    public Labyrinthe getLabyrinthe() {
        return labyrinthe;
    }
}
