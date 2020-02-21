import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Ticker extends JPanel implements Runnable{

	private int myXStart = 20;
	private int myYStart = 50; 
	private String myMessage = "Hi There";
	private String myForeColor = "Green";
	private String myBackColor = "Yellow";
	private boolean myMoving = false;
	private Thread thread; 

	/**
	 * Create the panel.
	 */
	public Ticker() {
		thread = new Thread(this);
		thread.start();
	}

	public int getMyXStart() {
		return myXStart;
	}

	public void setMyXStart(int newXStart) {
		this.myXStart = myXStart;
		repaint();
	}

	public int getMyYStart() {
		return myYStart;
	}

	public void setMyYStart(int newYStart) {
		this.myYStart = newYStart;
		repaint();
	}

	public String getMyMessage() {
		return myMessage;
	}

	public void setMyMessage(String newMessage) {
		this.myMessage = newMessage;
		repaint();
	}

	public String getMyForeColor() {
		return myForeColor;
	}

	public void setMyForeColor(String newForeColor) {
		this.myForeColor = newForeColor;
		repaint();
	}

	public String getMyBackColor() {
		return myBackColor;
	}

	public void setMyBackColor(String newBackColor) {
		this.myBackColor = newBackColor;
		repaint();
	}

	public boolean getMyMoving() {
		return myMoving;
	}

	public void setMyMoving(boolean newMoving) {
		this.myMoving = newMoving;
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(myBackColor.equalsIgnoreCase("Green"))
		{
			this.setBackground(Color.green);
		}
		else if(myBackColor.equalsIgnoreCase("Blue"))
		{
			this.setBackground(Color.blue);
		}
		else if(myBackColor.equalsIgnoreCase("Yellow"))
		{
			this.setBackground(Color.yellow);
		}
		else if(myBackColor.equalsIgnoreCase("Red"))
		{
			this.setBackground(Color.red);
		}
		
		if(myForeColor.equalsIgnoreCase("Green"))
		{
			g.setColor(Color.green);
		}
		else if(myForeColor.equalsIgnoreCase("Blue"))
		{
			g.setColor(Color.blue);
		}
		else if(myForeColor.equalsIgnoreCase("Yellow"))
		{
			g.setColor(Color.yellow);
		}
		else if(myForeColor.equalsIgnoreCase("Red"))
		{
			g.setColor(Color.red);
		}
		
		g.drawString(myMessage, myXStart, myYStart);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			if(myMoving)
			{
				 myXStart = (myXStart + 10)%this.getWidth();
				 repaint();
			}
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException ex)
			{
				
			}
		}
	}

	
}
