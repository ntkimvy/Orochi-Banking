package model.entity;

import model.dao.AccountDB;
import model.dao.BillDB;
import model.dao.Characters;

public class Account {

    private String custNo;
    private String fullName;
    private int accNo;
    private String password;
    private long balance;

    public Account() {
    }

    public Account(int accNo) {
        this(AccountDB.getAccount(accNo));
    }

    public Account(Account a) {
        this(a.custNo, a.fullName, a.accNo, a.password, a.balance);
    }

    public Account(String custNo, String password, long balance) {
        this.custNo = custNo;
        this.password = password;
        this.balance = balance;
    }

    public Account(String custNo, String fullName, int accNo, String password, long balance) {
        this.custNo = custNo;
        this.fullName = fullName;
        this.accNo = accNo;
        this.password = password;
        this.balance = balance;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getFullName() {
        return Characters.conver(fullName);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" + "custNo=" + custNo + ", fullName=" + fullName + ", accNo=" + accNo + ", password=" + password + ", balance=" + balance + '}';
    }

    public static Account login(int accNo, String password) {
        Account a = new Account(accNo);
        if (a.getPassword().equals(password)) {
            return a;
        } else {
            throw new RuntimeException("M???t kh???u sai!");
        }
    }

    public boolean changePass(String oldPass, String newPass) {
        if (oldPass.equals(password)) {
            password = newPass;
            return true;
        } else {
            throw new RuntimeException("M???t kh???u sai!");
        }
    }

    public void deposit(long amount) {
        this.setBalance(this.getBalance() + amount);
        AccountDB.transaction(this);
    }

    public void withdraw(long amount) {
        if (balance - amount < 0) {
            throw new RuntimeException("S??? d?? t??i kho???n kh??ng ?????...");
        }
        this.setBalance(this.getBalance() - amount);
        AccountDB.transaction(this);
    }

    public void payBill(Bill b) {
        this.withdraw(b.getAmount());
        new Account(100000).deposit(b.getAmount());
        new History(100000, null, b.getAmount(), new Account(100000).getBalance(), 7, "T??i kho???n " + this.getAccNo() + " thanh to??n h??a ????n s??? " + b.getBillID()).createHistory();
        new History(this.getAccNo(), null, -b.getAmount(), this.getBalance(), 7, "Thanh to??n h??a ????n s??? " + b.getBillID() + ": " + b.getType().toLowerCase() + " kh??ch h??ng " + b.getCustName()).createHistory();
        BillDB.payBill(b);
    }

    public void orderDeposit(long amount) {
        new Order(this.getAccNo(), 1, amount, "Y??u c???u n???p ti???n ??ang ch??? x??? l??, vui l??ng mang " + amount + " VN?? ?????n qu???y giao d???ch ????? ho??n t???t th??? t???c n???p ti???n!").create();
    }

    public void orderWithdraw(long amount) {
        this.withdraw(amount);
        new Account(100000).deposit(amount);
        new Order(this.getAccNo(), 2, -amount, "Y??u c???u r??t ti???n ??ang ch??? x??? l??, vui l??ng ?????n qu???y giao d???ch ????? nh???n " + amount + " VN??").create();
    }
    
    public void cancelOrder(Order o){
        if(o.getTypeID() == 1) {
            o.delete();
        } else {
            this.deposit(-o.getAmount());
            new Account(100000).withdraw(-o.getAmount());
            o.delete();
            
        }
    }

    public void transfer(int toAcc, long amount, String note) {
        this.withdraw(amount);
        new History(this.getAccNo(), null, -amount, this.getBalance(), 3, "Chuy???n ti???n ?????n " + new Account(toAcc).getFullName() + ".<br>S??? t??i kho???n: " + toAcc+"<br>N???i dung chuy???n kho???n: "+note).createHistory();
        new Account(toAcc).deposit(amount);
        new History(toAcc, null, amount, new Account(toAcc).getBalance(), 4, "Nh???n ti???n t??? " + this.getFullName() + ".<br>N???i dung chuy???n kho???n: "+note).createHistory();
    }
}
