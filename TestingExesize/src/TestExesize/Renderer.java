package TestExesize;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * панель которая все отрисовывает
 * @author Пашка
 *
 */
public class Renderer extends JPanel
{
	private DancingFloor floor;

	Renderer(DancingFloor floor)
	{
		this.floor=floor;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		floor.render((Graphics2D) g);
	}

}
