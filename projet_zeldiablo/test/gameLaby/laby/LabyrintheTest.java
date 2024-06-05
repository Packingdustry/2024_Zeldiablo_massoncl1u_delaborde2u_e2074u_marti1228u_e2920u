package gameLaby.laby;
import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Perso;

import moteurJeu.MoteurJeu;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LabyrintheTest {
    @Test
    void testGetPerso() throws IOException {
        Labyrinthe l = new Labyrinthe("labySimple/laby0.txt");
        Perso principal = new Perso(2,3,50);
        l.persos.add(principal);
        assertEquals(null,l.getPerso(3,4),"La methode doit renvoyer null car le perso nest pas en 3,4");
        assertEquals(principal,l.getPerso(2,3),"La methode doit renvoyer le personnage principal car il est bien en 2,3");
    }

    @Test
    void testGetMonstre() throws IOException {
        Labyrinthe l = new Labyrinthe("labySimple/laby0.txt");
        Monstre m = new Monstre(5,50,3,3);
        l.persos.add(m);
        assertEquals(null,l.getMonstre(3,4),"La methode doit renvoyer null car le monstre nest pas en 3,4");
        assertEquals(m,l.getPerso(3,3),"La methode doit renvoyer le monstre car il est bien en 3,3");
    }
}