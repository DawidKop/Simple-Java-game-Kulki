package gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.AudioManager;
import main.ImageLoader;
import main.Keys;

public class PauseState extends States{

	private static BufferedImage[][] keys;
	
	public PauseState(GameManager gm) {
		super(gm);
		keys=ImageLoader.keys;
	}

	public void init() {}

	public void update() {
		handleInput();
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(25, 25, WIDTH, HEIGHT);
		g.setColor(mblue);
		g.drawRect(25, 25, WIDTH-50-4, HEIGHT-50-30);
		ImageLoader.drawString(g,"Paused",WIDTH/2-(18*3),HEIGHT/2-9-18-10);
		ImageLoader.drawString(g,"(continue)",WIDTH/2-(18*5),HEIGHT/2+120);
		ImageLoader.drawString(g,"(return to menu)",WIDTH/2-(18*5),HEIGHT/2+160);
		ImageLoader.drawString(g,"(exit game)",WIDTH/2-(18*5),HEIGHT/2+200);
		g.drawImage(keys[0][2],250,HEIGHT/2+110,40,40,null);
		g.drawImage(keys[0][0],250,HEIGHT/2+150,40,40,null);
		g.drawImage(keys[0][1],250,HEIGHT/2+190,40,40,null);
	}

	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) {gm.setPaused(false);AudioManager.resumeLoop("music1");}
		else if(Keys.isPressed(Keys.ENTER)) {gm.setPaused(false);gm.setState(GameManager.MENU);}
		else if(Keys.isPressed(Keys.SPACE)) {System.exit(0);} 
	}

}
