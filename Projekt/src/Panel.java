import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class Panel extends JPanel
{
	int h = 800;
	int w = 1000;
	static float [] k;
	static int[][] cilj  = new int[0][0];
	static ArrayList<Integer> x = new ArrayList<>();
	static ArrayList<Integer> y = new ArrayList<>();
	static boolean poln = false;
	Random r = new Random();
	boolean gui;
	
	public Panel(boolean gui)
	{
		this.gui = gui;
		this.setPreferredSize(new Dimension(w,h));
		this.setFocusable(true);
		this.setBackground(Color.WHITE);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if (Main.porazdeljeno)
			g2d.drawString("OHHH, NO NO NO NO...", 500, 500);
		else
		if (!gui)
		{
			g2d.setStroke(new BasicStroke(5));
			g2d.drawLine(0, h/2, w, h/2);
			g2d.drawLine(w/2, 0, w/2, h);
			g2d.setStroke(new BasicStroke(3));
			if (poln)
			{
				Polinom p = new Polinom(k);
				g2d.setColor(Color.BLUE);
				this.narisiGraf(g2d, p);
				
			}	
			g2d.setColor(Color.red);
			for (int i = 1; i < cilj.length; i++)
			{
				if (cilj[i][1]!=0)
				{	
					g2d.drawLine(cilj[i-1][0], (int)(cilj[i-1][1]), cilj[i][0], (int)(cilj[i][1]));
				} else {
					break;
				}
			}
		} else {
			this.setBackground(new Color(164,219,232));
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font(Font.SANS_SERIF,Font.BOLD,100));
			g2d.drawString("GENETIC", 260, 100);
			g2d.drawString("ALGORITHM", 200, 200);
		}
	}
	
	public void narisiGraf(Graphics2D g2d,Polinom p) {
		int preX=-w/2;
		float preY=(float) -p.vrednost(preX*((float)0.005));
		for (int i = 0; i < w; i+=w/100)
		{
			g2d.drawLine(preX+w/2,(int) (preY*400)+h/2, preX+w/100+w/2,(int) -(p.vrednost((preX+w/100)*((float)0.005))*400)+h/2);
			preX+=w/100;
			preY=(float) -p.vrednost(preX*((float)0.005));
		}
	}
	
}
