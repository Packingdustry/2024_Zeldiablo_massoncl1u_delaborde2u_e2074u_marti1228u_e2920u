package gameLaby.laby;

public class Joueur extends Perso{
    /**
     * permet de verifier si un monstre a deja attaqué le joueur ou non
     */
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

    /**
     * permet de mettre l'attaque en cours a false afin qu'un monstre n'attaque pas a l'infini
     */
    public void reinitialiserAttaque() {
        this.attaqueEnCours = false;
    }
    public boolean getAttaqueEnCours(){
        return attaqueEnCours;
    }

    /**
     * permet de mettre l'attaque en cours a true afin qu'un monstre puisse de nouveau attaquer
     */
    public void setAttaqueEnCours(){
        this.attaqueEnCours=true;
    }
}
