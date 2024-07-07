/**
 * 
 */
package cn.loong38.game.chess.main;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author LiuZhiwen
 *
 */
public class Main {

    /**
     * 
     */
    public Main() {
	JFrame jf = new JFrame();
	init(jf);
    }

    public void init(JFrame jf) {
	jf.setLayout(new GridLayout(15, 15));
	jf.setLocationRelativeTo(null);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jf.setVisible(true);

	JButton[][] btnChess = new JButton[15][15];
	for (int y = 0; y < btnChess.length; y++) {
	    for (int x = 0; x < btnChess.length; x++) {
		btnChess[y][x] = new JButton(y + ":" + x);
		jf.add(btnChess[y][x]);
		jf.pack();

	    }
	}

//	double[][] size = { { 10, 30, TableLayout.FILL, 10 }, { 10, TableLayout.FILL, 100, 50 } };

//	JButton jButton = new JButton("按钮");
//	jf.add(jButton, "1,2"); // “1，2”代表第1列第2行

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	new Main();
    }

}

//JPanel panel = new JPanel();
//3     double[][] size = { { 10,30,TableLayout.FILL,10 }, { 10,TableLayout.FILL,100,50 } }; 
//表格尺寸｛｛每一列宽度｝，｛每一行宽度｝｝。这里是4列3行。
//4     panel.setLayout(new TableLayout(size));
//5     JButton jButton = new JButton("按钮");
//6     panel.add(jButton,"1,2");  // “1，2”代表第1列第2行
