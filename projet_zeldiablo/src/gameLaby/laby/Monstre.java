package gameLaby.laby;

public class Monstre extends Perso {
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

    public int infligerDegat() {
        if(this.vie>0){
            return this.degats;
        }
        else{
            return 0;
        }
    }
}
