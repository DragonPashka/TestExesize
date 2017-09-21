package TestExesize;
import java.util.Random;
/**
 * ���� ������� ����� ������������ �� ������
 * � ����� ������ ���������� �� �� ����������� 
 * @author ����� ���������
 * 
 *
 */
public class Ball
{
	private int width, height;
	private double x,y;
	
	private int motionX, motionY, speed;
	private Random random;
	private String name=null;
	
	/** 
	 * @param width ������ ������� 
	 * @param height ������ �������
	 * @param speed �������� ��� ��� � ������� ����� �������� ���������� ������������ �������
	 * @param name ��� ����, � ������� ������������� �������� ������ R-Rnb, E-Electrohouse,P-pop
	 */
	Ball(int width, int height, int speed, String name)
	{
		this.width=width;
		this.height=height;
		this.speed=speed;
		this.name=name;
		random=new Random();
	}
	/**
	 * 
	 * @return ��� ����, � ������� ������������� �������� ������ R-Rnb, E-Electrohouse,P-pop
	 */
	public String getName()
	{
		return name;
	}	
	
	/**
	 * 
	  ����� ������������� ����� �������� ��������� ������ ���� 
	 * @param borderX �������������� ������� �� ������� ������ ����� �������
	 * @param borderY ������������ ������� �� ������� ������ ����� �������
	 */
	public void update(int borderX, int borderY)
	{
		//���� �� ���� �� ���� �� ���������� ���������, ����� ������� �� �������
		if(this.y<=700 )
		{
			this.x+= this.motionX * speed;
			this.y+= this.motionY * speed;
			//�������� �� ������������ � ��������������� ���������
			if (this.y + height > borderY || this.y < 100)
			{
				if (this.motionY < 0)
				{
					this.y = 100;
					this.motionY = random.nextInt(4);
	
					if (this.motionY == 0)
					{
						this.motionY = 1;
					}
				}
				else
				{
					this.motionY = -random.nextInt(4);
					this.y = borderY - height;
	
					if (this.motionY == 0)
					{
						this.motionY = -1;
					}
				}
			}		
			//�������� �� ������������ � ������������� ���������
			if (this.x + width > borderX || this.x  < 0)
			{
				if (this.motionX < 0)
				{
					this.x = 0;
					this.motionX = random.nextInt(4);
	
					if (this.motionX == 0)
					{
						this.motionX = 1;
					}
				}
				else
				{
					this.motionX = -random.nextInt(4);
					this.x = borderX - width;
	
					if (this.motionX == 0)
					{
						this.motionX = -1;
					}
				}		
			}
		}
		else this.y-=speed;
		
	}	
	
	/**
	 * ���������� ��� � ���
	 * @param index -������ ������� balls �� �������� �� ���������� ������ ���
	 */
	public void moveToDrink(int index)
	{
		//�� ��������� ������ ���� ����(����), ��������� ������ �� ��������� � �������� ����������
		double k, b, y2, x2;
		
		y2=800;		
		x2=index*width;
		// ���� �� �� �������� �������� ����� �� ���������, ����� ���������������(����)
		if (x2-this.x!=0 && this.y<=y2)
		{
			k=(y2-this.y)/(x2-this.x); 
			b=y2-k*x2;		
			//���������� ������������ ������� ������(������ ������� ������������)
			if(x2-this.x>0)
			{
				this.x+=speed;//������ � �������� 
				this.y=k*this.x+b;				
			}
			else
			{
				this.x-=speed;//������ � �������� 
				this.y=k*this.x+b;
			}		
		}
		else
		{
			//�������� ����� ��� ������ ����
			this.x=x2;
			this.y=y2;
		}
			
				
	}
	
	/**
	 *  @param motionX ��������� �������� �� -1 �� 3(��� 0) �������� �� ����������� �������� �� ��� ��
	 */
	public void setMotionX(int motionX)
	{
		this.motionX=motionX;		
	}
	
	public int getMotionX()
	{
		return motionX;		
	}
	
	
	/**
	 *  @param motionY ��������� �������� �� -1 �� 3(��� 0) �������� �� ����������� �������� �� ��� �Y
	 */	
	public void setMotionY(int motionY)
	{
		this.motionY=motionY;		
	}	
	public int getMotionY()
	{
		return motionY;		
	}
	
	
	/**
	 *  @param x �������� �� ������������ ������� �� ��� ��
	 */	
	public void setX(double x)
	{
		this.x=x;		
	}	
	public double getX()
	{
		return x;		
	}
	
	/**
	 *  @param y �������� �� ������������ �������  �� ��� �Y
	 */	
	public void setY(double y)
	{
		this.y=y;		
	}	
	public double getY()
	{
		return y;		
	}
	
		
	/**
	 * @return width- ����� �������������� � ������� �������� �������
	 */
	public int getWidth()
	{
		return width;		
	}
	
	/**
	 * @return height- ������ �������������� � ������� �������� �������
	 */
	public int getHeight()
	{
		return height;		
	}

	
	/** 
	 * @return speed �������� ����
	 */
	public int getSpeed()
	{
		return speed;
	}
}
