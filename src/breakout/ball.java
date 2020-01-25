package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ball {
    public static final String BOUNCER_IMAGE = "ball.gif";
    private int BOUNCER_SPEED = 70;
    private static double directionX = 1;
    private static double directionY = 1;
    private static ImageView myBouncer;
    public static final int SIZE = 400;

    public ball(){
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
        myBouncer = new ImageView(image);
        BOUNCER_SPEED=70;
    }

    public ImageView ballImage(){
        return myBouncer;
    }
    public void setLayout(double X, double Y){
        this.myBouncer.setX(X);
        this.myBouncer.setY(Y);
    }
    public void setSpeed(int speed){
        BOUNCER_SPEED=speed;
    }
    public int getSpeed(){
        return BOUNCER_SPEED;
    }
    public double directionX(){
        return directionX;
    }
    public double directionY(){
        return directionY;
    }
    public void setdirectionY(){
        directionY*=-1;
    }
    public void setdirectionX(){
        directionX*=-1;
    }
    public void setdirection(){
        directionX*=-1;
        directionY*=-1;
    }
    public void initialiseY(){
        directionY=1;
    }
    public void initialiseX(){
        directionX=1;
    }
    public void changeX(){
        directionX=-1;
    }




}
