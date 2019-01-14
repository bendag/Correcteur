import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

//Auteurs
//Benjamin Dagenais 20116871
//Jessy Grondin 20119453


public class Interface  {
	
	private InterfaceModele modele;
	private InterfaceControle controle;
	
	private JFrame frame; 
	private JPanel p1, p2;	
	private JSplitPane sp;
		
	//public JTextArea text;
	public Texte textObjet;
	public JButton fichier, ouvrir, enreg, dict, verif;
	public JPopupMenu menu;
	
	
	public Interface() {
		frame = new JFrame("Correcteur");
		p1 = new JPanel();
		p2 = new JPanel();
		textObjet = new Texte();
		
		fichier = new JButton("Fichier");
		ouvrir = new JButton("Ouvrir");
		enreg = new JButton("Enregistrer");
		verif = new JButton("Verfication");
		dict = new JButton("Dictionnaire");
		menu = new JPopupMenu("Fichier");
		menu.add(ouvrir);
		menu.add(enreg);
		
		sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, p1, p2);
		p1.setBackground(Color.BLUE);
		
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(sp);
		
		//ajout des boutons au panel1
		JButton[] buttons = {fichier, dict, verif};
		for(int i = 0 ; i < buttons.length; i++) {
			p1.add(buttons[i]);
		}
		
		//creer un modele et un controle
		modele = new InterfaceModele(this);
		controle = new InterfaceControle(modele);
		
		//ajout liens d'ecoute
		fichier.addActionListener(controle.new FichierBouton());
		ouvrir.addActionListener(controle.new OuvrirBouton());
		enreg.addActionListener(controle.new EnregistrerBouton());
		dict.addActionListener(controle.new DictionnaireBouton());
		verif.addActionListener(controle.new VerificationBouton());
		textObjet.text.addMouseListener(controle.new ClicMot());
		
		//ajout textArea au panel2
		p2.add(new JScrollPane(textObjet.text));
	}
	
	public static void main(String[] args) {
		Interface correcteur = new Interface();
		correcteur.frame.setVisible(true);
	}

}
