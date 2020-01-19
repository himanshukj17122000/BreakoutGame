package breakout;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ball {
    private int BALL_SPEED;
    private ImageView Image;

    public void setImage(String ballimage){
        javafx.scene.image.Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(ballimage));
        Image = new ImageView(image);
    }
    public ImageView BallImage(){
        return this.Image;
    }
}
