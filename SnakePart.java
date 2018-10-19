import greenfoot.*;
import java.util.ArrayList;
public class SnakePart extends Actor
{
    boolean timerStarted;
    int time=0;
    public void act() 
    {
         if(timerStarted)
         {
             time++;
         }
    }    
    public void startTimer()
    {
        timerStarted=true;
    }
    public int getTime()
    {
        return time;
    }
    public void stopTimer()
    {
        timerStarted=false;
        time=0;
    }
}
