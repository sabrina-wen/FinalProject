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
    private String selected, allText, restOfText;
    private ArrayList<String> boldedWords = new ArrayList<String>();

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
    JButton bold = new JButton("Bold");
    bold.setActionCommand("bold");
    bold.addActionListener(this);

    // adding elements
    pane.add(uiscroll);
    pane.add(bold);
    pane.add(htmlscroll);
  }

     public void actionPerformed(ActionEvent e) {
    // checks if any texts were selected
    if (ui.getSelectedText() != null) {
      String boldStart = "<b>";
      String boldEnd = "</b>";
      int startSelected = ui.getSelectionStart();
      int endSelected = ui.getSelectionEnd();
      ui.selectAll();
      int afterAll = ui.getSelectionEnd();
      System.out.println(afterAll);
      ui.select(0, startSelected);
      String beforeSelected = ui.getSelectedText();
      ui.select(startSelected, endSelected);
      String selected = ui.getSelectedText();
      ui.select(endSelected, afterAll);
      String afterSelected;
      if (endSelected != afterAll) {
        afterSelected = ui.getSelectedText();
      } else {
        afterSelected = "";
      }
      if (e.getActionCommand().equals("bold")) {
        String newBoldText = beforeSelected + boldStart + selected + boldEnd + afterSelected;
        boldedWords.add(startSelected + "," + endSelected);
        ui.setText(newBoldText);
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


