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


public class NameController {

    // declaration of class variables
    private Stage stage;
    private Parent root;
    private Scene scene;

    private String name;


    // FXML elements
    @FXML
    private Button submitBtn;

    @FXML
    private TextField nameField;

    @FXML
    private Label enterNameLabel;


    /* method attached to button, if the submit button is pressed and the nameField is not empty
    then the next scene is loaded.
     */

    public void submit(ActionEvent e) throws IOException{

        // the input from the nameField is assigned to the name variable
        // .trim() removes and trailing spaces before or after the text

        name = nameField.getText().trim();

        // .isBlank() checks if the value is null, empty, or contains only whitespace characters.
        /* if the user just presses spacebar a couple of times to skip this process, then they are asked
        to "enter a name" once again */

        if(name.isBlank()){
            enterNameLabel.setText("please enter a name.");
        }
        else {

            // after receiving input and ensuring it is not blank, we load a new "Category" scene.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Category.fxml"));
            root = loader.load();

            /*  below we create an instance of the controller, which we then use to call methods
            within that controller */
            CategoryController cat = loader.getController();

            /* below are the methods called, notTraversable() makes it so the user cant press the
             buttons using spacebar or enter, and we also pass on the name entered by the user */

            cat.notTraversable();
            cat.setName(name);

            // we get the current stage (cast the source to a node)
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

            // we make a new scene (FXML file)
            scene = new Scene(root);

            // we set the scene and show it on the stage.
            stage.setScene(scene);
            stage.show();
        };
    }

}
