package TestExesize;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;
/**
 * ����� ���������� �� ������ �����
 * � �������� �� ������� ����� ������, ������������ ����� 
 * � �������������� ��������� ����� �� ��������
 * @author ����� ���������
 *
 */
public class Balls implements ActionListener
{	
	//�������
	private DancingFloor floor;		

	//��� ������ ������� ������������ �� ������
	private Ball[] balls;
	private Random random;
	// ��� ������ ������� ����� ���������� ����������� �������(����) ���������
	boolean flag=true;	
	private Font Font20TimesRomanBold   = new Font("TimesRoman", Font.BOLD, 20);
	//����������� �������� ������, ������� ����� ������������ �� �����
	private String[] dance={"R","E","P"};
	//������ �������� ������
	private String[] dances={"Rnb ", "Electrohouse ", "Pop "};
	
	//����� ������� � ������ ������ ������ 
	private String now;
	//����� � ��������, ���������� ������� �������� ������ 
	private int time=0;
	/**
	 * 
	 * @param DancingFloor
	 * @param num
	 * @param speed
	 */
	public Balls(DancingFloor floor, int num, int speed)
	{
		this.floor = floor;
		this.random = new Random();
		//100 � 100 �������������� ������
		Spawn(num, 100, 100, speed);	
	}	

	/**
	 * ����� � ������� ������������ ��������� ����� ��� ������������ � ����� ������
	 */
	public void update()
	{
		double X, Y, X0, Y0, R, distance;
		boolean lastFlag=false;
		for (int i=0; i<balls.length; i++)
		{	
			//�������� ����� �� �� ��������� ������ �����
			if(balls[i].getName().indexOf(now.charAt(0))!=-1)
			{
				flag=true;
			}
			else
				flag=false;
			
			
			for (int j=0; j<balls.length; j++)
			{
				//� ����� ����� ��� �� ����������
				if (i==j)
					continue;
				
				//�������� �� ������������ �� ��������� ����������;
				R=balls[i].getWidth()/2;
				X=balls[i].getX()+R;
				Y=balls[i].getY()+R;
				X0=balls[j].getX()+R;
				Y0=balls[j].getY()+R;
				distance = Math.sqrt(Math.pow(X - X0, 2) + Math.pow(Y - Y0, 2));
				// ���������� ��� ����� ����������� � ������ ������� �� ����� ��������� � ������� �����������
				if(distance<=2*R && flag)
				{									
					balls[i].setX(balls[i].getX()-balls[i].getMotionX()*balls[i].getSpeed());
					balls[i].setY(balls[i].getY()-balls[i].getMotionY()*balls[i].getSpeed());
					balls[j].setX(balls[j].getX()-balls[j].getMotionX()*balls[j].getSpeed());
					balls[j].setY(balls[j].getY()-balls[j].getMotionY()*balls[j].getSpeed());	  
				
				    balls[i].setMotionX(-balls[i].getMotionX());
				    balls[i].setMotionY(-balls[i].getMotionY());
				    balls[j].setMotionX(-balls[j].getMotionX());
				    balls[j].setMotionY(-balls[j].getMotionY());					
				}					
				
			}
			//���� ����� ��������� �� ������� ��� ����� ������� ��� ���������, ����� ��� ����
			if(flag)	
				balls[i].update(floor.width, floor.height+100);
			else
				balls[i].moveToDrink(i);
					
				
		}				
	}
	
	
/**
 * ����� �������� ��������� ���������, ����� ��� ������ ����������, ����������� � �������� �������� ����
 * @param num -���������� �������� 
 * @param width -������ �������(��� �������������� ��������������)
 * @param height - ������ �������(������������ ��������������)
 * @param speed	- �������� �����
 */
	public void Spawn(int num, int width, int height, int speed)
	{	
		//���������� ������� �������� �� ����� ������, ������� ����� ������(�������� �� 0 �� 3, ���� ������ �������)
		int amountOfDance;
		StringBuilder name;
		//������������ ������� ��� � �������
		Timer timer=new Timer(1000, this);
		//������������ ������ ��������� �������� �� �������
		now=dances[random.nextInt(3)];
		//������� ������ ���������� �����(��������) (������� 2 �������� 15)
		balls=new Ball[num];			
		//��������� ���, � ������� ����� ������� ����� ������� �����, ������� ������ �����		
		for (int i=0; i<balls.length; i++)
		{
			name=new StringBuilder("");
			amountOfDance = random.nextInt(4);
			if (amountOfDance==3)
			for (int j=0; j<amountOfDance; j++)
			{
				name.append(dance[j]);
				if (j==amountOfDance-1)
					continue;
				name.append(",");			
				
			}
			else if (amountOfDance==2)
			{
				amountOfDance = random.nextInt(2);
				name.append(dance[amountOfDance]+","+dance[amountOfDance+1]);		
			}
			else name.append(dance[random.nextInt(3)]);	
			
			// ������� ��� � ��������� ��������� ���������� ���������, ���(speed � ������� ����� ��� ������� � ����������� ��������)
			balls[i]=new Ball(width, height, speed, name.toString());	
			balls[i].setX(width / 2+i*width);
			balls[i].setY(floor.height / 2 - height / 2);						
			balls[i].setMotionY (-2 + random.nextInt(4));
			
			//��������� ������� ������ ����������� ��������
			if (balls[i].getMotionY() == 0)
			{
				balls[i].setMotionY(1);
			}

			if (random.nextBoolean())
			{
				balls[i].setMotionX(1);
			}
			else
			{
				balls[i].setMotionX(-1);
			}
		}
		
		timer.start();
		
	}
	
	/**
	 * ����� ���������� ������������ ���� � ������ ��� ���������
	 * @param g ������ ���������� �� ���������
	 */

	public void render(Graphics g)
	{
		
		g.setFont(Font20TimesRomanBold);
		for (Ball ball: balls)
		{	
		  g.setColor(new Color(255,255,120));
		  //��������� ����
		  g.fillOval((int)ball.getX(), (int)ball.getY(), ball.getWidth(), ball.getHeight());
		  g.setColor(Color.RED);
		  //��������� ������ �� ����.
		  g.drawString(ball.getName(), (int)ball.getX()+(int)ball.getHeight()/5, (int)ball.getY()+(int)ball.getHeight()/2);
		  
		}
		g.setColor(Color.BLACK);	
		//��������� ������ � ������� ������� � ��������
		g.drawString("Now playing "+ now+ Integer.toString((time)), floor.width / 2 - 75, 50);
	}
/**
 * 
 * @return ������ ������� ���������� �������� �������� �����
 */
	public String getNow()
	{	
		return now;
	}
/** 
 * @return ����� �� 0 �� 5 ������� ���������� ����� ������� ������ ������
 */
	public int getTime() 
	{
		return time;
	}

	
	/**
	 * ����� ������� ��������� �� �������, ������� ����� �������������� ��� � 1 ������� �������� ���� Timer
	 * @param arg0 �������
	 */
	public void actionPerformed(ActionEvent arg0)
	{
		if (floor.getStatus()==2)
		{
			time++;
			if(time==6)
			{
				time=0;
				now=dances[random.nextInt(3)];
				
			}
		}
		
	}

	
	

}
