package tetris;

public class GameThread extends Thread
{
    private GameArea ga;
    private GameForm gf; 
    
    private int level = 1, score; 
    
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
            ga.spawnBlock();
        }
    }
}
