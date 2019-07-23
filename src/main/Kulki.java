/**
* @author  Dawid S. Kopczewski
* @version 0.03
* @since   2015-02-02 
*/

package main;
import javax.swing.JFrame;

	@SuppressWarnings("serial")
	public class Kulki extends JFrame{
		public Kulki(){
			GameFrame gameframe = new GameFrame();
			JFrame frame = new JFrame("Kulki");
			frame.add(gameframe);
			frame.setUndecorated(true);
			frame.setResizable(false);
			frame.setSize(GameFrame.WIDTH,GameFrame.HEIGHT);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);

			Loader t1 = new Loader("media loader"); t1.start(); //creates and launches separate thread to load media
	
		}
		
	public static void main(String[] args) {
		new Kulki();
	}
}