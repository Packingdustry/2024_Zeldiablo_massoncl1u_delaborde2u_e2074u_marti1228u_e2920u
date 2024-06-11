package gameLaby.laby;

public class Bouclier extends Item {
    /**
     * vie du bouclier
     */
    int vie;
    public Bouclier(int abscisse, int ordonne, int v) {
        super(abscisse, ordonne);
        this. vie = v;
    }
}
