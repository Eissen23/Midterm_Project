import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class App extends Application{   
    Stage window;

    @Override
    //this method will be called whenever the program lauch
    public void start(Stage primaryStage) throws Exception {

        //load the fxml file of the GUI
        Parent root = FXMLLoader.load(getClass().getResource("Tableview.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Staff Management Program");
        //set the initial scene as scene
        primaryStage.setScene(scene);
        //show the scene
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        //call this method will jump the start method above
        launch(args);
    }
}