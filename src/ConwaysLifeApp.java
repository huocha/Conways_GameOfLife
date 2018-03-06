import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class ConwaysLifeApp extends JFrame implements Runnable, MouseListener {
	private BufferStrategy strategy;
	private boolean gameState[][] = new boolean [40][40];
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
				gameState[i][k]= false;
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
		
		//toggle the state of the cell
		gameState[x][y] = !gameState[x][y];
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
		while (1==1) {
			try {
				Thread.sleep(20);
			}
			catch (InterruptedException e) {  
			}
//			if (isPlaying) {
//				this.repaint();
//			}
			this.repaint();
		}
	}
	public void paint(Graphics g) {
		g = strategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 800);
		
		
		g.setColor(Color.white);
		for (int x=0;x<40;x++) {
			for (int y=0;y<40;y++) {
				if (gameState[x][y] == true) {
					g.fillRect(x*20, y*20, 20, 20);
				}
			}
		}
		g.dispose();
		strategy.show();
	}
	public static void main (String [] arg) {
		ConwaysLifeApp g = new ConwaysLifeApp();
	}
}
