import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextArea extends JPanel {

	private JTextArea ta;
	
	public TextArea(String texte) {
		ta = new JTextArea(texte);
		
	}
}
