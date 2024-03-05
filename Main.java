import javax.swing.*;
import java.util.Random;

public class Main
{
    private JFrame frame;
    private MyKeyListener keyListener;
    private Snake snake;
    private Direction currentDirection;
    private snakeFood food;
    private gameScreen gameScreen;  // Add a GameScreen instance


    public Main()
    {
        //startMenu = new StartMenu(this); //  (decided to remove it)

        Coordinate initialPosition = new Coordinate(100, 100);  // Adjust as needed
        Coordinate initialPositionFood = new Coordinate(50, 50);  // Adjust as needed
        int pixelJump = 10;  // should not be changed (to change the speed go to Thread.sleep - line 96)
        snake = new Snake(initialPosition, pixelJump);
        currentDirection = Direction.RIGHT;  // Set an initial direction
        food = new snakeFood(initialPositionFood);  // Assuming initial food position
        gameScreen = new gameScreen(snake, food);
        keyListener = new MyKeyListener(snake);


    }

    public void run()
    {

        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600); // Adjust the size as needed
        //frame.add(startMenu); // add start menu (decided to remove it)
        frame.setVisible(true);

        frame.add(gameScreen);  // Add the GameScreen to the frame
        frame.addKeyListener(keyListener);
        frame.setVisible(true);

        int maxX = gameScreen.getWidth() - snake.SEGMENT_SIZE + 5;  // Maximum X-coordinate considering snake's size
        int maxY = gameScreen.getHeight() + 20;  // Maximum Y-coordinate considering snake's size

        while(true)
        {
            // Check if the snake eats the food (collision between snake and food)
            boolean ateFood = checkCollisionWithFood();
            // Check if the snake collides with itself
            boolean collidedWithSelf = snake.checkSelfCollision();

            // Handle collision with self
            if (collidedWithSelf)
            {
                // Game over due to collision with self
                gameOver("Game Over! You collided with yourself.");

                return;
            }

            // Check if the snake collides with the walls
            if (snake.getBody().getFirst().getX() < 10 || snake.getBody().getFirst().getX() > maxX ||
                    snake.getBody().getFirst().getY() < 10 || snake.getBody().getFirst().getY() > maxY)
            {
                // Snake hit the wall, end the game
                gameOver("Snake hit the wall! Game Over.");
                return;  // Return to exit the game loop
            }


            snake.move(currentDirection, ateFood);

            // If the snake ate food, grow the snake
            if (ateFood)
            {
                snake.grow();

                generateRandomFood();  // Generate new food
                food.setEaten(true);  // Mark the current food as eaten

            }
            // Repaint the game screen to update the rendering
            gameScreen.repaint();

            try
            {
                Thread.sleep(100);  // Adjust the sleep time for desired speed(control the actual speed of the snake
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private boolean checkCollisionWithFood()
    {
        Coordinate head = snake.getBody().getFirst();
        Coordinate foodPosition = food.getPosition();

        // Check if the head of the snake is at the same position as the food
        if(head.getX() == foodPosition.getX() && head.getY() == foodPosition.getY())
        {
            return true;
        }
        return false;

    }

    private void generateRandomFood()
    {
        // Assuming your game board has certain dimensions (e.g., maxX and maxY)
        // Adjust these values based on your game's requirements
        int maxX = 600;  // Adjust as needed
        int maxY = 600;  // Adjust as needed

        // Create a random number generator
        Random random = new Random();
        // Generate random x and y coordinates within the game board boundaries
        // Adjust the range to ensure that the food is fully reachable by the snake
        // Generate random x and y coordinates within the game board boundaries
        int randomX = random.nextInt(10, maxX - snake.SEGMENT_SIZE);
        int randomY = random.nextInt(10 ,maxY - snake.SEGMENT_SIZE);

        // Adjust the coordinates to be multiples of SEGMENT_SIZE to align with the snake's movement
        randomX = (randomX / snake.SEGMENT_SIZE) * snake.SEGMENT_SIZE;
        randomY = (randomY / snake.SEGMENT_SIZE) * snake.SEGMENT_SIZE;

        // Set the old food as eaten

        food.setEaten(true);


        // Create a new coordinate for the food at the random position
        Coordinate foodPosition = new Coordinate(randomX, randomY);

        // Create a new food instance with the random position
        food.setPosition(foodPosition);
        food.setEaten(false);  // Set the new food as not eaten



    }

    private void gameOver(String message)
    {
        JOptionPane.showMessageDialog(null, message);
        System.exit(0);
    }

    public static void main(String[] args)
    {

        Main game = new Main();
        game.run();
    }
}