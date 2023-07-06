package com.chess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.chess.engine.board.BoardUtils;

public class Table {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;

    private final static DimensionUIResource OuterFrameDimension = new DimensionUIResource(600, 600);
    private final static DimensionUIResource BoardPanelDimension = new DimensionUIResource(400, 350);
    private final static DimensionUIResource TilePanelDimension = new DimensionUIResource(10, 10);
    private final Color lightTileColor = Color.decode("#FFFACD");
    private final Color darkTileColor = Color.decode("#593E1A");

    public Table() {
        this.gameFrame = new JFrame("JChess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OuterFrameDimension);

        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);

        this.gameFrame.setVisible(true);
    }

    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        final JMenuItem printThhing = new JMenuItem("to cathch a butterfly");

        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("open up png file!");
            }
        });

        printThhing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("illusions of the past, ummmm BREAKKKKK");
            }
        });

        fileMenu.add(openPGN);
        fileMenu.add(printThhing);
        final JMenuItem exitMenuItem = new JMenuItem("exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return fileMenu;
    }

    private class BoardPanel extends JPanel {
        final java.util.List<TilePanel> boardTiles;

        BoardPanel() {
            super(new GridLayout(8, 8));
            this.boardTiles = new ArrayList<>();
            for (int index = 0; index < BoardUtils.numTitles; index++) {
                final TilePanel tilePanel = new TilePanel(this, index);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BoardPanelDimension);
            validate();
        }
    }

    private class TilePanel extends JPanel {
        private final int tileId;

        TilePanel(final BoardPanel boardPanel, final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(TilePanelDimension);
            assignTileColor();
            validate();
        }

        private void assignTileColor() {
            if (BoardUtils.FirstRow[this.tileId] || BoardUtils.ThirdRow[this.tileId] || BoardUtils.FithRow[this.tileId]
                    || BoardUtils.SeventhRow[this.tileId]) {
                setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
            } else if (BoardUtils.SecondRow[this.tileId] || BoardUtils.ForthRow[this.tileId]
                    || BoardUtils.SixRow[this.tileId] || BoardUtils.EightthRow[this.tileId]) {
                setBackground(this.tileId % 2 != 0 ? lightTileColor : darkTileColor);
            }
        }

    }
}
