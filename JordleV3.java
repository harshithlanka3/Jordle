import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.layout.FlowPane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.text.Font;
// import javafx.scene.control.RadioButton;
// import javafx.scene.control.ToggleGroup;
// import javafx.scene.layout.VBox;

/**
 * @author hlanka3
 * @version 3.1
 */
public class Jordle extends Application {
    protected Random random = new Random();
    protected int randInt = random.nextInt(Words.list.size());
    protected String randWord = Words.list.get(randInt);
    protected Text[][] textArray = new Text[6][5];
    protected GridPane grid = new GridPane();
    protected int column = 0;
    protected int row = 0;
    protected String guess = "";
    protected boolean isCorrect = false;
    protected Label status = new Label("Try guessing a word!");
    protected Alert error = new Alert(Alert.AlertType.ERROR, "Please enter a 5-letter word.");
    protected StackPane[][] letterPaneArray = new StackPane[6][5];

    /**
     * Main method that takes in args from the user at the command line. Used to launch the app in some IDEs.
     * @param args Array of string that contains the user input
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage jordleStage) {
        BorderPane mainPane = new BorderPane();

        // Making the jordle label
        Label jordleLabel = new Label("JORDLE");
        jordleLabel.setTextFill(Color.WHITE);
        Font jordleFont = Font.font("Metro", FontWeight.BOLD, 50);
        jordleLabel.setFont(jordleFont);

        // Adding the label to the top node of the main border pane.
        StackPane titlePane = new StackPane();
        titlePane.getChildren().add(jordleLabel);
        titlePane.setPadding(new Insets(60, 12, 15, 10));
        titlePane.setStyle("-fx-background-color:black");
        mainPane.setTop(titlePane);

        // Making the grid.
        // Making the panels for the squares and label for each panel.
        Font charFont = Font.font("Metro", 21);

        grid.setStyle("-fx-background-color:black");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                letterPaneArray[i][j] = new StackPane();
                letterPaneArray[i][j].setStyle("-fx-background-color:black; -fx-border-color:gray");
                letterPaneArray[i][j].setMinSize(50, 50);
                textArray[i][j] = new Text("");
                textArray[i][j].setStyle("-fx-fill:white;");
                textArray[i][j].setFont(charFont);
                letterPaneArray[i][j].getChildren().add(textArray[i][j]);
                grid.add(letterPaneArray[i][j], j, i);
            }
        }
        mainPane.setCenter(grid);

        // Choosing a random word.
        // System.out.println(randWord);
        // jordleLabel.setText(randWord);

        // Making status, instructions, and restart area.
        FlowPane flowPane = new FlowPane();

        status.setTextFill(Color.WHITE);
        Font statusFont = Font.font("Metro", FontWeight.BOLD, 18);
        status.setFont(statusFont);
        flowPane.setPadding(new Insets(15, 15, 50, 15));
        flowPane.setAlignment(Pos.CENTER);
        flowPane.getChildren().add(status);
        flowPane.setStyle("-fx-background-color:black");
        Button instructions = new Button("Instructions");
        instructions.setFont(statusFont);
        Button restart = new Button("Restart");
        restart.setFont(statusFont);

        flowPane.setHgap(10);
        flowPane.getChildren().add(instructions);
        flowPane.getChildren().add(restart);
        mainPane.setBottom(flowPane);

        Scene myScene = new Scene(mainPane, 600, 700);
        jordleStage.setTitle("Jordle");
        jordleStage.setScene(myScene);
        jordleStage.show();

        // Instructions window.
        Stage instructionStage = new Stage();
        Label instructionsLabel = new Label("Guess the JORDLE in six tries. Each guess must be a 5 letter word. \n"
            + "Hit the enter button to submit. \n\nAfter each guess, the color of the tiles will change to show how "
            + "close your\n guess was to the word. \n\nIf a guessed letter is in the word you are trying to guess "
            + "and in the right position,\n"
            + "the tile will turn green.\n\nIf a guessed letter is in the word you are trying to guess but"
            + "in the wrong position,\nthen the tile will turn yellow.\n\nThe restart button will clear your entries "
            + "and get you a new word to guess.\n\n The word cannot contain a number or special character"
            + "and will only contain letters."
            );
        Font textFont = Font.font("Metro", 16);
        instructionsLabel.setTextFill(Color.WHITE);
        instructionsLabel.setFont(textFont);
        instructionsLabel.setPadding(new Insets(5, 20, 20, 20));
        StackPane instructionPane = new StackPane(instructionsLabel);
        instructionPane.setStyle("-fx-background-color:black");
        Scene instructionScene = new Scene(instructionPane, 600, 400);
        instructionStage.setTitle("Instructions");
        instructionStage.setScene(instructionScene);

        // Instruction button implementation.
        instructions.setOnMouseClicked(e -> {
            instructionStage.show();
            grid.requestFocus();
        });

        // Restart button implementation. FIGURE out picking a new word as well...
        restart.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
                public void handle(MouseEvent a) {
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 5; j++) {
                            letterPaneArray[i][j].setStyle("-fx-background-color:black; -fx-border-color:gray");
                            textArray[i][j].setText("");
                        }
                    }
                    randInt = random.nextInt(Words.list.size());
                    randWord = Words.list.get(randInt);
                    // System.out.println(randWord);
                    status.setText("Try guessing a word!");
                    column = 0;
                    row = 0;
                    guess = "";
                    isCorrect = false;
                    grid.requestFocus();
                }
            }
        );

        // Dark mode and light mode.
        // RadioButton darkMode = new RadioButton("Dark Mode");
        // RadioButton lightMode = new RadioButton("Light Mode");
        // ToggleGroup themeGroup = new ToggleGroup();
        // Font themeFont = Font.font("Metro", 20);

        // darkMode.setSelected(true);
        // darkMode.setToggleGroup(themeGroup);
        // darkMode.setStyle("-fx-text-fill: white");
        // darkMode.setPadding(new Insets(10, 0, 10, 0));

        // lightMode.setSelected(false);
        // lightMode.setToggleGroup(themeGroup);
        // lightMode.setStyle("-fx-text-fill: white");
        // lightMode.setPadding(new Insets(10, 0, 10, 0));
        
        // VBox vbox = new VBox(10);
        // vbox.getChildren().add(darkMode);
        // vbox.getChildren().add(lightMode);
        // vbox.setAlignment(Pos.CENTER);
        // vbox.setPadding(new Insets(0, 10, 10, 0));
        // vbox.setStyle("-fx-background-color: black");
        
        // mainPane.setRight(vbox);

        // User input test I guess...
        class InputHandler implements EventHandler<KeyEvent> {
            @Override
            public void handle(KeyEvent e) {
                // Dealing with inputs of letter keys.
                if (e.getCode().isLetterKey() && column < 4 && !isCorrect) {
                    textArray[row][column].setText(e.getText().toUpperCase());
                    column += 1;
                } else if (e.getCode().isLetterKey() && column == 4
                    && textArray[row][column].getText().equals("") && !isCorrect) {
                    textArray[row][column].setText(e.getText().toUpperCase());
                }
    
                // Dealing with the backspace input
                if (e.getCode() == KeyCode.BACK_SPACE && column != 0
                    && !textArray[row][column].getText().equals("") && !isCorrect) {
                    textArray[row][column].setText("");
                } else if (e.getCode() == KeyCode.BACK_SPACE && column != 0
                    && textArray[row][column].getText().equals("") && !isCorrect) {
                    column -= 1;
                    textArray[row][column].setText("");
                }
    
                // Dealing with input of enter.
                if (e.getCode() == KeyCode.ENTER && !textArray[row][column].getText().equals("") && !isCorrect) {
                    for (Text txt : textArray[row]) {
                        guess += txt.getText();
                    }
                    guess = guess.toLowerCase();
                    if (guess.equals(randWord)) {
                        for (StackPane pane : letterPaneArray[row]) {
                            pane.setStyle("-fx-background-color:green; -fx-border-color:gray");
                        }
                        status.setText("Congratulations! You have guessed the word!");
                        isCorrect = true;
                    } else {
                        for (int i = 0; i < 5; i++) {
                            if (guess.charAt(i) == randWord.charAt(i)) {
                                letterPaneArray[row][i].setStyle("-fx-background-color:green; -fx-border-color:gray");
                            } else if (randWord.indexOf(guess.charAt(i)) != -1) {
                                letterPaneArray[row][i].setStyle("-fx-background-color:gold; -fx-border-color:gray");
                            } else {
                                letterPaneArray[row][i].setStyle("-fx-background-color:darkgray; -fx-border-color:gray");
                            }
                        }
                    }
                    if (row < 5) {
                        guess = "";
                        row += 1;
                        column = 0;
                    } else {
                        status.setText("Game Over. The word was " + randWord);
                        isCorrect = true;
                    }
                } else if (e.getCode() == KeyCode.ENTER && textArray[row][column].getText().equals("") && !isCorrect) {
                    error.showAndWait();
                }
                grid.requestFocus();
            }
        }
        InputHandler ih = new InputHandler();
        grid.setOnKeyPressed(ih);
        grid.requestFocus();
    }
}