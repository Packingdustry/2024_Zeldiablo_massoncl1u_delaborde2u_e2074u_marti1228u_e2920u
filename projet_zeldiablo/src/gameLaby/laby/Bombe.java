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

    public void explose(){
        int x = this.getX();
        int y = this.getY();
        for(int i=x;i<=x+3;i++){
            for(int j=y;j<=j+3;j++){
              Perso p = getPerso(i,j);
              if(p!=null){
                  this.mettredgt(p);
              }
            }
        }
    }
}
