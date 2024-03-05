import java.util.LinkedList;


public class Snake
{
    private LinkedList<Coordinate> body;
    private int pixelJump; // the number of pixels the snake move everytime
    public static final int SEGMENT_SIZE = 10; // the snake size (Adjust the size as needed - shouldn't be changed only optional)
    private Direction currentDirection;
    public Snake(Coordinate initialPosition, int pixelJump)
    {
        body = new LinkedList<>();
        body.add(initialPosition);
        this.pixelJump = pixelJump;
        currentDirection = Direction.RIGHT;

    }

    public void move(Direction direction, boolean ateFood)
    {

        Coordinate head = body.getFirst();
        Coordinate newHead;

        // Determine the new head position based on the current direction
        switch (currentDirection)
        {
            case UP:
                newHead = new Coordinate(head.getX(), head.getY() - pixelJump);
                break;
            case DOWN:
                newHead = new Coordinate(head.getX(), head.getY() + pixelJump);
                break;
            case LEFT:
                newHead = new Coordinate(head.getX() - pixelJump, head.getY());
                break;
            case RIGHT:
                newHead = new Coordinate(head.getX() + pixelJump, head.getY());
                break;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }

        body.addFirst(newHead);
        if (!ateFood)
        {
            body.removeLast();

        }
    }

    public void setCurrentDirection(Direction newDirection)
    {


        if ((currentDirection == Direction.LEFT && newDirection != Direction.RIGHT) ||
                (currentDirection == Direction.RIGHT && newDirection != Direction.LEFT) ||
                (currentDirection == Direction.UP && newDirection != Direction.DOWN) ||
                (currentDirection == Direction.DOWN && newDirection != Direction.UP))
        {
            currentDirection = newDirection;
        }
    }

    public void grow()
    {
        // Get the tail of the snake
        Coordinate tail = body.getLast();

        // Determine the new body segment's position based on the tail's position
        int newX = tail.getX();
        int newY = tail.getY();

        // Add the new body segment at the determined position
        body.addLast(new Coordinate(newX, newY));
    }

    public boolean checkSelfCollision()
    {
        // Start from the second body part, as the head (first part) can't collide with itself
        Coordinate head = body.getFirst();
        for (int i = 1; i < body.size(); i++)
        {
            Coordinate bodyPart = body.get(i);
            if (head.getX() == bodyPart.getX() && head.getY() == bodyPart.getY())
            {
                return true;  // Collision with itself
            }
        }
        return false;
    }

    public LinkedList<Coordinate> getBody()
    {
        return body;
    }

}
