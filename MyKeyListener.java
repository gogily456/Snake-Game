import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener
{
    private Snake snake;

    public MyKeyListener(Snake snake)
    {
        this.snake = snake;
    }

    // This method overrides a method from the KeyListener interface
    @Override
    public void keyTyped(KeyEvent e)
    {
        // This method is called when a key is typed (pressed and released).
    }

    // This method overrides a method from the KeyListener interface
    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        Direction newDirection = null;

        if (keyCode == KeyEvent.VK_UP)
        {
            newDirection = Direction.UP;
        } else if (keyCode == KeyEvent.VK_DOWN)
        {
            newDirection = Direction.DOWN;
        } else if (keyCode == KeyEvent.VK_LEFT)
        {
            newDirection = Direction.LEFT;
        } else if (keyCode == KeyEvent.VK_RIGHT)
        {
            newDirection = Direction.RIGHT;
        }

        if (newDirection != null)
        {
            snake.setCurrentDirection(newDirection);
        }
    }


    // This method overrides a method from the KeyListener interface
    @Override
    public void keyReleased(KeyEvent e)
    {
        // This method is called when a key is released.

    }

}



