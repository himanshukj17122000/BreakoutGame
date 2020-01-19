package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class powerup extends Brick {
    public static final int strikes=3;
    public static final String brickpower = "brick2.gif";
    public powerup(){
        super();
        Image brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(brickpower));
        ImageView blockImage = new ImageView(brickImage);
        this.setBrickImage(blockImage);
        this.setType(brickpower);
        this.setStrikes(strikes);
    }

}






