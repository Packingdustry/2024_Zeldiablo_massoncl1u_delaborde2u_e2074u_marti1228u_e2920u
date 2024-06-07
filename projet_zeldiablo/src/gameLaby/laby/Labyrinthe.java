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
    public ArrayList<Perso> persos;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on augmente colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

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
        this.persos = new ArrayList<Perso>();

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
                        this.persos.add(0, new Joueur(colonne, numeroLigne, 100));
                        break;
                    case MONSTRE:
                        this.murs[colonne][numeroLigne] = false;
                        this.persos.add(new Monstre(10, 50, colonne, numeroLigne));
                        break;
                    case BOMBE:
                        this.murs[colonne][numeroLigne] = false;
                        this.persos.add(new Bombe(colonne, numeroLigne, 10));
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


    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action) {
        // case courante
        int[] courante = {this.persos.get(0).x, this.persos.get(0).y};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);

        // si c'est pas un mur, on effectue le deplacement
        majPos(0, suivante);
        deplacerMonstres();
    }


    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return false;
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

    public void verifierMonstre() {
        int x = persos.get(0).getX();
        int y = persos.get(0).getY();
        if (getMonstre(x, y) != null && !(getMonstre(x, y) instanceof Bombe)) {
            Joueur j = (Joueur) persos.get(0);
            Monstre m = (Monstre) getMonstre(x, y);
            j.subirDegats(m.infligerDegat());
        } else if (getMonstre(x, y) != null && (getMonstre(x, y) instanceof Bombe)) {
            Joueur j = (Joueur) persos.get(0);
            Bombe b = (Bombe) getMonstre(x, y);
            b.explosion(j,this);
        }
    }

    public Perso getPerso(int x, int y) {
        for (Perso perso : persos) {
            if (perso.getX() == x && perso.getY() == y) {
                return perso;
            }
        }
        return null;
    }

    public Perso getMonstre(int x, int y) {
        for (int i = 1; i < persos.size(); i++) {
            if (persos.get(i).getX() == x && persos.get(i).getY() == y) {
                return persos.get(i);
            }
        }
        return null;
    }

    public int getNbMonstres() {
        int nbMonstres = 0;
        for (Perso p : persos) {
            if (p instanceof Monstre) {
                nbMonstres++;
            }
        }
        return nbMonstres;
    }

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

    public void majPos(int i, int[] suivante) {
        if (!this.murs[suivante[0]][suivante[1]]) {
            // on met a jour personnage
            this.persos.get(i).x = suivante[0];
            this.persos.get(i).y = suivante[1];
        }
    }
}
