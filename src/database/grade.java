
package database;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class grade {
   private final SimpleStringProperty name;
   private final SimpleStringProperty cid;
   private final SimpleIntegerProperty division;
    private final SimpleIntegerProperty grade;
   private final SimpleIntegerProperty year;
   private final SimpleIntegerProperty semester;

    public grade(String name, String cid, int division,int grade, int year, int semester) {
        this.name =new SimpleStringProperty(name) ;
        this.cid =new SimpleStringProperty(cid);
        this.division =new SimpleIntegerProperty(division);
        this.year =new SimpleIntegerProperty(year);
        this.semester =new SimpleIntegerProperty(semester);
        this.grade =new SimpleIntegerProperty(grade);
    }

    public int getGrade() {
        return grade.get();
    }

    public String getName() {
        return name.get();
    }

    public String getCid() {
        return cid.get();
    }

    public int getDivision() {
        return division.get();
    }

    public int getYear() {
        return year.get();
    }

    public int getSemester() {
        return semester.get();
    }
   
}
