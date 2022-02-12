import java.util.Random;

public class Worker extends Thread 
{
	Polinom[] pop;
	int iter;
	Random r;
	int[][] cilj;
	int vel;
	
	public Worker(int size)
	{
		vel = Main.vel;
		cilj = Panel.cilj;
		r = new Random();
		pop = Main.pop(size);
		iter = Miska.iter;
	}
	
	public void run() 
	{
		//GA
		for (int i = 0; i < iter; i++)
		{
			sel(pop);
			cros(pop);
			mut(pop);
		}
	}
	
    float razlika(Polinom p)
	{
		float raz = 0;
		for (int i = 0; i < cilj.length; i++)
		{
			raz += Math.sqrt(Math.pow(
			(p.vrednost(((cilj[i][0]-500)*(float)(0.005)))*400 + cilj[i][1]-400),2)
			);
		}
		return raz;
	}
    
    void sel(Polinom[] pop)
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
    
    void cros(Polinom[] pop)
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
    
    void mut(Polinom[] pop)
	{
		Polinom [] pop2 = Main.pop(pop.length/2);
		for (int i = pop.length/2+1; i < pop.length; i++)
			pop[i] = pop2[i-pop.length/2-1];
	
		for (int i = 1; i < pop.length; i++)
			if(r.nextBoolean())
				for (int j = 0; j < pop[0].k.length; j++)
					if(r.nextBoolean())
						if(r.nextBoolean())
							pop[i].k[j] = r.nextFloat();
						else pop[i].k[j] = -r.nextFloat();
	}

}

