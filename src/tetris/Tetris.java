package tetris;

public class Tetris 
{
    public static MainForm mf; 
    public static GameForm gf; 
    public static LeaderboardForm lf; 
    public static GameThread gt; 
    
    public static void main(String[] args) 
    {
        mf = new MainForm(); 
        gf = new GameForm(); 
        lf = new LeaderboardForm(); 
        gt = new GameThread(gf, gf.ga);
        mf.setVisible(true);
    }
}
