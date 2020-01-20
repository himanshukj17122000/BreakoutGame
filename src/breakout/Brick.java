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

    public int Strikes(){
        return this.strikes;
    }

    public int getWidth(){
        return this.WIDTH;
    }
    public int getHeight(){
        return this.HEIGHT;
    }
    public void ifhit(int damagedone){
       this.setStrikes(this.Strikes()-damagedone);
    }


   public void setType(String types){
       this.typename=types;
   }

   public String getType(){
        return this.typename;
   }


    public void setBrickImage(ImageView brickImage) {
        this.Image = brickImage;
        this.Image.setFitWidth(WIDTH);
        this.Image.setFitHeight(HEIGHT);
    }


    public void setStrikes(int hitsLeft){
        this.strikes = hitsLeft;
    }


    public void setX(double x){
        this.Image.setX(x);
    }


    public void setY(double y){
        this.Image.setY(y);
    }

    public ImageView getImage(){
        return this.Image;
    }
}

