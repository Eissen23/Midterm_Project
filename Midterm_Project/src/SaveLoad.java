import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class SaveLoad {
    
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
        return currentList;
    }
}
