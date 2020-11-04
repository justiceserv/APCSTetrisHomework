package tetris;

import java.awt.Color;

public class TetrisBlock 
{
    private int[][] shape;
    private int[][][] shapes = new int[4][][];
    private int currentRotation;
    private int x, y;
    private Color color;
    private Color[] availableColors = { Color.green, Color.blue, Color.cyan, Color.red, Color.yellow, Color.darkGray };
    private int cols; 
    public TetrisBlock( int[][] shape )
    {
        initShapes(shape);
    }
    public void spawn(int cols)
    {
        this.color = availableColors[(int)(Math.random() * availableColors.length)];
        this.shape = shapes[(int)(Math.random() * shapes.length)];
        
        x = (cols - getWidth()) / 2; 
        y = -getHeight();
    }
    private void initShapes( int[][] shape )
    {
        shapes[0] = shape;
        
        for (int i = 1; i < 4; i++)
        {
            int h = shape.length;
            int w = shape[0].length;
            
            shapes[i] = new int[w][h];
            
            for (int c = 0; c < w; c++)
            {
                for (int r = 0; r < h; r++)
                {
                    shapes[i][c][r] = shape[h - 1 - r][c];
                }
            }
            shape = shapes[i];
        }
    }
    
    public int[][] getShape(){ return shape; }
    public int getHeight(){ return shape.length; }
    public int getWidth(){  return shape[0].length; }
    
    public int getX(){ return x; }
    public int getY(){ return y; }
    
    public Color getColor() {  return color; }
    
    public void moveRight() { x++; }
    public void moveLeft()  { x--; }
    public void moveDown()  { y++; }
    public void moveUp() {y--;}
    public int[][] getNextRotation()
    {
        /*
        int newR;
        if(currentRotation <3)
        {
            newR = currentRotation +1; 
        }
        else 
            newR = 0; 
        return shapes[newR]; -> Same as undercode.
        */
        return shapes[currentRotation < 3 ? currentRotation + 1 : 0]; 
    }
    public void rotate()
    {
        if(currentRotation >= 3)
        {
            currentRotation = 0; 
        }
        else if(currentRotation < 3)
        {
            currentRotation++; 
        }
        shape = shapes[currentRotation];
    }
    public void unRotate()
    {
        if(currentRotation == 0)
        {
            currentRotation = 3; 
        }
        else if(currentRotation > 0)
        {
            currentRotation--; 
        }
        shape = shapes[currentRotation];
    }
}







