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
            p.subirDegats(dgt);
        }
        for(int i=0;i<labyrinthe.monstres.size();i++){
            if(labyrinthe.monstres.get(i).getX()==this.getX()
                    &&labyrinthe.monstres.get(i).getY()==this.getY()
            ){
                labyrinthe.monstres.remove(i);
            }
        }
    }

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
