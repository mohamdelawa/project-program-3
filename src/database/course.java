
package database;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class course {
    
   private final SimpleStringProperty name;
   private final SimpleStringProperty cid;
   private final SimpleIntegerProperty division;
   private final SimpleStringProperty room;
   private final SimpleStringProperty insid;
   private final SimpleStringProperty days;
   private final SimpleStringProperty day1;
   private final SimpleStringProperty day2;
   private final SimpleStringProperty day3;
   private final SimpleStringProperty day4;
   private final SimpleStringProperty day5;
   private final SimpleStringProperty day6;

    public course(String name,String cid, int division, String room, String insid, String day1,String day2,String day3,String day4, String day5, String day6) {
        this.name     =new SimpleStringProperty(name);
        this.cid      =new SimpleStringProperty(cid);
        this.division =new SimpleIntegerProperty(division);
        this.room     =new SimpleStringProperty(room);
        this.insid    =new SimpleStringProperty(insid);
        this.days     =new SimpleStringProperty(getdays(day1,day2,day3,day4,day5,day6));
        this.day1     =new SimpleStringProperty(day1);
        this.day2     =new SimpleStringProperty(day2);
        this.day3     =new SimpleStringProperty(day3);
        this.day4     =new SimpleStringProperty(day4);
        this.day5     =new SimpleStringProperty(day5);
        this.day6     =new SimpleStringProperty(day6);
    }

    public String getDay1() {
        return day1.get();
    }

    public String getDay2() {
        return day2.get();
    }

    public String getDay3() {
        return day3.get();
    }

    public String getDay4() {
        return day4.get();
    }

    public String getDay5() {
        return day5.get();
    }

    public String getDay6() {
        return day6.get();
    }

   

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name); 
    }

    public String getCid() {
        return cid.get();
    }

    public void setCid(String cid) {
        this.cid.set(cid);
    }

    public int getDivision() {
        return division.get();
    }

    public void setDivision(int division) {
        this.division.set(division);
    }

    public String getRoom() {
        return room.get();
    }

    public void setRoom(String room) {
        this.room.set(room);
    }

    public String getInstructorName() {
        return insid.get();
    }

    public void setInstructorName(String insid) {
        this.insid.set(insid);
    }

    public String getDays() {
        return days.get();
    }

   
   
    public static String getdays(String day1,String day2,String day3,String day4,String day5,String day6) {
        String day="";
        if (day1!=null) {
            day=day+"Sturday : "+day1+"\n";
        }
        if (day2!=null) {
            day=day+"Sunday : "+day2+"\n";
        }
        if (day3!=null) {
            day=day+"Monday : "+day3+"\n";
        }
        if (day4!=null) {
            day=day+"Tuesday : "+day4+"\n";
        }
        if (day5!=null) {
            day=day+"Wednesday : "+day5+"\n";
        }
        if (day6!=null) {
            day=day+"Thursday : "+day6+"\n";
        }
        day=day+" ";
        return day;
    }
}
