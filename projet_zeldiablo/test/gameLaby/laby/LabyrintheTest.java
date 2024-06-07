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

    @Test
    void testGetVie(){
        Perso principal = new Perso(2,3,50);
        assertEquals(50,principal.getVie(),"Cela doit renvoyer 50 pvs");
    }

    @Test
    void testInfligerDegats(){
        Perso principal = new Perso(2,3,50);
        Monstre m = new Monstre(5,50,3,3);
        principal.vie-=m.infligerDegat();
        assertEquals(45,principal.getVie(),"Cela doit renvoyer 45 pvs car le perso s'est fait frapper");
    }

    @Test
    void testGetMur() throws IOException {
        Labyrinthe l = new Labyrinthe("labySimple/laby0.txt");
        assertEquals(true,l.getMur(0,0),"la case 0,0 doit etre un mur");
    }

    @Test
    void testGetLength() throws IOException {
        Labyrinthe l = new Labyrinthe("labySimple/laby0.txt");
        assertEquals(7,l.getLength(),"le tableau de murs a une longueur de 7");
    }

    @Test
    void testSubirDegatsJoueur(){
        Joueur principal = new Joueur(2,3,50);
        principal.subirDegats(32);
        assertEquals(18,principal.getVie(),"le personnage doit avoir 18 pv car il en a perdu 32");
    }

    @Test
    void testSubirDegatsMonstre(){
        Monstre m = new Monstre(5,50,2,3);
        assertEquals(50,m.getVie(),"le monstre doit avoir 50 pv car il n'en a pas perdu");
        m.subirDegats(10);
        assertEquals(40,m.getVie(),"le monstre doit avoir 40 pv car il en a perdu 10");
    }

    @Test
    void testMettreDegats(){
        Bombe b = new Bombe(2,2,20);
        Perso principal = new Perso(3,3,50);
        assertEquals(50,principal.getVie(),"Cela doit renvoyer 50 pvs");
        b.mettredgt(principal);
        assertEquals(30,principal.getVie(),"le perso a subi 20 degats de la bombe, il a donc 30 pv");
    }
}