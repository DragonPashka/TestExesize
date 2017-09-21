package TestExesize;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;
/**
 * Класс отвечающий за массив шаров
 * и отвечает за события смены музыки, столкновения шаров 
 * и первоначальное появления шаров на танцполе
 * @author Пашка Поздняков
 *
 */
public class Balls implements ActionListener
{	
	//Танцпол
	private DancingFloor floor;		

	//Все шарики уоторые отображаются на экране
	private Ball[] balls;
	private Random random;
	// при помощи данного флага определяем способность танцора(шара) танцевать
	boolean flag=true;	
	private Font Font20TimesRomanBold   = new Font("TimesRoman", Font.BOLD, 20);
	//Сокращенные названия танцев, которые будут отображаться на шарах
	private String[] dance={"R","E","P"};
	//полные названия танцев
	private String[] dances={"Rnb ", "Electrohouse ", "Pop "};
	
	//Таней который в данный момент играет 
	private String now;
	//время в секундах, показываеь сколько отыграла музыка 
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
		//100 и 100 полуокружности элипса
		Spawn(num, 100, 100, speed);	
	}	

	/**
	 * Метод в котором определяется поведение шаров при столкновении и смене музыки
	 */
	public void update()
	{
		double X, Y, X0, Y0, R, distance;
		boolean lastFlag=false;
		for (int i=0; i<balls.length; i++)
		{	
			//проверка умеем ли мы танцевать данный танец
			if(balls[i].getName().indexOf(now.charAt(0))!=-1)
			{
				flag=true;
			}
			else
				flag=false;
			
			
			for (int j=0; j<balls.length; j++)
			{
				//с самим собой шар не сравниваем
				if (i==j)
					continue;
				
				//проверка на столкновение из уравнения окружности;
				R=balls[i].getWidth()/2;
				X=balls[i].getX()+R;
				Y=balls[i].getY()+R;
				X0=balls[j].getX()+R;
				Y0=balls[j].getY()+R;
				distance = Math.sqrt(Math.pow(X - X0, 2) + Math.pow(Y - Y0, 2));
				// определяем что будет происходить с шарами которые не умеют танцевать и которые столкнулись
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
			//Если умеем танцевать то танцуем или после выпивки идём танцевать, иначе идём пить
			if(flag)	
				balls[i].update(floor.width, floor.height+100);
			else
				balls[i].moveToDrink(i);
					
				
		}				
	}
	
	
/**
 * Метод задающий начальные параметры, такие как навыки участников, направление и скорость движения шара
 * @param num -количество эллипсов 
 * @param width -ширина эллипса(или горизонтальная полуокружность)
 * @param height - высота эллипса(вертикальная полуокружность)
 * @param speed	- скорость шаров
 */
	public void Spawn(int num, int width, int height, int speed)
	{	
		//переменная которая отвечает за число танцев, которые знает танцор(значения от 0 до 3, ноль вообще незнает)
		int amountOfDance;
		StringBuilder name;
		//Обрабытывает событие раз в секунду
		Timer timer=new Timer(1000, this);
		//присваивание строке случайное значение из массива
		now=dances[random.nextInt(3)];
		//создаем нужное количество шаров(танцоров) (минимум 2 максимум 15)
		balls=new Ball[num];			
		//формируем имя, в котором через запятую будут указаны танцы, которые танцор знает		
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
			
			// создаем шар и указывает начальные координаты появления, шаг(speed с которым будут они менятся и направление движения)
			balls[i]=new Ball(width, height, speed, name.toString());	
			balls[i].setX(width / 2+i*width);
			balls[i].setY(floor.height / 2 - height / 2);						
			balls[i].setMotionY (-2 + random.nextInt(4));
			
			//Следующие условия ставят направление движения
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
	 * метод помогающий отрисовывать шары и строку над танцполом
	 * @param g объект отвечающий за отрисовку
	 */

	public void render(Graphics g)
	{
		
		g.setFont(Font20TimesRomanBold);
		for (Ball ball: balls)
		{	
		  g.setColor(new Color(255,255,120));
		  //отрисовка шара
		  g.fillOval((int)ball.getX(), (int)ball.getY(), ball.getWidth(), ball.getHeight());
		  g.setColor(Color.RED);
		  //Отрисовка имения на шаре.
		  g.drawString(ball.getName(), (int)ball.getX()+(int)ball.getHeight()/5, (int)ball.getY()+(int)ball.getHeight()/2);
		  
		}
		g.setColor(Color.BLACK);	
		//отрисовка строки с текущей музыкой и временем
		g.drawString("Now playing "+ now+ Integer.toString((time)), floor.width / 2 - 75, 50);
	}
/**
 * 
 * @return строка которая показывает название текущего танца
 */
	public String getNow()
	{	
		return now;
	}
/** 
 * @return число от 0 до 5 которое отображает время которое играла музыка
 */
	public int getTime() 
	{
		return time;
	}

	
	/**
	 * метод который реагирует на событие, событие будет генерироваться раз в 1 секунду объектом типа Timer
	 * @param arg0 событие
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
