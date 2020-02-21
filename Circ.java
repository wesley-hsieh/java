import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Circ extends JPanel {

	private int myXStart = 20;
	private int myYStart = 20; 
	private int myWidth = 50;
	private int myHeight = 50;
	private String myForeColor = "Blue";
	private String myBackColor = "Green";
	private String myFill = "Yes";
	/**
	 * Create the panel.
	 */
	public Circ() {

	}
	public int getMyWidth() {
		return myWidth;
	}
	public void setMyWidth(int newWidth) {
		this.myWidth = newWidth;
		repaint();
	}
	
	public int getMyHeight()
	{
		return myHeight;
	}
	public void setMyHeight(int newHeight) {
		this.myHeight = newHeight;
		repaint();
	}

	public int getMyXStart()
	{
		return myXStart;
	}
	public void setMyXStart(int newX)
	{
		this.myXStart = newX;
		repaint();
	}
	public int getMyYStart() {
		return myYStart;
	}
	public void setMyYStart(int newY) {
		this.myYStart = newY;
		repaint();
	}
	public String getMyFill() {
		return myFill;
	}
	public void setMyFill(String newFill) {
		this.myFill = newFill;
		repaint();
	}
	public String getMyForeColor() {
		return myForeColor;
	}
	public void setMyForeColor(String newFColor) {
		this.myForeColor = newFColor;
		repaint();
	}
	public String getMyBackColor() {
		return myBackColor;
	}
	public void setMyBackColor(String newBColor) {
		this.myBackColor = newBColor;
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
		
		if(myFill.equalsIgnoreCase("Yes"))
		{
			g.fillOval(myXStart, myYStart, myWidth, myHeight);
		}
		else
		{
			g.drawOval(myXStart, myXStart, myWidth, myHeight);
		}
	}//paint

}
