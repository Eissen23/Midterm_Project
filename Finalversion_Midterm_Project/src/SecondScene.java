/**
 * @author
 * Coded and created by Le Minh Nghia
 *  The Student Series: 20207694
 * Class: IT-VUW 01-K65
 * 
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

    //create ObservableList
    private ObservableList<Staff> moneyList;

    @FXML
    private TextField searchAll;

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

        // wrap the ObservabelList in a FilteredList, initialy display all data
        FilteredList<Staff> filteredData = new FilteredList<>(moneyList, b -> true);

        searchAll.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(staff -> {
                // if the TextField is empty, display all data
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // lower case the value for easier searching, this will be temporary
                String lowerCaseFilter = newValue.toLowerCase();

                // compare the lowercase version of both value (the staff'name and the newValue)
                if (staff.getName1().toLowerCase().indexOf(lowerCaseFilter) != -1
                        || staff.getWorku1().toLowerCase().indexOf(lowerCaseFilter) != -1
                        || String.valueOf(staff.getBasic1()).indexOf(lowerCaseFilter) != -1) {
                    return true; // if the values are alike, return the data
                } else
                    // if not, don't return the data
                    return false;

            });
        });

        // wrap the FilteredList in SortedList, this will sort out all the value that
        // are not alike
        SortedList<Staff> sortedData = new SortedList<>(filteredData);

        // bind to the table use the comperator, otherwise, this won't have any effect
        sortedData.comparatorProperty().bind(table3.comparatorProperty());
        // set item of the table
        table3.setItems(sortedData);
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

