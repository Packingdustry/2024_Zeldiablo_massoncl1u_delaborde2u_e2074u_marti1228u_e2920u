package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Perso {

    /**
     * position du personnage
     */
    int x, y;
    /**
     * points de vie du personnage
     */
    int vie;

    final static int degats = 1;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy, int pv) {
        this.x = dx;
        this.y = dy;
        this.vie = pv;
    }

    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    // ############################################
    // GETTER
    // ############################################

    /**
     * @return position x du personnage
     */
    public int getX() {
        // getter
        return this.x;
    }

    /**
     * @return position y du personnage
     */
    public int getY() {
        //getter
        return this.y;
    }

    /**
     *
     * @return vie du personnage
     */
    public int getVie(){
        return this.vie;
    }

    /**
     * permet a un perso de subir des degats si ses pvs sont superieurs a 0
     * @param dgs degats infliges au personnage
     */
    public void subirDegats(int dgs){
        if(this.vie>0){
            this.vie-=dgs;
        }
    }
}
