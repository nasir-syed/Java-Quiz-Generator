import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class ResultsController {



    private Parent root;

    private Stage stage;

    private Scene scene;

    @FXML
    private Label scoreLabel;

    @FXML
    private Button playAgainButton, logoutButton, previousScoresButton;

    @FXML
    private AnchorPane scorePane;
    public void getResult(int score, int numOfQuestions){

        // the scoreLabel will display the score
        scoreLabel.setText(score+"/"+numOfQuestions);

        logoutButton.setFocusTraversable(false);
        playAgainButton.setFocusTraversable(false);

    }

    public void playAgain (ActionEvent e)throws IOException {

        // if the user presses the button, an alert is shown asking if they're sure about their choice

        Alert playAgainAlert = new Alert(Alert.AlertType.CONFIRMATION);
        playAgainAlert.setTitle("Play Again");
        playAgainAlert.setContentText("Are you sure you want to play again?");
        playAgainAlert.getDialogPane().getStylesheets().add(getClass().getResource("quiz.css").toString());
        playAgainAlert.getDialogPane().getStyleClass().add("Alert");
        playAgainAlert.initStyle(StageStyle.UNDECORATED);

        // if the user clicks "OK" then it begins once again by loading the Name.fxml file

        if (playAgainAlert.showAndWait().get() == ButtonType.OK) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Name.fxml"));
            root = loader.load();

            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void logout(ActionEvent e) {

        //once again when the button is pressed, an alert is shown asking if they're sure about their choice

        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit");
        exitAlert.setContentText("Are you sure you want to exit?");
        exitAlert.getDialogPane().getStylesheets().add(getClass().getResource("quiz.css").toString());
        exitAlert.getDialogPane().getStyleClass().add("Alert");
        exitAlert.initStyle(StageStyle.UNDECORATED);


        // if the user clicks on "OK" it closes the stage.
        if (exitAlert.showAndWait().get() == ButtonType.OK) {

            stage = (Stage) scorePane.getScene().getWindow();
            stage.close();
            System.exit(0);

        }


    }
    public void previousScores(ActionEvent e) throws IOException{


        // if the button is pressed then the "PreviousScores" FXML file is loaded

        FXMLLoader loader = new FXMLLoader(getClass().getResource("PreviousScores.fxml"));
        root = loader.load();

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}

