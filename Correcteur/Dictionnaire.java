
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Dictionnaire {
	
	public HashMap dictionnaire = new HashMap();
	//Deuxieme structure, un tableau simple pour pouvoir parcourir tous les mots 
	//lorsqu'on recherche les 5 mots les plus proches d'un mot inconnu.

	
	//Lit un fichier et ajoute ses mots 
	public String[] lireDict(String path) throws IOException {
		
		//remettre dictionnaire a null pour commencer
		String[] tMots = lireFichier(path);
			
		int compteur = 1;
			for(int i = 0; i< tMots.length; i++) {
				
				//ajoute chaque mot du fichier
				dictionnaire.inserer(tMots[i]);
				System.out.println("ajout mot" + compteur);
				compteur++;
			}
			
			//deuxieme structure 
			return tMots;
	}
	
	//Lit un fichier et le retourne sous forme de tableau de mots
	public String[] lireFichier(String path) throws IOException {
	
		Tokenizer token = new Tokenizer();
		
		try {
			
			token.lireFichier(path);
			token.createTokens();
		} catch(IOException e) {
			e.getMessage();
		}
		//Tableau de mots
		return token.texteToken;
		
	}

}
