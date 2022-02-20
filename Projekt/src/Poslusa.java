import java.awt.event.*;

public class Poslusa implements MouseMotionListener{

	static boolean dovoljeno = true;
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (!Main.GUI)
		if (dovoljeno)
		{
			Panel.x.add(e.getX());
			Panel.y.add(e.getY());
			Panel.cilj = new int[Panel.x.size()][2];
			for (int i = 0; i < Panel.cilj.length; i++)
			{
				Panel.cilj[i][0] = Panel.x.get(i);
				Panel.cilj[i][1] = Panel.y.get(i);
			}
			Main.p2.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
