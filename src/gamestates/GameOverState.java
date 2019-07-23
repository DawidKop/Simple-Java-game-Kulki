package gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GameFrame;
import main.ImageLoader;
import main.Keys;
public class GameOverState extends States{
	public GameOverState(GameManager gm) {
		super(gm);
	}

	public void init() {GameFrame.skor=0;}

	public void update() {handleInput();}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(WIDTH/2-160, HEIGHT/2-50, 320, 100);
		g.setColor(mblue);
		g.drawRect(WIDTH/2-160, HEIGHT/2-50, 320, 100);//30px bar u góry
		ImageLoader.drawString(g,"Gameover",WIDTH/2-(18*4),HEIGHT/2-18-5);
		ImageLoader.drawString(g,"(press any key)",WIDTH/2-(18*7)-9,HEIGHT/2+5);
	}

	public void handleInput() {
		if(Keys.anyKeyPress())gm.setState(GameManager.MENU);
	}

}
