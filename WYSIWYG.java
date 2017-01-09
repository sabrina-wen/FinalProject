import javax.swing.*;
import javax.swing.text.*;
<<<<<<< HEAD

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events

import java.net.URL;
import java.io.IOException;

public class WYSIWYG extends JFrame implements ActionListener {
  private Container pane;
  private JLabel uilabel;
  private JTextComponent ui;

  public WYSIWYG () {
    this.setTitle("WYSIWYG Editor");
    this.setSize(800,600);
    this.setLocation(100,100);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // initializing pane
    pane = this.getContentPane();
    pane.setLayout(new FlowLayout());

    // lableing ui pane
    uilabel = new JLabel("Type and format code here.", null, JLabel.LEFT);
    uilabel.setPreferredSize(new Dimension(300, 30));

    // labeling html code viewer
    JLabel htmllabel = new JLabel("This is your HTML code! :o", null, JLabel.LEFT);
    htmllabel.setPreferredSize(new Dimension(300, 30));
=======
import java.awt.*;
import java.awt.event.*;

public class WYSIWYG extends JFrame {
    private Container pane;

    public WYSIWYG () {
	this.setTitle("WYSIWYG Editor");
	this.setSize(800,600);
	this.setLocation(100,100);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	// initializing pane
	pane = this.getContentPane();
	pane.setLayout(new FlowLayout());

	// lableing ui pane
	JLabel uilabel = new JLabel("Type and format code here.", null, JLabel.LEFT);
	uilabel.setPreferredSize(new Dimension(300, 30));

	// labeling html code viewer
	JLabel htmllabel = new JLabel("This is your HTML code! :o", null, JLabel.LEFT);
     	htmllabel.setPreferredSize(new Dimension(300, 30));
	
	// creating ui pane
	JTextPane ui = new JTextPane();
	ui.setText( "dis b a jtextpane, the other is jeditor pane" );
	JScrollPane uiscroll = new JScrollPane(ui);
	uiscroll.setPreferredSize(new Dimension(300, 500));

	// creating html code viewer pan
	JEditorPane htmldisplay = new JEditorPane();
       	JScrollPane htmlscroll = new JScrollPane(htmldisplay);
	htmlscroll.setPreferredSize(new Dimension(300, 500));

	// adding elements
	pane.add(uilabel);
	pane.add(htmllabel);
	pane.add(uiscroll);
	pane.add(htmlscroll);
    }
>>>>>>> cc0df5d89de8cce8eb17e03f9f9c4e74f95ba69e

    // creating convert button for testing
    JButton convert = new JButton("Convert");
    convert.setActionCommand("convert");
    convert.addActionListener(this);
    JButton bold = new JButton("Bold");
    bold.setActionCommand("bold");
    convert.addActionListener(this);

    // creating ui pane
    ui = new JTextPane();
    ui.setText( "dis b a jtextpane, the other is jeditor pane" );
    JScrollPane uiscroll = new JScrollPane(ui);
    uiscroll.setPreferredSize(new Dimension(300, 500));

    // creating html code viewer pan
    JEditorPane htmldisplay = new JEditorPane();
    JScrollPane htmlscroll = new JScrollPane(htmldisplay);
    htmlscroll.setPreferredSize(new Dimension(300, 500));

    // adding elements
    pane.add(uilabel);
    pane.add(htmllabel);
    pane.add(uiscroll);
    pane.add(convert);
    pane.add(bold);
    pane.add(htmlscroll);
  }

  public void actionPerformed(ActionEvent e) {
    // checks if any texts were selected
    if (ui.getSelectedText() != null) {
        // replaces selected text with the stylized replacement
        String replacement = ui.getSelectedText() + " REPLACED ";
        ui.replaceSelection(replacement);
      }
    }
<<<<<<< HEAD


  public static void main (String[] args) {
    WYSIWYG editor = new WYSIWYG();
    editor.setVisible(true);
  }
=======
>>>>>>> cc0df5d89de8cce8eb17e03f9f9c4e74f95ba69e
}
