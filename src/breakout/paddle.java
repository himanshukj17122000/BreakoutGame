package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class paddle {
    public static final String paddle = "paddle.gif";
    private final static int MOVER_SIZE = 100;
    public static final int MOVER_HEIGHT = 10;
    private int MOVER_SPEED = 50;
    private static boolean extended = false;
    private static Rectangle myMover;
    private final static int SIZE=400;

    public paddle(){
        myMover = new Rectangle(SIZE / 2 - MOVER_SIZE / 2, SIZE - MOVER_HEIGHT - 20, MOVER_SIZE, MOVER_HEIGHT);
        Image ima = new Image(this.getClass().getClassLoader().getResourceAsStream(paddle));
        ImagePattern imagePattern = new ImagePattern(ima);
        myMover.setFill(imagePattern);
        extended=false;
    }
    public Rectangle mover(){
        return this.myMover;
    }
    public void setMOVER_SPEED(int speed){
        MOVER_SPEED=speed;
    }
    public void setLayout(int width, double positionX, Boolean value){
        myMover.setWidth(width);
        myMover.setX(positionX);
        extended=value;
    }

    public Boolean getExtended(){
        return extended;
    }
    public void setSpeed(int speed){
        MOVER_SPEED=speed;
    }
    public int getSpeed(){
        return this.MOVER_SPEED;
    }
    public int getMoverSize(){
        return this.MOVER_SIZE;
    }
}
