import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;


public class MainScene implements Initializable{

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
    private TextField searchCoeffi;

    @FXML
    private TextField searchName;

    @FXML
    private TextField searchWorku;

    @FXML
    //create a choice box for enhanced categorizing
    ChoiceBox<String> staffCate;

    //create a menu box
    @FXML
    MenuBar menuBar;

    @FXML
    Menu fileMenu;

    @FXML 
    Menu aboutMenu;
    //This method is gonna be called whenever the view load : App.java 

    //file chooser
    FileChooser fileChooser = new FileChooser();
    //this will represent the file you use in the time
    static String curFilePath;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {   
        fileChooser.setInitialDirectory(new File("C:\\Users\\PC\\Documents"));
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
        staffCate.getItems().addAll("Teacher", "Adminstrative Staff");
        //set the first categorize box as blank
        staffCate.getSelectionModel().select(null);
      
        table1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //wrap the ObservabelList in a FilteredList, initialy display all data
        FilteredList<Staff> filteredData = new FilteredList<>(staffList, b -> true);

        //set the filter predicate whenever there is a change in TextField: searchName
        searchName.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(staff ->{
                //if the TextField is empty, display all data
                if(newValue == null|| newValue.isEmpty()){
                    return true;
                }
                //lower case the value for easier searching, this will be temporary
                String lowerCaseFilter = newValue.toLowerCase();

                //compare the lowercase version of both value (the staff'name and the newValue)
                if (staff.getName1().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true; // if the values are alike, return the data
                }
                else
                return false; // if not, don't return the data
                
            });
        });
        searchWorku.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(staff ->{
                if(newValue == null|| newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (staff.getWorku1().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else
                return false;
                
            });
        });
        searchCoeffi.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(staff ->{
                if(newValue == null|| newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(staff.getBasic1()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else
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
        double money;
        if(newStaff.getCategori1() == "Teacher"){   
            money = newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 45000;
        }
        else{
            money = newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 200000;
        }
        newStaff.setSalary((long)money);

        //in the end add the the ObservableList for display
        staffList.add(newStaff);  
        //clear the TextField
        nameText.clear();
        workunitText.clear();
        basicsalText.clear();
        bonussalText.clear();
        daysText.clear();
    }

    // connected to the "Delete Imformation"  button, Tableview.fxml: 29
    public void delete (ActionEvent e)

    {   
        //on selected and after choose the delete button, remove the selected row out of the table 
        Staff selected1 = table1.getSelectionModel().getSelectedItem();
        staffList.remove(selected1);
        table1.getSelectionModel().clearSelection();
    }
    
    @FXML
    //stop selecting the row when click from the outside
    void clearSelected1(MouseEvent event)
    {
        table1.getSelectionModel().clearSelection();
    }

    @FXML
    void clearSelected2(ActionEvent event) {
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
    //more explaination in the  future readme.txt file 
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
        if (selected1.getCategori1() == "Teacher")
        {
            Long money1 = (long) (newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 45000);
            newStaff.setSalary(money1);
            // newStaff.setCategori1("Teacher");
        }
        else 
        {
            Long money2 = (long) (newStaff.getBasic1() * 750000 + newStaff.getBonus1() + newStaff.getDay1() * 200000);
            newStaff.setSalary(money2);
            // newStaff.setCategori1("Adminstrative Staff");
        }
        staffList.add(newStaff);
        table1.getSelectionModel().clearSelection();
        }
    }

    public void saveClicked(ActionEvent ev){
        Window saveScreen = anchorPane.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Binary file", "*.bin"));
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
            fileChooser.setInitialDirectory(file.getParentFile());
            SaveLoad.saveFile(staffList, file.getPath());
            curFilePath = file.getPath();
        }catch(Exception ex){}
    }
    
    public void saveAsClicked(ActionEvent ev){
        Window saveScreen = anchorPane.getScene().getWindow();
        fileChooser.setTitle("Save File As");
        fileChooser.setInitialFileName("mydata");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Binary File", "*.bin"));
        try{
            File file = fileChooser.showSaveDialog(saveScreen);
            fileChooser.setInitialDirectory(file.getParentFile());
            SaveLoad.saveFile(staffList, file.getPath());
            curFilePath = file.getPath();
        }catch(Exception ex){}
    }

    public void exportClicked(ActionEvent ev){
        Window exportScreen = anchorPane.getScene().getWindow();
        fileChooser.setTitle("Export File");
        fileChooser.setInitialFileName("Manager_Doc");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file","*txt"));
        try{
            File file = fileChooser.showSaveDialog(exportScreen);
            SaveLoad.saveFile(staffList, file.getPath());
        }catch(Exception ex){}
    }

    public void loadClicked(ActionEvent ev){
        Window loadScreen = anchorPane.getScene().getWindow();
        fileChooser.setTitle("Load file");

        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("Binary file", "*.bin"));

        try{
            File file = fileChooser.showOpenDialog(loadScreen);
            fileChooser.setInitialDirectory(file.getParentFile());
            staffList = SaveLoad.loadFile(file.getPath(), staffList);
            table1.setItems(staffList);
            table1.refresh();
            curFilePath = file.getPath();
        }catch(Exception ex){}
    }

    //connected to the "Listing Staff's Salary button", detail at Tableview.fxml:73
    //jump to the next window
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
    
