package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Account;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Staff;

public class StaffDB implements DBContext{

    public static ArrayList<Staff> getListStaff() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT staffNo, userName, password, fullName, dob, id, address, sex, email, avatar, tel, userTypeID FROM STAFF";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Staff> list = new ArrayList<>();
            while (rs.next()) {
                Staff a = new Staff(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), Date.valueOf(rs.getString(5)),
                        rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getBoolean(12));
                list.add(a);
            }
            conn.close();
            return list;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.getListAccount()");
        }
        return null;
    }

    public static String createStaff(Staff a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO STAFF(password, fullName, dob, id, address, sex, email, tel)\n"
                    + "OUTPUT inserted.staffNo\n"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getPassword());
            ps.setString(2, Characters.format(a.getFullName()));
            ps.setString(3, a.getDobToAdd());
            ps.setString(4, a.getId());
            ps.setString(5, a.getAddress());
            ps.setBoolean(6, a.isSex());
            ps.setString(7, a.getEmail());            
            ps.setString(8, a.getTel());
            ResultSet rs = ps.executeQuery();            
            String staffNo = "";
            if (rs.next()) {
                staffNo = rs.getString(1);
            }
            conn.commit();
            conn.close();
            String userName = Characters.abbreviation(a.getFullName()) + staffNo;
            createUserName(staffNo, userName);
            return userName;
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.createAccount()");
            throw new RuntimeException("Số chứng minh nhân dân đã tồn tại!");
        }
    }

    private static void createUserName(String staffNo, String userName) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE STAFF\n"
                    + "SET userName = ?\n"
                    + "WHERE staffNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, staffNo);
            ps.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.createAccount()");
            throw new RuntimeException("Username đã tồn tại!");
        }
    }

    public static Staff getStaff(String userName) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT staffNo, userName, password, fullName, dob, id, address, sex, email, avatar, tel, userTypeID FROM STAFF\n"
                    + "WHERE userName = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Staff a = new Staff(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), Date.valueOf(rs.getString(5)),
                        rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getBoolean(12));
                return a;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.getAccount()");
        }
        throw new RuntimeException("Tài khoản không tồn tại!");
    }
    
    public static Staff getStaff(int staffNo) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT staffNo, userName, password, fullName, dob, id, address, sex, email, avatar, tel, userTypeID FROM STAFF\n"
                    + "WHERE staffNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, staffNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Staff a = new Staff(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), Date.valueOf(rs.getString(5)),
                        rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getBoolean(12));
                return a;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.getAccount()");
        }
        throw new RuntimeException("Tài khoản không tồn tại!");
    }
    
    public static void main(String[] args) {
//        Staff s = new Staff("123", "trần văn a", "20-12-2000", "12345678", "huế", true, "ádfgh@gmail.com", "123456789");
//        StaffDB.createStaff(s);
//        new Staff("tva1001").doRequest(new Account(100001), false, 1000000);
//new Staff("tva1001").createAccount(new Customer("abcd"), "12", 1000);
        new Staff("tva1001").doTransaction(new Order(1));
    }
}
