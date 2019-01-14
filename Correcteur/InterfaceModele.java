import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

public class InterfaceModele {
	
	private Interface correcteur;
	//Create a file chooser
	final JFileChooser fc = new JFileChooser();
	final JOptionPane op = new JOptionPane();
	
	public Dictionnaire d = new Dictionnaire();
	
	public String mot;
	public int start, end, offset;
	public JPopupMenu pmenu;
	
	public String[] listeMot2;
	
	public InterfaceModele(Interface correcteur) {
		this.correcteur = correcteur;
	}
	
	//Bouton Fichier
	public void fichier() {
		correcteur.menu.show(correcteur.fichier, correcteur.fichier.getWidth()/2, correcteur.fichier.getHeight()/2);
	}
	
	//Sous-bouton de fichier : Ouvrir
	public void ouvrir() throws IOException {
		fc.setCurrentDirectory(new File("."));
		fc.setDialogTitle("Ouvrir un fichier");
		int value = fc.showOpenDialog(correcteur.ouvrir);
		
		if(value == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String path = file.getAbsolutePath();
			
			Tokenizer token = new Tokenizer();
	  		token.lireFichier(path);
			correcteur.textObjet.text.setText(token.texte);
		}	
	}

	//Sous-bouton de fichier : Enregistrer
	public void enregistrer() throws IOException {
		int reponse = op.showConfirmDialog(correcteur.menu, "Voulez-vous enregistrer le contenu dans un nouveau fichier?"
				+"\n(Yes : nouveau fichier, No : fichier existant)", "Enregistrer", op.YES_NO_OPTION);
		//1er cas : Si on veut enregistrer par-dessus un fichier qui existe deja
		 if(reponse == op.NO_OPTION) {
			fc.setCurrentDirectory(new File("."));
			fc.setDialogTitle("Enregistrer un fichier");
			int value = fc.showOpenDialog(correcteur.ouvrir);
				
			if(value == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				String path = file.getAbsolutePath();
				BufferedWriter writer = new BufferedWriter(new FileWriter(path));
				PrintWriter out = new PrintWriter(writer);
				out.println(correcteur.textObjet.text.getText());
				out.close();
			}
		//2e cas : si nous voulons enregistrer dans un nouveau fichier
		} else if(reponse == op.YES_OPTION) {
			try {
				String fichier = op.showInputDialog(correcteur.menu, "Entrer un nom pour le nouveau fichier : ", 
						"Enregistrer");
				BufferedWriter writer = new BufferedWriter(new FileWriter(fichier));
				PrintWriter out = new PrintWriter(fichier);
				out.println(correcteur.textObjet.text.getText());
				out.close();
			} catch(NullPointerException e) {
				e.getStackTrace();
			}
		}
	}
	
	//Bouton Dictionnaire : lire un fichier dictionnaire
	public void dictionnaire() throws IOException {
		fc.setCurrentDirectory(new File("."));
		fc.setDialogTitle("Choisir un Dictionnaire");
		int returnVal = fc.showOpenDialog(correcteur.dict);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String path = file.getAbsolutePath();
			listeMot2 = d.lireDict(path);
		}				
	}
	
	//Bouton Verification : surligne les mots inconnus en rouge
	public void verification() {
		
		//Mettre le Texte dans un tableau 
		Tokenizer textComplet = new Tokenizer();
		textComplet.texte = correcteur.textObjet.text.getText();
		textComplet.createTokens();
		
		//Verifier si chaque mot est dans le dictionnaire, sinon le surligner en rouge
		for(int i = 0 ; i < textComplet.texteToken.length; i++) {
			if(!d.dictionnaire.chercher(textComplet.texteToken[i])) {
				correcteur.textObjet.highlight(correcteur.textObjet.text, textComplet.texteToken[i]);
			}
		}	
	}
	
	public void clicMot(MouseEvent e) {
		
		//1) selectionne le mot sur lequel l'utilisateur a clique
		try {
			offset = correcteur.textObjet.text.viewToModel(e.getPoint());
			start = Utilities.getWordStart(correcteur.textObjet.text,offset);
			end = Utilities.getWordEnd(correcteur.textObjet.text, offset);
			mot = correcteur.textObjet.text.getDocument().getText(start, end-start);
		} catch(BadLocationException e1) {
			e1.getStackTrace();
		}

		//2) Trouver les 5 mots les plus proches
		//MotPlusProche[] mot5 = new MotPlusProche[5];
		String[] mot5 = new String[5];
		
		try {
			//initialise des valeurs
			for(int k = 0; k < 5; k ++) {
			mot5[k] = listeMot2[k];
			}
		
			for(int i = 0; i < listeMot2.length; i++) {
				for(int j = 0; j < mot5.length; j++) {
					if(distance(listeMot2[i], mot) < distance(mot5[j], mot)) {
						mot5[j] = listeMot2[i];
						break;
					}
				}
			}	
		} catch(NullPointerException e2) {
			op.showMessageDialog(correcteur.textObjet.text, "Vous devez charger un dictionnaire!", "Erreur", op.ERROR_MESSAGE);
		} catch(ArrayIndexOutOfBoundsException e2) {
			op.showMessageDialog(correcteur.textObjet.text, "Il est recommendé d'utiliser un dictionnaire de plus de 5 mots.", "Avertisseur", op.WARNING_MESSAGE);
		}
		
		//3)popupmenu qui propose les 5 mots les plus proche et qui remplace
		//le mot le par celui selectionne
		pmenu = new JPopupMenu(mot);

		for(int i = 0; i < mot5.length; i++) {
			pmenu.add(new MotsMenu(mot5[i]));
		}
		//Si le  mot n'est pas un espace vide, le menu s'affiche
		if(mot.trim().length() > 0) {
			pmenu.show(e.getComponent(), e.getX(), e.getY());	
		}
	}
	
	private class MotsMenu extends JMenuItem implements ActionListener {
		
		public MotsMenu(String mot) {
			super(mot);
			addActionListener(this);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//Remplace le mot clique par le mot choisit parmi la liste d'items du popupmenu
			correcteur.textObjet.text.replaceRange(e.getActionCommand(), start, end);	
		}
	}
	
	public static int distance(String s1, String s2){
	     int edits[][]=new int[s1.length()+1][s2.length()+1];
	     for(int i=0;i<=s1.length();i++)
	         edits[i][0]=i;
	     for(int j=1;j<=s2.length();j++)
	         edits[0][j]=j;
	     for(int i=1;i<=s1.length();i++){
	         for(int j=1;j<=s2.length();j++){
	             int u=(s1.charAt(i-1)==s2.charAt(j-1)?0:1);
	             edits[i][j]=Math.min(
	                             edits[i-1][j]+1,
	                             Math.min(
	                                edits[i][j-1]+1,
	                                edits[i-1][j-1]+u
	                             )
	                         );
	         }
	     }
	     return edits[s1.length()][s2.length()];
	}
	
	public static void main(String[] args) {
		System.out.println(distance("allo", "Bonjour"));
	}
}
