package gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.ImageLoader;
//import main.JukeBox;
import main.Keys;

public class IntroState extends States{

	public static BufferedImage ima, introbackground,sign;
	private int alpha;
	private int klatki;
	private final int FADE_IN = 60;
	private final int LENGTH = 60;
	private final int FADE_OUT = 60;
	@SuppressWarnings("unused")
	private Graphics2D g;
	public static boolean letit;//keep value about mp3 files being loaded or not
	public IntroState(GameManager gm) {
		super(gm);
		introbackground = ImageLoader.intro;
		sign = ImageLoader.sign;
		ima = new BufferedImage(960,800,1);
		letit=false;
	}

	public void init(){
		klatki = 0;
		g = (Graphics2D) ima.getGraphics();
		System.out.println("Intro state;");
	}

	public void update() {
		handleInput();
		klatki++;
		if(klatki < FADE_IN) {
			alpha = (int) (255 - 255 * (1.0 * klatki / FADE_IN));
			if(alpha < 0) alpha = 0;
		}
		if((klatki > FADE_IN + LENGTH)&&(letit)) {
			alpha = (int) (255 * (1.0 * klatki - FADE_IN - LENGTH) / FADE_OUT);
			if(alpha > 255) alpha = 255;
		}
		if((klatki > FADE_IN + LENGTH)&&(!letit)) {klatki--;}//waits for mp3
		if(klatki > FADE_IN + LENGTH + FADE_OUT) {
			System.out.println("Za�adowano pliki. ->menu");
			gm.setState(GameManager.MENU);
		}
	}

public static void letAdvanceToMenu(){letit=true;}

public void draw(Graphics2D g) {
	g.setColor(Color.BLACK);
	g.fillRect(0, 0, WIDTH, HEIGHT);
	g.drawImage(introbackground, 0, 0,WIDTH, HEIGHT, null);
	g.drawImage(sign, 240, 440, null);
	g.setColor(new Color(0, 0, 0, alpha));
	g.fillRect(0, 0, 960, 800);
}
	public void handleInput() {
		if((Keys.isPressed(Keys.ENTER))||(Keys.isPressed(Keys.SPACE))){}
	}
	
	@SuppressWarnings("unused")private void drawToScreen() {}
}