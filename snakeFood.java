

public class snakeFood
{
    private Coordinate position;
    public static final int FOOD_SIZE = 10; // Adjust the size as needed (should be the same as the SEGMENT_SIZE in the snake class)
    private boolean eaten;  // Flag to track whether the food has been eaten
    public snakeFood(Coordinate position)
    {
        this.position = position;
        this.eaten = false;  // Initialize as not eaten
    }

    public Coordinate getPosition()
    {
        return position;
    }

    public void setPosition(Coordinate position)
    {
        this.position = position;
    }


    public void setEaten(boolean eaten)
    {
        this.eaten = eaten;

    }
}
