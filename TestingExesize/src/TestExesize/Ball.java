package TestExesize;
import java.util.Random;
/**
 * ћ€чи которые будут отображатьс€ на фрейме
 * а также методы отвечающие за их перемещение 
 * @author ѕашка ѕоздн€ков
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
	 * @param width ширина Ёллипса 
	 * @param height высота Ёллипса
	 * @param speed скорость или шаг с котором будут мен€тьс€ координаты расположени€ Ёллипса
	 * @param name им€ шара, в котором перечисл€ютс€ названи€ танцев R-Rnb, E-Electrohouse,P-pop
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
	 * @return им€ шара, в котором перечисл€ютс€ названи€ танцев R-Rnb, E-Electrohouse,P-pop
	 */
	public String getName()
	{
		return name;
	}	
	
	/**
	 * 
	  ћетод устанавливает новое значение координат одного шара 
	 * @param borderX горизонтальна€ граница за которую нельз€ выйти эллипсу
	 * @param borderY вертикальна€ граница за которую нельз€ выйти эллипсу
	 */
	public void update(int borderX, int borderY)
	{
		//если не пили до этот то продолжаем танцевать, иначе выходим на танцпол
		if(this.y<=700 )
		{
			this.x+= this.motionX * speed;
			this.y+= this.motionY * speed;
			//проверка на столкновение с горизонтальными границами
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
			//проверка на столкновение с вертикальными границами
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
	 * направл€ем шар в низ
	 * @param index -индекс массива balls из которого мы направл€ем данный шар
	 */
	public void moveToDrink(int index)
	{
		//по уравнению пр€мой идет пить(вниз), уравнение строим из начальной и конечной координаты
		double k, b, y2, x2;
		
		y2=800;		
		x2=index*width;
		// если мы не достигли конечной точки то двигаемс€, иначе останавливаемс€(пьЄм)
		if (x2-this.x!=0 && this.y<=y2)
		{
			k=(y2-this.y)/(x2-this.x); 
			b=y2-k*x2;		
			//определ€ем монотонность функции пр€мой(первое условие возрастающа€)
			if(x2-this.x>0)
			{
				this.x+=speed;//уходим с танцпола 
				this.y=k*this.x+b;				
			}
			else
			{
				this.x-=speed;//уходим с танцпола 
				this.y=k*this.x+b;
			}		
		}
		else
		{
			//конечное место где танцор пьЄт
			this.x=x2;
			this.y=y2;
		}
			
				
	}
	
	/**
	 *  @param motionX принимает значение от -1 до 3(без 0) отвечает за направление движени€ по оси о’
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
	 *  @param motionY принимает значение от -1 до 3(без 0) отвечает за направление движени€ по оси оY
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
	 *  @param x отвечает за расположение эллипса по оси о’
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
	 *  @param y отвечает за расположение эллипса  по оси оY
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
	 * @return width- шиина пр€моугольника в который заключЄн Ёллипса
	 */
	public int getWidth()
	{
		return width;		
	}
	
	/**
	 * @return height- высота пр€моугольника в который заключЄн Ёллипса
	 */
	public int getHeight()
	{
		return height;		
	}

	
	/** 
	 * @return speed скорость шара
	 */
	public int getSpeed()
	{
		return speed;
	}
}
