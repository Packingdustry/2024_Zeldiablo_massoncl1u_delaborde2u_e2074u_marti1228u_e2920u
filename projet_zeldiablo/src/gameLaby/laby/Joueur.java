package gameLaby.laby;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Joueur extends Perso{
    /**
     * permet de verifier si un monstre a deja attaqué le joueur ou non
     */
    private boolean attaqueEnCours;
    private Color color;
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

    public Color getCouleur() {
        if(this.bouclier!=null){
            this.color=Color.ORANGE;
        }
        else{
            this.color=Color.BLUE;
        }
        return this.color;
    }



    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action, boolean[][] murs, ArrayList<Monstre> monstres) {
        // case courante
        int[] courante = {x, y};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        this.majPos(suivante, murs);
        reinitialiserAttaque();
        for (Monstre m : monstres) {
            m.deplacerMonstre(murs);
        }
    }

    /**
     * methode qui permet de verifier si un monstre est a la même postion que le joueur si il y en a un le joueur
     * prend des degats
     * @param laby labyrinnthe
     * @param monstres liste de monstres
     * @param bombes liste de bombes
     */
    public void verifierMonstre(Labyrinthe laby, ArrayList<Monstre> monstres, ArrayList<Bombe> bombes) {
        if (getMonstre(x, y, monstres) != null && !getAttaqueEnCours()) {
            Monstre m = getMonstre(x, y, monstres);
            if(this.bouclier!=null){
                this.bouclier.subirDegats(m.infligerDegat());
                if(this.bouclier.vie<=0){
                    this.bouclier=null;
                }
            }
            else{
                subirDegats(m.infligerDegat());
            }
            reinitialiserAttaque();
            setAttaqueEnCours();
        } else if (getBombe(x, y, bombes) != null && !getAttaqueEnCours()) {
            getBombe(x, y, bombes).explosion(this, laby);
        }
    }

    /**
     * methode qui permet de savoir où se situe un monstre
     * @param x abscisse
     * @param y ordonnee
     * @param monstres liste de monstres
     * @return le monstre s'il est a la postion x, y sinon null
     */
    public Monstre getMonstre(int x, int y, ArrayList<Monstre> monstres) {
        for (Monstre m : monstres) {
            if (m.getX() == x && m.getY() == y) {
                return m;
            }
        }
        return null;
    }

    /**
     * methode qui permet de savoir où se situe une bombe
     * @param x abscisse
     * @param y ordonnee
     * @param bombes list de bombes
     * @return la bombe si elle est la position x, y ou null
     */
    public Bombe getBombe(int x, int y, ArrayList<Bombe> bombes) {
        for (Bombe bombe : bombes) {
            if (bombe.getX() == x && bombe.getY() == y) {
                return bombe;
            }
        }
        return null;
    }

    public Bouclier getBouclier(){
        return this.bouclier;
    }
    public boolean avoirBouclier(){
        if(this.bouclier!=null){
            return true;
        }
        else{
            return false;
        }
    }
    }
