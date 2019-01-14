import java.io.*;

public class Tokenizer {
	public String texte;
	public String[] texteToken;
	
	public Tokenizer(){	
	}
	
	// prend un path en paramètre, va chercher le contenu du texte
	// donné par le path et initie le texte
	public void lireFichier(String path) throws IOException{
		
		@SuppressWarnings("resource")
		
		// le fileReader prend un path relatif qui est le root du
		// projet créé
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line = "";
		String text = "";
		int compteur = 1;
		while((line = reader.readLine()) != null){
		
			System.out.println("entre dans le while token" + compteur);
			compteur++;
			line.toLowerCase();
			text += line + " ";
		}
		
		this.texte = text;
		
	}
	
	// prend le texte et créé le tokenize
	public void createTokens(){
		
		texte = texte.replaceAll("[^A-z0-9àâçéèêëîïôûùüÿñÀÂÇÉÈÊËÎÏÔÛÙÜæ]", " ");
		this.texteToken = this.texte.split(" +");
	}
}
