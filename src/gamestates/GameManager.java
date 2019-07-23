package gamestates;
import java.awt.Graphics2D;
import main.AudioManager;


public class GameManager {

		private boolean paused;
		private States[] games; 
		private static int currentState;
		private static int previousState;
		private PauseState pauseState;
		public static final int NUM_STATES = 5;
		public static final int INTRO = 0;
		public static final int MENU = 1;
		public static final int GAME1 = 2;
		public static final int TUTORIAL = 3;
		public static final int GAMEOVER = 4;
		
		public GameManager() {
			AudioManager.init();
			paused = false;
			pauseState = new PauseState(this);
			games = new States[NUM_STATES];
			setState(INTRO);
		}
		
		public static int returnState(){return currentState;}
		
		public void setState(int i) {
			previousState = currentState;
			unloadState(previousState);
			currentState = i;
			if(i == INTRO) {
				games[i] = new IntroState(this);
				games[i].init();
			}
			else if(i == MENU) {
				games[i] = new MenuState(this);
				games[i].init();
			}
			else if(i == GAME1) {
				games[i] = new GameState(this);
				games[i].init();
			}
			else if(i == TUTORIAL) {
				games[i] = new Tutorial(this);
				games[i].init();
			}
			else if(i == GAMEOVER) {
				games[i] = new GameOverState(this);
				games[i].init();
			}
		}
		
		public void unloadState(int i) {
			games[i] = null;
		}
		
		public void setPaused(boolean b) {
			paused = b;
		}
		
		public void update() {
			if(paused) {
				pauseState.update();
			}
			else if(games[currentState] != null) {
				games[currentState].update();
			}
		}
		
		public void draw(Graphics2D g) {
			if(paused) {
				pauseState.draw(g);
			}
			else if(games[currentState] != null) {
				games[currentState].draw(g);
			}
		}
	}

