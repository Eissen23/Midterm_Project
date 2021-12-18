import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class SaveLoad {
    //to reduce saving process
    public static boolean isSaved = false;

    public static void saveFile(ObservableList<Staff> list, String directory){
        try{
        
            FileOutputStream fos = new FileOutputStream(directory);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            ArrayList<Staff> data = new ArrayList<>(list);
            
            oos.writeObject(data);

            fos.close();
            oos.close();
        
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        isSaved = true;
    }

    public static ObservableList<Staff> loadFile(String directory, ObservableList<Staff> currentList){
    
        try {
            FileInputStream fis = new FileInputStream(directory);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            List<Staff>data = (List<Staff>) ois.readObject();
            
            currentList =FXCollections.observableArrayList();
            currentList = FXCollections.observableArrayList(data);

            fis.close();
            ois.close();
               
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        isSaved = true;

        return currentList;
    }

    public static void exportFile(ObservableList<Staff> list, String directory) throws IOException{
        FileWriter writer = new FileWriter(directory);

        writer.write("Staff Infor \n");
        writer.write("Name \tUnit \tCoefficient Salary " 
            +"\tBonus Salary \tDay of Work \tSalary(VND) \tCategory \n");
        for(Staff data: list){
            
            writer.write(data.getName1()+" \t"+data.getWorku1()+" \t");
            writer.write(String.valueOf(data.getBasic1())+" \t"+String.valueOf(data.getBonus1())+" \t");
            writer.write(String.valueOf(data.getDay1())+" \t"+data.getCategori1()+ " \n");
        }

        writer.close();
    }
}
