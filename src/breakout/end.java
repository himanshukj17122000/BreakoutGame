package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class end {
    private final static int SIZE=400;
    private final static int FONT_SIZE=25;
    private final static int HEIGHT=400;
    private Button replay;
    private final static int WIDTH=420;
    private static final Paint BACKGROUND = Color.BLACK;
    private Scene EndScreen;

    /**
     * Gives the end screen that is displayed at the end of the game and when the user loses the game
     */

    public end(){
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("giphy.gif"));
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(HEIGHT);
        imageView.setFitWidth(WIDTH);
        imageView.setPreserveRatio(true);
        Label last = new Label();
        last.setText("GO FOR IT, AGAIN!");
        last.setFont(Font.font("Brush Script MT", FONT_SIZE));
        replay = new Button();
        replay.setText("Replay");
        last.setLayoutY(SIZE / 1.5);
        last.setTextFill(Color.WHITE);
        last.setLayoutX(90);
        replay.setLayoutY(SIZE - 50);
        replay.setLayoutX(SIZE / 2 - 40);
        Group root = new Group();
        EndScreen = new Scene(root, SIZE, SIZE, BACKGROUND);
        root.getChildren().addAll(imageView,replay,last);
    }
    
    /**
     * the end scene has been made in the constructor and this method is called when the game ends
     * @return the scene names EndScreen which is displayed on the screen when the game ends or the player loses
     */
    public Scene EndScene(){
        return this.EndScreen;
    }
    
    /**
     * it is used for setting the button and further manipulation with it
     * @return the button that has been made so that it could respond the mouse inputs that are given
     */
    public Button Replay(){
        return this.replay;
    }
}
