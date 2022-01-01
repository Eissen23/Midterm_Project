/**
 * @author
 * Project group: 3
 * 
 * Start method, main: Nguyen Minh Duc 
 * The Student Series: 20207664
 * Class: IT-VUW 01-K65
 * 
 * closeRequest(), setOncloseRequest: coded by Pham Duc Phuc
 * The Student Series: 20207698
 * Class: IT-VUW 01-K65
 * 
*/
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class App extends Application{   
    Stage window;

    @Override
    //this method will be called whenever the program lauch
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        //load the fxml file of the GUI
        Parent root = FXMLLoader.load(getClass().getResource("Tableview.fxml"));
        Scene scene = new Scene(root);

        window.setOnCloseRequest(e ->{

            //prevent the logic error
            e.consume();
            closeRequest();

        });

        window.setTitle("Staff Management Program");
        //set the initial scene as scene
        window.setScene(scene);
        //show the scene
        window.show();
    }

    public void closeRequest(){
        //choose the type of alert box, this case Confirmation box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("EXIT");

        alert.setHeaderText("Are you sure you wanted to exit the program?");
        alert.setContentText("Note: this dialog will always be shown to remind you of saving your work.");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.OK){
            window.close();
        }
    }

    public static void main(String[] args) throws Exception {
        //call this method will jump the start method above
        launch(args);
    }
}