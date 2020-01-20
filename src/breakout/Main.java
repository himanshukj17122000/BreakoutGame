package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/** CODE WRITTEN BY: Himanshu Jain (@hkj4)
 *  This is the main class for the game Breakout in which
 *  the user has to use a paddle to destroy all the bricks
 *  on the screen. The private variables have been declared
 *  and initialised in the main class. The game can be run by
 *  running the Main.main()
 */

public class Main extends Application {
    /**
     * declared the global variables
     * initialised some of the variables
     * some variables remain constant throughout the program
     */
    public static final String TITLE = "Himanshooter";
    public static final int SIZE = 400;
    private static Timeline animation = new Timeline();
    private static int count = 1;
    private static int numbricks = 0;
    private static int damage = 1;
    private String a = "";
    private Brick first = new Brick();
    private Brick second = new Brick();
    private Boolean started= false;
    private Brick third = new Brick();
    private Brick fourth = new Brick();
    private Brick bomb1 = new Brick();
    private Brick bomb2 = new Brick();
    private int linenum;
    public static final int FRAMES_PER_SECOND = 100;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.BLACK;
    public static final String BOUNCER_IMAGE = "ball.gif";
    private int BOUNCER_SPEED = 70;
    private static int numro = 0;
    public static final int long_paddle = 140;
    private static int distance = 0;
    private static double directionX = 1;
    private static double directionY = 1;
    private static int scoregame = 0;
    private ArrayList<Brick> brickw = new ArrayList<Brick>();
    private static Label label2 = new Label();
    private static Label label3 = new Label();
    private static Label label4 = new Label();
    private static Scene myScene;
    private static ImageView myBouncer;
    private static int lives = 3;
    private static Stage stagee = new Stage();
    paddle mover;

    /**
     * first function that is called whenever the program is run
     * it is run only once throughout the game. The dimensions
     * of the stage are set in this class
     * @param stage is the screen where the scene is displayed
     */
    @Override
    public void start(Stage stage) {
        makeScene("Level1.txt");
        stagee = stage;
        SplashScreen splash= new SplashScreen();
        stage.setScene(splash.SplashScreen());
        splash.welcomeButton().setOnMouseClicked(e -> checkclick(stagee));
        stage.setTitle(TITLE);
        stage.show();
    }

    /**
     * this class is used to make the different scenes based on the level of the user
     * if the user is done with level3, this function takes the user to the game over window
     * @param levels allows us to choose the level the user is on and hence display the relevant screen
     */
    public void makeScene(String levels) {
        if (count > 3) {
            animation.stop();
            end endscreen= new end();
            endscreen.Replay().setOnMouseClicked(e -> mouseclicked());
            stagee.setScene(endscreen.EndScene());
        } else {
            myScene = setupGame(SIZE, SIZE, BACKGROUND, levels);
            checkclick(stagee);
        }
    }

    /**
     * function that is called by clicking on the button of the last screen
     * count has been set to 1 so the first level is displayed whenever the user wants to replay the game
     */
    private void mouseclicked() {
        count = 1;
        changeScene();
    }
    /**
     * this function is called when the user clicks the button on the splash screen
     * @param stage allows us to change the scene to the first level of the game
     */

    private void checkclick(Stage stage) {
        stage.setScene(myScene);
        game();
    }

    /**
     * it is the game loop of the game and it allows us to play the animation required
     * it calls step function within MILLISECOND_DELAY
     */
    private void game() {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
    }

    /**
     * this sets up the scenes for us. Allows us to put the paddle, the ball and the 3 different labels on the screen
     * @param width is the width of the stage
     * @param height is the height of the stage
     * @param background is the background color of the scene
     * @param level is the string that signifies which file is read to make the brickwall
     * @return
     */
    private Scene setupGame(int width, int height, Paint background, String level) {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
        myBouncer = new ImageView(image);
        Group root = new Group();
        mover=new paddle();
        setSpeed(70);
        this.myBouncer.setX((double) (width / 2) - this.myBouncer.getBoundsInLocal().getWidth() / 2.0D);
        this.myBouncer.setY((double) (height / 2) - this.myBouncer.getBoundsInLocal().getHeight() / 2.0D);
        setLayout(label2, 40,SIZE-20,root);
        setLayout(label3, SIZE / 2,SIZE-20,root);
        setLayout(label4, SIZE - 40,SIZE-20,root);
        label4.setText("Level: " + count);
        root.getChildren().addAll(myBouncer,mover.mover());
        brickwall(root, level);
        bombbricks();
        Scene scene = new Scene(root, width, height, background);
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    private void setLayout(Label labels, int i, int j, Group base) {
        labels.setLayoutX(i);
        labels.setLayoutY(j);
        labels.setFont(Font.font("Brush Script MT", 13));
        labels.setTextFill(Color.web("#0076a3"));
        base.getChildren().add(labels);
    }

    /**
     * sets the speed of the ball
     * @param i is the speed that is passed in to change the speed of the ball
     */
    private void setSpeed(int i) {
        BOUNCER_SPEED = i;
    }

    /**
     * it is the function that is called by the game loop
     * allows us to check the collisions happening in the game and take decisions based on that
     * @param elapsedTime is the parameter passed on by the setupGame(). Changes the position of the ball in the game
     */
    private void step(double elapsedTime) {
           label3.setText("Lives: " + lives);
           checkMover();
            myBouncer.setX(myBouncer.getX() + BOUNCER_SPEED * elapsedTime * directionX);
            myBouncer.setY(myBouncer.getY() + BOUNCER_SPEED * elapsedTime * directionY);
            checkBrickArrayList();
            for (int i = 0; i < brickw.size(); i++) {
                if ((myBouncer.getX() >= brickw.get(i).getImage().getX() && myBouncer.getX() <= brickw.get(i).getImage().getX() + brickw.get(i).getWidth() - 2) && (myBouncer.getY() >= brickw.get(i).getImage().getY() && myBouncer.getY() <= brickw.get(i).getImage().getY() + brickw.get(i).getHeight())) {
                    directionY *= -1;
                    brickw.get(i).ifhit(damage);
                    checkPower(brickw.get(i).getType(), brickw.get(i).Strikes());
                    checkBomb(brickw.get(i), bomb1);
                    checkBomb(brickw.get(i),bomb2);
                        if (brickw.get(i).Strikes() <= 0) {
                        AudioClip note = new AudioClip(this.getClass().getResource("pong_beep.wav").toString());
                        note.play();
                        brickw.get(i).getImage().imageProperty().set(null);
                        brickw.remove(brickw.get(i));
                            changeScore();
                        return; }
                    directionY*= -1; }}
            checkBouncer(); }

    private void checkPower(String type, int strikes) {
        if (type.equals("brick2.gif") && strikes <= 0) {
            int rand = getRandomNumberInRange(2, 0);
            if (rand == 0) {
                changeSpeed();
            }
            if (rand == 1) {
                increaseLives();
            }
            if (rand == 2) {
                changeDamage();
            } } }

    private void checkBomb(Brick inArray, Brick toCheck){
        if (inArray == toCheck && inArray.Strikes() <= 0) {
            inArray.getImage().imageProperty().set(null);
            brickw.remove(inArray);
            changeScore();
            if (inBounds(first)) {
                first.getImage().imageProperty().set(null);
                brickw.remove(first);
                changeScore();
            }
            if (inBounds(second)) {
                second.getImage().imageProperty().set(null);
                brickw.remove(second);
                changeScore();
            }
        }
    }


    private void checkBouncer() {
        if ((myBouncer.getY() > stagee.getHeight() || myBouncer.getY() < -20) && lives < 0) {
            count = 4;
            makeScene("End");
        }

        if ((myBouncer.getY() > stagee.getHeight() || myBouncer.getY() < -20) && lives >= 0) {
            lives--;
            changeScene();
        }
        if (myBouncer.getX() + myBouncer.getBoundsInLocal().getWidth() >= myScene.getWidth()) {
            directionX *= -1;
        }
        if (myBouncer.getX() <= 0) {
            directionX *= -1;
        }
        if (myBouncer.getY() <= 0) {
            directionY *= -1;
        }

    }
    private void changeScore(){
        scoregame+=5;
        changeLabel();
    }

    private void changeLabel() {
        label2.setText("Score: "+scoregame);
    }

    private void checkBrickArrayList(){
        if (brickw.size() == 0) {
            count++;
            changeScene();
        }
        if (brickw.size() <= 10 && brickw.size() > 4) {
            mover.mover().setY(SIZE / 1.7);
                if (mover.mover().getBoundsInParent().intersects(myBouncer.getBoundsInParent())) {
                directionY *= -1;
                directionX *= -1;
            }
        }

        if (brickw.size() <= 4) {
            mover.mover().setY(0);
                if (mover.mover().getBoundsInParent().intersects(myBouncer.getBoundsInParent())) {
                directionY *= -1;
                directionX *= -1;
            }
            if (myBouncer.getY() <= 0) {
                directionY = 1;
            }
            if (myBouncer.getY() + myBouncer.getBoundsInLocal().getHeight() >= myScene.getHeight()) {
                directionY *= -1;
            }
        }
    }

    private void checkMover() {
        if(mover.mover().getX()>=SIZE){
            mover.mover().setX(0);
        }
        if(mover.mover().getX()+mover.mover().getWidth()<=0){
            mover.mover().setX(SIZE-mover.mover().getWidth());
        }
        if (mover.mover().getBoundsInParent().intersects(myBouncer.getBoundsInParent())) {
            directionY *= -1;
        }
        if (mover.mover().getBoundsInParent().intersects(myBouncer.getBoundsInParent()) && (myBouncer.getY() + myBouncer.getBoundsInLocal().getHeight() - 3 >mover.mover().getY())) {
            directionX *= -1;
            directionY *= -1;
        }
    }

    /**
     * Powerup function that changes the damage made by the ball on the brick for 10 seconds
     */
    private void changeDamage() {
        damage = 2;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        damage = 1;
                    }
                },
                5000
        );
    }

    /**
     * another powerup that increases the lives of the player
     */
    private void increaseLives() {
        lives++;
    }

    /**
     * another powerup that changes the speed of the ball for 10 seconds
     */

    private void changeSpeed() {
        setSpeed(50);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        setSpeed(70);
                    }
                },
                10000
        );

    }

    private boolean inBounds(Brick brick) {
        return (brick != null);
    }

    /**
     * changes the scene based on the count variable
     * allows us to navigate between different levels of the game
     */
    private void changeScene() {
        SceneChange();

        if (count == 2) {
            makeScene("level2.txt");

        } else if (count == 3) {
            makeScene("level3.txt");

        } else {
            makeScene("Level1.txt");

        }
    }

    /**
     * made to check the bomb bricks in the 3rd level
     * allows us to destroy the neighbouring bricks when the bomb brick is destroyed
     */
    private void bombbricks() {
        if (count == 3) {
            first = brickw.get(0);
            second = brickw.get(10);
            third = brickw.get(3);
            fourth = brickw.get(13);
            bomb1 = brickw.get(7);
            bomb2 = brickw.get(8);
        }

    }

    /**
     * function that is used to perform action based on the specific actions by the user
     * @param code checks the key pressed by the user and allows us to perform different things based on what was pressed
     */

    private void handleKeyInput(KeyCode code) {
        if (code == KeyCode.RIGHT) {
            if(!started){
                if (mover.mover().getX() + mover.mover().getWidth() >= myScene.getWidth())
                    if (mover.mover().getX() + mover.mover().getWidth() >= myScene.getWidth()) {
                        return;
                    }}mover.mover().setX(mover.mover().getX() +mover.getSpeed());
        } else if (code == KeyCode.LEFT) {
            if(!started){
                if (mover.mover().getX() <= 0) {
                    return;
                }}
           mover.mover().setX(mover.mover().getX() - mover.getSpeed());}
        else if (code == KeyCode.SPACE && !mover.getExtended()) {
            mover.setLayout(long_paddle,mover.mover().getX() - (mover.mover().getWidth() / 4),true);
        } else if (code == KeyCode.SPACE) {
            mover.setLayout(100,mover.mover().getX()+ (mover.mover().getWidth() / 4),false);
        } else if (code == KeyCode.W) {
            mover.setMOVER_SPEED(2*mover.getSpeed());
            setSpeed(2 * BOUNCER_SPEED);
        }
        else if (code == KeyCode.U) {
            setSpeed(BOUNCER_SPEED / 2);
        }
        else if (code == KeyCode.R) {
            changeScene();
            scoregame = 0;
            changeLabel();
        }
        else if (code == KeyCode.L) {
            lives++;}
            else if (code == KeyCode.C && !mover.getExtended()) {
                mover.setLayout(SIZE,0,true);
            } else if (code == KeyCode.C) {
            mover.setLayout(mover.getMoverSize(),myScene.getWidth() / 2.5,false);

        } else if (code == KeyCode.P) {
            animation.playFromStart();
            started=true;
        }
        changeLevels(code, KeyCode.DIGIT3,3);
        changeLevels(code, KeyCode.DIGIT2,2);
        changeLevels(code, KeyCode.DIGIT1,1);

    }

    private void changeLevels(KeyCode key, KeyCode buttonPressed, int countnum){
        if(key==buttonPressed){
            count=countnum;
            changeScene();
        } }

    /**
     * this makes the brickwall by reading the text file
     * @param c is the group that all the bricks are added to
     * @param filenam is the file that is being read and is basically the levels value in setupGame
     */

    private void brickwall(Group c, String filenam) {
        initialiser();
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream(filenam));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            linenum++;
            if (linenum > 1) {
                numro++; }
            numbricks = 0;
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                int hits = lineScanner.nextInt();
                if (hits == 1) {
                    bomb bo = new bomb();
                    makelayers(bo, c);
                } else if (hits == 2) {
                    normalbr norm = new normalbr();
                    makelayers(norm, c); }
                else if (hits == 3) {
                    powerup power = new powerup();
                    makelayers(power, c); }
                else if (hits == 4) {
                    stone sto = new stone();
                    makelayers(sto, c); }
                if (hits == 0) {
                    numbricks++;}}}}

    private void initialiser() {
        numbricks = 0;
        numro = 0;
        brickw.clear();
        setDistance();
        linenum = 0;
    }

    /**
     * used to make the different bricks based on the number of hits of each
     * @param brick is the brick type that we have
     * @param brickwall is the group that all the bricks are a part of
     */
    private void makelayers(Brick brick, Group brickwall) {
        ImageView im = brick.getImage();
        im.setX(SIZE / 8 + numbricks * Brick.WIDTH);
        brickw.add(brick);
        numbricks++;
        im.setY(numro * Brick.HEIGHT + numro * 10 + distance);
        brickwall.getChildren().add(im);
    }

    private static void setDistance() {
        distance = getRandomNumberInRange(5, 3) * 10;
    }

    /**
     * used to get a random number for setting the distance of the bricks from the top, the direction of the ball when released
     * and to decide the powerup that the powerup brick has
     * @param max is the maximum integer that we want
     * @param min is the smallest integer that we want
     * @return returns a number between and including the maximum and minimum numbers
     */
    private static int getRandomNumberInRange(int max, int min) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;

    }

    /**
     * used to pause the animation and change the different scenes on the stage
     */
    private void SceneChange() {
        setSpeed(70);
        started=false;
        mover.setMOVER_SPEED(50);
        animation.pause();
        scoregame = 0;
        changeLabel();
        myBouncer.setX(myScene.getWidth() / 2);
        myBouncer.setY(0);
        if(getRandomNumberInRange(1,-1)==0|| getRandomNumberInRange(1,-1)==-1){
            directionX=-1;
        }else directionX=1;
        directionY = 1;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
