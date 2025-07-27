package cs112.lab08;//package cs112.lab08;


import cs112.lab08.LoteriaCard;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Random;

import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    //CONSTANTS

    //array of LoteriaCards to use for game:
    private static final LoteriaCard[] LOTERIA_CARDS = {
            new LoteriaCard("Las matematicas", "1.png", 1),
            new LoteriaCard("Las ciencias", "2.png", 2),
            new LoteriaCard("La Tecnología", "8.png", 8),
            new LoteriaCard("La ingeniería", "9.png", 9),
    }; //all the new stuff is going in past here
    final Random random = new Random(); //rng helper

    private int cardsDrawn = 0;


    @Override
    public void start(Stage stage) {
        //title time
        Label title = new Label("LOTERIA");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24)); //I want to make the font arial, reading into docs

        ImageView cardImageView = new ImageView(LOTERIA_CARDS[0].getImage());
        cardImageView.setFitWidth(250); //250 width set for card view
        cardImageView.setPreserveRatio(true); //handles for height and makes sure the card isn't going to get messed up by screwy aspect ratios

        Label messageLabel = new Label("Press the button to draw a card!"); //label instructions for the end user

        Button drawCardButton = new Button("Draw Card"); //button for drawing the cards!

        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0);
        progressBar.setPrefWidth(200); //empty starting bar

        VBox root = new VBox(10, title, cardImageView, messageLabel, drawCardButton, progressBar); //10 pixel vert spacing
        root.setAlignment(Pos.CENTER); //centers it

        //inner class time

        drawCardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = random.nextInt(LOTERIA_CARDS.length); //selects random card
                LoteriaCard card = LOTERIA_CARDS[index];

                cardImageView.setImage(card.getImage()); //swaps the message and image per card
                messageLabel.setText(card.getCardName());

                cardsDrawn++;
                progressBar.setProgress(cardsDrawn / (double) LOTERIA_CARDS.length); //handles the progress bar advancement
            }
        });
        //now it's time for the scene setup itself
        Scene scene = new Scene(root, 350, 500); //set box to be 350x500 for decent size
        stage.setTitle("Loteria GUI");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
