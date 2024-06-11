package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Perso extends Entite{

    /**
     * points de vie du personnage
     */
    int vie;

    final static int degats = 1;

    /**
     * constructeur
     * @param dx position selon x
     * @param dy position selon y
     * @param pv vie du personnage
     */
    public Perso(int dx, int dy, int pv) {
       super(dx,dy);
        this.vie = pv;
    }

    /**
     * permet de savoir si le personnage est en x,y
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {
        return (this.x == dx && this.y == dy);
    }


    public int getVie(){
        return this.vie;
    }

    public void subirDegats(int dgs){
        if(this.vie>0){
            this.vie-=dgs;
        }
    }
}
