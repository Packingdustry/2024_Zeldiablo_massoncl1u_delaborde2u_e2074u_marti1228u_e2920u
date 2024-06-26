package gameLaby.laby;

import gameArkanoid.Balle;
import gameArkanoid.Raquette;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {

    /**
     * permet de dessiner le jeu a tout moment
     * @param jeu jeu a afficher
     * @param canvas canvas dans lequel dessiner l'etat du jeu
     */
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu labyJeu = (LabyJeu) jeu;

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // dessin fond
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // dessin laby
        Labyrinthe laby = labyJeu.getLabyrinthe();
        int tailleY = laby.getLengthY();
        int taille = laby.getLength();
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < tailleY; y++) {
                if (laby.getMur(x, y)) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(
                            (labyJeu.HEIGHT / taille) * x,
                            (labyJeu.WIDTH / tailleY) * y,
                            labyJeu.HEIGHT / taille,
                            labyJeu.WIDTH / tailleY
                    );
                }
            }
        }
        for (Entite e : laby.entites) {
            gc.setFill(e.getCouleur());
            gc.fillOval(
                    (labyJeu.HEIGHT / taille) * e.getX(),
                    (labyJeu.WIDTH / tailleY) * e.getY(),
                    labyJeu.HEIGHT / taille,
                    labyJeu.WIDTH / tailleY
            );
        }
        for (Monstre m : laby.monstres) {
            gc.setFill(Color.WHITE);
            String pv = Integer.toString(m.getVie());
            gc.fillText(pv,
                    (labyJeu.HEIGHT / taille) * m.getX() + (labyJeu.HEIGHT / taille) / 2,
                    (labyJeu.WIDTH / tailleY) * m.getY() + (labyJeu.WIDTH / tailleY) / 2);
            }
        String pv = Integer.toString(laby.pj.getVie());
            gc.fillText(pv,
            (labyJeu.HEIGHT / taille) * laby.pj.getX() + (labyJeu.HEIGHT / taille) / 2,
            (labyJeu.WIDTH / tailleY) * laby.pj.getY() + (labyJeu.WIDTH / tailleY) / 2);
    }
}