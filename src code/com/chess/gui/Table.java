package com.chess.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.plaf.DimensionUIResource;

public class Table {

    private final JFrame gameFrame;

    private final static DimensionUIResource OuterFrameDimension = new DimensionUIResource(600, 600);

    public Table() {
        // Creates big canvas
        this.gameFrame = new JFrame("JChess");
        // creates a space on the canvas for menu options
        final JMenuBar tableMenuBar = new JMenuBar();
        // creates the options in the menue bar
        populateMenuBar(tableMenuBar);
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OuterFrameDimension);
        this.gameFrame.setVisible(true);
    }

    private void populateMenuBar(JMenuBar tableMenuBar) {
        // adds the option to the menu bar
        tableMenuBar.add(createFileMenu());

    }

    private JMenu createFileMenu() {
        // Creates a menu option
        final JMenu fileMenu = new JMenu("File");
        // // Creates a drop down option to the menu option
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        final JMenuItem printThhing = new JMenuItem("to cathch a butterfly");

        // performs the drop down option
        openPGN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("open up png file!");
            }
        });

        printThhing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("illusions of the past, ummmm BREAKKKKK");
            }
        });

        // links the drop down option to the menu option
        fileMenu.add(openPGN);
        fileMenu.add(printThhing);
        // returns menu
        return fileMenu;
    }
}
