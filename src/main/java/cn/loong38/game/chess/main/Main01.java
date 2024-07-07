/**
 * 
 */
package cn.loong38.game.chess.main;

import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;

import cn.loong38.game.chess.component.FiveChess;

/**
 * @author LiuZhiwen
 *
 */
public class Main01 {

//    JButton

    /**
     * 
     */
    public Main01() {
	init();
    }

    private void init() {
	JFrame jf = new JFrame();
	jf.setLayout(null);

	FiveChess fivechess = new FiveChess();

	jf.add(fivechess);

	int width = (int) fivechess.getSize().getWidth();
	int height = (int) fivechess.getSize().getHeight();
	jf.setSize(width + 100, height + 100);
	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jf.setLocationRelativeTo(null);
	jf.setVisible(true);

//	try {
//	    Thread.sleep(3000);
//	} catch (InterruptedException e) {
//	    // TODO 自动生成的 catch 块
//	    e.printStackTrace();
//	}
//	fivechess.chess[4][5] = 1;
//	fivechess.repaint();
    }

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws Exception {
	new Main01();
//	String str = "E:\\liuzh\\Desktop\\java_project\\project_1\\五子棋\\.classpath";
//	File file = new File(str);
////	FileReader fr = new FileReader(file);
//	FileInputStream fis = new FileInputStream(file);
//	byte[] a = fis.readAllBytes();
//	fis.close();
////	7656a1567d3f72f18c9ef5829e5b885c
////	207bed2d37f14eebe0dd323f43669004
//	String md5 = MD5(a);
//	System.out.println(md5);
    }

    public static String MD5(byte[] b) {
	MessageDigest md5 = null;
	try {
	    md5 = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}
//	md5.update(string.getBytes());
	md5.update(b);
	byte[] bytes = md5.digest();
	StringBuffer stringBuffer = new StringBuffer();
	for (int i = 0; i < bytes.length; i++) {
	    if (Integer.toHexString(0xff & bytes[i]).length() == 1) {
		stringBuffer.append('0').append(Integer.toHexString(0xff & bytes[i]));
	    } else {
		stringBuffer.append(Integer.toHexString(0xff & bytes[i]));
	    }
	}
	return stringBuffer.toString();
    }
}
