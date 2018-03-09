import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class ConwaysLifeApp extends JFrame implements Runnable, MouseListener {
	private BufferStrategy strategy;
	private int gameStateFrontBuffer = 0;
	private boolean gameState[][][] = new boolean [40][40][2];
	private boolean isPlaying = false;
	private static final Dimension WindowSize = new Dimension(800,800);
	public ConwaysLifeApp() {
		this.setTitle("Conways Game of Life application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - WindowSize.width/2;
		int y = screensize.height/2 - WindowSize.height/2;
		setBounds(x,y, WindowSize.width, WindowSize.height);
		setVisible(true);
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		Thread t = new Thread(this);
		t.start();
		addMouseListener(this);
		
		//initialize the game state 
		for(int i = 0; i < 40; i++) {
			for(int k = 0; k < 40; k++) {
				for (int j = 0 ; j < 2; j++) {
				gameState[i][k][j]= false;
				}
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		//determine which cell is pressed
		int x = e.getX()/20;
		int y = e.getY()/20;
		
		if(e.getX() > 10 && e.getX() < 70 && e.getY() > 30 && e.getY() <60 ) {
			isPlaying =  !isPlaying;
		}
			
		
		//toggle the state of the cell
		gameState[x][y][0] = !gameState[x][y][0];
		this.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void run() {
		//isPlaying = false;
		while (1==1) {
			try {
				Thread.sleep(20);
			}
			catch (InterruptedException e) {  
			}
			if (isPlaying) {
				checkRules();
				this.repaint();
			}
			//this.repaint();
		}
	}
	public void paint(Graphics g) {
		g = strategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 800);
		
		// button Start
		g.setColor(Color.green);
		g.fillRect(10, 30, 60, 30);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif", Font.PLAIN, 18));
		g.drawString("Start", 20, 48);
		
		// button Random
		g.setColor(Color.green);
		g.fillRect(90, 30, 80, 30);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif", Font.PLAIN, 18));
		g.drawString("Random", 93, 48);
		
		g.setColor(Color.white);
		if (isPlaying) {
			for (int x=0;x<40;x++) {
				for (int y=0;y<40;y++) {
					if (gameState[x][y][gameStateFrontBuffer] == true) {
						g.fillRect(x*20, y*20, 20, 20);
					}
				}
			}
		}
		
		g.dispose();
		strategy.show();
	}
	
	private void checkRules() {
		//apply game rules to game state 'front buffer' , copy result to 'back buffer'
		int front = gameStateFrontBuffer;
		int back = (front+1)%2;
		for(int x = 0; x < 40; x ++) {
			for(int y = 0; y < 4; y++) {
				// count the live neighbours of cell[x][y][0]
				int liveNeighbours = 0;
				for(int xx = -1; xx <= 1; xx++) {
					for(int yy = -1; yy<=1; yy++) {
						if(xx!=0 || yy!=0) {
							int xxx = x+xx;
							if(xxx<0) xxx=39;
							else if  (xxx>39) xxx = 0;
							
							int yyy = y+yy;
							if(yyy<0) yyy=39;
							else if  (yyy>39) yyy =0;
							if (gameState[x][y][front]) liveNeighbours++; 
						}
					}
				}
				if(gameState[x][y][front]) {
					// any live cell with fewer than 2 live neighbours dies
					if(liveNeighbours<2) gameState[x][y][back] = false;
					else if (liveNeighbours<4)  gameState[x][y][back] = true;
					else  gameState[x][y][back] = false;
				}
				else {
					if(liveNeighbours==3)  gameState[x][y][back] = true;
					else  gameState[x][y][back] = false;
				}
			}
		}
		gameStateFrontBuffer = back;
	}
	public static void main (String [] arg) {
		ConwaysLifeApp g = new ConwaysLifeApp();
	}
}
