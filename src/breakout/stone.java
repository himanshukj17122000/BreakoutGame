package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** CODE WRITTEN BY: Himanshu Jain (@hkj4)
 * the stone bricks require more hits to be broken than the normal bricks
 */
public class stone extends Brick {
    public static final int strikes=4;
    public static final String brickstone = "brick3.gif";

    public stone(){
        super();
        Image brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(brickstone));
        ImageView blockImage = new ImageView(brickImage);
        this.setBrickImage(blockImage);
        this.setType(brickstone);
        this.setStrikes(strikes);
    }
}
