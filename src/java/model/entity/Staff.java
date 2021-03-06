package model.entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.AccountDB;
import model.dao.Characters;
import model.dao.OrderDB;
import model.dao.StaffDB;

public class Staff {
    private int staffNo;
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
    private boolean userTypeID;

    public Staff() {
    }

    public Staff(String password, String fullName, String dob, String id, String address, boolean sex, String email, String tel) {        
        this.password = password;
        this.fullName = fullName;
        try {
            SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
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

    public Staff(int staffNo, String userName, String password, String fullName, Date dob, String id, String address, boolean sex, String email, String avatar, String tel, boolean userTypeID) {
        this.staffNo = staffNo;
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
        this.userTypeID = userTypeID;
    }
    
    public Staff(String userName){
        this(StaffDB.getStaff(userName));
    }
    
    public Staff(int staffNo){
        this(StaffDB.getStaff(staffNo));
    }
    
    public Staff(Staff s){
        this(s.staffNo, s.userName, s.password, s.fullName, s.dob, s.id, s.address, s.sex, s.email, s.avatar, s.tel, s.userTypeID);
    }

    public int getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(int staffNo) {
        this.staffNo = staffNo;
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

    public boolean isUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(boolean userTypeID) {
        this.userTypeID = userTypeID;
    }

    @Override
    public String toString() {
        return "Staff{" + "staffNo=" + staffNo + ", userName=" + userName + ", password=" + password + ", fullName=" + fullName + ", dob=" + dob + ", id=" + id + ", address=" + address + ", sex=" + sex + ", email=" + email + ", avatar=" + avatar + ", tel=" + tel + ", userTypeID=" + userTypeID + '}';
    }
    
    public static Staff login(String userName, String password) {
        Staff s = new Staff(userName);
        if(s.getPassword().equals(password)) return s;
        else throw new RuntimeException("M???t kh???u sai!");
    }

    public boolean changePass(String oldPass, String newPass) {
        if (oldPass.equals(password)) {
            password = newPass;
            return true;
        } else {
            throw new RuntimeException("M???t kh???u sai!");
        }
    }
    
    public void doTransaction(Order o) {
        if(o.getTypeID() == 1) {
            new Account(o.getAccNo()).deposit(o.getAmount());
            new History(o.getAccNo(), this.getUserName(), o.getAmount(), new Account(o.getAccNo()).getBalance(), 1, "N???p "+o.getAmount()+" VN?? v??o t??i kho???n.").createHistory();
            new Account(100000).withdraw(o.getAmount());
            new History(100000, this.getUserName(), -o.getAmount(), new Account(100000).getBalance(), 1, "N???p "+o.getAmount()+" VN?? v??o s??? t??i kho???n "+o.getAccNo()).createHistory();
        } else {
            new Account(o.getAccNo()).withdraw(o.getAmount());
            new History(o.getAccNo(), this.getUserName(), o.getAmount(), new Account(o.getAccNo()).getBalance(), 1, "R??t "+o.getAmount()+" VN?? ti???n m???t.").createHistory();
            new Account(100000).deposit(o.getAmount());
            new History(100000, this.getUserName(), o.getAmount(), new Account(100000).getBalance(), 1, "T??i kho???n "+o.getAccNo()+" r??t "+o.getAmount()+" VN?? ti???n m???t.").createHistory();
        }
        OrderDB.deleteOrder(o);
    }
    
    public void payBill(Bill b) {
        new Account(100000).withdraw(b.getAmount());
        new History(100000, null, -b.getAmount(), new Account(100000).getBalance(), 7, "Kh??ch h??ng thanh to??n ti???n m???t h??a ????n s??? " + b.getBillID()).createHistory();
    }
    
    public void doRequest(Account a, boolean type, long amount) {
        //true: r??t ti???n; false n???p ti???n
        if(type) {
            
            new History(a.getAccNo(), this.getUserName(), -amount, a.getBalance(), 2, "R??t ti???n m???t t???i qu???y giao d???ch.").createHistory();
            
            new History(100000, this.getUserName(), amount, new Account(100000).getBalance(), 2, "T??i kho???n "+a.getAccNo()+" r??t "+amount+" VN?? ti???n m???t.").createHistory();
        } else {
            a.deposit(amount);
            new History(a.getAccNo(), this.getUserName(), amount, a.getBalance(), 1, "N???p ti???n v??o t??i kho???n t???i qu???y giao d???ch.").createHistory();
            new Account(100000).withdraw(amount);
            new History(100000, this.getUserName(), -amount, new Account(100000).getBalance(), 1, "N???p "+amount+" VN?? v??o s??? t??i kho???n "+a.getAccNo()).createHistory();
        }
    }
    
    public void cancelOrder(Order o){
        if(o.getTypeID() == 1) {
            new History(o.getAccNo(), this.getUserName(), 0, new Account(o.getAccNo()).getBalance(), 8, "Nh??n vi??n h???y giao d???ch n???p ti???n.").createHistory();
            o.delete();
        } else {
            new Account(o.getAccNo()).deposit(o.getAmount());
            new Account(100000).withdraw(o.getAmount());
            new History(o.getAccNo(), this.getUserName(), 0, new Account(o.getAccNo()).getBalance(), 8, "Nh??n vi??n h???y giao d???ch r??t ti???n.").createHistory();
            o.delete();
            
        }
    }
    
    public void createAccount(Customer c, String password, long balance) {
        AccountDB.addAccount(new Account(c.getUserName(), password, balance), this);
    }
}
