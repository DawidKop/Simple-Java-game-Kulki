package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import main.GameFrame;
public abstract class States {
	
		protected GameManager gm;
		public static Font myfont=new Font(Font.MONOSPACED, Font.BOLD, 100);
		public static Font sfont=new Font(Font.MONOSPACED, Font.BOLD, 50);
		public static Font xsfont=new Font(Font.SANS_SERIF, Font.BOLD, 20);
		public static Color szary, mblue;
		public static final int WIDTH = GameFrame.WIDTH;
		public static final int HEIGHT = GameFrame.HEIGHT;
			
		public States(GameManager gm) {
				this.gm = gm;
				szary=new Color(20,20,20,5);
				mblue = new Color(52,181,255);
			}
			
			public abstract void init();
			public abstract void update();
			public abstract void draw(Graphics2D g);
			public abstract void handleInput();
}