package tetris;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer 
{
    private Clip clearClip, gameoverClip, themeClip; 
    private String clearClipPath = "sounds" + File.separator + "clear.wav"; 
    private String gameoverClipPath = "sounds" + File.separator + "gameover.wav"; 
    private String themeClipPath = "sounds" + File.separator + "TetrisThemeA.wav"; 
    
    public AudioPlayer()
    {
        
        try
        {
            clearClip = AudioSystem.getClip(); 
            gameoverClip = AudioSystem.getClip(); 
            themeClip = AudioSystem.getClip(); 
            
            clearClip.open(AudioSystem.getAudioInputStream(new File(clearClipPath).getAbsoluteFile()));
            gameoverClip.open(AudioSystem.getAudioInputStream(new File(gameoverClipPath).getAbsoluteFile()));
            themeClip.open(AudioSystem.getAudioInputStream(new File(themeClipPath).getAbsoluteFile()));
            
        } catch(Exception e) {}
        
    }
    public void playClear()
    {
        clearClip.setFramePosition(0); 
        clearClip.start();
        System.out.println("Playing Clear");
    }
    public void playGameOver()
    {
        gameoverClip.setFramePosition(0);
        gameoverClip.start(); 
        System.out.println("Playing GameOver");
    }
    public void playTheme()
    {
        themeClip.setFramePosition(0);
        themeClip.start();
    }
}
