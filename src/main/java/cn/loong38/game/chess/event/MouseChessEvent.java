/**
 * 
 */
package cn.loong38.game.chess.event;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import cn.loong38.game.chess.component.FiveChess;

/**
 * @author Administrator
 *
 */
public class MouseChessEvent implements MouseListener {

    private FiveChess fiveChess;
    private int chessSize;
    int chessR;
    private FiveChess.Piece[][] piece;

    /**
     * @param fiveChess
     * 
     */
    public MouseChessEvent(FiveChess fiveChess) {
	this.fiveChess = fiveChess;
	chessSize = fiveChess.chessSize;
	chessR = fiveChess.chessR;
	piece = fiveChess.piece;
    }

    FiveChess.Piece whoDown = FiveChess.Piece.White;

    @Override
    public void mouseClicked(MouseEvent e) {

	moveInChess(e);
	int whoWin = fiveChess.getWhoWin();
	if (whoWin >= 5) {
	    System.out.println("白棋");
	    fiveChess.setWin(whoWin);
	} else if (whoWin <= -5) {
	    System.out.println("黑棋");
	    fiveChess.setWin(whoWin);
	} else {

	}

    }

    private void moveInChess(MouseEvent e) {
	Point chessDownPoint = fiveChess.getChessDownPoint(e.getPoint());
	int x = chessDownPoint.x;
	int y = chessDownPoint.y;

	if (x >= 0 && x < chessSize && y >= 0 && y < chessSize)
	    if (piece[x][y] == FiveChess.Piece.Null)
		if (whoDown == FiveChess.Piece.White) {
		    piece[x][y] = FiveChess.Piece.White;
		    fiveChess.newMICPoint.move(x, y);
		    whoDown = FiveChess.Piece.Black;
		} else if (whoDown == FiveChess.Piece.Black) {
		    piece[x][y] = FiveChess.Piece.Black;
		    fiveChess.newMICPoint.move(x, y);
		    whoDown = FiveChess.Piece.White;
		} else {

		}
	fiveChess.repaint();
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
}
