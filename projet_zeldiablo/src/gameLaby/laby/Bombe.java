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
        if (this.getX() - 1 == labyrinthe.persos.get(0).getX() && this.getY() == labyrinthe.persos.get(0).getY()
                || this.getX() + 1 == labyrinthe.persos.get(0).getX() && this.getY() == labyrinthe.persos.get(0).getY()
                || this.getX() == labyrinthe.persos.get(0).getX() && this.getY() + 1 == labyrinthe.persos.get(0).getY()
                || this.getX() == labyrinthe.persos.get(0).getX() && this.getY() - 1 == labyrinthe.persos.get(0).getY()
                || this.getX() == labyrinthe.persos.get(0).getX() && this.getY() == labyrinthe.persos.get(0).getY()
                || this.getX() == labyrinthe.persos.get(0).getX()-1 && this.getY() - 1 == labyrinthe.persos.get(0).getY()
                || this.getX() == labyrinthe.persos.get(0).getX()+1 && this.getY() - 1 == labyrinthe.persos.get(0).getY()
                || this.getX() == labyrinthe.persos.get(0).getX()-1 && this.getY() + 1 == labyrinthe.persos.get(0).getY()
                || this.getX() == labyrinthe.persos.get(0).getX()+1 && this.getY() + 1 == labyrinthe.persos.get(0).getY()
        ) {
            p.subirDegats(dgt);
        }
        for(int i=1;i<labyrinthe.persos.size();i++){
            if(labyrinthe.persos.get(i) instanceof Bombe
                    &&labyrinthe.persos.get(i).getX()==this.getX()
                    &&labyrinthe.persos.get(i).getY()==this.getY()
            ){
                labyrinthe.persos.remove(i);
            }
        }
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
