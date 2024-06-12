package gameLaby.laby;
import gameLaby.laby.Labyrinthe;
import gameLaby.laby.Joueur;

import moteurJeu.MoteurJeu;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LabyrintheTest {

    @Test
    void testGetVie(){
        Joueur principal = new Joueur(2,3,50);
        assertEquals(50,principal.getVie(),"Cela doit renvoyer 50 pvs");
    }

    @Test
    void testInfligerDegats(){
        Joueur principal = new Joueur(2,3,50);
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
    void testMettreDegats() throws IOException {
        Labyrinthe l = new Labyrinthe("labySimple/laby0.txt");
        Bombe b = new Bombe(2,2,20);
        Joueur principal = new Joueur(2,3,50);
        assertEquals(50,principal.getVie(),"Cela doit renvoyer 50 pvs");
        b.mettredgt(principal,l);
        assertEquals(49,principal.getVie(),"le perso a subi 1 degat de la bombe, il a donc 49 pv");
    }

    @Test
    void testPrendreBouclier() throws IOException {
        Labyrinthe l = new Labyrinthe("labySimple/laby0.txt");
        Joueur principal = new Joueur(4, 3, 50);
        Bouclier bouclier = new Bouclier(4, 4, 50);
        l.entites.add(bouclier);
        principal.deplacerPerso(Labyrinthe.BAS, l.murs, l.monstres);
        l.prendreBouclier(principal.getX(),principal.getY());
        assertFalse(principal.avoirBouclier(), "Le joueur ne devrait pas avoir de bouclier");
    }

}