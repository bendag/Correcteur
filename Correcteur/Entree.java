
public class Entree {
	
	//attributs pour les cles, les valeurs, et reference au prochain noeud
	private String mot;
	private Entree prochain;
	
	public Entree(String mot, Entree prochain){
		this.mot = mot;
		this.prochain = null;
	}
	
	public String getMot() {
		return this.mot;
	}
	
	public void setMot(String mot) {
		this.mot = mot;
	}
	
	public Entree getProchaineEntree() {
		return this.prochain;
	}
	
	public void setProchaineEntree(Entree prochain) {
		this.prochain = prochain;
	}
		
		public static void main(String[] args) {
			
		}
}