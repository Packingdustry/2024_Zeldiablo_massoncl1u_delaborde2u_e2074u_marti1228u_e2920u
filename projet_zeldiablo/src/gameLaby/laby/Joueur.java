package gameLaby.laby;

public class Joueur extends Perso{
    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Joueur(int dx, int dy, int pv) {
        super(dx, dy, pv);
    }

    public void subirDegats(int pv){
        this.vie-=pv;
    }
}
