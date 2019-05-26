/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import university.Student;

/**
 *
 * @author aashgar
 */
public class DbConnection {
    private static DbConnection adbConnection;
    private Connection aConnection;
    private Statement aStatement;

    private DbConnection() {
    }
    public static DbConnection getdbConnection(){
        if(adbConnection == null)
            adbConnection = new DbConnection();
        return adbConnection;
    }
    private void createStatement(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.aConnection = DriverManager.
         getConnection("jdbc:mysql://localhost:3306/registration_courses",
                 "root","");
            this.aStatement = this.aConnection.createStatement();
        } catch (Exception ex) {
        }
               
    }
    public void closeConnection(){
        try {
            this.aStatement.close();
            this.aConnection.close();            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean verifyUser(String id, String password){
           boolean isFound = false;
        try {
            createStatement();
            ResultSet rs = this.aStatement.
              executeQuery("Select * From students Where id='"+
                     id + "' And password='" + 
                      password + "'");
            if(rs.next())
               isFound = true;
            logfile("Select * From students Where id='"+
                     id + "' And password='" + 
                      password + "'"+"\n");
           closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            closeConnection();
        }
         return isFound;
    }
    //تقوم هذه المثود بعرض البيانات التى ستحتاجها للفصل الحالي والتي سيتم اضافتها
    public ObservableList<course> getcourses(String id){
        ObservableList<course> coursesList =
                    FXCollections.observableArrayList();
        
        ResultSet rs=null;
        try {           
            createStatement();
       
            
            String query="select  table_time.*,courses.cname,instructor.name"+
                 " from  table_time,courses,instructor"+
                 " where table_time.insid =instructor.insid and table_time.cid=courses.cid and table_time.cid not in("+
                 " SELECT current_courses.cid"+
                 " FROM current_courses"+
                 " INNER JOIN registered_courses"+
                 " ON current_courses.cid =registered_courses.cid"+
                 " where registered_courses.id='"+id+"')"+
                 " and table_time.cid not in("+
                 " SELECT current_semester.cid"+
                 " FROM current_semester"+
                 " where current_semester.id='"+id+"') ";
            rs = this.aStatement.
                 executeQuery(query);

                while(rs.next()){
                    
                course cou = null;
               
                     String cid   =rs.getString("cid");
                     String name  =rs.getString("cname");
                     String insid =rs.getString("name");
                     String room  =rs.getString("room");
                     int division =rs.getInt("division");
                     String day1  =rs.getString("day1");
                     String day2  =rs.getString("day2");
                     String day3  =rs.getString("day3");
                     String day4  =rs.getString("day4");
                     String day5  =rs.getString("day5");
                     String day6  =rs.getString("day6");
                     cou=new course(name,cid,division,room,insid,day1,day2,day3,day4,day5,day6);
                     coursesList.add(cou);
                    
                }
               
                logfile(query+"\n");
               
                
                closeConnection();
            
        } catch (SQLException ex) {
            closeConnection();
            System.out.println("error connection database");
        }
        return coursesList;
    }
    //تقوم هذه المثود بعرض بيانات الطالب  من قواعد البيانات
    public Student getStudent(String id){
         Student student=null;
        try {
            
            createStatement();
            ResultSet rs = this.aStatement.executeQuery("Select * From students Where id='"+id + "'");
            while(rs.next()){
               String name=rs.getString("name");
               String address=rs.getString("address");
               String email=rs.getString("email");
               int gender=rs.getInt("gender");
               String phone=rs.getString("phone");
               
               int sid=Integer.parseInt(id);
                
                
              student=new Student(sid, name, phone, gender,email, address);
              logfile("Select * From students Where id='"+id + "'"+"\n");
            }
            
            closeConnection();
        } catch (SQLException ex) {
            closeConnection();
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }
    //هنا عملية اضافة course والتأكد من صحة العملية
    public  void  addcourse(String id,String cid,int division){
        
            
          try {
              
              createStatement();
               
             
                int a=JOptionPane.showConfirmDialog(null,"Are you sure?");  
                if(a==JOptionPane.YES_OPTION){ 
                 int insert=this.aStatement.executeUpdate("insert into current_semester (id,cid,division)"
                         + " values("+id+", '"+cid+"',"+division+")");
                        if (insert==1) {
                  JOptionPane.showMessageDialog(null,"This course has been successfully added.","Add",JOptionPane.WARNING_MESSAGE);
                            logfile("insert into current_semester (id,cid,division)\n"
                            + " values("+id+", '"+cid+"',"+division+")");
                            
                        }else{
                            JOptionPane.showMessageDialog(null,"This course is already registered.","Add",JOptionPane.WARNING_MESSAGE);
                            System.out.println("error");
                        }
                 
                 }
                 closeConnection();
         }catch (SQLException ex) {
             closeConnection();
                      System.out.println("error connection database");
              }
        
         
    }
    //هنا قمنا بعرض المساقات التي تم تسجيلها للفصل الحالي 
     public ObservableList<course> getcoursesselected(String id){
        ObservableList<course> coursesList =
                    FXCollections.observableArrayList();
        
        ResultSet rs=null;
        try {   
            
            createStatement();
       
            
           String query="SELECT  courses.cname,current_semester.cid,current_semester.division\n" +
            " from current_semester,courses " +" where  current_semester.id='"+id+"' and current_semester.cid=courses.cid";
            rs = this.aStatement.
                 executeQuery(query);

                while(rs.next()){
                    
                course cou = null;
               
                     String cid   =rs.getString("cid");
                     String name  =rs.getString("cname");
                     int division =rs.getInt("division");
                     cou=new course(name,cid,division," "," "," "," "," "," "," "," ");
                     coursesList.add(cou);
                    
                }
               
                logfile(query+"\n");
               
                
             closeConnection();   
            
        } catch (SQLException ex) {
            closeConnection();
            System.out.println("error connection database");
        }
        return coursesList;
    }
    // هنا قمنا بعملية حذف المساق
     public  void  deletecourse(String id,String cid){
        
            
          try {
              
              createStatement();
               
             
                int a=JOptionPane.showConfirmDialog(null,"Are you sure?");  
                if(a==JOptionPane.YES_OPTION){ 
             int insert=this.aStatement.executeUpdate("DELETE FROM current_semester where  current_semester.id='"+id+"'and  current_semester.cid='"+cid+"'");
                        if (insert==1) {
                  JOptionPane.showMessageDialog(null,"This course has been successfully deleted.","Delete",JOptionPane.WARNING_MESSAGE);
                 logfile("DELETE FROM current_semester where  current_semester.id='"+id+"' and current_semester.cid='"+cid+"'");
                            
                        }else{
                            JOptionPane.showMessageDialog(null,"This course is already registered.","Add",JOptionPane.WARNING_MESSAGE);
                            System.out.println("error");
                        }
                 
                 }
                closeConnection();
                 
         }catch (SQLException ex) {
             closeConnection();
                      System.out.println("error connection database");
              }
        
         
    }
     // (object orinted)هذه المثود تقوم بعرض بيانات جدول الفصل الدراسي  من قواعد البيانات على شكل
      public ObservableList<course> get_time_table_courses(String id){
        ObservableList<course> coursesList =
                    FXCollections.observableArrayList();
        
        ResultSet rs=null;
        try {           
            createStatement();
       
            
            String query="SELECT table_time.*,courses.cname,instructor.name" +
" from table_time,current_semester,courses,instructor\n" +
" where current_semester.id='"+id+"' and current_semester.cid=table_time.cid and " +
" current_semester.division=table_time.division and current_semester.cid=courses.cid and table_time.insid=instructor.insid";
            rs = this.aStatement.
                 executeQuery(query);

                while(rs.next()){
                    
                course cou = null;
               
                     String cid   =rs.getString("cid");
                     String name  =rs.getString("cname");
                     String insid =rs.getString("name");
                     String room  =rs.getString("room");
                     int division =rs.getInt("division");
                     String day1  =rs.getString("day1");
                     String day2  =rs.getString("day2");
                     String day3  =rs.getString("day3");
                     String day4  =rs.getString("day4");
                     String day5  =rs.getString("day5");
                     String day6  =rs.getString("day6");
                     cou=new course(name,cid,division,room,insid,day1,day2,day3,day4,day5,day6);
                     coursesList.add(cou);
                    
                }
               
                logfile(query+"\n");
               
                
                closeConnection();
            
        } catch (SQLException ex) {
            closeConnection();
            System.out.println("error connection database");
        }
        return coursesList;
    }
        
      
      //هنا قمنا بعرض العلامات الطالب
        public ObservableList<grade> getgrades(String id){
        ObservableList<grade> coursesList =
                    FXCollections.observableArrayList();
        
        ResultSet rs=null;
        try {           
            createStatement();
       
            
            String query="SELECT registered_courses.*,courses.cname" +
              " FROM registered_courses,courses" +
              " where registered_courses.cid=courses.cid and registered_courses.id='"+id+"'";
            rs = this.aStatement.
                 executeQuery(query);

                while(rs.next()){
                    
                grade cou = null;
               
                     String cid   =rs.getString("cid");
                     String name  =rs.getString("cname");
                     int division =rs.getInt("division");
                     int grade  =rs.getInt("grade");
                     int year  =rs.getInt("year");
                     int semester  =rs.getInt("semester");
                    cou=new grade(name,cid, division, grade,  year,  semester);
                    
                     coursesList.add(cou);
                    
                }
               
                logfile(query+"\n");
               
                
                closeConnection();
            
        } catch (SQLException ex) {
            closeConnection();
            System.out.println("error connection database");
        }
        return coursesList;
    }
   // سجل  العمليات على قواعد البينات
    public static void logfile(String query) {
        
        try {
            File file=new File("src/database/logfile.txt");
            if (!file.exists()) {
                 file.createNewFile();
            }
             FileWriter fw=new FileWriter(file,true);
             PrintWriter pw=new PrintWriter(fw);
             pw.append(query);
             pw.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
