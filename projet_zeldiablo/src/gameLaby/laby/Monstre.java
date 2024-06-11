package gameLaby.laby;

public class Monstre extends Perso {
    /**
     * indique les degats que met un monstre
     */
    private int degats;

    /**
     * constructeur
     *
     * @param vie
     * @param dx  position selon x
     * @param dy  position selon y
     */
    public Monstre(int degats, int vie, int dx, int dy) {
        super(dx, dy, vie);
        this.degats = degats;
    }

    /**
     * permet d'infliger des degats si la cible a des pv superieurs a 0
     * @return degats infliges
     */
    public int infligerDegat() {
        if(this.vie>0){
            return this.degats;
        }
        else{
            return 0;
        }
    }
}
