import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPopupMenu;
import javax.swing.text.BadLocationException;
import javax.swing.text.Utilities;

public class InterfaceControle {

	private InterfaceModele modele;
	
	public InterfaceControle(InterfaceModele modele) {
		this.modele = modele;
	}
	
	public class FichierBouton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			modele.fichier();
		}
	}

	public class OuvrirBouton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				modele.ouvrir();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}	
	}
	
	public class EnregistrerBouton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				modele.enregistrer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	}
		
	public class DictionnaireBouton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				modele.dictionnaire();
			} catch (IOException  e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
			
	}
	
	public class VerificationBouton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			modele.verification();	
		}	
	}	
	
	public class ClicMot implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			modele.clicMot(e);		
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
