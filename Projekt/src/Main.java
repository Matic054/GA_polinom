
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;

public class Main 
{
	static Panel p1,p2;
	static Random r = new Random();
	
	static int[][] cilj;
	static Polinom [] pop;
	
	static int vel;
	static int threads;
	static boolean paralelno = false;
	static boolean evolution = true;
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setBackground(Color.WHITE);
		frame.setSize(1000,800);
		
		p2 = new Panel();
		frame.add(p2);
		frame.pack();
		Poslusa po = new Poslusa();
		Miska m = new Miska();
		frame.addMouseMotionListener(po);
		frame.addMouseListener(m);
	}
	
	static void GA(int iter)
	{
		//PARAMETER POP-A MORA BITI VECKRATNI OD 4
		pop = pop(vel);
		Polinom best = pop[0];
		int cnt = 0;
		for (int i = 0; i < iter; i++)
		{
			sel(pop);
		//	get(pop);
			cros(pop);
			mut(pop);
			
	     	if (razlika(pop[0]) < razlika(best) - 100)
			{
				System.out.println(razlika(pop[0]));
				best = pop[0];
				if (evolution)
				{
					Panel.k = pop[0].k;
					Graphics g = p2.getGraphics();
					p2.paint(g);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				cnt++;
			}
		}
		System.out.println("Sprememb: "+cnt);
		Panel.k = pop[0].k;
	}
	
	//Fitnes funkcija
	static float razlika(Polinom p)
	{
		float raz = 0;
		for (int i = 0; i < cilj.length; i++)
			raz += Math.sqrt(Math.pow(
			(p.vrednost(((cilj[i][0]-500)*(float)(0.005)))*400 + cilj[i][1]-400),2)
			);
		return raz;
	}
	
	//Nakljucna populacija
	static Polinom [] pop(int vel)
	{
		Polinom [] rez = new Polinom[vel];
		float [] k;
		for (int i = 0; i < vel; i++)
		{
			k = new float [5];
			for (int j=0; j < k.length; j++)
			{
				if (r.nextBoolean())
					k[j]=r.nextFloat();
				 else 
					k[j]=-r.nextFloat();
			}
			rez[i]= new Polinom(k);
		}
		return rez;
	}
	
	//Pou najbolsih gre na prvih pol mest
	static void sel(Polinom[] pop)
	{
		Polinom[] rez = new Polinom[pop.length/2];
		float [] razlike = new float [pop.length/2];
		
		for (int i = 0; i < pop.length/2; i++)
			razlike[i] = razlika(pop[i]);
		
		for (int i = 0; i < pop.length/2; i++)
		{
			int ind = 0;
			for (int j = i; j < rez.length; j++)
			if (razlike[ind] > razlike[j])
					ind = j;
				
			razlike[ind] = Float.MAX_VALUE;
			rez[i]=pop[ind];
		}
		
		for (int i = 0; i < rez.length; i++)
			pop[i] = rez[i];
	}

	//Slabsi dveh postane njuno povprecje
	static void cros(Polinom[] pop)
	{
		for (int i = 0; i < pop.length/2; i++)
		{
			float [] k = new float [pop[i].k.length];
			Polinom povp = new Polinom(k);
			for (int j = 0; j < k.length; j++)
				k[j] = (pop[i].k[j]+pop[i+1].k[j])/((float)2);	
			pop[i+1] = povp;
		}
	}
	
	static void mut(Polinom[] pop)
	{
		//Slabso polovico nadomesti nakljucna populacija
		Polinom [] pop2 = pop(pop.length/2);
		for (int i = pop.length/2+1; i < pop.length; i++)
			pop[i] = pop2[i-pop.length/2-1];
		//Polovica primerkov mutira
		for (int i = 1; i < pop.length; i++)
			if(r.nextBoolean())
				for (int j = 0; j < pop[0].k.length; j++)
					if(r.nextBoolean())
						if(r.nextBoolean())
							pop[i].k[j] = r.nextFloat();
						else pop[i].k[j] = -r.nextFloat();
	}
	
	static void get(Polinom[] pop)
	{
		float[] k = pop[0].k.clone();
		pop = pop(pop.length);
		pop[0].k = k;
	}
	
}