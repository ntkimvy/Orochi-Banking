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
            throw new RuntimeException("Mật khẩu sai!");
        }
    }

    public boolean changePass(String oldPass, String newPass) {
        if (oldPass.equals(password)) {
            password = newPass;
            return true;
        } else {
            throw new RuntimeException("Mật khẩu sai!");
        }
    }

    public void deposit(long amount) {
        this.setBalance(this.getBalance() + amount);
        AccountDB.transaction(this);
    }

    public void withdraw(long amount) {
        if (balance - amount < 0) {
            throw new RuntimeException("Số dư tài khoản không đủ...");
        }
        this.setBalance(this.getBalance() - amount);
        AccountDB.transaction(this);
    }

    public void payBill(Bill b) {
        this.withdraw(b.getAmount());
        new Account(100000).deposit(b.getAmount());
        new History(100000, null, b.getAmount(), new Account(100000).getBalance(), 7, "Tài khoản " + this.getAccNo() + " thanh toán hóa đơn số " + b.getBillID()).createHistory();
        new History(this.getAccNo(), null, -b.getAmount(), this.getBalance(), 7, "Thanh toán hóa đơn số " + b.getBillID() + ": " + b.getType().toLowerCase() + " khách hàng " + b.getCustName()).createHistory();
        BillDB.payBill(b);
    }

    public void orderDeposit(long amount) {
        new Order(this.getAccNo(), 1, amount, "Yêu cầu nộp tiền đang chờ xử lý, vui lòng mang " + amount + " VNĐ đến quầy giao dịch để hoàn tất thủ tục nộp tiền!").create();
    }

    public void orderWithdraw(long amount) {
        this.withdraw(amount);
        new Account(100000).deposit(amount);
        new Order(this.getAccNo(), 2, -amount, "Yêu cầu rút tiền đang chờ xử lý, vui lòng đến quầy giao dịch để nhận " + amount + " VNĐ").create();
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
        new History(this.getAccNo(), null, -amount, this.getBalance(), 3, "Chuyển tiền đến " + new Account(toAcc).getFullName() + ".<br>Số tài khoản: " + toAcc+"<br>Nội dung chuyển khoản: "+note).createHistory();
        new Account(toAcc).deposit(amount);
        new History(toAcc, null, amount, new Account(toAcc).getBalance(), 4, "Nhận tiền từ " + this.getFullName() + ".<br>Nội dung chuyển khoản: "+note).createHistory();
    }
}
