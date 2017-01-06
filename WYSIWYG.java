import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
/*from  w ww.j  a va2s  .c om*/
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class WYSIWYG extends JFrameadLocationException implements ActionListener {
	this.setTitle("WYSIWYG");
	this.setSize(600,400);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        pane = this.getContentPane();
	pane.setLayout(new BorderLayout());

	SimpleAttributeSet set = new SimpleAttributeSet();
	StyleConstants.setBold(set, true);

	// Set the attributes before adding text
	ui.setCharacterAttributes(set, true);
	ui.setText("java2s.com ");

	set = new SimpleAttributeSet();
	StyleConstants.setItalic(set, true);
    // StyleConstants.setForeground(set, Color.red);
	StyleConstants.setBackground(set, Color.blue);

	Document doc = ui.getStyledDocument();
	doc.insertString(doc.getLength(), "Swing ", set);

	set = new SimpleAttributeSet();
	StyleConstants.setFontSize(set, 24);

	doc.insertString(doc.getLength(), "Tutorial", set);

	JScrollPane scrollPane = new JScrollPane(pane);
	pane.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main (String[] args) throws BadLocationException {
	WYSIWYG editor = new WYSIWYG();
	editor.setVisible(true);
    }
}
