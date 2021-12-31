/**
 * @author
 * Project group: 3 
 * Add method, clear selection method  by Le Minh Nghia 
 * The Student Series: 20207694
 * Class: IT-VUW 01-K65
 * 
 * Initialize method, delete, fix method by Nguyen Minh Duc 
 * The Student Series: 20207664
 * Class: IT-VUW 01-K65
 * 
 *  Created and coded by Pham Duc Phuc 
 * The Student Series: 20207698
 * Class: IT-VUW 01-K65
 */
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;


public class MainScene implements Initializable{
    //declare an AnchorPane represent the GUI
    @FXML
    AnchorPane anchorPane;

    //declare a table view for Staff object
    @FXML
    //this will declare a table for Staff object
    private TableView<Staff> table1;

    /* For each and every column, declare a TableColumn<Class_name,Attribute_datatype>
    this will create a column for a property each */
    @FXML
    //creat different column display the method
    private TableColumn<Staff, Integer> workingdayColumn;
    
    @FXML
    private TableColumn<Staff, Double> basicsal1Column;

    @FXML
    private TableColumn<Staff, String> name1Column;

    @FXML
    private TableColumn<Staff, String> workunit1Column;

    @FXML
    private TableColumn<Staff, Double> bonussal1Column;

    @FXML
    private TableColumn<Staff, Long> salaryColumn;

    @FXML
    private TableColumn<Staff, String> categoriColumn;

    /* Create an ObservabelList<Class_name> object, this will act as a List that 
    store the object of the Class, including its attribute and method */
    private ObservableList<Staff> staffList;
    int index = -1;
    
    //in the GUI, TextField will be the place you input your data

    @FXML
    private TextField daysText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField basicsalText;

    @FXML
    private TextField workunitText;

    @FXML
    private TextField bonussalText;

    @FXML
    private TextField salaryText;

    @FXML
    private TextField categoriText;

    @FXML
    private TextField searchAll;

    @FXML
    //create a choice box for enhanced categorizing
    ChoiceBox<String> staffCate;

    @FXML 
    private CheckBox rememberWork;
    //create a menu box
    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu fileMenu;

    @FXML 
    private Menu aboutMenu;
    //This method is gonna be called whenever the view load : App.java 

    //file chooser
     FileChooser fileChooser = new FileChooser();
    //this will represent the file you use in the time
    static String curFilePath;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {   
        fileChooser.setInitialDirectory(new File("C:"));
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Binary file", "*.bin"));
        //this is for testing only
        staffList = FXCollections.observableArrayList();

        //link each column data to different attribute of object
        name1Column.setCellValueFactory(new PropertyValueFactory<Staff, String>("name1"));
        workunit1Column.setCellValueFactory(new PropertyValueFactory<Staff, String>("worku1"));
        basicsal1Column.setCellValueFactory(new PropertyValueFactory<Staff, Double>("basic1"));
        bonussal1Column.setCellValueFactory(new PropertyValueFactory<Staff, Double>("bonus1"));
        workingdayColumn.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("day1"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<Staff, Long>("salary"));
        categoriColumn.setCellValueFactory(new PropertyValueFactory<Staff, String>("categori1"));
        

        //set the item: the ObservableList of Staff, for display
        table1.setItems(staffList);
        //set the item for choice box
        staffCate.getItems().addAll("Teacher", "Administrative Staff");
        //set the first categorize box as blank
        staffCate.getSelectionModel().select(0);
      
        table1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //wrap the ObservabelList in a FilteredList, initialy display all data
        FilteredList<Staff> filteredData = new FilteredList<>(staffList);

        searchAll.textProperty().addListener((observ, oldValue, newValue) -> {
            filteredData.setPredicate(staff ->{
                //if the TextField is empty, display all data
                if(newValue == null|| newValue.isEmpty()){
                    return true;
                }
                //lower case the value for easier searching, this will be temporary
                String lowerCaseFilter = newValue.toLowerCase();

                //compare the lowercase version of both value (the staff'name and the newValue)
                if (staff.getName1().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
                 staff.getWorku1().toLowerCase().indexOf(lowerCaseFilter) != -1 ||
                 String.valueOf(staff.getBasic1()).indexOf(lowerCaseFilter) != -1){
                    return true; // if the values are alike, return the data
                }
                else
                    // if not, don't return the data
                    return false;
                
            });
        });

        //wrap the FilteredList in SortedList, this will sort out all the value that are not alike
        SortedList<Staff> sortedData = new SortedList<>(filteredData);

        //bind to the table use the comperator, otherwise, this won't have any effect
        sortedData.comparatorProperty().bind(table1.comparatorProperty());
        //set item of the table
        table1.setItems(sortedData);

    }


    // the actual add method
    public void add (ActionEvent e){
        
        Staff newStaff = new Staff();

        // from the value in the TextField, use set method to modify the object
        newStaff.setName1(nameText.getText());
        newStaff.setWorku1(workunitText.getText());
        newStaff.setBasic1(Double.parseDouble(basicsalText.getText()));
        newStaff.setBonus1(Double.parseDouble(bonussalText.getText()));
        newStaff.setDay1(Integer.parseInt(daysText.getText()));
        newStaff.setCategori1(staffCate.getValue());
        long money;
        if(newStaff.getCategori1() == "Teacher"){   
            money = (long)(newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 45000);
        }
        else{
            money = (long)(newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 200000);
        }
        newStaff.setSalary(money);

        //in the end add the the ObservableList for display
        staffList.add(newStaff);  
        //clear the TextField
        nameText.clear();
        basicsalText.clear();
        bonussalText.clear();
        daysText.clear();
        if(rememberWork.isSelected()==false){
            workunitText.clear();
        }
    }

    // connected to the "Delete Imformation"  button, Tableview.fxml: 29
    public void delete (ActionEvent e)

    {   
        //remove all selected items on viewtable
        staffList.removeAll(table1.getSelectionModel().getSelectedItems());
        table1.getSelectionModel().clearSelection();
        nameText.clear();
        workunitText.clear();
        basicsalText.clear();
        bonussalText.clear();
        daysText.clear();
    }
    
     //clear the name text when pressed the "R" key
    @FXML
    void clearSelection(KeyEvent event){
        if(event.getCode().toString() =="R")
        {
            table1.getSelectionModel().clearSelection();
            nameText.clear();
            workunitText.clear();
            basicsalText.clear();
            bonussalText.clear();
            daysText.clear();
        }
    }

    @FXML
    void clearSelection2(ActionEvent event){
        table1.getSelectionModel().clearSelection();
         nameText.clear();
        workunitText.clear();
        basicsalText.clear();
        bonussalText.clear();
        daysText.clear();
    }

    @FXML
    // choose a row, this will also display all the value into the TextField, allow for fix() method to work
    void getSelected1(MouseEvent event)
    {
        index= table1.getSelectionModel().getSelectedIndex();
        if(index<=-1){
            return;
        }
        nameText.setText(name1Column.getCellData(index).toString());
        workunitText.setText(workunit1Column.getCellData(index).toString());
        basicsalText.setText(basicsal1Column.getCellData(index).toString());
        bonussalText.setText(bonussal1Column.getCellData(index).toString());
        daysText.setText(workingdayColumn.getCellData(index).toString());

        // dong nay toi them vao 
        staffCate.setValue(categoriColumn.getCellData(index));
    }
    
    @FXML
    //delete the original Staff and replace it with the fixed one
    void fix(ActionEvent event) 
    {
        Staff selected1 = table1.getSelectionModel().getSelectedItem();
        if (staffList.remove(selected1)){
            table1.getSelectionModel().clearSelection(0);
        
            Staff newStaff = new Staff();
            newStaff.setName1(nameText.getText());
            newStaff.setWorku1(workunitText.getText());
            newStaff.setBasic1(Double.parseDouble(basicsalText.getText()));
            newStaff.setBonus1(Double.parseDouble(bonussalText.getText()));
            newStaff.setDay1(Integer.parseInt(daysText.getText()));

            newStaff.setCategori1(staffCate.getValue());
            if (selected1.getCategori1() == "Teacher"){

                Long money1 = (long) (newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 45000);
                newStaff.setSalary(money1);
            }
            else 
            {
                Long money2 = (long) (newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 200000);
                newStaff.setSalary(money2);
            }
            staffList.add(newStaff);
            table1.getSelectionModel().clearSelection();
            nameText.clear();
            basicsalText.clear();
            bonussalText.clear();
            daysText.clear();
            if (rememberWork.isSelected() == false) {
                workunitText.clear();
            }
        }
        
    }

    @FXML
    // the refined save with fully functional
    public void saveClicked(ActionEvent e){
        // so that the save dialog will pop up in this scene
        Window saveScreen = anchorPane.getScene().getWindow();
        try{
            if(SaveLoad.isSaved){
                // handle the second and beyond save
                SaveLoad.saveFile(staffList, curFilePath);
                return;
            }
            fileChooser.setTitle("Save file");
            fileChooser.setInitialFileName("mydata");
           
            //make a file that has the directory according to the dialog
            File file = fileChooser.showSaveDialog(saveScreen);
            //set the next directory as the folder that contain the 
            fileChooser.setInitialDirectory(file.getParentFile());

            //save the staff's list into the file we chosed
            SaveLoad.saveFile(staffList, file.getPath());

            /* set the current file path into a static attribute 
            so that we can work with it later one*/
            curFilePath = file.getPath();
        }catch(Exception ex){}
    }

    @FXML
    // the save as method, same as above but without handling secondsave
    public void saveAsClicked(ActionEvent e){
        Window saveScreen = anchorPane.getScene().getWindow();
        fileChooser.setTitle("Save File As");
        fileChooser.setInitialFileName("mydata");
        try{
            File file = fileChooser.showSaveDialog(saveScreen);
            fileChooser.setInitialDirectory(file.getParentFile());
            SaveLoad.saveFile(staffList, file.getPath());
            curFilePath = file.getPath();
        }catch(Exception ex){}
    }

    @FXML
    //export the file into readable document
    public void exportClicked(ActionEvent e){
        Window exportScreen = anchorPane.getScene().getWindow();
        fileChooser.setTitle("Export File");
        fileChooser.setInitialFileName("Manager_Doc");
        
        // add a new extenstion filter for txt file
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file","*.txt"), new FileChooser.ExtensionFilter("Docx file", "*.docx"));
        try{
            //export the text file use exportFile method
            File file = fileChooser.showSaveDialog(exportScreen);
            SaveLoad.exportFile(staffList, file.getPath());
        }catch(Exception ex){}
    }

    @FXML
    //load the binary data into the table
    public void loadClicked(ActionEvent e){
        Window loadScreen = anchorPane.getScene().getWindow();
        fileChooser.setTitle("Load file");

        try{
            File file = fileChooser.showOpenDialog(loadScreen);
            fileChooser.setInitialDirectory(file.getParentFile());

            // the array list contain the data from the bin file
            // it contain the staffList you  wanna work with 
            ArrayList<Staff> data = SaveLoad.loadFile(file.getPath());
            
            //clear the entire staff list for the imported one
            staffList.clear();

            //for each element add the staff into the staffList
            // the sorted and filtered list can be update also
            for(int i = 0; i < data.size(); i++){
                staffList.add(data.get(i));
            }
            
            //refresh the table so that the information shown is up-to-date
            table1.refresh();
            //get the file path to the file we just load
            curFilePath = file.getPath();
        }catch(Exception ex){}
    }

    @FXML 
    // show the credit of the program
    public void showAbout(ActionEvent e) throws IOException{

        Stage window = new Stage();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AboutDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("ABOUT");
        window.setScene(scene);
        window.show();
    }

    @FXML 
    public void showInstruction(ActionEvent e){
        try{
            //use this method to get relative file path inside folder
            URL url = getClass().getResource("Readme.txt");
            File file = new File(url.getPath());
            //open the file using the os feature
            Desktop.getDesktop().open(file);
        }catch(Exception ex){

        }
    }
    //connected to the "Listing Staff's Salary button", detail at Tableview.fxml:73
    //jump to the next window
    @FXML
    public void gonext(ActionEvent event) throws IOException {
        //make a new window
        Stage window = new Stage();
        //load the fxml design for the new window
        FXMLLoader loading = new FXMLLoader();
        loading.setLocation(getClass().getResource("Caculateview.fxml"));
        Parent root = loading.load();
        Scene scene = new Scene(root);
        
        //this scene will use SecondScene file as controller
        SecondScene controlScene = loading.getController();
        controlScene.getInfo(staffList);

        //this window won't be close until the user asked to 
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("List of Staff'salary");
        window.setScene(scene);
        window.showAndWait();
    }
    
    //connected to the "Total staff's salary", detail at Tableview.fxml:74
    // this method is alike to the previous one 
    @FXML
    public void gonext2(ActionEvent event) throws IOException {
        Stage window = new Stage();
        FXMLLoader loading = new FXMLLoader();
        loading.setLocation(getClass().getResource("Totalview.fxml"));
        Parent root = loading.load();
        Scene scene = new Scene(root);

        ThirdScene controlscene = loading.getController();
        controlscene.addmoney(staffList);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Caculate result");
        window.setScene(scene);
        window.showAndWait();
    }

}
    
