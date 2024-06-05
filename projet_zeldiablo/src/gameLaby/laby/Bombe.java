package gameLaby.laby;

public class Bombe extends Perso{
    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     * @param dgt degat de la bombe
     */
    public Bombe(int dx, int dy, int dgt){
        super(dx,dy,dgt);
    }

    public void mettredgt(Perso p){
        int dgt=this.getVie();
        p.vie-=dgt;
    }

   public void explose(Labyrinthe laby){
        int x = this.getX();
        int y = this.getY();
        for(int i=x-1; i<=x+1; i++){
            for(int j=y-1; j<=j+1; j++){
              Perso p = laby.getPerso(i,j);
              if(p!=null){
                  this.mettredgt(p);
              }
            }
        }
    }
}
