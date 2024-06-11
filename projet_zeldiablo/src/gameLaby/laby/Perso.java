package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public abstract class Perso extends Entite{

    /**
     * points de vie du personnage
     */
    int vie;

    final static int degats = 1;
    Bouclier bouclier;

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

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case Labyrinthe.HAUT:
                // on monte une ligne
                y--;
                break;
            case Labyrinthe.BAS:
                // on descend une ligne
                y++;
                break;
            case Labyrinthe.DROITE:
                // on augmente colonne
                x++;
                break;
            case Labyrinthe.GAUCHE:
                // on augmente colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    public void majPos(int[] suivante, boolean[][] murs) {
        if (!murs[suivante[0]][suivante[1]]) {
            // on met a jour personnage
            x = suivante[0];
            y = suivante[1];
        }
    }
}
