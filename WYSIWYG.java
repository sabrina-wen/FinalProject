import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events

import java.net.URL;
import java.io.IOException;

public class WYSIWYG extends JFrame {
  private Container pane;
  private JTextPane ui;

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
    ui.setEditorKit(new HTMLEditorKit());
    ui.setText( "dis b a <strong>jtextpane</strong>, the other is jeditor pane" );
    JScrollPane uiscroll = new JScrollPane(ui);
    uiscroll.setPreferredSize(new Dimension(600, 500));

    uiscroll.setBorder(
            BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder("Text Fields"),
                            BorderFactory.createEmptyBorder(1,1,1,1)));

    JToolBar bar = new JToolBar();
    bar.add(new StyledEditorKit.BoldAction());
    bar.add(new StyledEditorKit.ItalicAction());
    bar.add(new StyledEditorKit.UnderlineAction());
    bar.add(new StyledEditorKit.AlignmentAction("Left", -1));
    bar.add(new StyledEditorKit.AlignmentAction("Center", 1));
    bar.add(new StyledEditorKit.AlignmentAction("Right", 2));
    bar.add(new StyledEditorKit.FontSizeAction("14", 14));
    bar.add(new StyledEditorKit.FontSizeAction("16", 16));

    // creating html code viewer pan
    //JEditorPane htmldisplay = new JEditorPane();
    //JScrollPane htmlscroll = new JScrollPane(htmldisplay);
    //htmlscroll.setPreferredSize(new Dimension(300, 500));

    // adding elements
    //pane.add(uilabel);
    pane.add(bar);
    //pane.add(htmllabel);
    pane.add(uiscroll);
    //pane.add(htmlscroll);
  }

  public static void main (String[] args) {
    WYSIWYG editor = new WYSIWYG();
    editor.setVisible(true);
  }

}
