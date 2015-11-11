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

/**
 * @author Guillaume Serneels
 * @auhtor Arthur Verdon
 * @version 1.0
 */
 
package compte;

/**
 * Classe pérmettant de gérer un compte bancaire
 * ainsi que d'éffectuer des operations avec celui-ci
 */
public class Compte {

   /** Débit maximum par défaut */
   private final static int DEB_MAX = 1000;

   /** Découvert maximum par défaut */
   private final static int DEC_MAX = 800;

   /** Solde par défaut */
   private final static double SOLDE_NUL = 0.0;
   
   /** le numéro du compte*/
   private int noCompte;

   /** le nom du titulaire*/
   private String nomTitulaire;

   /** la solde actuelle du comtpe*/
   private double solde;

   /** le débit max authorisé*/
   private int debMax;

   /** le découvert max authorisé*/
   private int decMax;

   /** le nombre de compte actuellement créé*/
   private static int nbreComptes = 1;

   /**
    * Instancie un compte avec des valeurs par defauts pour le solde
    * et le découvert/débit max
    * @param nomTitulaire le nom du titulaire du compte
    */
   public Compte(String nomTitulaire) {
      
      this(nomTitulaire, SOLDE_NUL, DEC_MAX, DEB_MAX);
   }

   /**
    * Instancie avec un compte avec des valeurs par défauts pour le
    * découvert/débit max
    * @param nomTitulaire le nom du titulaire du compte
    * @param dépôtInit la solde initiale du compte
    */
   public Compte(String nomTitulaire, double dépôtInit) {
      
      this(nomTitulaire, dépôtInit, DEC_MAX, DEB_MAX);
   }

   /**
    * Instancie un compte avec une valeur par défaut pour le débit max
    * @param nomTitulaire le nom du titulaire du compte
    * @param dépôtInit la solde initiale du compte
    * @param decMaxAut le découvert max authorisé pour le compte
    */
   public Compte(String nomTitulaire, double dépôtInit, int decMaxAut) {
            
      this(nomTitulaire, dépôtInit, decMaxAut, DEB_MAX);
   }

   /**
    * Instancie un compte avec les paramètres suivants
    * @param nomTitulaire le nom du titulaire du compte
    * @param dépôtInit la solde initiale du compte
    * @param decMaxAut le découvert max authorisé pour le compte
    * @param debMaxAut le débit maximum authorisé pour le compte
    */
   public Compte(String nomTitulaire, double dépôtInit, int decMaxAut, int debMaxAut) {
      
      if(nomTitulaire.isEmpty() || dépôtInit < 0 || decMaxAut < 0 || debMaxAut < 0) //Vérification des paramètre
         throw new IllegalArgumentException();
      
      noCompte = nbreComptes++;
      this.nomTitulaire = nomTitulaire;
      solde = dépôtInit;
      debMax = debMaxAut;
      decMax = decMaxAut;
   }

   /**
    *
    * @return le numéro de comtpe
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
    * @return le débit maximum authorisé
    */
   public int getDebMax() {
      return debMax;
   }

   /**
    *
    * @return le découvert maximum authorisé
    */
   public int getDecMax() {
      return decMax;
   }

   /**
    * change le débit max authorisé
    * @param debMax nouveau débit max
    */
   public void setDebMax(int debMax) {
      this.debMax = debMax;
   }

   /**
    * change le découvert max authorisé
    * @param decMax nouveau découvert max
    */
   public void setDecMax(int decMax) {
      this.decMax = decMax;
   }

   /**
    * Permet de déterminer si un compte est à découvert
    * @return si le compte est à découvert
    */
   public boolean estADecouvert() {
      return (solde < 0);
   }

   /**
    * méthode calculant le montant maximum pouvant être débité du compte
    * @return le montant max pouvant être debité
    */
   private double debAutorisé() {      
      if(solde - debMax > -decMax)
         return debMax;         
      else
         return decMax + solde;  
   }

   /**
    * methode créditant le compte du montant passe en paramètre
    * @param monCrédit montant à créditer
    * @throws IllegalArgumentException
    */
   public void créditer(double monCrédit) throws IllegalArgumentException {
      this.verfifArg(monCrédit);      
      solde += monCrédit;
   }

   /**
    * Débite un compte selon le montant choisi
    * @param monDébit le montant devant être débité 
    * @return le montant qui a effectivement pu être débité
    * @throws IllegalArgumentException

    */
   public double débiter(double monDébit) throws IllegalArgumentException {
          
      this.verfifArg(monDébit);     
      if(monDébit > this.debAutorisé()){
         solde -= this.debAutorisé();
         return this.debAutorisé();
      }
      else
         solde -= monDébit;
         return monDébit;         
   }


   /**
    * Vire un montant d'argent du compte courant à un autre compte
    * @param compteACrediter le compte devant être crédité 
    * @param montant la valeur du montant à crediter
    * @throws IllegalArgumentException

    */
   public void virement(Compte compteACrediter, double montant) throws IllegalArgumentException{
      this.verfifArg(montant);      
      montant = this.débiter(montant);
      compteACrediter.créditer(montant);
   }
   
   /**
    * Permet d'afficher un compte
    */
   public void affiche(){
      System.out.println(this.toString()); 
   }
   
   /**
    * fonction retournant le contenu de la classe en string
	* @return le contenu de la classe
	*/
   public String toString(){
      String s = "Compte no : " + noCompte + "\nNom du titulaire : " + nomTitulaire + "\nDécouvert maximum autorisé : "
                + decMax + "\nDébit maximum autorisé : " + debMax + "\nSolde du compte : " + solde + "\n";
      if(this.estADecouvert())
         s += "\nLe compte est à découvert \n\n";
      return s;    
   }
   
   /**
    * Vérifie si un montant est supérieur ou égal à zéro
    * @param montant le montant à vérifier
    * @throws IllegalArgumentException
    */
   private void verfifArg(double montant) throws IllegalArgumentException{
      if(montant <= 0)
        throw new IllegalArgumentException();
   }
   
}