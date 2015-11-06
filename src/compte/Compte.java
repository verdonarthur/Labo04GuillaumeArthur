package compte;

public class Compte {
   
   private final int DEB_MAX = 1000;
   private final int DEC_MAX = 800;
   private final double SOLDE_NUL = 0.0;
   

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
      solde = SOLDE_NUL;
      debMax = DEB_MAX;
      decMax = DEC_MAX;
   }

   public Compte(String nomTitulaire, double dépôtInit) {
      
      if(nomTitulaire.isEmpty() || dépôtInit < 0)
         throw new IllegalArgumentException();
      
      noCompte = nbreComptes++;
      nomTitulaire = this.nomTitulaire;
      solde = dépôtInit;
      debMax = DEB_MAX;
      decMax = DEC_MAX;
   }

   public Compte(String nomTitulaire, double dépôtInit, int decMaxAut) {
      
      if(nomTitulaire.isEmpty() || dépôtInit < 0 || decMaxAut < 0)
         throw new IllegalArgumentException();
      
      noCompte = nbreComptes++;
      nomTitulaire = this.nomTitulaire;
      solde = dépôtInit;
      debMax = DEB_MAX;
      decMax = decMaxAut;
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
      /*
        
       
      if(estADecouvert())
         return max(decMax + solde, debMax + solde - ;
      else
         
      */
      return 0.0;
      //return Math.min(Math.abs(a), b)
   }

   public void créditer(double montCrédit) {
      if(montCrédit <= 0){}
   }

   public void débiter() {
      // TODO implement here
   }

   public void virement(Compte compteACrediter, double montant) {
      // TODO implement here
   }
}