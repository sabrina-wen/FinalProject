import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.undo.*;

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
  private String allText;

  private Action cutAction = new DefaultEditorKit.CutAction();
	private Action copyAction = new DefaultEditorKit.CopyAction();
	private Action pasteAction = new DefaultEditorKit.PasteAction();

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


    JToolBar editbar = new JToolBar();
    editbar.add(new StyledEditorKit.BoldAction());
    editbar.add(new StyledEditorKit.ItalicAction());
    editbar.add(new StyledEditorKit.UnderlineAction());
    editbar.add(new StyledEditorKit.AlignmentAction("Left", -1));
    editbar.add(new StyledEditorKit.AlignmentAction("Center", 1));
    editbar.add(new StyledEditorKit.AlignmentAction("Right", 2));
    editbar.add(new StyledEditorKit.FontSizeAction("14", 14));
    editbar.add(new StyledEditorKit.FontSizeAction("16", 16));
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
    pane.add(convert);
    pane.add(uiscroll);
    pane.add(htmlscroll);

    JMenuBar menuBar = new JMenuBar();
    getContentPane().add(menuBar, BorderLayout.NORTH);
    JMenu editMenu = new JMenu("Edit");

    menuBar.add(editMenu);

    JMenuItem cutItem = new JMenuItem(cutAction);
    JMenuItem copyItem = new JMenuItem(copyAction);
    JMenuItem pasteItem = new JMenuItem(pasteAction);

    cutItem.setText("Cut");
    copyItem.setText("Copy");
    pasteItem.setText("Paste");

    editMenu.add(cutItem);
    editMenu.add(copyItem);
    editMenu.add(pasteItem);

    JPanel editorControlPanel = new JPanel();
    //editorControlPanel.setLayout(new GridLayout(3,3));
    editorControlPanel.setLayout(new FlowLayout());

    /* JButtons */
    JButton cutButton = new JButton(cutAction);
    JButton copyButton = new JButton(copyAction);
    JButton pasteButton = new JButton(pasteAction);

    cutButton.setText("Cut");
    copyButton.setText("Copy");
    pasteButton.setText("Paste");

    editorControlPanel.add(cutButton);
    editorControlPanel.add(copyButton);
    editorControlPanel.add(pasteButton);

    JPanel toolPanel = new JPanel();
    toolPanel.setLayout(new BorderLayout());
    toolPanel.add(editorControlPanel, BorderLayout.NORTH);
    getContentPane().add(menuBar, BorderLayout.NORTH);
    //getContentPane().add(toolPanel, BorderLayout.CENTER);
    setVisible(true);
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
  }

  public static void main (String[] args) {
    WYSIWYG editor = new WYSIWYG();
    editor.setVisible(true);
  }
}
