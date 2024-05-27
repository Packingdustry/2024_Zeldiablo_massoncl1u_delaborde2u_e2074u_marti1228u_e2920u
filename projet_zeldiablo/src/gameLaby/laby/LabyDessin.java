package gameLaby.laby;

import gameArkanoid.Balle;
import gameArkanoid.Raquette;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {

    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu labyJeu = (LabyJeu) jeu;

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        // dessin fond
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // dessin murs
        gc.setFill(Color.BLACK);
        Labyrinthe laby = labyJeu.getLabyrinthe();
        int tailleY = laby.getLengthY();
        int taille = laby.getLength();
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < tailleY; y++) {
                if (laby.getMur(x, y)) {
                    gc.fillRect(
                         (labyJeu.HEIGHT/taille) * x,
                        (labyJeu.WIDTH/tailleY) * y,
                        labyJeu.HEIGHT/taille,
                        labyJeu.WIDTH/tailleY
                    );
                }
                if (laby.pj.etrePresent(x, y)) {
                    gc.fillOval(
                        (labyJeu.HEIGHT/taille) * x,
                        (labyJeu.WIDTH/tailleY) * y,
                        labyJeu.HEIGHT/taille,
                        labyJeu.WIDTH/tailleY
                    );
                }
            }
        }

    }
}