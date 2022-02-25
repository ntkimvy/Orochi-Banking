package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import model.dao.Characters;
import model.dao.HistoryDB;

public class History {

    private int hisID;
    private int accNo;
    private String custID;
    private String custName;
    private String staffNo;
    private String staffName;
    private long amount;
    private long postBalance;
    private Date time;
    private int typeID;
    private String type;
    private String note;

    public History() {
    }

    public History(int accNo, String staffNo, long amount, long postBalance, int typeID, String note) {
        this.accNo = accNo;
        this.staffNo = staffNo;
        this.amount = amount;
        this.postBalance = postBalance;
        this.typeID = typeID;
        this.note = note;
    }

    public History(int hisID, int accNo, String custID, String custName, String staffNo, String staffName, long amount, long postBalance, Date time, int typeID, String type, String note) {
        this.hisID = hisID;
        this.accNo = accNo;
        this.custID = custID;
        this.custName = custName;
        this.staffNo = staffNo;
        this.staffName = staffName;
        this.amount = amount;
        this.postBalance = postBalance;
        this.time = time;
        this.typeID = typeID;
        this.type = type;
        this.note = note;
    }

    public int getHisID() {
        return hisID;
    }

    public void setHisID(int hisID) {
        this.hisID = hisID;
    }

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return Characters.conver(custName);
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        if(staffName != null) {
            return Characters.conver(staffName);
        } else return "-";
    }
    
    public String getStaffID(){
        if(staffNo != null) {
            return new Staff(Integer.parseInt(staffNo)).getUserName();
        } else return "-";
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getPostBalance() {
        return postBalance;
    }

    public void setPostBalance(long postBalance) {
        this.postBalance = postBalance;
    }

    public String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return f.format(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "History{" + "hisID=" + hisID + ", accNo=" + accNo + ", custID=" + custID + ", custName=" + custName + ", staffNo=" + staffNo + ", staffName=" + staffName + ", amount=" + amount + ", postBalance=" + postBalance + ", time=" + time + ", typeID=" + typeID + ", type=" + type + ", note=" + note + '}';
    }

    public void createHistory() {
        HistoryDB.createHistory(this);
    }
}
