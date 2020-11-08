
package tetris;

import blocks.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameArea extends JPanel
{
    private TetrisBlock block;

    private final int rows, cols, cellSize;
    
    private final Color[][] background;
    
    private boolean isWorking = true; 
    
    public GameArea( JPanel placeholder, int rows )
    {

        this.setBounds      ( placeholder.getBounds() );
        this.setBorder      ( placeholder.getBorder() );
        this.setBackground  ( placeholder.getBackground() );
        
        this.rows = rows;
        cellSize = this.getBounds().height / rows;
        cols = this.getBounds().width / cellSize;
        
        background = new Color[this.rows][cols];
        
        spawnBlock();
    }
    
    private TetrisBlock[] availableBlocks = {new JShape(), new IShape(), new LShape(), new OShape(), new SShape(), new TShape(), new ZShape()}; 
    
    public void spawnBlock()
    {
        block = availableBlocks[(int)(Math.random() * availableBlocks.length)];
        block.spawn(cols);
    }
    public int getCols()
    {
        return cols; 
    }
    public boolean getWorking()
    {
        return isWorking;
    }
    public void moveBlockToBackground()
    {
        int[][] shape = block.getShape();
        Color color = block.getColor();
        int y = block.getY();
        int x = block.getX();
        
        for(int r = 0; r < block.getHeight(); r++)
        {
            for (int c = 0; c < block.getWidth(); c++)
            {
                if (shape[r][c] == 1)
                {
                    try
                    {
                        background[ r + y ][ c + x ] = color;
                    }
                    catch(Exception e)
                    {
                        JFrame frame = new JFrame("InputDiag #1"); 
                        JOptionPane.showInputDialog(frame, "The game is OVER! Please input your username.");
                        isWorking = false; 
                        break;
                    }
                }
            }
        }
    }
    
    public boolean moveBlockDown()
    {
        /*if the block touches the bottom edge of the game area*/
            if ( block.getY() + block.getHeight() == rows ) 
            { 
                return false;
            }
        
        
        int[][] shape = block.getShape();
        
        //if the block touches another block
        for (int c = 0; c < block.getWidth(); c++)
        {
            for (int r = block.getHeight() - 1; r >= 0; r--)
            {
                if( shape[r][c] == 1 )
                {
                    int y = r + 1 + block.getY();
                    
                    if ( y < 0 ) break;
                    
                    if ( background[y][c + block.getX()] != null )
                    {
                        return false;
                    }
                    break;
                }

            }
        }
        
        block.moveDown();
        this.repaint();
        
        return true;
    }
    
    public void moveBlockRight()
    {
        /*if the block touches the right edge of the game area*/
        if ( block.getX() + block.getWidth() >= cols ) return;
        
        for (int r = 0; r < block.getHeight(); r++)
        {
            for (int c = block.getWidth() - 1; c >= 0; c--)
            {
                int y = r + block.getY();
                    
                if ( y < 0 ) break;
                    
                if ( background[y][c + block.getX() + 1] != null )
                {
                    return;
                }
            }
        }

        block.moveRight();
        this.repaint();
    }
    
    public void moveBlockLeft()
    {
        /*if the block touches the left edge of the game area*/
        if ( block.getX() <= 0 ) return;
        
        for (int r = 0; r < block.getHeight(); r++)
        {
            for (int c = 0; c < block.getWidth(); c++)
            {
                int y = r + block.getY();
                    
                if ( y < 0 ) break;
                
                if ( background[y][c + block.getX() - 1] != null )
                {
                    return;
                }
            }
        }
        
        block.moveLeft();
        this.repaint();
    }
    
    public void rotateBlock()
    {
//        block.rotate();
//        int y = block.getY();
//        int x = block.getX();
//        
//        if(block.getX() < 0) {block.unRotate();}
//        else if(block.getX() + block.getWidth() >= cols) {block.unRotate();}
//        else if(block.getY() + block.getHeight() == rows) {block.unRotate();}
//        else
//        {
//            for(int r = 0; r < block.getHeight(); r++)
//            {
//                for (int c = 0; c < block.getWidth(); c++)
//                {
//                    try
//                    {
//                        if (background[ r + y ][ c + x ] != null)
//                        {
//                            block.unRotate(); 
//                            this.repaint();
//                            break;
//                        }
//                    }
//                    catch (Exception e) {block.unRotate(); this.repaint(); break;}
//                }
//            }
//        }
        block.rotate();
        if(block.getX() < 0 || block.getX() + block.getWidth() > cols || block.getY() + block.getHeight() >= rows)
            block.unRotate();
        this.repaint();
    }
    
    public void dropBlock()
    {
        while ( moveBlockDown() ){}
    }
    
    public int clearLines()
    {
        int linesCleared = 0; 
        int flags = 0; 
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                if(background[i][j] != null)
                    flags++; 
            }
            if(flags == cols)
            {
                for(int j = i; j > 0; j--)
                {
                    for(int k = 0; k < cols; k++)
                    {
                        background[j][k] = background[j - 1][k]; 
                    }
                }
                for(int j = 0; j < cols; j++)
                {
                    background[0][j] = null; 
                }
                linesCleared++;
            }
            flags = 0;  
        }
        return linesCleared; 
    }
    
    private void drawBackground(Graphics g)
    {
        Color color;
        
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                color = background[r][c];
                if (color != null)
                {   
                    drawGridSquare(g, color, c, r);
                }
            }
        }
    }
    
    private void drawBlock(Graphics g)
    {
        int[][] shape = block.getShape();
        int blockX = block.getX();
        int blockY = block.getY();
        Color color = block.getColor();
        
        for (int x = 0; x < block.getWidth(); x++)
        {
            for (int y = 0; y < block.getHeight(); y++)
            {
                if( shape[y][x] == 1 )
                {
                    drawGridSquare(g, color, x + blockX, y + blockY);
                }
            }
        }
    }
    
    private void drawGridSquare(Graphics g, Color color, int x, int y)
    {
        g.setColor( color );
        g.fillRect( x * cellSize, y * cellSize, cellSize, cellSize);
        g.setColor(Color.black);
        g.drawRect( x * cellSize, y * cellSize, cellSize, cellSize);      
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        //call the original paintComponent method
        super.paintComponent(g);
        drawBackground(g);
        drawBlock(g);
        
        //g.fillRect(0, 0, 50, 50);
    }
}
