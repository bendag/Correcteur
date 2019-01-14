import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;

public class Texte extends JFrame {
	
	public JTextArea text;
	
	//initialisation du format de TextArea
	public Texte() {
		text = new JTextArea(40,50);
		text.setEditable(true);
		text.setSize(400, 300);
		text.setLineWrap(true);
		text.setFont(new Font("Arial Black", Font.PLAIN, 12));
			
	}
	
	//Fonctions pour manipuler les objets de TextArea
		// Creates highlights around all occurrences of pattern in textComp
	    public void highlight(JTextComponent textComp, String pattern) {
	      
	        try {
	            Highlighter hilite = textComp.getHighlighter();
	            Document doc = textComp.getDocument();
	            String text = doc.getText(0, doc.getLength());
	            int pos = 0;
	    
	            // Search for pattern
	            while ((pos = text.indexOf(pattern, pos)) >= 0) {
	                // Create highlighter using private painter and apply around pattern
	                hilite.addHighlight(pos, pos+pattern.length(), myHighlightPainter);
	                pos += pattern.length();
	            }
	        } catch (BadLocationException e) {
	        }
	    }
	    
	    // Removes only our private highlights
	    public void removeHighlights(JTextComponent textComp) {
	        Highlighter hilite = textComp.getHighlighter();
	        Highlighter.Highlight[] hilites = hilite.getHighlights();
	    
	        for (int i=0; i<hilites.length; i++) {
	            if (hilites[i].getPainter() instanceof MyHighlightPainter) {
	                hilite.removeHighlight(hilites[i]);
	            }
	        }
	    }
	    
	    // An instance of the private subclass of the default highlight painter
	    Highlighter.HighlightPainter myHighlightPainter = new MyHighlightPainter(Color.red);
	    
	    // A private subclass of the default highlight painter
	    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {
	        public MyHighlightPainter(Color color) {
	            super(color);
	        }
	    }

}
