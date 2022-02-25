package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.function.Predicate;
import model.DBConnect.DBContext;
import model.entity.Customer;

public class CustomerDB implements DBContext {

    public static ArrayList<Customer> getListCustomer() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT userName, password, fullName, dob, id, address, sex, email, avatar, tel FROM CUSTOMER";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<Customer> list = new ArrayList<>();
            while (rs.next()) {
                Customer a = new Customer(rs.getString(1),
                        rs.getString(2), rs.getString(3), Date.valueOf(rs.getString(4)),
                        rs.getString(5), rs.getString(6), rs.getBoolean(7), 
                        rs.getString(8), rs.getString(9), rs.getString(10));
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

    public static void createCustomer(Customer a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO CUSTOMER(userName, password, fullName, dob, id, address, sex, email, tel)\n"                    
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getUserName());
            ps.setString(2, a.getPassword());
            ps.setString(3, Characters.format(a.getFullName()));
            ps.setString(4, a.getDobToAdd());
            ps.setString(5, a.getId());
            ps.setString(6, a.getAddress());
            ps.setBoolean(7, a.isSex());
            ps.setString(8, a.getEmail());            
            ps.setString(9, a.getTel());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.createAccount()");
            throw new RuntimeException("Số chứng minh nhân dân đã tồn tại!");
        }
    }


    public static Customer getCustomer(String userName) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT userName, password, fullName, dob, id, address, sex, email, avatar, tel FROM CUSTOMER\n"
                    + "WHERE userName = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer a = new Customer(rs.getString(1),
                        rs.getString(2), rs.getString(3), Date.valueOf(rs.getString(4)),
                        rs.getString(5), rs.getString(6), rs.getBoolean(7), 
                        rs.getString(8), rs.getString(9), rs.getString(10));
                return a;
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.getAccount()");
        }
        throw new RuntimeException("Tài khoản không tồn tại!");
    }
    
    public static ArrayList<Customer> searchCustomer(Predicate<Customer> s) {
        ArrayList<Customer> result = new ArrayList<>();
        for(Customer c : CustomerDB.getListCustomer()) {
            if(s.test(c)) {
                result.add(c);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        Customer c = new Customer("abcd", "123", "sdfgh", "2000-12-19", "123456780", "huế", true, "sdfg@gmail.com", "12978");
//        CustomerDB.createCustomer(c);
//        for(Customer d :getListCustomer()){
//            System.out.println(d);
//        }
//        new Customer("abcd").createAccount("1234");
    }
}
