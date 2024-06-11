package gameLaby.laby;

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

    public Entite[][] entites;
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
        this.monstres = new ArrayList<Monstre>();
        this.bombes = new ArrayList<Bombe>();

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
                        break;
                    case MONSTRE:
                        this.murs[colonne][numeroLigne] = false;
                        this.monstres.add(new Monstre(10, 50, colonne, numeroLigne));
                        break;
                    case BOMBE:
                        this.murs[colonne][numeroLigne] = false;
                        this.bombes.add(new Bombe(colonne, numeroLigne, 10));
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

    /**
     * Renvoie le perso si il se trouve a la position x,y
     * @param x: position x du perso
     * @param y: position y du perso
     * @return
     */
    public Perso getPerso(int x, int y) {
        for (Perso perso : persos) {
            if (perso.getX() == x && perso.getY() == y) {
                return perso;
            }
        }
        return null;
    }

    /**
     * Verifie si il y a un monstre a la position x,y
     * @param x: position x du monstre
     * @param y: position y du monstre
     * @return
     */
    public Perso getMonstre(int x, int y) {
        for (int i = 1; i < persos.size(); i++) {
            if (persos.get(i).getX() == x && persos.get(i).getY() == y) {
                return persos.get(i);
            }
        }
        return null;
    }

    /**
     * Renvoie le nombre de monstres
     * @return int representant le nb de monstres
     */
    public int getNbMonstres() {
        int nbMonstres = 0;
        for (Perso p : persos) {
            if (p instanceof Monstre) {
                nbMonstres++;
            }
        }
        return nbMonstres;
    }

    /**
     * Verifie la presence de monstres et gere les interactions avec le joueur
     */
    public void verifierMonstre() {
        int x = persos.get(0).getX();
        int y = persos.get(0).getY();
        Joueur j = (Joueur) persos.get(0);
        if (getMonstre(x, y) != null && !(getMonstre(x, y) instanceof Bombe) && !j.getAttaqueEnCours()) {
            Monstre m = (Monstre) getMonstre(x, y);
            j.subirDegats(m.infligerDegat());
            j.reinitialiserAttaque();
            j.setAttaqueEnCours();
        } else if (getMonstre(x, y) != null && (getMonstre(x, y) instanceof Bombe)) {
            Bombe b = (Bombe) getMonstre(x, y);
            b.explosion(j, this);
        }
    }

    /**
     * Permet de deplacer les monstres aleatoirement a chaque deplacement
     */
    public void deplacerMonstres() {
        for (int i = 1; i < persos.size(); i++) {
            if (persos.get(i) instanceof Monstre) {
                Monstre m = (Monstre) persos.get(i);
                double direction = Math.random();
                int x = m.getX();
                int y = m.getY();
                int[] suivante = new int[2];
                if (direction < 0.25) {
                    suivante = getSuivant(x, y, Labyrinthe.HAUT);
                } else if (direction >= 0.25 && direction < 0.5) {
                    suivante = getSuivant(x, y, Labyrinthe.DROITE);
                } else if (direction >= 0.5 && direction < 0.75) {
                    suivante = getSuivant(x, y, Labyrinthe.BAS);
                } else {
                    suivante = getSuivant(x, y, Labyrinthe.GAUCHE);
                }
                majPos(i, suivante);
            }
        }
    }

    /**
     * Met a jour la position des persos
     * @param i
     * @param suivante
     */
    public void majPos(int i, int[] suivante) {
        if (!this.murs[suivante[0]][suivante[1]]) {
            // on met a jour personnage
            this.persos.get(i).x = suivante[0];
            this.persos.get(i).y = suivante[1];
        }
    }
}
