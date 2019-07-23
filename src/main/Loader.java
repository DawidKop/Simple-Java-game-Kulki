package main;

import gamestates.IntroState;

public class Loader extends Thread{
	
	private Thread t;
	private String threadName;
	private boolean pom,cleanstart;
	
	public Loader(String name){
		threadName=name;
		cleanstart=true;
	}

	 public void run() {
	      System.out.println("Running " +  threadName );
	    while(cleanstart){
	    pom=true;
	    try {
	    	AudioManager.load("/Music/bgmusic.mp3", "music1");  
	    } catch (Exception e) {
	         System.out.println(e);
	         pom=false;
	     }cleanstart=!pom;
	     }
	     System.out.println("Exiting " +  threadName);
	     IntroState.letAdvanceToMenu();
	     try {
		     join();
		     }catch (Exception e) {
		    	 System.out.println(e);
		     }
	   }
	   
	   public void start (){
	      System.out.println("Starting " +  threadName );
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
}
