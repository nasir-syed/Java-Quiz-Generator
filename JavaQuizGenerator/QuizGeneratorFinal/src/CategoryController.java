import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class CategoryController {

    @FXML
    private Button historyButton, scienceButton, mathsButton, sportsButton, musicButton;


    private Stage stage;
    private Parent root;
    private Scene scene;

    private String name;
    private String category;

    public void notTraversable(){

        // if the user preses spacebar or enter, the buttons will not fire.

        historyButton.setFocusTraversable(false);
        scienceButton.setFocusTraversable(false);
        musicButton.setFocusTraversable(false);
        sportsButton.setFocusTraversable(false);
        mathsButton.setFocusTraversable(false);
    }

    public void setName(String name){
        // setting the value of "name" which will be passed on later to another FMXL file
        this.name = name;
    }
    public void chooseCategory(ActionEvent e) throws IOException{

        /* below we make a "confirmation" alert, which gives the user options between "ok" and "cancel"
        the purpose of this alert is to make sure that the category the user has chosen is their
        final choice
         */

        Alert categoryAlert = new Alert(Alert.AlertType.CONFIRMATION);
        categoryAlert.initStyle(StageStyle.UNDECORATED);
        categoryAlert.setTitle("Confirmation");
        categoryAlert.setContentText("Are you sure?");

        // we apply css to the alertbox below
        categoryAlert.getDialogPane().getStylesheets().add(getClass().getResource("quiz.css").toString());

        categoryAlert.getDialogPane().getStyleClass().add("Alert");

        // if the user selects "OK" on the alert
        if (categoryAlert.showAndWait().get() == ButtonType.OK){

            // if the "history button" is pressed
            if(e.getSource()==historyButton){

                //category variable is set to "history"
                category = "history";
            }
            else if(e.getSource()==scienceButton){
                category = "science";
            }

            else if(e.getSource()==musicButton){
                category = "music";
            }

            else if(e.getSource()==mathsButton){
                category = "maths";
            }

            else if(e.getSource()==sportsButton){
                category = "sports";
            }

            // we switch to the next scene
            switchToNumQScene(e);
        }
    }



    public void switchToNumQScene(ActionEvent e) throws IOException {

        // loads the NumOfQuestions FXML file and switches to that scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NumOfQuestions.fxml"));
        root = loader.load();

        NumOfQuestionsController numQ = loader.getController();
        numQ.setCategoryAndName(category,name);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}









