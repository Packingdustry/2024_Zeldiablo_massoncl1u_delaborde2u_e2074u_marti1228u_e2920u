package gameLaby.laby;
import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;

public class Bombe extends Entite {

    int degats;
    /**
     * constructeur
     * @param dx position selon x
     * @param dy position selon y
     * @param dgt degat de la bombe
     */
    public Bombe(int dx, int dy, int dgt){
        super(dx,dy);
        this.degats = dgt;
    }

    /**
     * permet a une bombe de mettre des degats autour de la case ou elle explose
     * @param p personnage du labyrinthe
     * @param labyrinthe labyrinthe dans lequel la bombe va exploser
     */
    public void mettredgt(Perso p,Labyrinthe labyrinthe) {
        int dgt = 1;
        if (this.getX() - 1 == labyrinthe.pj.getX() && this.getY() == labyrinthe.pj.getY()
                || this.getX() + 1 == labyrinthe.pj.getX() && this.getY() == labyrinthe.pj.getY()
                || this.getX() == labyrinthe.pj.getX() && this.getY() + 1 == labyrinthe.pj.getY()
                || this.getX() == labyrinthe.pj.getX() && this.getY() - 1 == labyrinthe.pj.getY()
                || this.getX() == labyrinthe.pj.getX() && this.getY() == labyrinthe.pj.getY()
                || this.getX() == labyrinthe.pj.getX()-1 && this.getY() - 1 == labyrinthe.pj.getY()
                || this.getX() == labyrinthe.pj.getX()+1 && this.getY() - 1 == labyrinthe.pj.getY()
                || this.getX() == labyrinthe.pj.getX()-1 && this.getY() + 1 == labyrinthe.pj.getY()
                || this.getX() == labyrinthe.pj.getX()+1 && this.getY() + 1 == labyrinthe.pj.getY()
        ) {
            if(p.bouclier!=null){
                p.bouclier.subirDegats(dgt);
                if(p.bouclier.vie<=0){
                    p.bouclier=null;
                }
            }
            else{
                p.subirDegats(dgt);
            }
        }
        labyrinthe.bombes.remove(this);
        labyrinthe.entites.remove(this);
    }

    /**
     * permet a une bombe de exploser au bout d'un certain temps
     * @param p personnage du labyrinthe
     * @param labyrinthe labyrinthe ou la bombe explose
     */
    public void explosion(Perso p, Labyrinthe labyrinthe){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                mettredgt(p, labyrinthe);
            }
        };
        timer.schedule(task, 500);
    }

    public Color getCouleur() {
        return Color.VIOLET;
    }
}
