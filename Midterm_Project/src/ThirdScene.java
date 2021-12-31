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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
//this class help bring out the total staff salary
public class ThirdScene implements Initializable {

    @FXML
    public Label totalMoney;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void btnback(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    // add the money of each staff into one and bring out
    public void addmoney(ObservableList<Staff> staffList){
        int i;
        Staff person = new Staff();
        long tong = 0;
        for (i = 0; i < staffList.size() ; i++) {
            person = staffList.get(i);
            tong = tong + person.getSalary();
        }
        totalMoney.setText(tong +"VND");
    }
}
