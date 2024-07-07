package cn.loong38.game.chess.componentUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.plaf.PanelUI;

import cn.loong38.game.chess.component.FiveChess;

public class ChessPanelUI extends PanelUI {
    private int chessSize;
    private int chessR;
    private FiveChess fiveChess;
    int chessBoardWidth;
    int chessBoardHeight;
    public int whoWin = 0;

    public ChessPanelUI(FiveChess fiveChess) {
	this.fiveChess = fiveChess;
	chessSize = fiveChess.chessSize;
	chessR = fiveChess.chessR;

	chessBoardWidth = fiveChess.getWidth();
	chessBoardHeight = fiveChess.getHeight();
    }

    @Override
    public void paint(Graphics g, JComponent c) {
	super.paint(g, c);
	Graphics2D g2 = (Graphics2D) g;
	drawChessboard(g2);
	drawChessPieces(g2);
	drawWin(g2 , whoWin);
    }

    private void drawWin(Graphics2D g2, int whoWin2) {
	int width = 200;
	int height = 100;
	g2.setColor(Color.red);
	if (whoWin >= 5) {
	    System.out.println("白棋"+whoWin+"连珠。");
//	    fiveChess.setWin(whoWin);
	    g2.fillRect(50, 100, width, height);
	    
	} else if (whoWin <= -5) {
	    whoWin *= -1;
	    System.out.println("黑棋"+whoWin+"连珠。");
//	    fiveChess.setWin(whoWin);
	    g2.fillRect(50, 100, width, height);
	} else {

	}
	
    }
    
    private void drawChessboard(Graphics2D g2) {

	g2.setStroke(new BasicStroke(2.0f));

	// 背景
	g2.setColor(Color.green);
	g2.fillRect(0, 0, chessBoardWidth, chessBoardHeight);

	// 内边框
	g2.setColor(Color.BLACK);

	int srcX = chessR * 3;
	for (int s = 0; s < chessSize; s++) {
	    g2.drawLine(srcX, chessR * 3, srcX, chessBoardHeight - chessR * 3);
	    srcX += chessR * 3;
	}

	int srcY = chessR * 3;
	for (int s = 0; s < chessSize; s++) {
	    g2.drawLine(chessR * 3, srcY, chessBoardWidth - chessR * 3, srcY);
	    srcY += chessR * 3;
	}

	// 外边框
	g2.setColor(Color.RED);
	g2.drawLine(0, 0, chessBoardWidth, 0);
	g2.drawLine(0, chessBoardHeight, chessBoardWidth, chessBoardHeight);

	g2.drawLine(0, 0, 0, chessBoardHeight);
	g2.drawLine(chessBoardWidth, 0, chessBoardWidth, chessBoardHeight);
    }

    private void drawChessPieces(Graphics2D g2) {
	int startX = chessR * 2;
	int startY = startX;

	int setp = chessR * 3;

	FiveChess.Piece[][] piece = fiveChess.piece;
	for (int y = 0; y < chessSize; y++) {
	    for (int x = 0; x < chessSize; x++) {

		if (piece[x][y] == FiveChess.Piece.White) {
		    g2.setColor(Color.white);
		    int sx = x * setp + startX;
		    int sy = y * setp + startY;
		    int ex = chessR * 2;
		    int ey = chessR * 2;
		    g2.fillOval(sx, sy, ex, ey);

		} else if (piece[x][y] == FiveChess.Piece.Black) {
		    g2.setColor(Color.black);
		    int sx = x * setp + startX;
		    int sy = y * setp + startY;
		    int ex = chessR * 2;
		    int ey = chessR * 2;
		    g2.fillOval(sx, sy, ex, ey);

		} else {

		}
	    }
	}
    }

}