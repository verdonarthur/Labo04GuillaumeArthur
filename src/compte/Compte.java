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
      
      if(nomTitulaire.isEmpty() || dépôtInit < 0 || decMaxAut < 0 || debMaxAut < 0) //Vérification des paramètre
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
      return nomTitulaire;
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

   private double debAutorisé() { 
      if(solde - debMax >= -decMax)
         return debMax;         
      else
         return decMax + solde;  
   }

   public void créditer(double monCrédit) {
      this.verfifArg(monCrédit);      
      solde += monCrédit;
   }

   public double débiter(double monDébit) {
      
      this.verfifArg(monDébit);     
      if(monDébit > this.debAutorisé()){
         solde -= this.debAutorisé();
         return this.debAutorisé();
      }
      else
         solde -= monDébit;
         return monDébit;         
   }

   public void virement(Compte compteACrediter, double montant) {
      
      this.verfifArg(montant);      
      montant = this.débiter(montant);
      compteACrediter.créditer(montant);
   }
   
   public void affiche(){
      System.out.println("Compte no : " + noCompte + "\nNom du titulaire : " + nomTitulaire);
      System.out.println("Découvert maximum autorisé : " + decMax + "\nDébit maximum autorisé : " + debMax);
      System.out.println("Solde du compte : " + solde + "\n");
      if(this.estADecouvert())
         System.out.println("Le compte est à découvert \n\n");
   }
   
   private void verfifArg(double montant){
      if(montant <= 0)
        throw new IllegalArgumentException();
   }
   
}