package model.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import model.dao.BillDB;

public class Bill {
    private String billID;
    private String custName;
    private long amount;
    private int typeID;
    private String type;
    private Date fromDate;
    private Date toDate;

    public Bill() {
    }

    public Bill(String billID, String custName, long amount, int typeID, String type, Date fromDate, Date toDate) {
        this.billID = billID;
        this.custName = custName;
        this.amount = amount;
        this.typeID = typeID;
        this.type = type;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    
    public Bill(Bill b){
        this(b.billID, b.custName, b.amount, b.typeID, b.type, b.fromDate, b.toDate);
    }

    public Bill(String billID){
        this(BillDB.getBill(billID));
    }
    
    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
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

    public String getFromDate() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        return f.format(fromDate);
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
        return f.format(toDate);
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "Bill{" + "billID=" + billID + ", custName=" + custName + ", amount=" + amount + ", typeID=" + typeID + ", type=" + type + ", fromDate=" + fromDate + ", toDate=" + toDate + '}';
    }
}
