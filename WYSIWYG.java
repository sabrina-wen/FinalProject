import java.util.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events

import java.net.URL;
import java.io.*;
import java.io.IOException;

import java.util.*;

public class WYSIWYG extends JFrame implements ActionListener{
  private Container pane;
  private JTextPane ui;
  private JEditorPane html;
  private JComboBox colorPicker;
  private String allText;
  private String[] colorOptions;

  public WYSIWYG () {
    this.setTitle("WYSIWYG Editor");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int sizeWidth = (int)(screenSize.getWidth() * .85);
    int sizeHeight = (int)(screenSize.getHeight() * .85);
    this.setSize(sizeWidth, sizeHeight);
    this.setMinimumSize(new Dimension(sizeWidth,sizeHeight));
    this.setMaximumSize(new Dimension(sizeWidth,sizeHeight));
    this.setLocation((int)(screenSize.getWidth() * .075),(int)(screenSize.getHeight() * .075));
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // initializing pane
    pane = this.getContentPane();
    pane.setLayout(new FlowLayout());

    // creating ui pane
    ui = new JTextPane();
    ui.setContentType("text/html");
    ui.setText( "dis b a jtextpane, the <b>other</b> is jeditor pane" );
    JScrollPane uiscroll = new JScrollPane(ui);
    uiscroll.setPreferredSize(new Dimension(sizeWidth / 2 - sizeWidth / 10, sizeHeight - sizeHeight / 10));
    uiscroll.setBorder(BorderFactory.createTitledBorder("Text"));

    // creating html code viewer pan
    html = new JEditorPane();
    html.setContentType("text/plain");
    JScrollPane htmlscroll = new JScrollPane(html);
    htmlscroll.setPreferredSize(new Dimension(sizeWidth / 2 - sizeWidth / 10, sizeHeight - sizeHeight / 10));
    htmlscroll.setBorder(BorderFactory.createTitledBorder("HTML"));

    // initializing color list
    colorOptions = new String[2];
    colorOptions[0] = "red";
    colorOptions[1] = "blue";
    colorPicker = new JComboBox(colorOptions);
    colorPicker.addActionListener(this);

    JToolBar editbar = new JToolBar();
    editbar.add(new StyledEditorKit.BoldAction());
    editbar.add(new StyledEditorKit.ItalicAction());
    editbar.add(new StyledEditorKit.UnderlineAction());
    int fontSize = ui.getFont().getSize();
    editbar.add(new StyledEditorKit.AlignmentAction("Left", -1));
    editbar.add(new StyledEditorKit.AlignmentAction("Center", 1));
    editbar.add(new StyledEditorKit.AlignmentAction("Right", 2));
    editbar.add(new StyledEditorKit.FontSizeAction("+", fontSize + 20));
    fontSize = ui.getFont().getSize();
    editbar.add(new StyledEditorKit.FontSizeAction("-", fontSize - 2));
    editbar.add(new StyledEditorKit.ForegroundAction("Change color", Color.RED));

    JToolBar functionbar = new JToolBar();
    functionbar.add(new DefaultEditorKit.CopyAction());
    functionbar.add(new DefaultEditorKit.CutAction());
    functionbar.add(new DefaultEditorKit.PasteAction());

    // creating convert button for testing
    JButton convert = new JButton("Convert");
    convert.setActionCommand("convert");
    convert.addActionListener(this);

    // adding elements
    pane.add(editbar);
    pane.add(functionbar);
    pane.add(colorPicker);
    pane.add(convert);
    pane.add(uiscroll);
    pane.add(htmlscroll);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("convert")) {
      allText = new String();
      allText = ui.getText();
      html.setText(allText);

      try(PrintWriter out = new PrintWriter( "converted.html" )  ){
        out.println(allText);
      } catch(FileNotFoundException error) {
        System.err.println("FileNotFoundException: " + error.getMessage());
      }
    }
    if (e.getSource() == colorPicker) {
	JComboBox colorPickerCopy = (JComboBox)e.getSource();
	String colorFromList = (String)colorPickerCopy.getSelectedItem();
	switch (colorFromList) {
	case "red":
	    System.out.println("red");
	    break;
	case "blue":
	    System.out.println("blue");
	    break;
	default: System.out.print("Choose a color!");
	}
    }
  }

  public static void main (String[] args) {
    WYSIWYG editor = new WYSIWYG();
    editor.setVisible(true);
  }
}
