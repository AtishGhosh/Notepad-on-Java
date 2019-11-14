import java.awt.*;
import javax.swing.*;
import java.io.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import java.net.URI;

public class Notepad
{
    public static void main (String[] args)
    {
        NotepadUI notepad = new NotepadUI ();
        notepad.Interface();
    }
}
class NotepadUI extends JFrame
{
    private JFrame frame;
    private JTextArea textspace;
    private JScrollPane spV;
    private JScrollPane spH;
    private JMenuBar menu;
    private JMenu menu1;
    private JMenu menu2;
    private JMenuItem more1;
    private JMenuItem more2;
    private JMenuItem file2;
    private JMenuItem file3;
    private JMenuItem file4;
    private boolean FilePresent;
    private String fpath;

    NotepadUI ()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }
        frame = new JFrame ("Notepad");
        textspace = new JTextArea();
        spV = new JScrollPane(textspace);
        spH = new JScrollPane(textspace);
        menu = new JMenuBar();
        menu1 = new JMenu("File");
        menu2 = new JMenu("More");
        //file1 = new JMenuItem("New");
        file2 = new JMenuItem("Open");
        file3 = new JMenuItem("Save");
        file4 = new JMenuItem("Save As");
        more1 = new JMenuItem("Feedback");
        more2 = new JMenuItem("About");
        fpath = "";
        FilePresent = false;
    }
    private void SaveFileUI()
    {
        String texttosave = textspace.getText();
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(fpath));
            os.write(texttosave.getBytes(), 0, texttosave.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {  }
        }
        JFrame f = new JFrame ("Saved To");
        JOptionPane.showMessageDialog(f,"File saved as "+fpath);
    }
    private void SaveFileAsUI()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String texttosave = textspace.getText();
            OutputStream os = null;
            try {
                os = new FileOutputStream(new File(fileToSave.getAbsolutePath()));
                os.write(texttosave.getBytes(), 0, texttosave.length());
            } catch (IOException e) {   }
            finally {
                try {
                    os.close();
                } catch (IOException e) {   }
            }
            FilePresent = true; fpath = fileToSave.getAbsolutePath(); frame.setTitle("Notepad - "+fpath);
            JFrame f = new JFrame ("Saved To");
            JOptionPane.showMessageDialog(f,"File saved as "+fileToSave.getAbsolutePath());
        }
    }
    private void OpenFileUI()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open");
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            fpath = fileToOpen.getAbsolutePath();
            frame.setTitle("Notepad - "+fpath);
            FilePresent = true;
        }
    }
    private void OpenFile()
    {
        try {
            BufferedReader br=new BufferedReader(new FileReader(fpath));
            String s1="",s2="";
            while((s1=br.readLine())!=null){
                s2+=s1+"\n";
            }
            textspace.setText(s2);
            br.close();
        } catch (Exception e) { }
    }
    private void AboutUI ()
    {
        JFrame aboutbox = new JFrame ();
        aboutbox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aboutbox.setSize(400,200);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }
        JOptionPane.showMessageDialog(aboutbox,"Notepad\n\nby Atish Ghosh\n2019\nversion 1.0 (Unstable)\n ");
    }
    public void Interface ()
    {
        
        file2.setAccelerator(KeyStroke.getKeyStroke("control O"));
        file2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) {
                OpenFileUI();
                OpenFile();
            }
        });
        menu1.add(file2);
        file3.setAccelerator(KeyStroke.getKeyStroke("control S"));
        file3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) {
                if (FilePresent == false) {
                    SaveFileAsUI();
                }
                else {
                    SaveFileUI();
                }
            }
        });
        menu1.add(file3);
        file4.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
        file4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) {
                SaveFileAsUI();
            }
        });
        menu1.add(file4);
        more1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Desktop desktop = Desktop.getDesktop();
                    URI uri = URI.create("mailto:atishghosh30@gmail.com?subject=Notepad%20Feedback");
                    desktop.mail(uri);
                } catch (Exception e) {}
            }
        });
        menu2.add(more1);
        more2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0) {
                AboutUI();
            }
        });
        menu2.add(more2);
        menu.add(menu1);
        menu.add(menu2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        textspace.setWrapStyleWord(true);
        spH.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //spV.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        frame.getContentPane().add(BorderLayout.CENTER, textspace);
        frame.getContentPane().add(BorderLayout.NORTH, menu);
        frame.getContentPane().add(BorderLayout.SOUTH, spH);
        frame.getContentPane().add(BorderLayout.EAST, spV);
        spH.setViewportView(textspace);
        spV.setViewportView(textspace);
        frame.add(spH);
        frame.add(spV);
        frame.setSize(480,640);
        frame.setVisible(true);
    }
}
