package com.chess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.castleMove.MoveFactory;
import com.chess.engine.pieces.Piece;
import com.chess.engine.player.MoveTrans;

public class Table {

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private Board chessBoard;

    private Tile sourceTile;
    private Tile destTile;
    private Piece humanMovedPiece;

    private final static Dimension OuterFrameDimension = new Dimension(600, 600);
    private final static Dimension BoardPanelDimension = new Dimension(400, 350);
    private final static Dimension TilePanelDimension = new Dimension(10, 10);
    private static String defPieceImgPath = ("src code/chessIcons/");

    private final Color lightTileColor = Color.decode("#FFFACD");
    private final Color darkTileColor = Color.decode("#593E1A");

    public Table() {
        this.gameFrame = new JFrame("JChess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OuterFrameDimension);

        this.chessBoard = Board.makeBasicBoard();

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

        public void drawBoard(final Board board) {
            removeAll();

            for (final TilePanel tilePanel : boardTiles) {
                tilePanel.drawTile(board);
                add(tilePanel);
            }
            validate();
            repaint();

        }
    }

    private class TilePanel extends JPanel {
        private final int tileId;

        TilePanel(final BoardPanel boardPanel, final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(TilePanelDimension);
            assignTileColor();
            assignTilePieceIcon(chessBoard);
            validate();
            addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(final MouseEvent e) {

                    if (SwingUtilities.isRightMouseButton(e)) {

                        sourceTile = null;
                        destTile = null;
                        humanMovedPiece = null;

                    } else if (SwingUtilities.isLeftMouseButton(e)) {

                        if (sourceTile == null) {
                            sourceTile = chessBoard.getTile(tileId);
                            System.out.println(sourceTile);
                            humanMovedPiece = sourceTile.getPiece();
                            if (humanMovedPiece == null) {
                                sourceTile = null;
                            }

                        } else {
                            destTile = chessBoard.getTile(tileId);
                            final Move move = MoveFactory.creatMove((chessBoard), sourceTile.getTileCoords(),
                                    destTile.getTileCoords());
                            final MoveTrans trans = chessBoard.currentPlayer().makeMove(move);
                            if (trans.getMoveStatus().isDone()) {
                                chessBoard = trans.getTranBoard();
                                // TODO add the move that was made to the move log
                            }
                            sourceTile = null;
                            destTile = null;
                            humanMovedPiece = null;
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                boardPanel.drawBoard(chessBoard);
                            }
                        });
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });

            validate();
        }

        public void drawTile(final Board board) {
            assignTileColor();
            assignTilePieceIcon(board);
            validate();
            repaint();
        }

        private void assignTilePieceIcon(final Board board) {
            this.removeAll();
            if (board.getTile(this.tileId).isTileOccupied()) {

                try {

                    final BufferedImage image = ImageIO.read(new File(defPieceImgPath
                            + board.getTile(this.tileId).getPiece().getPieceSide().toString().substring(0, 1)
                            + board.getTile(this.tileId).getPiece().toString() + ".gif"));
                    add(new JLabel(new ImageIcon(image)));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void assignTileColor() {
            if (BoardUtils.EigththRank[this.tileId] || BoardUtils.SixthRank[this.tileId]
                    || BoardUtils.FourthRank[this.tileId]
                    || BoardUtils.SecondRank[this.tileId]) {
                setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
            } else if (BoardUtils.SeventhRank[this.tileId] || BoardUtils.FifthRank[this.tileId]
                    || BoardUtils.ThirdRank[this.tileId] || BoardUtils.FirstRank[this.tileId]) {
                setBackground(this.tileId % 2 != 0 ? lightTileColor : darkTileColor);
            }
        }

    }
}
