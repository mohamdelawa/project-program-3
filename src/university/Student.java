/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package university;

/**
 *
 * @author aashgar
 */
public class Student {
    private int ID;
    private String name;
    private String phone;
    private int gender;
    private String email;
    private String address;

    public Student(int ID, String name, String phone, int gender, String email, String address) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
        this.address = address;
    }

    public Student() {
    }
    

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        
        return "0"+this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
         if (this.gender==1) {
            return "Male";
        }else{
        return "Female";
         }
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   
}
