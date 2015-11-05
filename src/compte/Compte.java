package compte;

public class Compte {

   private int noCompte;

   private String nomTitulaire;

   private double solde;

   private int debMax;

   private int decMax;

   private static int nbreComptes = 1;

   public Compte(String nomTitulaire) {
      
      if(nomTitulaire.isEmpty())
         throw new IllegalArgumentException();  
      
      noCompte = nbreComptes++;
      nomTitulaire = this.nomTitulaire;
      solde = 0.0;
      debMax = 1000;
      decMax = 800;
   }

   public Compte(String nomTitulaire, double d�p�tInit) {
      
      if(nomTitulaire.isEmpty() || d�p�tInit < 0)
         throw new IllegalArgumentException();
      
      noCompte = nbreComptes++;
      nomTitulaire = this.nomTitulaire;
      solde = d�p�tInit;
      debMax = 1000;
      decMax = 800;
   }

   public Compte(String nomTitulaire, double d�p�tInit, int decMaxAut) {
      
      if(nomTitulaire.isEmpty() || d�p�tInit < 0 || decMaxAut < 0)
         throw new IllegalArgumentException();
      
      noCompte = nbreComptes++;
      nomTitulaire = this.nomTitulaire;
      solde = d�p�tInit;
      debMax = 1000;
      decMax = decMaxAut;
   }

   public Compte(String nomTitulaire, double d�p�tInit, int decMaxAut, int debMaxAut) {
      
      if(nomTitulaire.isEmpty() || d�p�tInit < 0 || decMaxAut < 0 || debMaxAut < 0)
         throw new IllegalArgumentException();
      
      noCompte = nbreComptes++;
      nomTitulaire = this.nomTitulaire;
      solde = d�p�tInit;
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

   public double debAutoris�() {
      /*
        
       
      if(estADecouvert())
         return max(decMax + solde, debMax + solde - ;
      else
         
      */
      return 0.0;
      //return Math.min(Math.abs(a), b)
   }

   public void cr�diter() {
      // TODO implement here
   }

   public void d�biter() {
      // TODO implement here
   }

   public void virement(Compte compteACrediter, double montant) {
      // TODO implement here
   }
}