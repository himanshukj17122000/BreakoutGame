package breakout;


import javafx.scene.image.ImageView;

/** CODE WRITTEN BY: Himanshu Jain (@hkj4)
 * Class of brick that has the functionalities that are common in all the different types of bricks
 * It has different methods to set up the different values of the bricks
 */

public class Brick{
    public static final int WIDTH = 40;
    public static final int HEIGHT = 20;
    private String typename;
    private int strikes;
    private ImageView Image;
    private boolean destroyed;

    /**
     * we get the number of strikes for the brick that are left
     * @return the strikes left for the brick
     */
    public int Strikes(){
        return this.strikes;
    }
    
    /**
     * we get the screen width of the game we are playing
     * @return the screen width
     */
    public int getWidth(){
        return this.WIDTH;
    }
    
     /**
     * we get the screen height of the game we are playing for working with it while placing the brick
     * @return the height of the screen that we are on
     */
    public int getHeight(){
        return this.HEIGHT;
    }
    
    /**
     * this is called when the ball hits the brick
     * @param damagedone is the number of strikes that have to be deducted from the brick's number of strikes when the ball collides with the brick
     */
    public void ifhit(int damagedone){
       this.setStrikes(this.Strikes()-damagedone);
    }

    /**
     * it sets the type of the brick that we are using in this method
     * @param types is the name of the brick that is being constructed using this method
     */
    public void setType(String types){
       this.typename=types;
   }

    /**
     * this returns the type of the brick that has just been made or destroyed which is useful for the powerup and the bomb bricks
     * @return the string which represents the type of brick just destroyed
     */
    public String getType(){
        return this.typename;
   }

    /**
     * sets the bricks for the brickwall
     * @param brickImage is the imageview of the type of the brick that is being constructed
     */
    public void setBrickImage(ImageView brickImage) {
        this.Image = brickImage;
        this.Image.setFitWidth(WIDTH);
        this.Image.setFitHeight(HEIGHT);
    }

     /**
     * it is used to set the number of hits each brick has
     * @param hitsLeft is the int value which is passed on and set as the number of hits that the brick can take
     */
    public void setStrikes(int hitsLeft){
        this.strikes = hitsLeft;
    }

    /**
     * returns the image value for working with it
     * @return the ImageView object that contains the brick that is being referred to
     */
    public ImageView getImage(){
        return this.Image;
    }
}

