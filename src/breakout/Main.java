package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Feel free to completely change this code or delete it entirely.
 * implements EventHandler<javafx.event.ActionEvent>
 */
public class Main extends Application {
    /**
     * Start of the program.
     * /**
     * Initialize what will be displayed and how it will be updated.
     */

    public static final String TITLE = "Example JavaFX";
    public static final int SIZE = 400;
    private static Timeline animation = new Timeline();
    public static String filename = "";
    public static int count = 1;
    Brick first= new Brick();
    Brick second= new Brick();
    Brick third= new Brick();
    Brick fourth= new Brick();
    Brick bomb1= new Brick();
    Brick bomb2= new Brick();
    public static final int FRAMES_PER_SECOND = 120;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.BLACK;
    public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    public static final String BOUNCER_IMAGE = "ball.gif";
    public static int BOUNCER_SPEED = 50;
    public static int SLOW_SPEED = 50;
    public static final String paddle = "paddle.gif";
    public static final Paint MOVER_COLOR = Color.BLACK;
    public static int MOVER_SIZE = 100;
    public static final int long_paddle = 140;
    public static final int MOVER_HEIGHT = 10;
    private int MOVER_SPEED = 35;
    public static final Paint GROWER_COLOR = Color.BISQUE;
    public static final double GROWER_RATE = 1.1;
    public static final int GROWER_SIZE = 50;
    public static int score = 0;
    public static int distance = 0;
    public static boolean extended = false;
    double directionX = 1;
    double directionY = 1;
    public static int scoregame = 0;
    public static double time;
    public ArrayList<Brick> brickw = new ArrayList<Brick>();
    private Label label2 = new Label();
    private Scene myScene;
    private ImageView myBouncer;
    private Rectangle myMover;


    private Boolean move = false;
    private static int lives=-1;
    Button splas;

    Stage stagee = new Stage();

    @Override
    public void start(Stage stage) {
        // attach scene to the stage and display it
        makeScene("Level1.txt");
        stagee = stage;
        stage.setScene(Splash());
        stage.setTitle(TITLE);
        stage.show();

    }

    public void makeScene(String levels) {
        if (count > 3) {
            Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("end.gif"));
            ImageView imageView = new ImageView(image);
            imageView.setX(0);
            imageView.setY(0);
            imageView.setFitHeight(400);
            imageView.setFitWidth(400);
            imageView.setPreserveRatio(true);
            Group root = new Group(imageView);
            Scene scene = new Scene(root, 400, 400,BACKGROUND);
            stagee.setScene(scene);
            animation.stop();
        } else {
            myScene = setupGame(SIZE, SIZE, BACKGROUND, levels);
//            myBouncer.setX(SIZE / 2-MOVER_SIZE/2+MOVER_SIZE/2);
//            myBouncer.setY(SIZE-MOVER_HEIGHT-10-MOVER_HEIGHT);
            checkclick(stagee);
        }
    }

    public Scene Splash() {
        Label label1 = new Label("The rules of the game are:\n\t 1) Arrow Keys- Move the paddle \n\t 2) 4 Types of Bricks-\n\t\t" +
                " a) Power-up- Has different power-ups like extra-life, " +
                "\n\t\t double damaging ball and slower ball\n\t\t b) Stone- Takes 4 hits to be broken" +
                " \n\t\t c) Normal- Takes 3 hits to be broken \n\t\t d) Bomb- Takes 1 hit and damages the neighbouring\n\t\t blocks " +
                "\n\t 3) There are a number of Cheat Keys- C for catching the\n\t ball, D gives an extra life, F restarts the game, W\n\t increases the speed, Space increases the paddle's length" +
                "\n\t 4) The paddle comes up by 1/2 when only 10 bricks and\n\t another 1/4 when only 4 bricks are left ");
        label1.setTextFill(Color.web("#0076a3"));
        label1.setLayoutX(10);
        label1.setLayoutY(10);
        splas = new Button("Welcome");
        splas.setLayoutX(SIZE / 2.5);
        splas.setLayoutY(SIZE / 1.6);
        splas.setOnAction(e -> checkclick(stagee));
        Group gro = new Group();
        gro.getChildren().add(label1);
        gro.getChildren().add(splas);
        Scene Splash = new Scene(gro, SIZE, SIZE, BACKGROUND);
        return Splash;
    }
    private void checkclick(Stage stage) {
        stage.setScene(myScene);
        game();
    }

    private void game() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    private Scene setupGame(int width, int height, Paint background, String level) {
        // create one top level collection to organize the things in the scene

        // make some shapes and set their properties
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
        myBouncer = new ImageView(image);
         Group root = new Group();
        myMover = new Rectangle(width / 2 - MOVER_SIZE / 2, height - MOVER_HEIGHT - 10, MOVER_SIZE, MOVER_HEIGHT);
        Image ima = new Image(this.getClass().getClassLoader().getResourceAsStream(paddle));
        ImagePattern imagePattern = new ImagePattern(ima);
        myMover.setFill(imagePattern);
        this.myBouncer.setX((double) (width / 2) - this.myBouncer.getBoundsInLocal().getWidth() / 2.0D);
        this.myBouncer.setY((double) (height / 2) - this.myBouncer.getBoundsInLocal().getHeight() / 2.0D);
//      myBouncer.setX(width / 2-MOVER_SIZE/2+MOVER_SIZE/2);
//      myBouncer.setY(height-MOVER_HEIGHT-10-MOVER_HEIGHT);
        label2.setLayoutY(SIZE / 1.2);
        label2.setLayoutX(SIZE - 80);
//      move=false;
//      myGrower = new Rectangle(width / 2 - GROWER_SIZE / 2, height / 2 + 50, GROWER_SIZE, GROWER_SIZE);
//      myGrower.setFill(GROWER_COLOR);
        // order added to the group is the order in which they are drawn
        root.getChildren().add(myBouncer);
        root.getChildren().add(myMover);
        root.getChildren().add(label2);
//      root.getChildren().add(myGrower);
        brickwall(root, level);
//        bombbricks();
        // create a place to see the shapes
        Scene scene = new Scene(root, width, height, background);
        // respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    // Change properties of shapes in small ways to animate them over time
    // Note, there are more sophisticated ways to animate shapes, but these simple ways work fine to start
    private void step(double elapsedTime) {
//        if(move==true) {

        myBouncer.setX(myBouncer.getX() + BOUNCER_SPEED * elapsedTime * directionX);
        myBouncer.setY(myBouncer.getY() + BOUNCER_SPEED * elapsedTime * directionY);
        if((myBouncer.getY()>stagee.getHeight() || myBouncer.getY()<-20)&& lives<0){
            count=4;
            makeScene("End");}
//        } else if((myBouncer.getY()>stagee.getHeight() || myBouncer.getY()<-20 && lives>=0)){
//           lives--;
//           return;
//        }
        //}
        if((myBouncer.getY()>stagee.getHeight() || myBouncer.getY()<-20) && lives>=0){
            lives--;
            changeScene();
        }
        if (brickw.size() == 0) {
            count++;
            changeScene();
        }
        if (brickw.size() <= 10 && brickw.size()>4) {
            myMover.setY(SIZE / 1.7);
            if (myMover.getBoundsInParent().intersects(myBouncer.getBoundsInParent())) {
                directionY *= -1;
                directionX*=-1;
            }}
//            if (myBouncer.getY() + myBouncer.getBoundsInLocal().getHeight() >= myScene.getHeight()) {
//                directionY *= -1;
//            }

         if (brickw.size() <= 4) {
            myMover.setY(0);
            if (myMover.getBoundsInParent().intersects(myBouncer.getBoundsInParent())) {
                directionY *= -1;
                directionX*=-1;
            }if (myBouncer.getY() <= 0) {
                directionY= 1;
            }
            if (myBouncer.getY() + myBouncer.getBoundsInLocal().getHeight() >= myScene.getHeight()) {
                directionY *= -1;
            }}
//        } Brick first= new Brick(); Brick second= new Brick();Brick third= new Brick();Brick fourth= new Brick();Brick fifth= new Brick();
//        Brick thirteen= new Brick();Brick fourteen= new Brick();Brick fifteen= new Brick();Brick sixteen= new Brick();Brick seventeen= new Brick();
        //, , , , , , , , , ;
//      if(count==3){
//         first= brickw.get(0);
//         System.out.print(first.Strikes());
//         second= brickw.get(1); System.out.print(first.Strikes());
//         third= brickw.get(2);System.out.print(first.Strikes());
//       fourth= brickw.get(3);System.out.print(first.Strikes());
//         fifth= brickw.get(4);System.out.print(first.Strikes());
//         thirteen= brickw.get(13);System.out.print(first.Strikes());
//         fourteen= brickw.get(14);System.out.print(first.Strikes());
//       fifteen= brickw.get(15);System.out.print(first.Strikes());
//       sixteen= brickw.get(16);System.out.print(first.Strikes());
////         seventeen= brickw.get(17);System.out.print(first.Strikes());}
//        for(Brick c:brickw){
//            System.out.print(c.Strikes());
//        }
        for (int i = 0; i < brickw.size(); i++) {
         // if(myBouncer.getBoundsInLocal().intersects(brickw.get(i).getImage().getBoundsInLocal())){
//            System.out.print(brickw.get(i).Strikes());

           if ((myBouncer.getX() >= brickw.get(i).getImage().getX() && myBouncer.getX() <= brickw.get(i).getImage().getX() + brickw.get(i).getWidth()-2) && (myBouncer.getY() >= brickw.get(i).getImage().getY() && myBouncer.getY() <= brickw.get(i).getImage().getY() + brickw.get(i).getHeight())) {
//               System.out.println(brickw.get(i).getType());
               directionY *= -1;
               directionX*=-1;
                brickw.get(i).ifhit();
//                System.out.print(brickw.get(i).getType());
                if(brickw.get(i).getType().equals("brick2.gif") && brickw.get(i).Strikes()==0){
                    lives++;
                }
//               if(brickw.get(i)==bomb1 && brickw.get(i).Strikes()==0) {
//
//                  if(inBounds(first)){
//                      first.getImage().imageProperty().set(null);
//                      brickw.remove(first);
//                      score+=10;
//                  }
//                   if(inBounds(second)){
//                       second.getImage().imageProperty().set(null);
//                       brickw.remove(second);
//                       score+=10;
//                   }}
//               if(brickw.get(i)==bomb2 && brickw.get(i).Strikes()==0){
//                   if(inBounds(third)){
//                       third.getImage().imageProperty().set(null);
//                       brickw.remove(third);
//                       score+=10;
//                   }
//                   if(inBounds(fourth)){
//                       fourth.getImage().imageProperty().set(null);
//                       brickw.remove(fourth);
//                       score+=10;
//                   }
//               }
                if (brickw.get(i).Strikes() == 0) {
                    System.out.println(brickw.get(i).getType());
                    brickw.get(i).getImage().imageProperty().set(null);
                    brickw.remove(brickw.get(i));
                    String a = "";
                    scoregame += 5;
                    a += scoregame;
                    label2.setText("The score \nis " + a);
                    label2.setTextFill(Color.web("#0076a3"));
                    return;
                }


//              System.out.print(brickw.get(i).Strikes());
                directionY *= -1;
            } }
        if (myBouncer.getX() + myBouncer.getBoundsInLocal().getWidth() >= myScene.getWidth()) {
            directionX *= -1;
        }
        if (myBouncer.getX() <= 0) {
            directionX *= -1;
        }

        if (myBouncer.getY() <= 0) {
            directionY *= -1;
        }
        if (myMover.getBoundsInParent().intersects(myBouncer.getBoundsInParent()) && (myBouncer.getY() + myBouncer.getBoundsInLocal().getHeight() - 3 > myMover.getY())) {
            directionX *= -1;
            directionY *= -1;
        }
        if (myMover.getBoundsInParent().intersects(myBouncer.getBoundsInParent())) {
            directionY *= -1;
        }
    }

    private boolean inBounds(Brick brick) {
        if(brick!=null){
            return true;
        }
        return false;
    }

    private void changeScene() {
        SceneChange();
        BOUNCER_SPEED = 30;
        MOVER_SPEED = 70;
        if (count == 2) {
            makeScene("level2.txt");

        } else if (count == 3) {
            makeScene("level3.txt");

        } else {
            makeScene("Level1.txt");

        }
    }

    private void bombbricks(){
        if(count==3) {
            first = brickw.get(0);
            second = brickw.get(12);
            third = brickw.get(3);
            fourth = brickw.get(15);
            bomb1= brickw.get(6);
            bomb2= brickw.get(9);
        }
    }

    // What to do each time a key is pressed
    private void handleKeyInput(KeyCode code) {
        if (code == KeyCode.RIGHT) {
            if (myMover.getX() + myMover.getWidth() >= myScene.getWidth()) {
                return;
            }
            myMover.setX(myMover.getX() + MOVER_SPEED);
        }
        //&&move
        else if (code == KeyCode.LEFT) {
            if (myMover.getX() <= 0) {
                return;
            }
            myMover.setX(myMover.getX() - MOVER_SPEED);
        } else if (code == KeyCode.SPACE && !extended) {
            myMover.setWidth(long_paddle);
            myMover.setX(myMover.getX() - (myMover.getWidth() / 4));
            extended = true;
        } else if (code == KeyCode.SPACE && extended) {
            myMover.setWidth(100);
            myMover.setX(myMover.getX() + (myMover.getWidth() / 4));
            extended = false;
        } else if (code == KeyCode.W) {
            MOVER_SPEED = 2 * MOVER_SPEED;
            BOUNCER_SPEED = 2 * BOUNCER_SPEED;

        } else if (code == KeyCode.U) {
            MOVER_SPEED = MOVER_SPEED / 2;
            BOUNCER_SPEED = BOUNCER_SPEED / 2;

        }
      else if(code==KeyCode.R){
          changeScene();
      }
      else if(code==KeyCode.N){
          count++;
          changeScene();
        }
      else if(code==KeyCode.B){
            count--;
            changeScene();
        }
        else if (code == KeyCode.C && !extended) {
            myMover.setWidth(SIZE);
            myMover.setX(0);
            extended = true;
        }
        else if (code == KeyCode.C && extended ) {
            myMover.setWidth(70);
            myMover.setX(myScene.getWidth()/2.5);
            extended = false;
        }
        else if(code== KeyCode.P){
            animation.play();
        }
    }
    private void brickwall(Group c, String filenam) {
        brickw.clear();
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream(filenam));
        int numbricks = 0;
        setDistance();
        System.out.print(distance);
        int linenum = 0;
        String eof = "";
        int numro = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            linenum++;

            if (linenum > 1) {
                numro++;
            }
            numbricks = 0;
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                int hits = lineScanner.nextInt();
                if (hits == 1) {
                    bomb bo = new bomb();
                    ImageView im = bo.getImage();
                    im.setX(SIZE / 8 + numbricks * Brick.WIDTH);
                    brickw.add(bo);
                    numbricks++;
                    im.setY(numro * Brick.HEIGHT + numro * 10+distance);
                    c.getChildren().add(im);
                } else if (hits == 2) {
                    normalbr norm = new normalbr();
                    ImageView im = norm.getImage();
                    brickw.add(norm);
                    im.setX(SIZE / 8 + numbricks * Brick.WIDTH);
                    numbricks++;
                    im.setY(numro * Brick.HEIGHT + numro * 10+distance);
                    c.getChildren().add(im);

                } else if (hits == 3) {
                    powerup power = new powerup();
                    ImageView im = power.getImage();
                    im.setX(SIZE / 8 + numbricks * Brick.WIDTH);
                    numbricks++;
                    brickw.add(power);
                    im.setY(numro * Brick.HEIGHT + numro * 10+distance);
                    c.getChildren().add(im);

                } else if (hits == 4) {
                    stone sto = new stone();
                    ImageView im = sto.getImage();
                    im.setX(SIZE / 8 + numbricks * Brick.WIDTH);
                    numbricks++;
                    brickw.add(sto);
                    im.setY(numro * Brick.HEIGHT + numro * 10+distance);
                    c.getChildren().add(im);

                }
                if (hits == 0) {
                    numbricks++;

                }
            }
        }


    }
    private static void setDistance(){
        distance= getRandomNumberInRange()*10;
    }

    private static int getRandomNumberInRange() {
        Random r = new Random();
        return r.nextInt(3)+3;

    }
    private void SceneChange() {
        animation.pause();
        score=0;
//        startMessage.setVisible(true);
        myBouncer.setX(myScene.getWidth()/2);
        myBouncer.setY(100);

        directionX = 1;
        directionY = 1;
        extended=false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}















