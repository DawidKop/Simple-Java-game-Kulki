package main;
import java.awt.event.KeyEvent;

public class Keys {

public static final int NUM_KEYS = 11;
		
		public static boolean keyState[] = new boolean[NUM_KEYS];
		public static boolean prevKeyState[] = new boolean[NUM_KEYS];
		
		public static int UP = 0;
		public static int BUTW = 0;
		public static int LEFT = 1;
		public static int BUTA = 1;
		public static int DOWN = 2;
		public static int BUTS = 2;
		public static int RIGHT = 3;
		public static int BUTD = 3;
		public static int SPACE = 4;
		public static int ENTER = 5;
		public static int ESCAPE = 6;
		public static int F1 = 7;
		public static int F2 = 8;
		public static int F3 = 9;
		public static int F4 =10;
		
		public static void keySet(int i, boolean b) {
			if(i == KeyEvent.VK_UP) keyState[UP] = b;
			else if(i == KeyEvent.VK_LEFT) keyState[LEFT] = b;
			else if(i == KeyEvent.VK_DOWN) keyState[DOWN] = b;
			else if(i == KeyEvent.VK_RIGHT) keyState[RIGHT] = b;
			else if(i == KeyEvent.VK_W) keyState[UP] = b;
			else if(i == KeyEvent.VK_A) keyState[LEFT] = b;
			else if(i == KeyEvent.VK_S) keyState[DOWN] = b;
			else if(i == KeyEvent.VK_D) keyState[RIGHT] = b;
			else if(i == KeyEvent.VK_SPACE) keyState[SPACE] = b;
			else if(i == KeyEvent.VK_ENTER) keyState[ENTER] = b;
			else if(i == KeyEvent.VK_ESCAPE) keyState[ESCAPE] = b;
			else if(i == KeyEvent.VK_F1) keyState[F1] = b;
			else if(i == KeyEvent.VK_F2) keyState[F2] = b;
			else if(i == KeyEvent.VK_F3) keyState[F3] = b;
			else if(i == KeyEvent.VK_F4) keyState[F4] = b;
		}

		public static void update() {
			for(int i = 0; i < NUM_KEYS; i++) {
				prevKeyState[i] = keyState[i];
			}
		}
		
		public static boolean isPressed(int i) {
			return keyState[i] && !prevKeyState[i];
		}
		
		public static boolean isDown(int i) {
			return keyState[i];
		}
		
		public static boolean anyKeyDown() {
			for(int i = 0; i < NUM_KEYS; i++) {
				if(keyState[i]) return true;
			}
			return false;
		}
		
		public static boolean anyKeyPress() {
			for(int i = 0; i < NUM_KEYS; i++) {
				if(keyState[i] && !prevKeyState[i]) return true;
			}
			return false;
		}	
	}

