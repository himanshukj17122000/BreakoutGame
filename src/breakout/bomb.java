package breakout;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class bomb extends Brick {
    public static final int strikes=1;
    public static final String brickbomb = "brick4.gif";
    public bomb(){
        super();
        Image brickImage = new Image(this.getClass().getClassLoader().getResourceAsStream(brickbomb));
        ImageView blockImage = new ImageView(brickImage);
        this.setBrickImage(blockImage);
        this.setType(brickbomb);
        this.setStrikes(strikes);
    }

}


