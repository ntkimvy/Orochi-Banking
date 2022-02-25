package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import model.dao.OrderDB;

public class Order {
    private int orderID;
    private int accNo;
    private String custName;
    private int typeID;
    private String type;
    private long amount;
    private String note;
    private Date time;

    public Order() {
    }

    public Order(int accNo, int typeID, long amount, String note) {
        this.accNo = accNo;
        this.typeID = typeID;
        this.amount = amount;
        this.note = note;
    }

    public Order(int orderID, int accNo, String custName, int typeID, String type, long amount, String note, Date time) {
        this.orderID = orderID;
        this.accNo = accNo;
        this.custName = custName;
        this.typeID = typeID;
        this.type = type;
        this.amount = amount;
        this.note = note;
        this.time = time;
    }

    public Order(Order o){
        this(o.orderID, o.accNo, o.custName, o.typeID, o.type, o.amount, o.note, o.time);
    }
    
    public Order(int orderID){
        this(OrderDB.getOrder(orderID));
    }
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getAccNo() {
        return accNo;
    }

    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return f.format(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", accNo=" + accNo + ", custName=" + custName + ", typeID=" + typeID + ", type=" + type + ", amount=" + amount + ", note=" + note + ", time=" + time + '}';
    }

    public void create(){
        OrderDB.createOrder(this);
    }
    
    public void delete(){
        OrderDB.deleteOrder(this);
    }
}
