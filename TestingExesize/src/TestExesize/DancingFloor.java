package TestExesize;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class DancingFloor implements ActionListener, KeyListener
{	
	public final int width = 1500, height = 700;
	//панель которая будет все отрисовывать
	private Renderer renderer;		
	private Balls balls;	
	//отвечает за состояние приложения 0-главное меню, 1-пауза 2-танцы
	private int status = 0; 
	private Random random;
	private JFrame jframe;	

	public DancingFloor()
	{
		Timer timer = new Timer(20, this);
		random = new Random();

		jframe = new JFrame("Night club");
		renderer = new Renderer(this);

		jframe.setSize(width, height + 300);
		jframe.setResizable(false);;
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(renderer);
		jframe.addKeyListener(this);

		timer.start();
	}

	public void start()
	{
		status = 2;		
		balls = new Balls(this, 2+random.nextInt(13), 3);
	}

	public void update()
	{
		balls.update();
	}
	
	public int getStatus()
	{		
		return status;
	}

	/**
	 * отрисовывает объекты в различных состояних
	 * @param g объект для отрисовки
	 */
	public void render(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 100, width, height);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (status == 0)
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", 1, 50));
			g.setFont(new Font("Arial", 1, 30));
			g.drawString("Press Space to Begin", width / 2 - 150, height / 2 - 25);
			
			
		}
		if (status == 1)
		{			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", 1, 50));
			g.drawString("PAUSED", width / 2 - 103, height / 2 +75);	
		}

		if (status == 1 || status == 2)
		{
			g.setColor(Color.WHITE);
			g.setStroke(new BasicStroke(5f));	
			g.setStroke(new BasicStroke(2f));
			g.drawOval(width / 2 - 150, height / 2 - 50, 300, 300);	
			balls.render(g);
		}

		
	}

	/**
	 * метод отрабатывающий событие которое происходит раз в 20 мс
	 * здесь происходит главная отрисовка
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (status == 2)
		{
			update();
		}

		renderer.repaint();
	}
	
	/**
	 * обрабатываем события для нажатия клавиш ESC и Space(пробел)
	 * при нажатии ESC во время танца вызовется начальное меню
	 * при нажатии space, во время танца остановит приложение
	 */
	public void keyPressed(KeyEvent e)
	{
		int id = e.getKeyCode();

		
		if (id == KeyEvent.VK_ESCAPE && (status == 2))
		{
			status = 0;
		}		
		else if (id == KeyEvent.VK_SPACE)
		{
			if (status == 0)
			{			

				start();
			}
			else if (status == 1)
			{
				status = 2;
			}
			else if (status == 2)
			{
				status = 1;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}


}
