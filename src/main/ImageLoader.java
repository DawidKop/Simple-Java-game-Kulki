package main;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage bb = iload("/images/bb.gif");
	public static BufferedImage intro = iload("/images/intro.gif");
	public static BufferedImage sign = iload("/images/sign.gif");
	public static BufferedImage cursor = iload("/images/cursor.gif");
	public static BufferedImage [][] bi = sload("/images/bi.gif",40,40);
	public static BufferedImage [][] ikons = sload("/images/ikony.gif",32,32);
	public static BufferedImage [][] font = sload("/images/arfas.gif", 18, 18);
	public static BufferedImage [][] kulki = sload("/images/kulki.gif", 40, 40);
	public static BufferedImage [][] keys = sload("/images/keys.gif", 48, 48);
	
	public static BufferedImage iload(String s) {
		try {
			BufferedImage loadimg = ImageIO.read(ImageLoader.class.getResourceAsStream(s));
			return loadimg;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error while an image was loading.");
			System.exit(0);
		}
		return null;
	}
	
	
	public static BufferedImage[][] sload(String s, int w, int h) {//�cie�ka wysoko�� i szeroko�c pojedynczego obiektu na spricie
		BufferedImage[][] ret;
		try {
			BufferedImage spritesheet = ImageIO.read(ImageLoader.class.getResourceAsStream(s));
			int width = spritesheet.getWidth() / w;
			int height = spritesheet.getHeight() / h;
			ret = new BufferedImage[height][width];
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
				}
			}
			return ret;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error while a sprite was loading.");
			System.exit(0);
		}return null;
	}
	
	public static void drawString(Graphics2D g, String s, int x, int y) {
		s = s.toUpperCase();
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(c >= 65 && c <= 90) c -= 65; // letters
			else if(c == 32) c = 58; // space
			else if(c == 33) c = 41; // ! (not working)
			else if(c == 36) c = 48; // $ (not working)
			else if(c == 37) c = 53; // (not working)
			else if(c == 38) c = 52; // (not working)
			else if(c == 40) c = 45; // ( (not working)
			else if(c == 41) c = 46; // ) (not working)
			else if(c == 42) c = 49; // *
			else if(c == 43) c = 47; // +
			else if(c == 44) c = 43; // ,
			else if(c == 45) c = 57; // (not working)
			else if(c == 46) c = 42; // . 
			else if(c >= 48 && c <= 57) c -= 18; // numbers (start from 26)
			else if(c == 58) c = 37; // colon :
			else if(c == 59) c = 44; // ;
			else if(c == 60) c = 51; // (not working)
			else if(c == 63) c = 40; // ? (not working)
			else if(c == 64) c = 50; // (not working)
			else if(c == 94) c = 54; // (not working) 
			int row = c / font[0].length;
			int col = c % font[0].length;
			g.drawImage(font[row][col], x + 18 * i, y, null);
		}
	}
}

