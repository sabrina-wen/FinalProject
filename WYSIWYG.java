import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events

import java.net.URL;
import java.io.IOException;

import java.util.*;

public class WYSIWYG extends JFrame implements ActionListener{
    private Container pane;
    private JTextPane ui;
    private JEditorPane html;
    private String selected;

  public WYSIWYG () {
    this.setTitle("WYSIWYG Editor");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int sizeWidth = (int)(screenSize.getWidth() * .75);
    int sizeHeight = (int)(screenSize.getHeight() * .75);
    this.setSize(sizeWidth, sizeHeight);
    this.setMinimumSize(new Dimension(sizeWidth,sizeHeight));
    this.setMaximumSize(new Dimension(sizeWidth,sizeHeight));
    this.setLocation((int)(screenSize.getWidth() * .125),(int)(screenSize.getHeight() * .125));
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // initializing pane
    pane = this.getContentPane();
    pane.setLayout(new FlowLayout());

    // creating ui pane
    ui = new JTextPane();
    ui.setContentType("text/html");
    ui.setText( "dis b a jtextpane, the <b>other</b> is jeditor pane" );
    JScrollPane uiscroll = new JScrollPane(ui);
    uiscroll.setPreferredSize(new Dimension(sizeWidth / 2 - 50, sizeHeight - 50));
    uiscroll.setBorder(BorderFactory.createTitledBorder("Text"));

    // creating html code viewer pan
    html = new JEditorPane();
    html.setContentType("text/plain");
    JScrollPane htmlscroll = new JScrollPane(html);
    htmlscroll.setPreferredSize(new Dimension(sizeWidth / 2 - 50, sizeHeight - 50));
    htmlscroll.setBorder(BorderFactory.createTitledBorder("HTML"));

    // creating convert button for testing
    JButton convert = new JButton("Convert");
    bold.setActionCommand("convert");
    bold.addActionListener(this);

    // adding elements
    pane.add(uiscroll);
    pane.add(convert);
    pane.add(htmlscroll);
  }

     public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("convert")) {
	allText = new String();
	allText = ui.getText();
	html.setText(allText);
        // System.out.println(boldedWords);
      }
    }
  }
    
  public static void main (String[] args) {
    WYSIWYG editor = new WYSIWYG();
    editor.setVisible(true);
  }
}


