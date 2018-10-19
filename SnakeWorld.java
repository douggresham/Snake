import greenfoot.*;
public class SnakeWorld extends World
{
    SnakeHead sH = new SnakeHead();
    int foodYCoord=sH.getFoodYCoord();
    int foodXCoord=sH.getFoodXCoord();
    public SnakeWorld()
    {    
        super(900, 600, 1); 
        sH.setHighScore(sH.getHighScore());
        addObject(sH, 450,300);
        sH.setRotation(270);
        addObject(sH.getFood(),foodXCoord,foodYCoord);
        addObject(new Line(),434,45);
        addObject(new Logo(),60,13); 
        addObject(new ScoreImage(),752,15);
        addObject(new ScoreImage(),349,15);
        addObject(new HighScore(),250,15);
        addObject(sH.getScore100(),828,23);
        addObject(sH.getScore10(),851,23);
        addObject(sH.getScore1(),873,23);
        addObject(sH.getHighScore100(),418,23);
        addObject(sH.getHighScore10(),439,23);
        addObject(sH.getHighScore1(),460,23);
    }
}
