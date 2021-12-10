import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.*;
    
// this class purpose is to control the Listing the staff
public class SecondScene implements Initializable{
    Stage window;
    Scene scene;

    //make new column 
    @FXML
    private TableColumn<Staff, Double> basicsal3Column;

    @FXML
    private TableColumn<Staff, Double> bonussal3Column;

    @FXML
    private TableColumn<Staff, Integer> daysColumn;

    @FXML
    private TableColumn<Staff, String> name3Column;

    @FXML
    private TableView<Staff> table3;

    @FXML
    private TableColumn<Staff, String> workunit3Column;
    
    @FXML
    private TableColumn<Staff, Double> salarycolumn;

    @FXML
    private TableColumn<Staff, String> categoriColumn;

    // new TextField 
    @FXML
    private TextField nameText;

    @FXML
    private TextField salaryText;

    @FXML
    private TextField workuText;

    @FXML
    private TextField bonussalText;

    @FXML
    private TextField daysText;
    
    @FXML
    private TextField basicsalText;

    //create ObservableList
    private ObservableList<Staff> moneyList;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {   
        moneyList = FXCollections.observableArrayList();
        name3Column.setCellValueFactory(new PropertyValueFactory<Staff, String>("name1"));
        workunit3Column.setCellValueFactory(new PropertyValueFactory<Staff, String>("worku1"));
        basicsal3Column.setCellValueFactory(new PropertyValueFactory<Staff, Double>("basic1"));
        bonussal3Column.setCellValueFactory(new PropertyValueFactory<Staff, Double>("bonus1"));
        daysColumn.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("day1"));
        salarycolumn.setCellValueFactory(new PropertyValueFactory<Staff, Double>("salary"));
        categoriColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("categori1"));
        table3.setItems(moneyList);
    }
    
    @FXML
    //end the window when hit the button
    void btnback(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    @FXML
    void getSelected1(MouseEvent event) {
    }
    
    //this method will list the staff that has salary level over 10,000,000 VND
    public void getInfo(ObservableList<Staff> staffList){
        int i;
        Staff person = new Staff();
        for (i = 0; i < staffList.size() ; i++) {
            person = staffList.get(i);
            if (person.getSalary() > 10000000)  
                moneyList.add(person);
            else
                continue;
        }
    }
}

