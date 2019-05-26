/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university;

import database.DbConnection;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aashgar
 */
public class LoginPageController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label aleart;
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
//  تعمل هذه المثود على التأكد من وجود رقم للطالب
    @FXML
    private void buttonlogin(ActionEvent event)  {
        try{
        // (Databaseمن class DbConnection)يتم التواصل مع
         DbConnection adbConnection =DbConnection.getdbConnection();
         //  تعمل هذه المثود على التأكد من وجود رقم للطالب
        if(adbConnection.verifyUser(username.getText(), 
                password.getText())){
        Parent root;        
        try {
            //  تعمل هذه المثود لتغيير الواجهة والدخول الى البرنامج وصفحة البروفايل
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("Profile.fxml"));
        root=loader.load();
       // root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
       //لاعطائه رقم الطالب حتى يتم التعمل معه(class profilecontroller) هنا قمنا باستدعاء
           ProfileController lpc=loader.getController();
           
           lpc.intiData(username.getText());
        Scene scene = new Scene(root);
        Stage stage = University.getStage();
          
         
        stage.setScene(scene);
        stage.setTitle("Profil");
        stage.setResizable(false);
        stage.show();
        } catch (IOException ex) {
                  aleart.setText("Error in loding Page.");
             }
        }else{
            aleart.setText("UserName OR Password Error.");
           
        }
        }catch(Exception e){
            aleart.setText("Error  in Connection database.");
        }
    }

   
}
