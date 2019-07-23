package gamestates;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import main.AudioManager;
import main.GameFrame;
import main.Keys;
import main.ImageLoader;

public class GameState extends States{
	private static boolean blockInput;
	private static int pomx;
	private static int pomy;
	private int pomk;
	private int pomcount;
	private static int numspheres;
	private static int matchx,matchy;
	private static int [][] plansza;
	private int [] pom3;private static boolean [] mx,my;
	Random r = new Random();
	private static boolean nextturn,delayednextturn;
	public static boolean mauseB,bonusturn,mute;
	private static boolean delanim,begx,begy,gameisover;
	static int animy,animx,animy2,animx2,delx,dely,recent=0;
	private int animcount,specialcount;
	private static String bonus="";

	public GameState(GameManager gm) {
		super(gm);
		plansza = new int[12][12];
		pom3= new int [3];pomcount=matchx=matchy=0;
		mx=new boolean [9];
		my=new boolean [9];animcount=0;
		for(int i=0;i<=8;i++)mx[i]=false;//no longer needed, cut it later
		delx=dely=1;
	}

	public void init() {
	blockInput=false;gameisover=false;Tutorial.setCurrentFrame(0);
	animcount=0;
	for(int i=0;i<12;i++){
		for(int j=0;j<12;j++){
			plansza[i][j]=9;
		}}
	while(pomcount<3){
	pomk=r.nextInt(6)+1;//1-7
	pomx=r.nextInt(12);
	pomy=r.nextInt(12);
	if(plansza[pomx][pomy]==9){plansza[pomx][pomy]=pomk;pomcount++;}
	}
	numspheres=3;
	nextSpheres();delanim=false;specialcount=1;
	//music
	AudioManager.setVolume("music1", -10);
	AudioManager.loop("music1");
	AudioManager.load("/SFX/match.wav", "match");
	}
	
	public void update() {
		handleInput();
		if(nextturn){gameOverCheck();}
		if(nextturn && !bonusturn){nextturn=false; if(numspheres<141){nextTurn();nextSpheres();}else {fillLastSqueres();}}
		if(nextturn && bonusturn){bonusturn=false;nextturn=false;if(numspheres>=141){fillLastSqueres();}}
		if(delanim){animcount++;if(animcount>=60){animcount=0;delanim=false;begx=begy=false;delx=dely=1;}}
		if((delanim)&&(animcount==35)){mauseB=false;}
		if(delayednextturn==true && !delanim){nextTurn();delayednextturn=false;}
		if(gameisover){animcount++;if(animcount==5){animcount=0;trueGameOver();}}
	}

	public void draw(Graphics2D g) {
	//drawing frames
	g.setColor(Color.BLACK);g.fillRect(0,0,WIDTH,HEIGHT);g.setColor(mblue);
	for(int i=0;i<12;i++){
		for(int j=0;j<12;j++){
		g.drawRect(25+(j*40),25+(i*40),40,40);
		if(plansza[i][j]<10){//if drawing 1st row sphere (without shield)
		g.drawImage(ImageLoader.kulki[0][plansza[i][j]],25+(j*40),25+(i*40),40,40,null);}
		if(plansza[i][j]>10){//if drawing 2nd row sphere (with shield)
		g.drawImage(ImageLoader.kulki[1][plansza[i][j]-10],25+(j*40),25+(i*40),40,40,null);}
		
	}}
	//UI on the right
	g.drawRect(532,25,239,200);g.drawImage(MenuState.bb,605,60,null);//bubblebird
	g.drawRect(532,245,239,70);
	g.drawRect(532,335,239,100);
	g.drawRect(532+15,460,92,38);
	g.drawRect(532+135,460,92,38);
	//text
	ImageLoader.drawString(g,"Kulki",557,35);
	g.setColor(Color.BLACK);g.fillRect(572,235,80,40);//background rectangle of 'next' tile
	ImageLoader.drawString(g,"Next",577,235);
	ImageLoader.drawString(g,"  Score",537,355);
	ImageLoader.drawString(g,"HiScore",537,395);
	ImageLoader.drawString(g,"New",537+30,470);
	ImageLoader.drawString(g,"Exit",537+120+20,470);
	//score
	ImageLoader.drawString(g,zeros(GameFrame.skor),670,355);
	ImageLoader.drawString(g,zeros(GameFrame.hajskor),670,395);
	//icons on the bottom right corner
	if(!mute){g.drawImage(ImageLoader.ikons[0][0],WIDTH-156,HEIGHT-42,null);}//32 i 10 zapasu
	else{g.drawImage(ImageLoader.ikons[0][1],WIDTH-156,HEIGHT-42,null);}
	g.drawImage(ImageLoader.ikons[0][4],WIDTH-118,HEIGHT-42,null);
	g.drawImage(ImageLoader.ikons[0][2],WIDTH-80,HEIGHT-42,null);
	g.drawImage(ImageLoader.ikons[0][3],WIDTH-42,HEIGHT-42,null);
	//next spheres
	if(pom3[0]>10){g.drawImage(ImageLoader.kulki[1][pom3[0]-10],562,265,40,40,null);}else {g.drawImage(ImageLoader.kulki[0][pom3[0]],562,265,40,40,null);}
	if(pom3[1]>10){g.drawImage(ImageLoader.kulki[1][pom3[1]-10],632,265,40,40,null);}else {g.drawImage(ImageLoader.kulki[0][pom3[1]],632,265,40,40,null);}
	if(pom3[2]>10){g.drawImage(ImageLoader.kulki[1][pom3[2]-10],702,265,40,40,null);}else {g.drawImage(ImageLoader.kulki[0][pom3[2]],702,265,40,40,null);}
	//last earned pts
	ImageLoader.drawString(g,bonus,40,HEIGHT-58);
	//animation of cleared sphares
	if(delanim){
		if(delx>=5){
		for(int i=0;i<delx;i++){
		if(animcount<5)                       {g.drawImage( ImageLoader.bi[0][0], animx+(40*i), animy ,40,40, null );}
		else if(animcount<10 && animcount>=5) {g.drawImage( ImageLoader.bi[0][1], animx+(40*i), animy ,40,40, null );}
		else if(animcount<15 && animcount>=10){g.drawImage( ImageLoader.bi[0][2], animx+(40*i), animy ,40,40, null );}
		else if(animcount<20 && animcount>=15){g.drawImage( ImageLoader.bi[0][3], animx+(40*i), animy ,40,40, null );}
		else if(animcount<25 && animcount>=20){g.drawImage( ImageLoader.bi[0][4], animx+(40*i), animy ,40,40, null );}
		else if(animcount<30 && animcount>=25){g.drawImage( ImageLoader.bi[0][5], animx+(40*i), animy ,40,40, null );}
		else if(animcount<35 && animcount>=30){g.drawImage( ImageLoader.bi[0][6], animx+(40*i), animy ,40,40, null );}
		}}
		if(dely>=5){
		for(int i=0;i<dely;i++){
		if(animcount<5)                  	  {g.drawImage( ImageLoader.bi[0][0], animx2, animy2+(40*i) ,40,40, null );}
		else if(animcount<10 && animcount>=5) {g.drawImage( ImageLoader.bi[0][1], animx2, animy2+(40*i) ,40,40, null );}
		else if(animcount<15 && animcount>=10){g.drawImage( ImageLoader.bi[0][2], animx2, animy2+(40*i) ,40,40, null );}
		else if(animcount<20 && animcount>=15){g.drawImage( ImageLoader.bi[0][3], animx2, animy2+(40*i) ,40,40, null );}
		else if(animcount<25 && animcount>=20){g.drawImage( ImageLoader.bi[0][4], animx2, animy2+(40*i) ,40,40, null );}
		else if(animcount<30 && animcount>=25){g.drawImage( ImageLoader.bi[0][5], animx2, animy2+(40*i) ,40,40, null );}
		else if(animcount<35 && animcount>=30){g.drawImage( ImageLoader.bi[0][6], animx2, animy2+(40*i) ,40,40, null );}
		}}
		}
	}
	
	public static boolean goNextT(){return nextturn;}
	
	public static void setMute(){if(mute){mute=false;AudioManager.resumeLoop("music1");}else{mute=true;AudioManager.stop("music1");}}
	
	public static void allowNextT(){nextturn=true;}
	
	public static boolean mouseBlocked(){return mauseB;}
	
	private String zeros(int num){
		if(num<10){return("0000"+num);}
		else if(num<100){return("000"+num);}
		else if(num<1000){return("00"+num);}
		else if(num<10000){return("0"+num);}
		else if(num<99999){return(""+num);}
		else if(num>=99999){return("99999");}
		else return "00000";
	}
	
	private void nextSpheres(){
		pom3[0]=r.nextInt(6)+1;
		pom3[1]=r.nextInt(6)+1;
		pom3[2]=r.nextInt(6)+1;
		specialcount++;if(specialcount==5){specialcount=0;pom3[0]+=10;pom3[1]+=10;pom3[2]+=10;}
	}
	
	public static void setSquere(int x, int y, int type){plansza[x][y]=type;}
	
	public static int returnSquere(int x, int y){
		if(plansza[x][y]<10) {return plansza[x][y];}
		else return 9;
	}
	private void gameOverCheck(){
		pomcount=0;
		for(int i=0;i<12;i++){
			for(int j=0;j<12;j++){
				if(plansza[i][j]==9){pomcount++;}
			}}
		if(pomcount<=3){fillLastSqueres();gameisover=true;nextturn=false;}
	}
	
	private void trueGameOver(){
		AudioManager.stop("music1");gm.setState(GameManager.GAMEOVER);
	}
	
	private void fillLastSqueres(){
		pomcount=0;
			for(int i=0;i<12;i++){
				for(int j=0;j<12;j++){
					if(plansza[i][j]==9){plansza[i][j]=pom3[pomcount];pomcount++;}
				}}
	}

	public void nextTurn(){//every 5 turns spheres with a shield are generated
		pomcount=0;
		if(!delanim){
		while(pomcount<3){
			pomx=r.nextInt(12);
			pomy=r.nextInt(12);
			if(plansza[pomx][pomy]==9){plansza[pomx][pomy]=pom3[pomcount];pomcount++;checkMatches(pomx,pomy);
			}} 	
		}
		else {delayednextturn=true;}
		
	}
	
	public static void checkMatches(int px,int py){
		matchx=matchy=1;int combo=0,firstrow,secrow;
		if(plansza[px][py]>10){secrow=plansza[px][py];firstrow=plansza[px][py]-10;}
		else {secrow=plansza[px][py]+10;firstrow=plansza[px][py];}
		
		for(int i=0;i<=8;i++){mx[i]=my[i]=false;}mx[4]=my[4]=true;
		if(px-1>=0)if(firstrow==plansza[px-1][py] || secrow==plansza[px-1][py] || 0==plansza[px-1][py]){matchx++;mx[3]=true;
		if(px-2>=0)if(firstrow==plansza[px-2][py] || secrow==plansza[px-2][py] || 0==plansza[px-2][py]){matchx++;mx[2]=true;
		if(px-3>=0)if(firstrow==plansza[px-3][py] || secrow==plansza[px-3][py] || 0==plansza[px-3][py]){matchx++;mx[1]=true;
		if(px-4>=0)if(firstrow==plansza[px-4][py] || secrow==plansza[px-4][py] || 0==plansza[px-4][py]){matchx++;mx[0]=true;
		}}}}//checks every step below only if match was found and if there is possibility for a next match
		if(px+1<=11)if(firstrow==plansza[px+1][py] || secrow==plansza[px+1][py] || 0==plansza[px+1][py]){matchx++;mx[5]=true;
		if(px+2<=11)if(firstrow==plansza[px+2][py] || secrow==plansza[px+2][py] || 0==plansza[px+2][py]){matchx++;mx[6]=true;
		if(px+3<=11)if(firstrow==plansza[px+3][py] || secrow==plansza[px+3][py] || 0==plansza[px+3][py]){matchx++;mx[7]=true;
		if(px+4<=11)if(firstrow==plansza[px+4][py] || secrow==plansza[px+4][py] || 0==plansza[px+4][py]){matchx++;mx[8]=true;
		}}}}
		if(py-1>=0)if(firstrow==plansza[px][py-1] || secrow==plansza[px][py-1] || 0==plansza[px][py-1]){matchy++;my[3]=true;
		if(py-2>=0)if(firstrow==plansza[px][py-2] || secrow==plansza[px][py-2] || 0==plansza[px][py-2]){matchy++;my[2]=true;
		if(py-3>=0)if(firstrow==plansza[px][py-3] || secrow==plansza[px][py-3] || 0==plansza[px][py-3]){matchy++;my[1]=true;
		if(py-4>=0)if(firstrow==plansza[px][py-4] || secrow==plansza[px][py-4] || 0==plansza[px][py-4]){matchy++;my[0]=true;
		}}}}
		if(py+1<=11)if(firstrow==plansza[px][py+1] || secrow==plansza[px][py+1] || 0==plansza[px][py+1]){matchy++;my[5]=true;
		if(py+2<=11)if(firstrow==plansza[px][py+2] || secrow==plansza[px][py+2] || 0==plansza[px][py+2]){matchy++;my[6]=true;
		if(py+3<=11)if(firstrow==plansza[px][py+3] || secrow==plansza[px][py+3] || 0==plansza[px][py+3]){matchy++;my[7]=true;
		if(py+4<=11)if(firstrow==plansza[px][py+4] || secrow==plansza[px][py+4] || 0==plansza[px][py+4]){matchy++;my[8]=true;
		}}}}
		
		if(matchy>=5){mauseB=true;
		for(int i=0;i<=8;i++){if(my[i]==true){plansza[px][py+i-4]=9;combo++;
			if(!begx){animx=25+((py+i-4)*40);animy=25+(px*40);begx=true;delanim=true;}
			else delx++;	
		}}}
		
		if(matchx>=5){mauseB=true;
		for(int i=0;i<=8;i++){if(mx[i]==true){plansza[px+i-4][py]=9;combo++;
		if(!begy){animx2=25+(py*40);animy2=25+((px+i-4)*40);begy=true;delanim=true;}
		else dely++;	
		}}}
		if(matchy>=5 || matchx>=5){AudioManager.play("match");bonusturn=true;recent++;calculateScore(combo,recent);}else {recent=0;bonus="";}
		
	}
	
	private static void calculateScore(int c,int x){
		x=(x-1)*10;//premium points
		switch(c){
		case  5: GameFrame.skor+=5+x;bonus="+"+(5+x)+" points";break;
		case  6: GameFrame.skor+=7+x;bonus="+"+(7+x)+" points";break;
		case  7: GameFrame.skor+=10+x;bonus="+"+(10+x)+" points";break;
		case  8: GameFrame.skor+=15+x;bonus="+"+(15+x)+" points";break;
		case  9: GameFrame.skor+=22+x;bonus="+"+(22+x)+" points";break;
		case 10: GameFrame.skor+=30+x;bonus="+"+(30+x)+" points";break;
		case 11: GameFrame.skor+=40+x;bonus="+"+(40+x)+" points";break;
		case 12: GameFrame.skor+=52+x;bonus="+"+(52+x)+" points";break;
		case 13: GameFrame.skor+=65+x;bonus="+"+(65+x)+" points";break;
		case 14: GameFrame.skor+=80+x;bonus="+"+(80+x)+" points";break;
		case 15: GameFrame.skor+=100+x;bonus="+"+(100+x)+" points";break;
		case 16: GameFrame.skor+=125+x;bonus="+"+(125+x)+" points";break;
		case 17: GameFrame.skor+=160+x;bonus="+"+(160+x)+" points";break;
		case 18: GameFrame.skor+=200+x;bonus="+"+(200+x)+" points";break;
		default: GameFrame.skor+=0;break;
		}
	}
	
	public static void loadBig(){
		AudioManager.load("/Music/bgmusic.mp3", "music1");
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) { AudioManager.stop("music1");gm.setPaused(true); }
		if(blockInput) return;
		if(Keys.isPressed(Keys.SPACE)) {nextTurn();nextTurn();nextTurn();nextTurn();nextTurn();}
	}

}
