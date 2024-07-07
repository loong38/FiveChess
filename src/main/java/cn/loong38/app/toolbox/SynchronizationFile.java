/**
 * 
 */
package cn.loong38.app.toolbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author liuzh
 *
 */
public class SynchronizationFile {

    private String sourcePath;
    private String targetPath;
    private String source_2;
    private String source_1;

    /**
     * 
     */
    public SynchronizationFile(String source_1, String source_2) {
	this.source_1 = source_1;
	this.source_2 = source_2;
//	HashMap<String , String>
    }

//    String[]
    public void readFiles() {
    }

    public HashMap<String, Object> readMD5(String sourcePath) throws IOException {
	File catalogue = new File(sourcePath);

	HashMap<String, Object> map = new HashMap<>();
	if (catalogue.isDirectory()) {
	    for (File files : catalogue.listFiles()) {
		if (files.isDirectory())
		    map.put(files.getPath(), readMD5(files.getPath()));
		else {
		    FileInputStream fis = new FileInputStream(files);
		    byte[] b = fis.readAllBytes();
		    fis.close();
		    String md5 = MD5(b);
		    System.out.println(md5);
		    map.put(files.getPath(), md5);
		}
	    }
	} else {
	    FileInputStream fis = new FileInputStream(catalogue);
	    byte[] b = fis.readAllBytes();
	    fis.close();
	    String md5 = MD5(b);
	    map.put(catalogue.getPath(), md5);
	    System.out.println(md5);
	}

	return map;

    }

    public void AcopyB() throws IOException {
	File catalogue = new File(sourcePath);

	HashMap<String, Object> map = new HashMap<>();
	if (catalogue.isDirectory()) {
	    for (File files : catalogue.listFiles()) {
		if (files.isDirectory())
		    map.put(files.getPath(), readMD5(files.getPath()));
		else {
		    FileInputStream fis = new FileInputStream(files);
		    byte[] b = fis.readAllBytes();
		    fis.close();
		    String md5 = MD5(b);
		    map.put(files.getPath(), md5);
		}
	    }
	} else {
	    FileInputStream fis = new FileInputStream(catalogue);
	    byte[] b = fis.readAllBytes();
	    fis.close();
	    String md5 = MD5(b);
	    map.put(catalogue.getPath(), md5);
	}
    }

    @SuppressWarnings("unchecked")
    public void printMap(HashMap<String, Object> map) {
	for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
	    String key = iterator.next();
	    if (map.get(key) instanceof String) {
		System.out.println(map.get(key).toString());
	    } else if (map.get(key) instanceof HashMap) {
		System.out.println(key);
		printMap((HashMap<String, Object>) map.get(key));
	    }

	}
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

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//	for(String key : /)
	String str = "./";
//	String str = "E:\\liuzh\\Desktop\\java_project\\project_1";
	SynchronizationFile s = new SynchronizationFile(str, str);
	HashMap<String, Object> map = s.readMD5(str);
//	s.printMap(map);

//	File file = new File(str);
//	System.out.println(file.list()[0]);
    }
}
