package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/** CODE WRITTEN BY: Himanshu Jain (@hkj4)
 * this class creates the Splash Screen for the game and returns the scene that could be displayed on the screen
 */
public class SplashScreen {
    private final static int SIZE=400;
    private Button splash;
    private final static String welcome="Welcome";
    private Scene SplashScreen;
    private final static String opening="opening.gif";
    private final static String title="HIMANSHOOTER";
    private final static String message="The rules of the game are:\n1) Arrow Keys- Move the paddle \n2) 4 Types of Bricks-\n\t" +
            " a) Power-up- Has different power-ups like extra-life, " +
            "\n\t double damaging ball and slower ball\n\t b) Stone- Takes 4 hits to be broken" +
            " \n\t c) Normal- Takes 3 hits to be broken \n\t d) Bomb- Takes 1 hit and damages the neighbouring\n\t blocks " +
            "\n 3) There are a number of Cheat Keys- C for paddle covering\n the screen, L gives an extra life, R restarts the game, W\n increases the speed, Space increases the paddle's length" +
            "\n 4) The paddle comes up by 1/2 when only 10 bricks and\n the game is upside down when 4 bricks are left ";

    public SplashScreen() {
            Label label1 = new Label();
            label1.setText(message);
            Label name = new Label(title);
            name.setFont(Font.font("Brush Script MT", 20));
            name.setLayoutX(140);
            name.setLayoutY(SIZE / 1.3);
            label1.setFont(Font.font("Chalkboard", 13));
            label1.setTextFill(Color.web("Green"));
            label1.setLayoutX(10);
            label1.setLayoutY(10);
            Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(opening));
            ImageView icon = new ImageView(image);
            icon.setX(30);
            icon.setY(SIZE/1.5);
            icon.setFitWidth(100);
            icon.setFitHeight(100);
            splash = new Button(welcome);
            splash.setLayoutX(SIZE / 2.5);
            splash.setLayoutY(SIZE / 1.1);
            Group gro = new Group();
            gro.getChildren().addAll(label1,splash,name,icon);
            SplashScreen= new Scene(gro, SIZE, SIZE, Color.BLACK);
    }
    public Scene SplashScreen(){
        return SplashScreen;
    }
    public Button welcomeButton(){
        return splash;
    }
}
