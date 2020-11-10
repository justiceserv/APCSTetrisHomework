package tetris;

public class GameThread extends Thread
{
    private GameArea ga;
    private GameForm gf; 
    
    private int level = 1;
    private static int score; 
    private int scorePerLevel = 2;
    private int pause = 1000; 
    private int speedPerLevel = 100; 
    
    public GameThread( GameArea ga, GameForm gf )
    {
        this.ga = ga;
        this.gf = gf; 
        level = 1; 
        score = 0; 
        gf.setScore(score);
        gf.setLevel(level);
    }
    @Override
    public void run()
    {
        while(ga.getWorking())
        {
            gf.initControls(); 
            while(ga.moveBlockDown())
            {
                try 
                {
                    Thread.sleep( pause );
                } catch (InterruptedException ex) {}
            }

            ga.moveBlockToBackground(); 
            score += ga.clearLines(); 
            
            gf.setScore(score);
            changeLevel();
            
            ga.spawnBlock();
        }
        if(!ga.getWorking())
        {
            gf.setScore(0); 
            gf.setLevel(1);
            this.stop(); 
        }
    }
    public static int getScore()
    {
        return score; 
    }
    private void changeLevel()
    {
        int newlevel = score / scorePerLevel + 1; 
        
        if(newlevel > level)
        {
            pause = pause <= 100 ? 100 : pause - speedPerLevel; 
            level = newlevel;
            gf.setLevel(level);
        }
    }
}
