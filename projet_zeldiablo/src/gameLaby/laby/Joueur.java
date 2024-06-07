package gameLaby.laby;

public class Joueur extends Perso{
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
    public void reinitialiserAttaque() {
        this.attaqueEnCours = false;
    }
    public boolean getAttaqueEnCours(){
        return attaqueEnCours;
    }
    public void setAttaqueEnCours(){
        this.attaqueEnCours=true;
    }
}
