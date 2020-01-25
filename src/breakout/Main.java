package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    private static int numro = 0;
    public static final int long_paddle = 140;
    private static int distance = 0;
    private static int scoregame = 0;
    private ArrayList<Brick> brickw = new ArrayList<Brick>();
    private static Label label2 = new Label();
    private static Label label3 = new Label();
    private static Label label4 = new Label();
    private static Scene myScene;
    private static int lives = 3;
    private static Stage stagee = new Stage();
    private paddle mover;
    private ball myBall;
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

    private void makeScene(String levels) {
        if (count > 3) {
            animation.stop();
            end endscreen= new end();
            endscreen.Replay().setOnMouseClicked(e -> mouseclicked());
            stagee.setScene(endscreen.EndScene());
        } else {
            myScene = setupGame(SIZE, SIZE, BACKGROUND, levels);
            checkclick(stagee);
        } }

    private void mouseclicked() {
        count = 1;
        changeScene();
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

    private Scene setupGame(int width, int height, Paint background, String level) {
        Group root = new Group();
        mover=new paddle();
        myBall= new ball();
        myBall.setLayout((double) (width / 2) - this.myBall.ballImage().getBoundsInLocal().getWidth() / 2.0D,(double) (height / 2) - this.myBall.ballImage().getBoundsInLocal().getHeight() / 2.0D);
        setLayout(label2, 40,SIZE-20,root);
        setLayout(label3, SIZE / 2,SIZE-20,root);
        setLayout(label4, SIZE - 40,SIZE-20,root);
        label4.setText("Level: " + count);
        root.getChildren().addAll(myBall.ballImage(),mover.mover());
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

    private void step(double elapsedTime) {
        label3.setText("Lives: " + lives);
        checkMover();
        myBall.setLayout(myBall.ballImage().getX() + myBall.getSpeed()* elapsedTime * myBall.directionX(),myBall.ballImage().getY() + myBall.getSpeed() * elapsedTime * myBall.directionY());
        checkBrickArrayList();
        for (int i = 0; i < brickw.size(); i++) {
            if ((myBall.ballImage().getX() >= brickw.get(i).getImage().getX() && myBall.ballImage().getX() <= brickw.get(i).getImage().getX() + brickw.get(i).getWidth() - 2) && (myBall.ballImage().getY() >= brickw.get(i).getImage().getY() && myBall.ballImage().getY() <= brickw.get(i).getImage().getY() + brickw.get(i).getHeight())) {
                myBall.setdirectionY();
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
                myBall.setdirectionY(); }}
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
            } } }

    private void checkBouncer() {
        if ((myBall.ballImage().getY() > stagee.getHeight() || myBall.ballImage().getY() < -20) && lives < 0) {
            count = 4;
            makeScene("End");
        }

        if ((myBall.ballImage().getY() > stagee.getHeight() || myBall.ballImage().getY() < -20) && lives >= 0) {
            lives--;
            changeScene();
        }
        if (myBall.ballImage().getX() + myBall.ballImage().getBoundsInLocal().getWidth() >= myScene.getWidth()) {
            myBall.setdirectionX();
        }
        if (myBall.ballImage().getX() <= 0) {
            myBall.setdirectionX();
        }
        if (myBall.ballImage().getY() <= 0) {
           myBall.setdirectionY();
        } }

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
            if (mover.mover().getBoundsInParent().intersects(myBall.ballImage().getBoundsInParent())) {
               myBall.setdirection();
            }
        }

        if (brickw.size() <= 4) {
            mover.mover().setY(0);
            if (mover.mover().getBoundsInParent().intersects(myBall.ballImage().getBoundsInParent())) {
                myBall.setdirection();
            }
            if (myBall.ballImage().getY() <= 0) {
                myBall.initialiseY();
            }
            if (myBall.ballImage().getY() + myBall.ballImage().getBoundsInLocal().getHeight() >= myScene.getHeight()) {
                myBall.setdirectionY();
            }}}

    private void checkMover() {
        if(mover.mover().getX()>=SIZE){
            mover.mover().setX(0);
        }
        if(mover.mover().getX()+mover.mover().getWidth()<=0){
            mover.mover().setX(SIZE-mover.mover().getWidth());
        }
        if (mover.mover().getBoundsInParent().intersects(myBall.ballImage().getBoundsInParent())) {
            myBall.setdirectionY();
        }
        if (mover.mover().getBoundsInParent().intersects(myBall.ballImage().getBoundsInParent()) && (myBall.ballImage().getY() + myBall.ballImage().getBoundsInLocal().getHeight() - 3 >mover.mover().getY())) {
           myBall.setdirection();
        }
    }

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
        ); }

    private void increaseLives() { lives++; }

    private void changeSpeed() {
        myBall.setSpeed(50);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        myBall.setSpeed(70);
                    }
                },
                10000
        ); }

    private boolean inBounds(Brick brick) {
        return (brick != null);
    }

    private void changeScene() {
        SceneChange();

        if (count == 2) {
            makeScene("level2.txt");
        } else if (count == 3) {
            makeScene("level3.txt");

        } else {
            makeScene("Level1.txt");

        } }

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
            myBall.setSpeed(2* myBall.getSpeed());
        }
        else if (code == KeyCode.U) {
            myBall.setSpeed(myBall.getSpeed()/2);
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

    private static int getRandomNumberInRange(int max, int min) {
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    private void SceneChange() {
        myBall.setSpeed(70);
        started=false;
        mover.setMOVER_SPEED(50);
        animation.pause();
        scoregame = 0;
        changeLabel();
        myBall.setLayout(myScene.getWidth() / 2,0);
        if(getRandomNumberInRange(1,-1)==0|| getRandomNumberInRange(1,-1)==-1){
            myBall.changeX();
        } else myBall.initialiseX();
        myBall.initialiseY();
    }

    public static void main(String[] args) {
        launch(args);
    }}
