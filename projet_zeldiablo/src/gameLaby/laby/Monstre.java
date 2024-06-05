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
        super(vie, dx, dy);
        this.degats = degats;
    }

    public int infligerDegat() {
        return this.degats;
    }
}
