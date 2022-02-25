package model.entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.AccountDB;
import model.dao.Characters;
import model.dao.CustomerDB;

public class Customer {

    private String userName;
    private String password;
    private String fullName;
    private Date dob;
    private String id;
    private String address;
    private boolean sex;
    private String email;
    private String avatar;
    private String tel;    

    public Customer() {
    }

    public Customer(String userName, String password, String fullName, String dob, String id, 
            String address, boolean sex, String email, String tel) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            this.dob = new Date(f.parse(dob).getTime());;
        } catch (ParseException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.id = id;
        this.address = address;
        this.sex = sex;
        this.email = email;        
        this.tel = tel;
    }

    public Customer(String userName, String password, String fullName, 
            Date dob, String id, String address, boolean sex, String email, 
            String avatar, String tel) {        
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.dob = dob;
        this.id = id;
        this.address = address;
        this.sex = sex;
        this.email = email;
        this.avatar = avatar;
        this.tel = tel;
    }
    
    public Customer(String userName){
        this(CustomerDB.getCustomer(userName));
    }
    
    public Customer(Customer c){
        this(c.userName, c.password, c.fullName, c.dob, c.id, c.address, c.sex, c.email, c.avatar, c.tel);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return Characters.conver(fullName);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return new SimpleDateFormat("dd-MM-yyyy").format(dob);
    }
    
    public String getDobToAdd() {
        return new SimpleDateFormat("yyyy-MM-dd").format(dob);
    }

    public void setDob(String birth) {
        try {
            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
            this.dob = new Date(f.parse(birth).getTime());;
        } catch (ParseException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Customer{" + "userName=" + userName + ", password=" + password + ", fullName=" + fullName + ", dob=" + dob + ", id=" + id + ", address=" + address + ", sex=" + sex + ", email=" + email + ", avatar=" + avatar + ", tel=" + tel + '}';
    }

    public static Customer login(String userName, String password) {
        Customer c = new Customer(userName);
        if(c.getPassword().equals(password)) return c;
        else throw new RuntimeException("Mật khẩu sai!");
    }

    public boolean changePass(String oldPass, String newPass) {
        if (oldPass.equals(password)) {
            password = newPass;
            return true;
        } else {
            throw new RuntimeException("Mật khẩu sai!");
        }
    }
    
    public void createAccount(String password) {
        AccountDB.addAccount(new Account(this.getUserName(), password, 0), null);
    }
    
    public ArrayList<Account> listAccount() {
        return AccountDB.getListAccount(this.getUserName());
    }
}
