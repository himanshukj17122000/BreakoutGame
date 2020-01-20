package breakout;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** CODE WRITTEN BY: Himanshu Jain (@hkj4)
 * the normal bricks are the most common bricks that I have
 * they require 2 hits to be broken
 */
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
