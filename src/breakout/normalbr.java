package breakout;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class normalbr extends Brick {
    public static final int strikes=2;
    public static final String bricknorm = "brick1.gif";

    public normalbr(){
        super();
        Image brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(bricknorm));
        ImageView blockImage = new ImageView(brickImage);
        this.setBrickImage(blockImage);
        this.setType(bricknorm);
        this.setStrikes(strikes);
    }


}
