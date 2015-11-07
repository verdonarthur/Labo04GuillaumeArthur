/*
-----------------------------------------------------------------------------------
Laboratoire : 4
Fichier     : Compte.java
Auteur(s)   : Guillaume Serneels et Arthur Verdon
Date        : 03.11.2015

But         : Classe modélisant un compte bancaire

Remarque(s) : Nous avons choisis d'implémenter le débit maximum autorisé ainsi le découvert
              maximum autorisé sous la forme de variable entières étant donné qu'il semble inutile¨
              d'y appliquer une précision au centime près.

Compilateur : jdk1.8.0_60
-----------------------------------------------------------------------------------
*/

package compte;

public class Compte {
   
   private final static int DEB_MAX = 1000;
   private final static int DEC_MAX = 800;
   private final static double SOLDE_NUL = 0.0;
   

   private int noCompte;

   private String nomTitulaire;

   private double solde;

   private int debMax;

   private int decMax;

   private static int nbreComptes = 1;
   
   public Compte(String nomTitulaire) {
      
      this(nomTitulaire, SOLDE_NUL, DEC_MAX, DEB_MAX);
   }

   public Compte(String nomTitulaire, double dépôtInit) {
      
      this(nomTitulaire, dépôtInit, DEC_MAX, DEB_MAX);
   }

   public Compte(String nomTitulaire, double dépôtInit, int decMaxAut) {
            
      this(nomTitulaire, dépôtInit, decMaxAut, DEB_MAX);
   }

   public Compte(String nomTitulaire, double dépôtInit, int decMaxAut, int debMaxAut) {
      
      if(nomTitulaire.isEmpty() || dépôtInit < 0 || decMaxAut < 0 || debMaxAut < 0)
         throw new IllegalArgumentException();
      
      noCompte = nbreComptes++;
      this.nomTitulaire = nomTitulaire;
      solde = dépôtInit;
      debMax = debMaxAut;
      decMax = decMaxAut;
   }


   public int getNoCompte() {
      return noCompte;
   }

   public String getNomTitulaire() {
      return "nomTitulaire";
   }

   public double getSolde() {
      return solde;
   }

   public int getDebMax() {
      return debMax;
   }

   public int getDecMax() {
      return decMax;
   }

   public boolean estADecouvert() {
      return (solde < 0);
   }

   public double debAutorisé() {
      
      if(solde - debMax > -decMax)
         return debMax;         
      else
         return decMax + solde;
   
   }

   public void créditer(double monCrédit) {
      if(monCrédit <= 0)
         throw new IllegalArgumentException();
      solde += monCrédit;
   }

   public boolean débiter(double monDébit) {
      
      if(monDébit <= 0)
         throw new IllegalArgumentException();
      
      if(monDébit > this.debAutorisé()){
         System.out.println("Impossible d'éffectuer le débit, le montant dépasse le débit maximal autorisé. \n");
         return false;
      }
      else
         solde -= monDébit;
         return true;
   }

   public boolean virement(Compte compteACrediter, double montant) {
      
      if(montant <= 0)
         throw new IllegalArgumentException();
      
      if(this.débiter(montant)){
         this.créditer(montant);
         return true;
      }else
         return false; 
   }
}