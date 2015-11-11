
/*
-----------------------------------------------------------------------------------
Laboratoire : 4
Fichier     : Labo_4.java
Auteur(s)   : Guillaume Serneels et Arthur Verdon
Date        : 03.11.2015

But         : Programme de test de la classe Compte

Remarque(s) : 

Compilateur : jdk1.8.0_60
-----------------------------------------------------------------------------------
*/

import compte.*;

/**
 * Classe executant le programme principale
 * @author Guillaume Serneels
 * @author Arthur Verdon
 * @version 1.0
 */
public class Labo_4 {

   /**
    * Programme principale
    *
    * @param args args du main
    */
   public static void main(String[] args) {

      // Programme de test demandé
      Compte c1 = new Compte("J.DUPONT", 1000.0, 1000);
      Compte c2 = new Compte("C.DURAND");
      c1.affiche();
      c2.affiche();

      System.out.println("Débite c1 de 500,- \n");
      c1.débiter(500);
      c1.affiche();

      System.out.println("Crédite c1 de 500,- \n");
      c1.créditer(500);
      c1.affiche();

      for (int i = 0; i < 3; i++) {
         System.out.println("Débite c1 de 5000,- \n");

         c1.débiter(5000);
         c1.affiche();
      }
      System.out.println("Crédite c1 de 500,- \n");
      c1.créditer(500);
      c1.affiche();

      System.out.println("Avant Virement \n");
      c1.affiche();
      c2.affiche();

      System.out.println("Virement de c1 à c2 , montant: 500,- \n");
      c1.virement(c2, 500);

      c1.affiche();
      c2.affiche();


      //Lancement de nos propres tests critiques
      testCompte();

   }

   /**
    * Effectue des tests critiques sur la classe compte
    */
   private static void testCompte() {

      System.out.println("TESTS CRITIQUES SUR COMPTE\n");

      Compte c1 = new Compte("Mr. Test");
      Compte c2 = new Compte("Mr. Test Virement");

      System.out.println("Test decouvert négatif");
      try {
         c1.setDebMax(-100);
      } catch (Exception e) {
         System.out.println(e.toString());
      }
      c1.affiche();

      System.out.println("Test créditer négativement");
      try {
         c1.créditer(-1000);
      } catch (Exception e) {
         System.out.println(e.toString());
      }
      c1.affiche();

      System.out.println("Test créditer avec une grande valeur");
      c1.créditer(9_999_999_999_999.0);

      try {
         c1.affiche();
      } catch (Exception e) {
         System.out.println(e.toString());
      }

      System.out.println("Test débiter négativement");
      try {
         c1.débiter(-1000);
      } catch (Exception e) {
         System.out.println(e.toString());
      }
      c1.affiche();

      System.out.println("Test virement négativement");
      try {
         c1.virement(c2, -1000);
      } catch (Exception e) {
         System.out.println(e.toString());
      }
      c1.affiche();
      c2.affiche();

      System.out.println("Test gros virement");
      try {
         c1.virement(c2, 9_999_999_999_999.0);
      } catch (Exception e) {
         System.out.println(e.toString());
      }
      c1.affiche();
      c2.affiche();
   }
}



