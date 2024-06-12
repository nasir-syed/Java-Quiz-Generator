import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NumOfQuestionsController {

    @FXML
    private Label numQLabel;
    @FXML
    private TextField numQTextField;
    @FXML
    private Button myButton;


    private int questions;

    private Parent root;
    private Stage stage;
    private Scene scene;

    private String category, name;

    public void setCategoryAndName(String category, String name){

        /*  here we set the category and name chosen by the user in the previous scenes, which will finally be passed
         on to the "ShowQuestions" class */

        this.category=category;
        this.name=name;
    }

    public void submit(ActionEvent event)throws IOException {

        try {

            // below we assign the input received in the numQTextField after turning it into and Integer
            questions = Integer.parseInt(numQTextField.getText().trim());


            // if the number of questions entered is not within the range 1-10
            if (questions < 1 || questions > 10){
                numQLabel.setText("Please enter a number from 1-10.");
            }

            // else if the number of questions entered is in the required range of 1-10
            else if (questions > 0 && questions <= 10){

                // ShowQuestions FXML file is loaded and shown on stage
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayQuestions.fxml"));
                root = loader.load();

                // an instance of the ShowQuestions controller class is made
                DisplayQuestionsController showQs = loader.getController();

                // methods from the controller class are called
                showQs.setVariables(category,name,questions);
                showQs.randomiseQuestions();
                showQs.questions();
                showQs.timer();

                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }



        } // if the user enters letters instead of numbers
        catch(NumberFormatException e){
            numQLabel.setText("please enter a number.");
        }



    }


}
