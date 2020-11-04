package tetris;

public class GameThread extends Thread
{
    private GameArea ga;
    
    public GameThread( GameArea ga )
    {
        this.ga = ga;
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            ga.moveBlockDown();

            try 
            {
                Thread.sleep( 300 );
            } 
            catch (InterruptedException ex) {}
            
        }
    }
}
