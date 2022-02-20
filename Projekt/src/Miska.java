import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Miska implements MouseListener {
	
	long t1;
	static int iter = 1000;
	boolean once = true;
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!Main.GUI)
		if (once)
		{
			Poslusa.dovoljeno = false;
			once = false;
			Panel.poln = true;
			Main.cilj = Panel.cilj;
			Main.threads = Runtime.getRuntime().availableProcessors();
			Main.vel = (int)Math.pow(2, 10);
			t1 = System.currentTimeMillis();
			if (Main.iterativno)
			{	
				Main.GA(iter);
			} else if (Main.paralelno) {
				System.out.println(Main.threads);
				Worker[] delavci = new Worker[Main.threads];
				for (int i = 0; i < delavci.length; i++)
				{
					delavci[i] = new Worker(Main.vel/delavci.length);
					delavci[i].start();
				}
				for (int i = 0; i < delavci.length; i++)
					try {
						delavci[i].join();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				Panel.k = delavci[0].pop[0].k;
				for (int i = 1; i < delavci.length; i++)
					if (Main.razlika(new Polinom(Panel.k)) > Main.razlika(delavci[i].pop[0]))
						Panel.k = delavci[i].pop[0].k;
			} else if (Main.porazdeljeno)
			{
				Main.p1 = null;
			}
			System.out.println("Cas: " + (System.currentTimeMillis() - t1));
			Main.p2.repaint();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
