package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

//import main.JukeBox;
import gamestates.GameManager;
import gamestates.GameState;
import gamestates.Tutorial;

@SuppressWarnings("serial")
public class GameFrame extends JPanel implements Runnable, MouseListener,KeyListener{

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int SCALE = 4;
	private Thread thread;
	private boolean running,loadedsphere;
	private final int FPS = 50;
	private final int TARGET_TIME = 1000 / FPS;
	private BufferedImage image;
	private Graphics2D g;
	private GameManager gm;
	private int x,y,mx,my,px,py,xx,xy,loadedtype,oldx,oldy;
	public static int hajskor,skor;
	
	public GameFrame() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			addKeyListener(this);
			addMouseListener(this);
			thread = new Thread(this);
			thread.start();
		}
	}
	
public void run() {
	
		init();
		long start,elapsed,wait;
		while(running) {
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();
			elapsed = System.nanoTime() - start;
			wait = TARGET_TIME - elapsed / 1000000;
			if(wait < 0) wait = TARGET_TIME;
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

private void init() {
	running = true;
	image = new BufferedImage(WIDTH, HEIGHT, 1);
	g = (Graphics2D) image.getGraphics();
	gm = new GameManager();
	loadedsphere=false;
	//requestFocus();
}

private void update() {
	gm.update();
	Keys.update();
	if(skor>hajskor)hajskor=skor;
}

private void draw() {
	gm.draw(g);
}

private void drawToScreen() {
	Graphics g2 = getGraphics();
	g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
	g2.dispose();
}
	
	//public static void setHighScore(int h){hajskor=h;}
	public static void setScore(int h){skor=h;}
	
	public void keyPressed(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), true);	
	}

	public void keyReleased(KeyEvent key) {
		Keys.keySet(key.getKeyCode(), false);	
	}

	public void keyTyped(KeyEvent key) {}
	//

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {

		//INTRO = 0;MENU = 1;GAME1 = 2;Tutorial = 3;GAMEOVER = 4;
		if(GameManager.returnState()==2 && !GameState.mauseB){//state 2 czyli gra "kulki"
		x=e.getX();y=e.getY();
		
		if((x>667 && x<765) && (y>460 && y<498)){/*gm.setState(GameManager.INTRO);JukeBox.stop("music1");*/System.exit(0);}
		else if((x>535 && x<637) && (y>460 && y<498)){gm.setState(GameManager.GAME1);}
		else if((x>25 && x<505)&&(y>25 && y<505) && loadedsphere!=true){
			mx=x-25;my=y-25;px=40;py=40;xx=0;xy=0;
			
			while(px<mx){
				px+=40;xx++;
			}
			while(py<my){
				py+=40;xy++;
			}//oblicza w kr�r� kratk� klikni�to
			if(GameState.returnSquere(xy,xx)!=9  && xy<=11 && xx<=11){ loadedtype=GameState.returnSquere(xy,xx);loadedsphere=true;oldx=xx;oldy=xy;}//11 aby obejsc wyj�tek wyjscia poza tablic�
		}
		
		else if((x>25 && x<505)&&(y>25 && y<505) && loadedsphere==true){
			mx=x-25;my=y-25;px=40;py=40;xx=0;xy=0;
			while(px<mx){
				px+=40;xx++;
			}
			while(py<my){
				py+=40;xy++;
			}
		if(GameState.returnSquere(xy,xx)==9  && xy<=11 && xx<=11){GameState.setSquere(xy,xx,loadedtype);GameState.setSquere(oldy,oldx,9);
		GameState.checkMatches(xy,xx);loadedsphere=false;GameState.allowNextT();
		}
		else loadedsphere=false;
		}
		//teraz ikonki na dole
		else if((x>758 && x<790)&&(y>558 && y<590)){//muzyka mute or not
			System.exit(0);
		}
		else if((x>720 && x<752)&&(y>558 && y<590)){//muzyka mute or not
			gm.setState(GameManager.GAME1);
		}
		else if((x>644 && x<676)&&(y>558 && y<590)){//muzyka mute or not
			GameState.setMute();
		}
		else if((x>682 && x<714)&&(y>558 && y<590)){//muzyka mute or not
			AudioManager.stop("music1");gm.setState(GameManager.MENU);
		}
		}//koniec dla gry1
		
		else if(GameManager.returnState()==3){//tut
			//x=e.getX();y=e.getY();
			//if((x>100 && x<350) && (y>150 && y<400)){gm.setState(GameManager.TUTORIAL);}//potem zmienic na turorial
			//else if((x>450 && x<700) && (y>150 && y<400)){gm.setState(GameManager.GAME1);}
			if (e.getButton() == MouseEvent.BUTTON1) {
				 if(Tutorial.returnCurrentFrame()==0)Tutorial.setCurrentFrame(1);
				 else if(Tutorial.returnCurrentFrame()==1)Tutorial.setCurrentFrame(2);
				 else if(Tutorial.returnCurrentFrame()==2)Tutorial.setCurrentFrame(3);
				 else if(Tutorial.returnCurrentFrame()==3)gm.setState(GameManager.GAME1);
			 }
		}//koniec dla tut
		else if(GameManager.returnState()==0){//intro
			//if (e.getButton() == MouseEvent.BUTTON1) {gm.setState(GameManager.MENU);}// -wy��czone aby da� czas na �adowanie muzyki
		}//koniec dla intro
		else if(GameManager.returnState()==4){//4=game over state
			 if (e.getButton() == MouseEvent.BUTTON1) {gm.setState(GameManager.MENU);}  
		}//koniec game over mode
		else if(GameManager.returnState()==1){//menu
			x=e.getX();y=e.getY();
			if((x>100 && x<350) && (y>150 && y<400)){gm.setState(GameManager.TUTORIAL);}//potem zmienic na turorial
			else if((x>450 && x<700) && (y>150 && y<400)){gm.setState(GameManager.GAME1);}
		}//koniec dla menu
	}

	public void mouseReleased(MouseEvent e) {
		//requestFocus();//chyba nie dzia�a
	}

}

