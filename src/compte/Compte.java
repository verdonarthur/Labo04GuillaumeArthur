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

/**
 * Classe permettant de gerer un compte bancaire
 * ainsi que d effectuer des operation avec
 */
public class Compte {

   /** Debit maximum par defaut */
   private final static int DEB_MAX = 1000;

   /** Decouvert maximum par defaut */
   private final static int DEC_MAX = 800;

   /** Solde par defaut */
   private final static double SOLDE_NUL = 0.0;
   
   /** le numero du compte*/
   private int noCompte;

   /** le nom du titulaire*/
   private String nomTitulaire;

   /** la solde actuelle du comtpe*/
   private double solde;

   /** le debit max authorise*/
   private int debMax;

   /** le decouvert max authorise*/
   private int decMax;

   /** le nombre de compte actuellement cree*/
   private static int nbreComptes = 1;

   /**
    * Instancie un compte avec des valeurs par defauts pour le solde
    * et le decouvert/debit max
    * @param nomTitulaire le nom du titulaire du compte
    */
   public Compte(String nomTitulaire) {
      
      this(nomTitulaire, SOLDE_NUL, DEC_MAX, DEB_MAX);
   }

   /**
    * Instancie avec un compte avec des valeurs par defauts pour le
    * decouvert/debit max
    * @param nomTitulaire le nom du titulaire du compte
    * @param dépôtInit la solde initiale du compte
    */
   public Compte(String nomTitulaire, double dépôtInit) {
      
      this(nomTitulaire, dépôtInit, DEC_MAX, DEB_MAX);
   }

   /**
    * Instancie un compte avec une valeur par defaut pour le debit max
    * @param nomTitulaire le nom du titulaire du compte
    * @param dépôtInit la solde initiale du compte
    * @param decMaxAut le decouvert max authorise pour le compte
    */
   public Compte(String nomTitulaire, double dépôtInit, int decMaxAut) {
            
      this(nomTitulaire, dépôtInit, decMaxAut, DEB_MAX);
   }

   /**
    * Instancie un compte avec les paramètre suivants
    * @param nomTitulaire le nom du titulaire du compte
    * @param dépôtInit la solde initiale du compte
    * @param decMaxAut le decouvert max authorise pour le compte
    * @param debMaxAut le debit maximum authorise pour le compte
    */
   public Compte(String nomTitulaire, double dépôtInit, int decMaxAut, int debMaxAut) {
      
      if(nomTitulaire.isEmpty() || dépôtInit < 0 || decMaxAut < 0 || debMaxAut < 0)
         throw new IllegalArgumentException();
      
      noCompte = nbreComptes++;
      this.nomTitulaire = nomTitulaire;
      solde = dépôtInit;
      debMax = debMaxAut;
      decMax = decMaxAut;
   }

   /**
    *
    * @return le numero de comtpe
    */
   public int getNoCompte() {
      return noCompte;
   }

   /**
    *
    * @return le nom du titulaire
    */
   public String getNomTitulaire() {
      return nomTitulaire;
   }

   /**
    *
    * @return la solde du compte
    */
   public double getSolde() {
      return solde;
   }

   /**
    *
    * @return le debit maximum authorise
    */
   public int getDebMax() {
      return debMax;
   }

   /**
    *
    * @return le decouvert maximum authorise
    */
   public int getDecMax() {
      return decMax;
   }

   /**
    * change le debit max authorise
    * @param debMax nouveau debit max
    */
   public void setDebMax(int debMax) {
      this.debMax = debMax;
   }

   /**
    * change le decouvert max authorise
    * @param decMax nouveau decouvert max
    */
   public void setDecMax(int decMax) {
      this.decMax = decMax;
   }

   /**
    *
    * @return si le le compte est à decouvert
    */
   public boolean estADecouvert() {
      return (solde < 0);
   }

   /**
    * methode calculant le montant maximum pouvant etre debite du compte
    * @return le montant max pouvant etre debite
    */
   public double debAutorisé() {
      
      if(solde - debMax > -decMax)
         return debMax;         
      else
         return decMax + solde;
   
   }

   /**
    * methode creditant le compte du montant passe en parametre
    * @param monCrédit montant a crediter
    */
   public void créditer(double monCrédit) {
      if(monCrédit <= 0)
         throw new IllegalArgumentException();
      solde += monCrédit;
   }

   /**
    * debite un compte selon le montant choisi
    * @param monDébit le montant devant etre debiter
    * @return vrai si le compte a bien ete debiter sinon retourne faux
    */
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

   /**
    * Vire un montant d'argent du compte courant a un autre
    * @param compteACrediter le compte devant etre crediter
    * @param montant la valeur du montant à crediter
    * @return vrai si le compte courant a bien pu etre debiter sinon retourne faux
    */
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