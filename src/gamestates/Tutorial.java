package gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import main.ImageLoader;
import main.AudioManager;
import main.Keys;

public class Tutorial extends States{
	private final int frames=3;
	private static int currentframe;
	String[] txt={"Match 5 or more", "bubbles to clear","the area.","You can combine","them and earn","more points."
			,"You can navigate and play this game","by using mouse only but keys can","help you as well."
			,"Arrows allow you to navigate", "thought menu and this tutorial","additionally escape and enter","buttons help you choose your","option, pause the game etc."
			,"Combos provide you geometrical point","bonus. While 5 bubbles match gives you", "fair 5 points, you can score up to 200"," points at once."
			," there is also a chance to spawn special","bubble. it behaves like every bubble","making matches such as below possible."};
	
	public Tutorial(GameManager gm) {
		super(gm);
	}

	public void init() {System.out.println("How to play.");AudioManager.load("/SFX/menuarrows.wav", "select");}

	public void update() {handleInput();}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(mblue);
		g.drawRect(25, 55, WIDTH-50-6, HEIGHT-110);
		
		if(currentframe==0){
			ImageLoader.drawString(g,"How to play",WIDTH/2-99,30);
			ImageLoader.drawString(g,txt[0],75,105);ImageLoader.drawString(g,txt[1],55,145);ImageLoader.drawString(g,txt[2],75,185);
			ImageLoader.drawString(g,txt[3],460,105);ImageLoader.drawString(g,txt[4],480,145);ImageLoader.drawString(g,txt[5],460,185);
			g.setColor(mblue);
			for(int i=0;i<7;i++){
				for(int j=0;j<7;j++){
			g.drawRect(55+(j*40),240+(i*40),40,40);
			g.drawRect(460+(j*40),240+(i*40),40,40);
				}}
			for(int i=0;i<6;i++){
				g.drawImage(ImageLoader.kulki[0][3],95+(i*40),280,40,40,null);
				g.drawImage(ImageLoader.kulki[0][3],135,280+(i*40),40,40,null);
				g.drawImage(ImageLoader.bi[0][6],500+(i*40),280,40,40,null);
				g.drawImage(ImageLoader.bi[0][6],540,280+(i*40),40,40,null);
			}
			g.setColor(Color.BLACK);g.fillRect(136,281,38,38);g.fillRect(136,481,38,38);g.fillRect(542,481,38,38);//zas�onic 3 pola
			g.drawImage(ImageLoader.kulki[0][3],255,360,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],175,320,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],175,360,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],215,440,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],580,320,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],580,360,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],620,440,40,40,null);
			g.drawImage(ImageLoader.cursor,265,380,40,40,null);
			g.drawImage(ImageLoader.cursor,550,300,40,40,null);
		}
		else if(currentframe==1){
			ImageLoader.drawString(g,"Special bubbles",WIDTH/2-99,30);
			ImageLoader.drawString(g,"they cannot be moved so it is",140,110);
			ImageLoader.drawString(g,"better to get rid of them fast",140,150);
			g.drawImage(ImageLoader.kulki[1][1],90,100,40,40,null);//1st row
			g.drawImage(ImageLoader.kulki[1][2],40,100,40,40,null);//2nd row
			
			//tables
			g.setColor(mblue);
			for(int i=0;i<7;i++){
				for(int j=0;j<7;j++){
			g.drawRect(55+(j*40),240+(i*40),40,40);
			g.drawRect(460+(j*40),240+(i*40),40,40);
				}}
			for(int i=0;i<5;i++){
				g.drawImage(ImageLoader.kulki[0][6],55+(i*40),280,40,40,null);
				g.drawImage(ImageLoader.bi[0][6],460+(i*40),280,40,40,null);
				//
				if(i>=2){
				
				g.drawImage(ImageLoader.kulki[0][2],540,320+(i*40),40,40,null);
				g.drawImage(ImageLoader.kulki[0][2],135,320+(i*40),40,40,null);}
			}
			g.setColor(Color.BLACK);g.fillRect(136,281,38,38);g.fillRect(136,481,38,38);g.fillRect(542,481,38,38);//zas�onic 3 pola
			g.drawImage(ImageLoader.kulki[1][6],175,280,40,40,null);
			g.drawImage(ImageLoader.kulki[1][6],215,280,40,40,null);
			g.drawImage(ImageLoader.kulki[0][6],255,360,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],175,320,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],175,360,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],215,440,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],580,320,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],580,360,40,40,null);
			g.drawImage(ImageLoader.kulki[0][2],620,440,40,40,null);
			g.drawImage(ImageLoader.cursor,265,380,40,40,null);
			g.drawImage(ImageLoader.cursor,550,300,40,40,null);
		}
		else if(currentframe==2){
			ImageLoader.drawString(g,"Key input",WIDTH/2-99,30);
			ImageLoader.drawString(g,txt[6],120,95);
			ImageLoader.drawString(g,txt[7],120,125);
			ImageLoader.drawString(g,txt[8],120,155);
			g.drawImage(ImageLoader.cursor,50,120,40,40,null);
			g.drawImage(ImageLoader.keys[0][0],50,260,40,40,null);
			g.drawImage(ImageLoader.keys[0][1],90,260,40,40,null);
			g.drawImage(ImageLoader.keys[0][2],50,320,40,40,null);
			g.drawImage(ImageLoader.keys[0][3],90,320,40,40,null);
			ImageLoader.drawString(g,txt[9],140,240);
			ImageLoader.drawString(g,txt[10],140,270);
			ImageLoader.drawString(g,txt[11],140,300);
			ImageLoader.drawString(g,txt[12],140,330);
			ImageLoader.drawString(g,txt[13],140,360);
		}
		else if(currentframe==3){
			ImageLoader.drawString(g,"Press mouse button to start the game",WIDTH/2-315,HEIGHT/2-20);
			ImageLoader.drawString(g,"or use arrows to reread the tutorial",WIDTH/2-315,HEIGHT/2+20);
		}
	}

	public static int returnCurrentFrame(){return currentframe;}
	public static void setCurrentFrame(int c){currentframe=c;}
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER)) {gm.setState(GameManager.GAME1);}
		else if(Keys.isPressed(Keys.ESCAPE)) {gm.setState(GameManager.GAME1);}
		else if(Keys.isPressed(Keys.SPACE)) {gm.setState(GameManager.GAME1);}
		else if(Keys.isPressed(Keys.RIGHT)) {if(currentframe<frames){currentframe++;AudioManager.play("select");}}
		else if(Keys.isPressed(Keys.LEFT)) {if(currentframe>0){currentframe--;AudioManager.play("select");}}
	}

}
