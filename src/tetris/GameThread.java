package tetris;

public class GameThread extends Thread
{
    private GameArea ga;
    private GameForm gf; 
    
    private int level = 1, score; 
    private int scorePerLevel = 2;
    private int pause = 1000; 
    private int speedPerLevel = 100; 
    
    public GameThread( GameArea ga, GameForm gf )
    {
        this.ga = ga;
        this.gf = gf; 
        
        gf.setScore(score);
        gf.setLevel(level);
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            while(ga.moveBlockDown())
            {
                try 
                {
                    Thread.sleep( 300 );
                } catch (InterruptedException ex) {}
            }
            ga.moveBlockToBackground(); 
            score += ga.clearLines(); 
            
            gf.setScore(score);
            changeLevel();
            
            ga.spawnBlock();
        }
    }
    private void changeLevel()
    {
        int newlevel = score / scorePerLevel + 1; 
        
        if(newlevel > level)
        {
            pause -= speedPerLevel; 
            level = newlevel;
            gf.setLevel(level);
        }
    }
}