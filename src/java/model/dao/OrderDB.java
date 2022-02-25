package model.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Order;

public class OrderDB implements DBContext {

    public static ArrayList<Order> getListOrder() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT O.orderID, O.accNo,C.fullName, O.typeID, T.type, O.amount, O.note, O.time FROM ORDERS O \n"
                    + "INNER JOIN ACCOUNT A ON O.accNo = A.accNo\n"
                    + "INNER JOIN CUSTOMER C ON A.userName = C.userName\n"
                    + "INNER JOIN TYPE T ON O.typeID = T.typeID";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Order> list = new ArrayList<>();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getLong(6), rs.getString(7), (Date) f.parse(rs.getString(8))));
            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.getListAccount()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static void deleteOrder(Order o) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE ORDERS\n"
                    + "WHERE orderID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, o.getOrderID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.getListAccount()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static void createOrder(Order o) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO ORDERS(accNo, typeID, amount, note)\n"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, o.getAccNo());
            ps.setInt(2, o.getTypeID());
            ps.setLong(3, o.getAmount());
            ps.setString(4, o.getNote());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.createAccount()");
        }
    }
    
    public static Order getOrder(int orderID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT O.orderID, O.accNo,C.fullName, O.typeID, T.type, O.amount, O.note, O.time FROM ORDERS O \n"
                    + "INNER JOIN ACCOUNT A ON O.accNo = A.accNo\n"
                    + "INNER JOIN CUSTOMER C ON A.userName = C.userName\n"
                    + "INNER JOIN TYPE T ON O.typeID = T.typeID\n"
                    + "WHERE O.orderID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Order o = null;
            if (rs.next()) {
                o = new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getLong(6), rs.getString(7), (Date) f.parse(rs.getString(8)));
            }
            conn.close();
            return o;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.getListAccount()");
            throw new RuntimeException("Không tìm thấy đơn cần xử lý...");
        }
    }

    public static ArrayList<Order> getOrders(int accNo) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT O.orderID, O.accNo,C.fullName, O.typeID, T.type, O.amount, O.note, O.time FROM ORDERS O \n"
                    + "INNER JOIN ACCOUNT A ON O.accNo = A.accNo\n"
                    + "INNER JOIN CUSTOMER C ON A.userName = C.userName\n"
                    + "INNER JOIN TYPE T ON O.typeID = T.typeID\n"
                    + "WHERE O.accNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, accNo);
            ResultSet rs = ps.executeQuery();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ArrayList<Order> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getLong(6), rs.getString(7), (Date) f.parse(rs.getString(8))));
            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.getListAccount()");
            throw new RuntimeException("Không tìm thấy đơn cần xử lý...");
        }
    }
    
    public static ArrayList<Order> searchOrder(String value) {
        ArrayList<Order> list = new ArrayList<>();
        for(Order o : getListOrder()){
            if(String.valueOf(o.getAccNo()).equals(value) || o.getCustName().toUpperCase().equals(value.toUpperCase())){
                list.add(o);
            }
        }
        return list;
    }
    
    public static void main(String[] args) {
        for(Order o : searchOrder("SDFGH")){
            System.out.println(o);
        }

    }
}
