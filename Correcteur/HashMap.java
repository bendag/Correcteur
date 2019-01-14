
public class HashMap {
	
	private static final int longueur = 300000;
	public Entree[] listeMot = new Entree[longueur];
	
	//On obtient la valeur d'une cle pour chacun des mots et 
	//on fait un modulo pour le placer dans le tableau
	public void inserer(String mot) {

		int hash = hashCode(mot);
		int positionTableau = hash%longueur;
		Entree bucket = listeMot[positionTableau];
		
		//Si aucune collision
		if(bucket == null ) {
			listeMot[positionTableau] = new Entree(mot, null);
			System.out.println(listeMot[positionTableau].getMot());
		} else {
			System.out.println("entre dans le else");
			//ajouter le nouveau mot a la suite de l'autre
			while(bucket.getProchaineEntree() != null) {
				if(mot.equals(bucket.getMot())) { 
					//Si meme mot, ne rien faire
					return;
				} bucket = bucket.getProchaineEntree();	
			}
			//Sinon on a un nouveau mot et on l'ajoute
			bucket.setProchaineEntree(new Entree(mot, null));
			System.out.println(listeMot[positionTableau].getMot());
		}
	}
	
	public int hashCode(String mot){
		
		int hash = 1;
		
		for(int i = 0; i < mot.length(); i++){
			int h = Character.getNumericValue(mot.charAt(i));
			
			hash += h*655561 ^ Integer.hashCode(h);
			
		}
		
		/*
		int hash = 0;
		
		for(int i = 0 ; i < mot.length(); i++) {
			int h = Character.getNumericValue(mot.charAt(i));
			System.out.println(h);
			hash += Math.pow(h*31, mot.length()+1-i);
		}*/
		return hash;
		
	}
	
	//Fonction chercher retourne vrai si le mot est dans la structure de données
	public boolean chercher(String mot) {
		int hash = hashCode(mot);
		int positionTableau = hash%longueur;
		
		Entree bucket = listeMot[positionTableau];
		
		//Si aucun element a la position donnée alors le mot est inconnus
		if(bucket == null) {
			return false;
		} else {//On compare les cles trouver a la bonne paire cle-mot
			while(bucket != null) {
				if(bucket.getMot().compareToIgnoreCase(mot) == 0) {
					return true;
				}
				bucket = bucket.getProchaineEntree();
			}
			//si aucun ne correspond
			return false;
		}
		
	}
	
	
	public static void main(String[] args) {

		
	}
}
