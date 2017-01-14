import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events

import java.net.URL;
import java.io.IOException;

public class WYSIWYG extends JFrame implements ActionListener{
  private Container pane;
  private JTextPane ui;
  private StyledDocument document;

  public WYSIWYG () {
    this.setTitle("WYSIWYG Editor");
    this.setSize(800,600);
    this.setLocation(100,100);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // initializing pane
    pane = this.getContentPane();
    pane.setLayout(new FlowLayout());

    // labeling ui pane
    JLabel uilabel = new JLabel("Type and format code here.", null, JLabel.LEFT);
    uilabel.setPreferredSize(new Dimension(300, 30));

    // labeling html code viewer
    JLabel htmllabel = new JLabel("This is your HTML code! :o", null, JLabel.LEFT);
    htmllabel.setPreferredSize(new Dimension(300, 30));

    // creating ui pane
    ui = new JTextPane();
    ui.setContentType("text/html");
    ui.setText( "dis b a jtextpane, the other is jeditor pane" );
    JScrollPane uiscroll = new JScrollPane(ui);
    uiscroll.setPreferredSize(new Dimension(300, 500));

    // // creating styledDocument
    // document = ui.getStyledDocument();
    // Style style = ui.addStyle("bold", null);
    // StyleConstants.setBold(style, true);

    // creating html code viewer pan
    JEditorPane htmldisplay = new JEditorPane();
    JScrollPane htmlscroll = new JScrollPane(htmldisplay);
    htmlscroll.setPreferredSize(new Dimension(300, 500));

    // creating convert button for testing
    JButton bold = new JButton("Bold");
    bold.setActionCommand("bold");
    bold.addActionListener(this);

    // adding elements
    pane.add(uilabel);
    pane.add(htmllabel);
    pane.add(uiscroll);
    pane.add(bold);
    pane.add(htmlscroll);
  }

  public void actionPerformed(ActionEvent e) {
    // checks if any texts were selected
    if (ui.getSelectedText() != null) {
      String boldStart = "<html><strong>";
      String boldEnd = "</strong></html>";
      int startSelected = ui.getSelectionStart();
      int endSelected = ui.getSelectionEnd();
      ui.selectAll();
      int afterAll = ui.getSelectionEnd();
      ui.select(0, startSelected);
      String beforeSelected = ui.getSelectedText();
      ui.select(startSelected, endSelected);
      String selected = ui.getSelectedText();
      ui.select(endSelected, afterAll);
      String afterSelected = ui.getSelectedText();

      System.out.println(beforeSelected);
      System.out.println(selected);
      System.out.println(afterSelected);
      if (e.getActionCommand().equals("bold")) {
        ui.setText(beforeSelected + boldStart + selected + boldEnd + afterSelected);
        // document.setCharacterAttributes(ui.getSelectionStart(),
        // ui.getSelectionEnd(),
        // ui.getStyle("Bold"),
        // true);
      }
    }
  }

  public static void main (String[] args) {
    WYSIWYG editor = new WYSIWYG();
    editor.setVisible(true);
  }

}
