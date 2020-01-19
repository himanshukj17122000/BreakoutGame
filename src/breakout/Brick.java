package breakout;


import javafx.scene.image.ImageView;


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
    public void ifhit(){
       this.strikes--;
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


    public String destroyed(){

        return null;
    }


    public void setLocation(double x, double y){
        this.setX(x);
        this.setY(y);
    }


    public ImageView getImage(){
        return this.Image;
    }
}

