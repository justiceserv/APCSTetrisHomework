package tetris;

public class Tetris 
{
    public static MainForm mf; 
    public static GameForm gf; 
    public static LeaderboardForm lf; 
    
    public static void main(String[] args) 
    {
        mf = new MainForm(); 
        lf = new LeaderboardForm(); 
        
        mf.setVisible(true);
    }
}
