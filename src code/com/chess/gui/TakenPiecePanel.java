package com.chess.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.ColorUIResource;

import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;
import com.chess.gui.Table.MoveLog;

public class TakenPiecePanel extends JPanel {

    private final JPanel northPanel;
    private final JPanel southPanel;

    private static final Color PanelColor = Color.decode("0xFDFE6");
    private static final EtchedBorder PanelBorder = new EtchedBorder(EtchedBorder.RAISED);
    private static final Dimension TakenPiecesDimension = new Dimension(40, 80);

    public TakenPiecePanel() {
        super(new BorderLayout());
        setBackground(Color.decode("0xFDF5E6"));
        setBorder(PanelBorder);
        this.northPanel = new JPanel(new GridLayout(8, 2));
        this.southPanel = new JPanel(new GridLayout(8, 2));
        this.northPanel.setBackground(PanelColor);
        this.southPanel.setBackground(PanelColor);
        this.add(this.northPanel, BorderLayout.NORTH);
        this.add(this.southPanel, BorderLayout.SOUTH);
        setPreferredSize(TakenPiecesDimension);
    }

    public void redo(final MoveLog moveLog) {

        this.southPanel.removeAll();
        this.northPanel.removeAll();

        final List<Piece> whiteTakenPieces = new ArrayList<>();
        final List<Piece> blackTakenPieces = new ArrayList<>();

        for (final Move move : moveLog.getMoves()) {
            if (move.isAttack()) {
                final Piece takenPiece = move.getAtkedPiece();
                if (takenPiece.getPieceSide().isWhite()) {
                    whiteTakenPieces.add(takenPiece);
                } else if (takenPiece.getPieceSide().isBlack()) {
                    blackTakenPieces.add(takenPiece);
                } else {
                    System.out.println("something or other");
                }

            }
        }
        // Collections.sort(whiteTakenPieces, new Comparator<Piece>(){
        // @Override
        // public int compare(Piece o1, Piece o2){
        // return Ints.compare(o1.getPieceValue, o2.getPieceValue());
        // }
        // });

        // Collections.sort(blackTakenPieces, new Comparator<Piece>(){
        // @Override
        // public int compare(Piece o1, Piece o2){
        // return Ints.compare(o1.getPieceValue, o2.getPieceValue());
        // }
        // });

        for (final Piece takenPiece : whiteTakenPieces) {
            try {

                final BufferedImage image = ImageIO.read(new File("TOOL_TIP_TEXT_KEY"
                        + takenPiece.getPieceSide().toString().substring(0, 1) + " " + takenPiece.toString()));
                final ImageIcon icon = new ImageIcon(image);
                final JLabel imageLabel = new JLabel();
                this.southPanel.add(imageLabel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (final Piece takenPiece : whiteTakenPieces) {
            try {

                final BufferedImage image = ImageIO.read(new File("TOOL_TIP_TEXT_KEY"
                        + takenPiece.getPieceSide().toString().substring(0, 1) + " " + takenPiece.toString()));
                final ImageIcon icon = new ImageIcon(image);
                final JLabel imageLabel = new JLabel();
                this.southPanel.add(imageLabel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        validate();
    }
}
