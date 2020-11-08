package blocks;

import tetris.TetrisBlock;

public class IShape extends TetrisBlock
{
    public IShape()
    {
        super( new int[][]{ {1, 1, 1, 1} } );
    }
    @Override
    public void rotate()
    {
        super.rotate(); 
        if(getWidth() == 1)
        {
            moveUp(); 
            moveRight(); 
        }
        else 
        {
            moveDown(); 
            moveLeft(); 
        }
    }
    
    @Override
    public void unRotate()
    {
        super.unRotate();
        if(getWidth() == 1)
        {
            moveUp(); 
            moveRight(); 
        }
        else 
        {
            moveDown(); 
            moveLeft(); 
        }
    }
}
