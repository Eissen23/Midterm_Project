/**
 * @author
 * Project group: 3
 * Created and coded by Pham Duc Phuc
 * The Student Series: 20207698
 * Class: IT-VUW 01-K65
 */
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import javafx.collections.ObservableList;

class SaveLoad {
    //to reduce saving process
    public static boolean isSaved = false;

    //save data into binary file for later use
    public static void saveFile(ObservableList<Staff> list, String directory){
        try{
            //create a FileOutputStream for writing data into the specific file
            FileOutputStream fos = new FileOutputStream(directory);
            //create a ObjectOutputStream to write an Object to the OutputStream
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            //make a new ArrayList data contain Object from ObservableList
            ArrayList<Staff> data = new ArrayList<>(list);
            
            //write the array list to the file in binary data
            oos.writeObject(data);

            //close both OutputStream
            fos.close();
            oos.close();
        
        } catch(IOException ex) {}
        //mark the file is saved
        isSaved = true;
    }

    //Load or open the data binary file into a ArrayList object
    public static ArrayList<Staff> loadFile(String directory){
        
        // create a empty ArrayList
        ArrayList<Staff> data = new ArrayList<>();
        try {

            //FileInputStream is made to obtain data from file
            FileInputStream fis = new FileInputStream(directory);
            // ObjectInputStream deserialize the object data from the file it reading
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            //cast the read data into an ArrayList 
            data = (ArrayList<Staff>) ois.readObject();
            
            //close both OutputStream
            fis.close();
            ois.close();
               
        } catch (Exception e) {}
        //mark that the file directory is valid
        isSaved = true;
        
        //return the ArrayList for the program to import 
        return data;
    }

    //save into a readable file (text file )
    public static void exportFile(ObservableList<Staff> list, String directory) throws IOException{
        
        // writing Stream of character to the file
        FileWriter writer = new FileWriter(directory);

        //write the headline 
        writer.write("Staff Infor \n");
        writer.write("Name \tUnit \tCoefficient Salary "  +"\tBonus Salary \tDay of Work \tSalary(VND) \tCategory \n");
        
        //for each Staff object from the list, write the converted String value of the Staff's each attribute
        for(Staff data: list){
            
            writer.write(data.getName1()+" \t"+data.getWorku1()+" \t");
            writer.write(String.valueOf(data.getBasic1())+" \t"+String.valueOf(data.getBonus1())+" \t");
            writer.write(String.valueOf(data.getDay1())+" \t"+data.getCategori1()+ " \n");
        }

        // close the stream
        writer.close();
    }
}
