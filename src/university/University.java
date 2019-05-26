/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class University extends Application {
        private static Stage stage;
        
        public static Stage getStage() {
        return stage;
       }

    public static void setStage(Stage stage) {
        University.stage = stage;
    }
   
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        setStage(primaryStage);
         //  تعمل هذه المثود لوضع صفحة الدخول  للدخول الى البرنامج
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setResizable(true);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}