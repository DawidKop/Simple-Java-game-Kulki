package gamestates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import main.ImageLoader;
import main.AudioManager;
import main.Keys;

public class MenuState extends States{

	private boolean option;
	public static BufferedImage bb;
	public boolean o1,o2;
	float dash1[]= {10.0f};
	BasicStroke dashed = new BasicStroke(3.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f, dash1, 0.0f);
	public MenuState(GameManager gsm) {
		super(gsm);
	}
	public void init() {
		System.out.println("Manu state.");
		bb = ImageLoader.bb;
		AudioManager.load("/SFX/menuarrows.wav", "select");
	}
	public void update() {handleInput();}
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);g.fillRect(0, 0, WIDTH, HEIGHT);
		if(o1){g.setColor(mblue);g.setStroke(dashed);g.draw(new RoundRectangle2D.Double(100, 150,250,250,20,20));}//g.drawRect (90, 140, 250, 250);
		if(o2){g.setColor(mblue);g.setStroke(dashed);g.draw(new RoundRectangle2D.Double(450, 150,250,250,20,20));}//g.drawRect(490, 140, 250, 250);
		if(!o1 || !o2)g.setStroke(new BasicStroke());
		ImageLoader.drawString(g,"How to play",130,170);
		ImageLoader.drawString(g,"Start Game",485,170);
		g.drawImage(bb, 153, 221, null);
		g.drawImage(bb, 503, 221, null);
	}

	public void setO1(boolean b){o1=b;}
	public void setO2(boolean b){o2=b;}

	private void selectOption() {
		if(option == false) {
			AudioManager.play("select");
			gm.setState(GameManager.TUTORIAL);
			System.out.println("=>Tutorial");
		}
		else if(option == true) {
			gm.setState(GameManager.GAME1);
			System.out.println("=>gra nr 1");
		}
	}

	public void handleInput() {
		if(Keys.isPressed(Keys.LEFT) )       {option=false;o1=true;o2=false;AudioManager.play("select");}
		else if(Keys.isPressed(Keys.RIGHT) ) {option=true;o2=true;o1=false;AudioManager.play("select");}
		else if((Keys.isPressed(Keys.ENTER))||(Keys.isPressed(Keys.SPACE))) {selectOption();}
	}
}
