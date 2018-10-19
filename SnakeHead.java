import greenfoot.*;
import java.util.ArrayList;
public class SnakeHead extends Actor
{
    private boolean gameOver;
    private boolean firstMoveDone;
    private Food f = new Food();
    private int foodYCoord = setFoodYCoord();
    private int foodXCoord = setFoodXCoord();
    private ArrayList<SnakePart> body = new ArrayList<SnakePart>();
    private int[] directions = new int[10000];
    private int bodyCount=0;
    private Score1 s1 = new Score1();
    private Score10 s10 = new Score10();
    private Score100 s100 = new Score100();
    private Score1 h1 = new Score1();
    private Score10 h10 = new Score10();
    private Score100 h100 = new Score100();
    private static int highScore;
    private int musicCount=0;
    public void act() 
   {
     musicCount++;
    if(!firstMoveDone)
    {
       if(Greenfoot.isKeyDown("Left"))
       {
         setRotation(180);
         Greenfoot.playSound("Main.mp3");
         firstMoveDone=true;
       }
       if(Greenfoot.isKeyDown("Right"))
       {
         setRotation(0);
         Greenfoot.playSound("Main.mp3");
         firstMoveDone=true;
       }
       if(Greenfoot.isKeyDown("Down"))
       {
         setRotation(90);
         Greenfoot.playSound("Main.mp3");
         firstMoveDone=true;
       }
       if(Greenfoot.isKeyDown("Up"))
       {
         setRotation(270);
         Greenfoot.playSound("Main.mp3");
         firstMoveDone=true;
       }
    }
     if(firstMoveDone&&!gameOver)
     {
       if(musicCount==137500)
       {
         Greenfoot.playSound("Main.mp3");
         musicCount=0;
       }
       move(3);
       for(int i=0;i<body.size();i++)
       {
         body.get(i).move(3);
       }
       if(Greenfoot.isKeyDown("Left"))
       {
         if(getRotation()!=0)
         {
            setRotation(180);
         }
       }
       if(Greenfoot.isKeyDown("Right"))
       {
         if(getRotation()!=180)
         {
           setRotation(0);
         }
       }
       if(Greenfoot.isKeyDown("Down"))
       {
         if(getRotation()!=270)
         {
           setRotation(90);
         }
       }
       if(Greenfoot.isKeyDown("Up"))
       {
         if(getRotation()!=90)
         {
           setRotation(270);
         }
       }
       if(isTouching(Line.class)||isAtEdge())
       {
         gameOver=true;
         getWorld().addObject(new GameOver(),450,300);
         if(bodyCount>highScore)
         highScore=bodyCount;
         setHighScore(highScore);
       }
       for(int i=1;i<body.size();i++)
       {
         if(intersects(body.get(i)))
         {
           gameOver=true;
           getWorld().addObject(new GameOver(),450,300);
           if(bodyCount>highScore)
           highScore=bodyCount;
           setHighScore(highScore);
         }
            }
       if(intersects(f))
       {
         Greenfoot.playSound("Eat.wav");
         removeTouching(Food.class);
         foodYCoord =setFoodYCoord();
         foodXCoord = setFoodXCoord();
         getWorld().addObject(f,foodYCoord,foodXCoord);
         bodyCount++;
         setScore(bodyCount);
         body.add(new SnakePart());
         if(bodyCount==1)
         {
            if(getRotation()==0)
            {
              getWorld().addObject(body.get(bodyCount-1),this.getX()-15,this.getY());
              body.get(bodyCount-1).setRotation(getRotation());
            }
            if(getRotation()==90)
            {
              getWorld().addObject(body.get(bodyCount-1),this.getX(),this.getY()-15);
              body.get(bodyCount-1).setRotation(getRotation());
            }
            if(getRotation()==180)
            {
              getWorld().addObject(body.get(bodyCount-1),this.getX()+15,this.getY());
              body.get(bodyCount-1).setRotation(getRotation());
            }
            if(getRotation()==270)
            {
              getWorld().addObject(body.get(bodyCount-1),this.getX(),this.getY()+15);
              body.get(bodyCount-1).setRotation(getRotation()); 
            }
            }
         if(bodyCount>1)
         {
           if(body.get(bodyCount-2).getRotation()==0)
           {
             getWorld().addObject(body.get(bodyCount-1),body.get(bodyCount-2).getX()-15,body.get(bodyCount-2).getY());
             body.get(bodyCount-1).setRotation(body.get(bodyCount-2).getRotation());
           }
           if(body.get(bodyCount-2).getRotation()==90)
           {
             getWorld().addObject(body.get(bodyCount-1),body.get(bodyCount-2).getX(),body.get(bodyCount-2).getY()-15);
             body.get(bodyCount-1).setRotation(body.get(bodyCount-2).getRotation());
           }
           if(body.get(bodyCount-2).getRotation()==180)
           {
             getWorld().addObject(body.get(bodyCount-1),body.get(bodyCount-2).getX()+15,body.get(bodyCount-2).getY());
             body.get(bodyCount-1).setRotation(body.get(bodyCount-2).getRotation());
           }
           if(body.get(bodyCount-2).getRotation()==270)
           {
              getWorld().addObject(body.get(bodyCount-1),body.get(bodyCount-2).getX(),body.get(bodyCount-2).getY()+15);
              body.get(bodyCount-1).setRotation(body.get(bodyCount-2).getRotation());
           }
         }
       }
       for(int i=0;i<body.size();i++)
       {
         if(body.get(i).getTime()==5)
         {
           body.get(i).setRotation(directions[i]);
           body.get(i).stopTimer();
         }
       }     
       for(int i=0;i<body.size();i++)
       {
         if(i==0)
         {
           if(body.get(i).getRotation()!=this.getRotation())
           {
             if(body.get(i).getTime()==0) 
             {    
               directions[i]=getRotation();
               body.get(i).startTimer();
             }
           }
         }
         if(i>0)
         {
           if(body.get(i).getRotation()!=body.get(i-1).getRotation())
           {
             if(body.get(i).getTime()==0)
             {   
               directions[i]=body.get(i-1).getRotation();
               body.get(i).startTimer();
             }
           }
         }                     
        }
    }  
  }
  public void setScore(int count)
    {
        if(count<10)
        {
            s1.setImage(count+".JPG");
        }
        if(count>10&&count<100)
        {
            s1.setImage(count%10+".JPG");
            s10.setImage(count/10+".JPG");
        }
        if(count>100&&count<1000)
        {
            s1.setImage(count%10+".JPG");
            s10.setImage((count%100)/10+".JPG");
            s100.setImage(count/100+".JPG");
        }
    }
  public void setHighScore(int count)
    {
        if(count<10)
        {
            h1.setImage(count+".JPG");
        }
        if(count>10&&count<100)
        {
            h1.setImage(count%10+".JPG");
            h10.setImage(count/10+".JPG");
        }
        if(count>100&&count<1000)
        {
            h1.setImage(count%10+".JPG");
            h10.setImage((count%100)/10+".JPG");
            h100.setImage(count/100+".JPG");
        }
    }
  public Score1 getScore1()
    {
        return s1;
    }
  public Score10 getScore10()
    {
        return s10;
    }
  public Score100 getScore100()
    {
        return s100;
    }
  public Score1 getHighScore1()
    {
        return h1;
    }
  public Score10 getHighScore10()
    {
        return h10;
    }
  public Score100 getHighScore100()
    {
        return h100;
    }
  public int getHighScore()
    {
        return highScore;
    }
  public int getFoodYCoord()
    {
        return foodYCoord;
    }
  public int getFoodXCoord()
    {
        return foodXCoord;
    }
  public int setFoodYCoord()
    {
        int x =(int)(Math.random()*585);
        while(x<55)
         x =(int)(Math.random()*585);
        return x;
    }
  public int setFoodXCoord()
    {
        int x =(int)((Math.random()*535)+50);
        while(x<55)
         x =(int)((Math.random()*535)+50);
        return x;
    }
  public Food getFood()
    {
        return f;
    }
}


