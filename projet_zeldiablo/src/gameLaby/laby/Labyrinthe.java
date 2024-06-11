package gameLaby.laby;

import moteurJeu.Clavier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MONSTRE = 'M';
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';
    public static final char BOMBE = 'B';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    /**
     * Monstres présents dans le labyrinthe
     */
    public ArrayList<Monstre> monstres;

    public ArrayList<Entite> entites;
    public Joueur pj;
    public Bouclier bouclier;
    public ArrayList<Bombe> bombes;

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.entites = new ArrayList<>();
        this.monstres = new ArrayList<>();
        this.bombes = new ArrayList<>();

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;
                    case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.pj = new Joueur(colonne, numeroLigne, 100);
                        this.entites.add(pj);
                        break;
                    case MONSTRE:
                        this.murs[colonne][numeroLigne] = false;
                        Monstre m = new Monstre(10, 50, colonne, numeroLigne);
                        this.monstres.add(m);
                        this.entites.add(m);
                        break;
                    case BOMBE:
                        this.murs[colonne][numeroLigne] = false;
                        Bombe b = new Bombe(colonne, numeroLigne, 10);
                        this.bombes.add(b);
                        this.entites.add(b);
                        break;
                    default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }

    public int getNbMonstres() {
        return monstres.size();
    }

    public void actualiser(Clavier clavier) {
        if (clavier.droite) {
            pj.deplacerPerso(Labyrinthe.DROITE, murs, monstres);
            clavier.droite =false;
        }else if (clavier.gauche) {
            pj.deplacerPerso(Labyrinthe.GAUCHE, murs, monstres);
            clavier.gauche =false;
        }else if (clavier.bas) {
            pj.deplacerPerso(Labyrinthe.BAS, murs, monstres);
            clavier.bas =false;
        }else if (clavier.haut) {
            pj.deplacerPerso(Labyrinthe.HAUT, murs, monstres);
            clavier.haut =false;
        }
        else if(clavier.space){
            for(Monstre m : monstres){
                if(m.getX()-1==pj.getX() && m.getY()==pj.getY()
                        || m.getX()+1==pj.getX() && m.getY()==pj.getY()
                        || m.getX()==pj.getX() && m.getY()-1==pj.getY()
                        || m.getX()==pj.getX() && m.getY()+1==pj.getY()
                ){
                    m.subirDegats(Perso.degats);
                }
            }
        }
        pj.verifierMonstre(this, monstres, bombes);
    }
}
