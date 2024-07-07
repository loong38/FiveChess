/**
 * 
 */
package cn.loong38.game.chess.component;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import cn.loong38.game.chess.componentUI.ChessPanelUI;
import cn.loong38.game.chess.event.MouseChessEvent;

/**
 * @author LiuZhiwen
 *
 */
public class FiveChess extends JPanel {
    public enum Dir {
	NO, WEST, NORTHWEST, NORTH, NORTHEAST
    }

    public enum Piece {
	White, Black, Null
    }

    public int chessR;
    public int chessSize;
    public Point newMICPoint = new Point(0, 0);// newMoveInChessPoint
    public Piece[][] piece;
    public ChessPanelUI ChessUI;
//    public final int WHITE = 1;
//    public final int BLACK = -1;
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public FiveChess() {
	this(15);
    }

    /**
     * 
     * @param chessSize 棋盘大小线的多少
     */
    public FiveChess(int chessSize) {
	this(chessSize, 15);
    }

    /**
     * 
     * @param chessSize 棋盘大小线的多少
     * @param chessR    棋子半径
     */
    public FiveChess(int chessSize, int chessR) {
	this.chessSize = chessSize;
	this.chessR = chessR;
	init();
    }

    private void init() {
	this.setLayout(null);
	piece = new Piece[chessSize][chessSize];
	for (int y = 0; y < chessSize; y++) {
	    for (int x = 0; x < chessSize; x++) {
		piece[x][y] = Piece.Null;
	    }
	}

//	piece[3][5] = Piece.White;
//	piece[4][5] = Piece.White;
//	piece[6][5] = Piece.White;
//	piece[7][5] = Piece.White;

	Dimension d = new Dimension();

//	d.height = chessSize * (chessR * 2) + (chessSize + 1) * chessWidth;
	d.height = chessR * 2 * chessSize + chessR * (chessSize + 1) + chessR * 2;
	d.width = d.height;

	this.setSize(d);
//	this.setLocation(50, 50);
	this.setUI(new ChessPanelUI(this));
//	this.repaint();
	ChessUI = (ChessPanelUI) this.getUI();
//	this.addMouseListener(new MouseChessEvent(this));
	super.addMouseListener(new MouseChessEvent(this));
//	this.addMouseListener(null);
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
//	System.out.println("addMouseListener");
	super.addMouseListener(l);
	MouseListener[] listener = this.getMouseListeners();
	for (int i = 0; i < listener.length; i++) {
	    if (listener[i] instanceof MouseChessEvent) {
		this.removeMouseListener(listener[i]);
	    }
	}
    }

    public int getWhoWin() {
	int sum = 0;
	int x = newMICPoint.x;
	int y = newMICPoint.y;
	Piece newwho = piece[x][y];

	for (Dir d : Dir.values()) {
	    int tSum = isWin(d, x, y, newwho);
	    if (tSum >= 5) {
		if (sum < tSum)
		    sum = tSum;
	    } else if (tSum <= -5) {
		if (sum > tSum)
		    sum = tSum;
	    }
	}
	return sum;
    }

    public void setWin(int who) {
	ChessUI.whoWin = who;
    }

    private int isWin(Dir direction, int sx, int sy, Piece newwho) {// 方向、原x，y，谁最后一次下的棋
	int sum = 0;

	switch (direction) {
	case WEST:
	    while (piece[sx][sy] == newwho) {// 找到东反方向最靠前的一个棋子的位置。
		if (sx - 1 < 0 || piece[sx - 1][sy] != newwho)
		    break;
		sx--;
	    }
	    while (piece[sx][sy] == newwho) {
		if (newwho == Piece.White)
		    sum += 1;
		else
		    sum += -1;

		if (sx + 1 >= chessSize || piece[sx + 1][sy] != newwho)
		    break;
		sx++;
	    }
	    break;
	case NORTH:
	    while (piece[sx][sy] == newwho) {
		if (sy - 1 < 0 || piece[sx][sy - 1] != newwho)
		    break;
		sy--;
	    }

	    while (piece[sx][sy] == newwho) {
		if (newwho == Piece.White)
		    sum += 1;
		else
		    sum += -1;

		if (sy + 1 >= chessSize || piece[sx][sy + 1] != newwho)
		    break;
		sy++;
	    }
	    break;
	case NORTHWEST:
	    while (piece[sx][sy] == newwho) {
		if (sy - 1 < 0 || sx - 1 < 0 || piece[sx - 1][sy - 1] != newwho)
		    break;

		sx--;
		sy--;
	    }
	    while (piece[sx][sy] == newwho) {
		if (newwho == Piece.White)
		    sum += 1;
		else
		    sum += -1;

		if (sx + 1 >= chessSize || sy + 1 >= chessSize || piece[sx + 1][sy + 1] != newwho)
		    break;
		sx++;
		sy++;
	    }
	    break;

	case NORTHEAST:
	    while (piece[sx][sy] == newwho) {
		if (sx + 1 >= chessSize || sy - 1 < 0 || piece[sx + 1][sy - 1] != newwho)
		    break;
		sx++;
		sy--;
	    }
	    while (piece[sx][sy] == newwho) {
		if (newwho == Piece.White)
		    sum += 1;
		else
		    sum += -1;

		if (sx - 1 < 0 || sy + 1 >= chessSize || piece[sx - 1][sy + 1] != newwho)
		    break;
		sx--;
		sy++;
	    }
	    break;
	default:
	}
	return sum;
    }

    public Point getChessDownPoint(Point m) {
	Point p = new Point();
	int srcX = chessR * 2;
	int srcY = srcX;
	int setp = chessR * 3;

	p.x = (m.x - srcX) / setp;
	p.y = (m.y - srcY) / setp;
	return p;
    }
}
