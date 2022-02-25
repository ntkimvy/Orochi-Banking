package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.DBConnect.DBContext;
import model.entity.Message;
import model.entity.Staff;

public class MessageDB implements DBContext {

    public static ArrayList<Message> getMessage(String userName) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT M.messageID, M.typeID, C.userName, C.fullName, S.userName, S.fullName, M.message, M.time\n"
                    + "FROM MESSAGE M INNER JOIN CUSTOMER C ON M.custID = C.userName\n"
                    + "LEFT JOIN STAFF S ON M.staffID = S.userName\n"
                    + "WHERE C.userName = ?\n"
                    + "ORDER BY messageID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            ArrayList<Message> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Message m = new Message(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), (Date) f.parse(rs.getString(8)));
                list.add(m);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at MessageBD.getMessage()");
        }
        return null;
    }

    public static ArrayList<Message> getListSender() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT M.messageID, M.typeID, C.userName, C.fullName, S.userName, S.fullName, M.message, M.time FROM\n"
                    + "(SELECT M.custID, MAX(M.messageID) AS MS FROM MESSAGE M INNER JOIN CUSTOMER C ON M.custID = C.userName\n"
                    + "GROUP BY M.custID) AS C1\n"
                    + "INNER JOIN MESSAGE M ON C1.MS = M.messageID\n"
                    + "INNER JOIN CUSTOMER C ON M.custID = C.userName\n"
                    + "LEFT JOIN STAFF S ON M.staffID = S.userName\n"
                    + "ORDER BY C1.ms DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Message> list = new ArrayList<>();
            while (rs.next()) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Message m = new Message(rs.getInt(1), rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), (Date) f.parse(rs.getString(8)));
                list.add(m);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at MessageBD.getListSender()");
        }
        return null;
    }

    public static void addMessage(Message m) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO MESSAGE(typeID, custID, staffID, message)\n"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, m.isTypeID());
            ps.setString(2, m.getCustID());
            ps.setString(3, m.getStaffID());
            ps.setString(4, m.getMessage());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at Message.addMessage()");
        }
    }

    public static void main(String[] args) {
//        Message m = new Message(true, "abcd", null, "alo");
//        MessageDB.addMessage(m);
        for (Message m : getListSender()) {
            System.out.println(m);
        }
//MessageDB.addMessage(new Message(false, "abcd", "tva1001", "alo"));
    }
}
