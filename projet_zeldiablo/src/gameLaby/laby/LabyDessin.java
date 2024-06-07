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
                if (laby.persos.get(0).etrePresent(x, y)) {
                    gc.setFill(Color.BLUE);
                    gc.fillOval(
                            (labyJeu.HEIGHT / taille) * x,
                            (labyJeu.WIDTH / tailleY) * y,
                            labyJeu.HEIGHT / taille,
                            labyJeu.WIDTH / tailleY
                    );
                    gc.setFill(Color.WHITE);
                    String pv = Integer.toString(laby.getPerso(x,y).getVie());
                    gc.fillText(pv,
                            (labyJeu.HEIGHT / taille) * x + (labyJeu.HEIGHT / taille) / 2,
                            (labyJeu.WIDTH / tailleY) * y + (labyJeu.WIDTH / tailleY) / 2 );
                }
                if (laby.getPerso(x, y) != null && laby.getPerso(x, y) instanceof Monstre) {
                    gc.setFill(Color.RED);
                    gc.fillOval(
                            (labyJeu.HEIGHT / taille) * x,
                            (labyJeu.WIDTH / tailleY) * y,
                            labyJeu.HEIGHT / taille,
                            labyJeu.WIDTH / tailleY
                    );
                    gc.setFill(Color.WHITE);
                    String pv = Integer.toString(laby.getPerso(x,y).getVie());
                    gc.fillText(pv,
                            (labyJeu.HEIGHT / taille) * x + (labyJeu.HEIGHT / taille) / 2,
                            (labyJeu.WIDTH / tailleY) * y + (labyJeu.WIDTH / tailleY) / 2 );
                }
                if (laby.getPerso(x, y) != null && laby.getPerso(x, y) instanceof Monstre) {
                    gc.setFill(Color.RED);
                    gc.fillOval(
                            (labyJeu.HEIGHT / taille) * x,
                            (labyJeu.WIDTH / tailleY) * y,
                            labyJeu.HEIGHT / taille,
                            labyJeu.WIDTH / tailleY
                    );
                    gc.setFill(Color.BLACK);
                    String pv = Integer.toString(laby.getPerso(x,y).getVie());
                    gc.fillText(pv,
                            (labyJeu.HEIGHT / taille) * x + (labyJeu.HEIGHT / taille) / 2,
                            (labyJeu.WIDTH / tailleY) * y + (labyJeu.WIDTH / tailleY) / 2 );
                }
                if (laby.getPerso(x, y) != null && laby.getPerso(x, y) instanceof Bombe) {
                    gc.setFill(Color.PURPLE);
                    gc.fillOval(
                            (labyJeu.HEIGHT / taille) * x,
                            (labyJeu.WIDTH / tailleY) * y,
                            labyJeu.HEIGHT / taille,
                            labyJeu.WIDTH / tailleY
                    );
                }
            }
        }
    }
}