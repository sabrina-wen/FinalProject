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
  private File currentFile;

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
    this.setLocation((int)(screenSize.getWidth() * .075),0);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    // initializing pane
    pane = this.getContentPane();
    pane.setLayout(new FlowLayout());

    // creating ui pane
    ui = new JTextPane();
    ui.setContentType("text/html");
    ui.setText( "dis b a jtextpane, the <b>other</b> is jeditor pane" );
    JScrollPane uiscroll = new JScrollPane(ui);
    uiscroll.setPreferredSize(new Dimension(sizeWidth / 2 - sizeWidth / 20, sizeHeight - sizeHeight / 10));
    uiscroll.setBorder(BorderFactory.createTitledBorder("Text"));

    // creating html code viewer pan
    html = new JEditorPane();
    html.setContentType("text/plain");
    JScrollPane htmlscroll = new JScrollPane(html);
    htmlscroll.setPreferredSize(new Dimension(sizeWidth / 2 - sizeWidth / 20, sizeHeight - sizeHeight / 10));
    htmlscroll.setBorder(BorderFactory.createTitledBorder("HTML"));


    JToolBar editbar = new JToolBar();
    editbar.add(new StyledEditorKit.BoldAction());
    editbar.add(new StyledEditorKit.ItalicAction());
    editbar.add(new StyledEditorKit.UnderlineAction());
    editbar.add(new StyledEditorKit.AlignmentAction("Left", -1));
    editbar.add(new StyledEditorKit.AlignmentAction("Center", 1));
    editbar.add(new StyledEditorKit.AlignmentAction("Right", 2));

    // creating convert button for testing
    JButton convert = new JButton("Convert");
    convert.setActionCommand("convert");
    convert.addActionListener(this);

    JMenuBar menuBar = new JMenuBar();

    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenu colorMenu = new JMenu("Color");
    JMenu sizeMenu = new JMenu("Size");

    menuBar.add(fileMenu);
    menuBar.add(editMenu);
    menuBar.add(colorMenu);
    menuBar.add(sizeMenu);

    // file menu
    JMenuItem saveItem = new JMenuItem("Save");

    saveItem.addActionListener(this);

    fileMenu.add(saveItem);

    // edit menu
    JMenuItem cutItem = new JMenuItem(cutAction);
    JMenuItem copyItem = new JMenuItem(copyAction);
    JMenuItem pasteItem = new JMenuItem(pasteAction);

    cutItem.setText("Cut");
    copyItem.setText("Copy");
    pasteItem.setText("Paste");

    editMenu.add(cutItem);
    editMenu.add(copyItem);
    editMenu.add(pasteItem);

    // color menu
    JMenuItem redOption = new JMenuItem(new StyledEditorKit.ForegroundAction("Red",Color.red));
    JMenuItem blackOption = new JMenuItem(new StyledEditorKit.ForegroundAction("Black",Color.black));
    JMenuItem blueOption = new JMenuItem(new StyledEditorKit.ForegroundAction("Blue",Color.blue));
    JMenuItem grayOption = new JMenuItem(new StyledEditorKit.ForegroundAction("Gray",Color.gray));
    JMenuItem greenOption = new JMenuItem(new StyledEditorKit.ForegroundAction("Green",Color.green));
    JMenuItem orangeOption = new JMenuItem(new StyledEditorKit.ForegroundAction("Orange",Color.orange));
    JMenuItem pinkOption = new JMenuItem(new StyledEditorKit.ForegroundAction("Pink",Color.pink));
    JMenuItem whiteOption = new JMenuItem(new StyledEditorKit.ForegroundAction("White",Color.white));
    JMenuItem yellowOption = new JMenuItem(new StyledEditorKit.ForegroundAction("Yellow",Color.yellow));

    colorMenu.add(redOption);
    colorMenu.add(blackOption);
    colorMenu.add(blueOption);
    colorMenu.add(grayOption);
    colorMenu.add(greenOption);
    colorMenu.add(orangeOption);
    colorMenu.add(pinkOption);
    colorMenu.add(whiteOption);
    colorMenu.add(yellowOption);

    // size menu
    JMenuItem titleOption = new JMenuItem (new StyledEditorKit.FontSizeAction("Title", 72));
    JMenuItem subtitleOption = new JMenuItem (new StyledEditorKit.FontSizeAction("SubTitle", 48));
    JMenuItem subsubtitleOption = new JMenuItem (new StyledEditorKit.FontSizeAction("SubSubTitle", 30));
    JMenuItem subsubsubtitleOption = new JMenuItem (new StyledEditorKit.FontSizeAction("SubSubSubTitle", 20));
    JMenuItem bodyOption = new JMenuItem (new StyledEditorKit.FontSizeAction("Body", 14));

    sizeMenu.add(titleOption);
    sizeMenu.add(subtitleOption);
    sizeMenu.add(subsubtitleOption);
    sizeMenu.add(subsubsubtitleOption);
    sizeMenu.add(bodyOption);

    JPanel editorControlPanel = new JPanel();
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
    setVisible(true);

    // adding elements
    pane.add(menuBar);
    pane.add(editbar);
    pane.add(convert);
    pane.add(uiscroll);
    pane.add(htmlscroll);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("convert")) {
      String allText = ui.getText();
      html.setText(allText);
    }
    else if (e.getActionCommand().equals("Save")) {
      try (PrintWriter out = new PrintWriter("converted.html")  ) {
        String allText = ui.getText();
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
