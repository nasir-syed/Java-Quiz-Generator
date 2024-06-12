import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class PreviousScoresController implements Initializable {

    @FXML
    private ListView<String> scoresListView;

    @FXML
    private Button playAgainBtn, exitBtn;

    @FXML
    private AnchorPane prevScoresPane;

    private Stage stage;

    private Parent root;

    private Scene scene;


    // the path to the file where the scores are stored is defined
    String path = "src\\Scores.txt";


    //we display the contents of the file using listview
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            FileReader inputFile = new FileReader(path);

            BufferedReader inputBuffer = new BufferedReader(inputFile);

            String numString = inputBuffer.readLine();

            // we add the scores to the scoresList
            while (numString != null) {
                scoresListView.getItems().add(numString);
                numString = inputBuffer.readLine();
            }

            inputBuffer.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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

            stage = (Stage) prevScoresPane.getScene().getWindow();
            stage.close();
            System.exit(0);


        }


    }
}
