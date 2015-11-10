
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

public class Labo_4 {

  public static void main(String[] args) {
     
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
     
     for(int i = 0; i < 3; i++ ){
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
     
   }

}