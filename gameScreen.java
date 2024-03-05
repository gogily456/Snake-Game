import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class gameScreen extends JComponent
{
    private Snake snake;
    private snakeFood food;

    public gameScreen(Snake snake, snakeFood food)
    {
        this.snake = snake;
        this.food = food;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Fill the component with a black background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Set the color for the walls
        g.setColor(Color.BLUE);
        // Draw the walls
        int wallThickness = Snake.SEGMENT_SIZE;  // Adjust the wall thickness to match the snake's size
        int topWallY = 0;
        int leftWallX = 0;
        int bottomWallY = Snake.SEGMENT_SIZE * 60 - wallThickness;
        int rightWallX = Snake.SEGMENT_SIZE * 60 - wallThickness;

        g.fillRect(leftWallX, topWallY, 600, wallThickness);  // Top wall
        g.fillRect(leftWallX, topWallY, wallThickness, 600);  // Left wall
        g.fillRect(leftWallX, bottomWallY, 600, wallThickness);  // Bottom wall
        g.fillRect(rightWallX, topWallY, wallThickness, 600);  // Right wall

        super.paintComponent(g);

        // Set the color for the snake's body
        g.setColor(Color.GREEN);

        // Iterate through the snake's body and render each segment
        LinkedList<Coordinate> body = snake.getBody();
        for (Coordinate segment : body)
        {
            int x = segment.getX();
            int y = segment.getY();

            // Render each segment as a rectangle
            g.fillRect(x, y, Snake.SEGMENT_SIZE, Snake.SEGMENT_SIZE);
        }

        // Render the food only if it exists
        if (food != null)
        {
            g.setColor(Color.RED);
            Coordinate foodPosition = food.getPosition();
            int foodX = foodPosition.getX();
            int foodY = foodPosition.getY();
            g.fillRect(foodX, foodY, food.FOOD_SIZE, food.FOOD_SIZE);

        }

    }
}

