package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import model.dao.Characters;

public class Message {
    private int messageID;
    private boolean typeID;
    private String custID;
    private String custName;
    private String staffID;
    private String staffName;
    private String message;
    private Date time;

    public Message() {
    }

    public Message(boolean typeID, String custID, String staffID, String message) {
        this.typeID = typeID;
        this.custID = custID;
        this.staffID = staffID;
        this.message = message;
    }

    public Message(int messageID, boolean typeID, String custID, String custName, String staffID, String staffName, String message, Date time) {
        this.messageID = messageID;
        this.typeID = typeID;
        this.custID = custID;
        this.custName = custName;
        this.staffID = staffID;
        this.staffName = staffName;
        this.message = message;
        this.time = time;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public boolean isTypeID() {
        return typeID;
    }

    public void setTypeID(boolean typeID) {
        this.typeID = typeID;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return Characters.conver(staffName);
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        return "Message{" + "messageID=" + messageID + ", typeID=" + typeID + ", custID=" + custID + ", custName=" + custName + ", staffID=" + staffID + ", staffName=" + staffName + ", message=" + message + ", time=" + time + '}';
    }
}
