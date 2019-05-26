/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university;

import database.DbConnection;
import database.course;
import database.grade;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author aashgar
 */
public class ProfileController implements Initializable {

    //خاص  بصفحة الطالب الشخصية 
    @FXML
    private ImageView image_user;
    @FXML
    private TextField name;
    @FXML
    private TextField gender;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField email;
    //جدول الاضافة 
    @FXML
    private TableView<course> TableAdd;
    //جدول الحذف 
    @FXML
    private TableView<course> TableDelete;
    //جدول العلامات
    @FXML
    private TableView<grade> TableGrade;
    //list الفصول 
    @FXML
    private ChoiceBox<grade> GradeCourses;
    //جدول  الجدول الدراسي 
    @FXML
    private TableView<course> TableTimeTable;
    @FXML
    //خاص بعرض المعدل
    private Label Average;
    //هذه الاعمدة تابعة لجدول الحذف
    @FXML
    private TableColumn<course,String> delete_cid;
    @FXML
    private TableColumn<course,String> delete_name;
    @FXML
    private TableColumn<course,Integer> delete_division;
    
    //هذه الاعمدة تابعة جدول الاضافة
    @FXML
    private TableColumn<course, String> add_cid;
    @FXML
    private TableColumn<course, String> add_cname;
    @FXML
    private TableColumn<course, Integer> add_division;
    @FXML
    private TableColumn<course, String> add_inst;
    @FXML
    private TableColumn<course, String> add_day;
    
    
    
     //LoginPageController تقوم هذه المثود للنواصل مع
    //والحصول على رقم الطالب
    
     private String id;
     //اعمدة الجدول الدراسي
    @FXML
    private TableColumn<course,String> time_cid;
    @FXML
    private TableColumn<course,String> time_name;
    @FXML
    private TableColumn<course,Integer> time_division;
    @FXML
    private TableColumn<course,String> time_inst;
    @FXML
    private TableColumn<course,String> day1;
    @FXML
    private TableColumn<course,String> day2;
    @FXML
    private TableColumn<course,String> day3;
    @FXML
    private TableColumn<course,String> day4;
    @FXML
    private TableColumn<course,String> day5;
    @FXML
    private TableColumn<course,String> day6;
    @FXML
    private TableColumn<grade,String> grad_id;
    @FXML
    private TableColumn<grade,String> grade_name;
    @FXML
    private TableColumn<grade,Integer> grade_division;
    @FXML
    private TableColumn<grade,Integer> grade_grade;
    
     public void intiData(String sid){
         id=sid;
         getstudent();
     }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //button action on click 
    //يقوم باختيار صورة وحفظها في  قواعد البيانات
    @FXML
    private void button_image_action(ActionEvent event) {
    }
    //يقوم بإغلاق البرنامج
    @FXML
    private void button_exit_action(ActionEvent event) {
        System.exit(0);
    }
      //يقوم بتحديث بيناته الشخصية
    @FXML
    private void button_upgrade_action(ActionEvent event) {
    
    }
     //يقوم باضافة المساق
    @FXML
    private void button_addCourse_action(ActionEvent event) {
        TableView.TableViewSelectionModel<course> s=TableAdd.selectionModelProperty().getValue();
      course c=s.getSelectedItem();
      if(c!=null){
          DbConnection adbConnection =DbConnection.getdbConnection();
          adbConnection.addcourse(id,c.getCid(),c.getDivision());
           getcourses();
      }else{
          JOptionPane.showMessageDialog(null,"You have not selected any courses.","Alert",JOptionPane.WARNING_MESSAGE);  
        
      }
        
    }
   //يقوم بحذف المساق
    @FXML
    private void button_DeleteCourse_action(ActionEvent event) {
         TableView.TableViewSelectionModel<course> s=TableDelete.selectionModelProperty().getValue();
         course c=s.getSelectedItem();
         if(c!=null){
          DbConnection adbConnection =DbConnection.getdbConnection();
          adbConnection.deletecourse(id,c.getCid());
          deletecourse();
        
      }else{
          JOptionPane.showMessageDialog(null,"You have not selected any courses.","Alert",JOptionPane.WARNING_MESSAGE);  
        
      }
    }
     //يقوم بعرض علامات المساقات للفصل الدراسي الذي تم اختياره
    @FXML
    private void button_SearchGradeCourse_action(ActionEvent event) {
    }
     //taps profile action on click
    @FXML
    private void tap_profile_action(Event event) {
        getstudent();
        
    }
    //تقوم هذه المثود باستدعاء بيانات الطالب
     private  void getstudent(){
          DbConnection adbConnection =DbConnection.getdbConnection();
        Student student=adbConnection.getStudent(id);
        if(student!=null){
           name.setText(student.getName());
           gender.setText(student.getGender());
           phone.setText(student.getPhone());
           address.setText(student.getAddress());
           email.setText(student.getEmail());
        }else{
            System.out.println("Student Error Connection Database : "+student);
        }
     }
     //taps addCourse action on click
    @FXML
    private void tap_addCourse_action(Event event) {
        getcourses();
    }
    //تقوم هذه المثود باستدعاء المساقات التي تمكنك للتسجيل في الفصل الحالي
    private void getcourses(){
         DbConnection adbConnection =DbConnection.getdbConnection();
         ObservableList<course> data=adbConnection.getcourses(id);
         
         add_cid.setCellValueFactory(new PropertyValueFactory<course,String>("cid"));
         add_cname.setCellValueFactory(new PropertyValueFactory<course,String>("name"));
         add_inst.setCellValueFactory(new PropertyValueFactory<course,String>("room"));
         add_division.setCellValueFactory(new PropertyValueFactory<course,Integer>("division"));
         add_day.setCellValueFactory(new PropertyValueFactory<course,String>("days"));
         TableAdd.setItems(data);
         
         
         
    }
      //taps deleteCourse action on click
    @FXML
    private void tap_deleteCourse_action(Event event) {
        
         deletecourse();
         
         
    }
    //تقوم هذه المثود بوضع البنات في جدول حذف المساقات 
    public void deletecourse(){
         DbConnection adbConnection =DbConnection.getdbConnection();
         ObservableList<course> data=adbConnection.getcoursesselected(id);
         delete_cid.setCellValueFactory(new PropertyValueFactory<course,String>("cid"));
         delete_name.setCellValueFactory(new PropertyValueFactory<course,String>("name"));
         delete_division.setCellValueFactory(new PropertyValueFactory<course,Integer>("division"));
          TableDelete.setItems(data);
    }
    
     //taps gradeCourse action on click
    @FXML
    private void tap_gradeCourse_action(Event event) {
        show_table_grade();
    }
    //show table grade
    public void show_table_grade(){
        DbConnection adbConnection =DbConnection.getdbConnection();
         ObservableList<grade> data=adbConnection.getgrades(id);
          grad_id.setCellValueFactory(new PropertyValueFactory<grade,String>("cid"));
          grade_name.setCellValueFactory(new PropertyValueFactory<grade,String>("name"));
          grade_division.setCellValueFactory(new PropertyValueFactory<grade,Integer>("division"));
          grade_grade.setCellValueFactory(new PropertyValueFactory<grade,Integer>("grade"));
          
         TableGrade.setItems(data);
    }
    
      //taps timeTable action on click
    @FXML
    private void tap_timeTable_action(Event event) {
        show_time_table();
    }
    //  هذه المثود تقوم بعرض الجدول الدراسي في جدول جدول الفصل الدراسي
    public void show_time_table(){
        DbConnection adbConnection =DbConnection.getdbConnection();
         ObservableList<course> data=adbConnection.get_time_table_courses(id);
          time_cid.setCellValueFactory(new PropertyValueFactory<course,String>("cid"));
          time_name.setCellValueFactory(new PropertyValueFactory<course,String>("name"));
          time_division.setCellValueFactory(new PropertyValueFactory<course,Integer>("division"));
          day1.setCellValueFactory(new PropertyValueFactory<course,String>("day1"));
          day2.setCellValueFactory(new PropertyValueFactory<course,String>("day2"));
          day3.setCellValueFactory(new PropertyValueFactory<course,String>("day3"));
          day4.setCellValueFactory(new PropertyValueFactory<course,String>("day4"));
          day5.setCellValueFactory(new PropertyValueFactory<course,String>("day5"));
          day6.setCellValueFactory(new PropertyValueFactory<course,String>("day6"));
          time_inst.setCellValueFactory(new PropertyValueFactory<course,String>("insid"));
         
         TableTimeTable.setItems(data);
    }
    
}
