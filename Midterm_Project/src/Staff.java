/**
 * @author 
 * Coded and created by Nguyen Minh Duc
 * Student series 20207664
 * Class IT-VUW 01-K65
*/

import java.io.Serializable;

public class Staff implements Serializable {
    private double bonus1, basic1;
    private String name1;
    private String worku1;
    private int day1;
    private long salary1;
    private String categori1;
    
    public Staff(String name1, String worku1, double basic1, double bonus1, int day1, String categori1){
        this.name1 = name1;
        this.worku1 = worku1;
        this.basic1 = basic1;
        this.bonus1 = bonus1;
        this.day1 = day1;
        this.categori1 = categori1;
    }
    
    public Staff(){
    }
    public String getName1(){
        return name1;
    }
    public void setName1(String name1){
        this.name1 = name1;
    }
    public String getWorku1(){
        return worku1;
    }
    public void setWorku1(String worku1){
        this.worku1 = worku1;
    }
    public Double getBasic1(){
        return basic1;
    }
    public void setBasic1(Double basic1){
        this.basic1 = basic1;
    }
    public double getBonus1(){
        return bonus1;
    }
    public void setBonus1(double bonus1){
        this.bonus1 = bonus1;
    }
    public int getDay1(){
        return day1;
    }
    public void setDay1(int day1){
        this.day1 = day1;
    }
    public long getSalary(){
        return salary1;
    }
    public void setSalary(Long salary1){
        this.salary1 = salary1;
    }

    public String getCategori1(){
        return categori1;
    }

    public void setCategori1(String categori1){
        this.categori1 = categori1;
    }
}

