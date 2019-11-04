import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JOptionPane;

class Notepad extends JFrame

{
    private JFrame frame;
    private JButton press;
    private JMenuBar menu;
    private JMenu menu1;
    private JMenu menu2;
    private JMenuItem more1;
    private JMenuItem more2;
    private JMenuItem file1;
    private JMenuItem file2;
    private JMenuItem file3;
    private JMenuItem file4;
    Notepad ()
    {
        frame = new JFrame ("Notepad");
        press = new JButton ("Press");
        menu = new JMenuBar();
        menu1 = new JMenu("File");
        menu2 = new JMenu("More");
        file1 = new JMenuItem("New");
        file2 = new JMenuItem("Open");
        file3 = new JMenuItem("Save");
        file4 = new JMenuItem("Save As");
        more1 = new JMenuItem("Help");
        more2 = new JMenuItem("About");
    }
    private void showSaveFileDialog()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            JFrame f = new JFrame ("Saved To");
            JOptionPane.showMessageDialog(f,"File saved as "+fileToSave.getAbsolutePath()); 
        }
    }
    private void AboutUI ()
    {
        JFrame aboutbox = new JFrame ();
        aboutbox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aboutbox.setSize(400,200);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }
        JOptionPane.showMessageDialog(aboutbox,"Notepad\n\nby Atish Ghosh\n2019\nversion 1.0 Alpha\n "); 
    }
    private void Interface ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480,640);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }
        menu1.add(file1);
        menu1.add(file2);
        menu1.add(file3);
        file4.addActionListener(new ActionListener()
        {
                public void actionPerformed(ActionEvent arg0) {
                    showSaveFileDialog();
            }
        });
        menu1.add(file4);
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
        frame.getContentPane().add(BorderLayout.NORTH, menu);
        frame.setVisible(true);
    }
    void main() throws AWTException
    {
        Interface();
    }
}
