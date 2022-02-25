package model.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Account;
import model.entity.Bill;
import model.entity.History;
import model.entity.Staff;

public class AccountDB implements DBContext {

    public static ArrayList<Account> getListAccount(String custNo) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT A.userName, C.fullName, A.accNo, A.password, A.balance FROM ACCOUNT A INNER JOIN CUSTOMER C\n"
                    + "ON A.userName = C.userName\n"
                    + "WHERE A.userName = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, custNo);
            ResultSet rs = ps.executeQuery();
            ArrayList<Account> list = new ArrayList<>();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                list.add(new Account(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getLong(5)));
            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.getListAccount()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static void addAccount(Account a, Staff s) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO Account(userName, Password, Balance)\n"
                    + "OUTPUT inserted.accNo\n"
                    + "VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, a.getCustNo());
            ps.setString(2, a.getPassword());
            ps.setLong(3, a.getBalance());
            ResultSet rs = ps.executeQuery();
            int accNo = -1;
            if (rs.next()) {
                accNo = rs.getInt(1);
            }
            if (s != null) {
                HistoryDB.createHistory(new History(accNo, s.getUserName(), a.getBalance(), a.getBalance(), 5, "Mở tài khoản giao dịch"));
            } else {
                HistoryDB.createHistory(new History(accNo, null, 0, 0, 5, "Mở tài khoản giao dịch"));
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.addAccount()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static void transaction(Account a) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE ACCOUNT\n"
                    + "SET balance = ?\n"
                    + "WHERE accNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setLong(1, a.getBalance());
            ps.setInt(2, a.getAccNo());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.addAccount()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static void deleteAllAccount(String custNo) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "DELETE Account\n"
                    + "WHERE userName = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, custNo);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.deleteAllAccount()");
        }
    }

    public static void deleteAccount(int accNo) {
        try (Connection conn = DBContext.getConnection()) {
//            TransLogDB.deleteTransLog(accNo);
            String query = "DELETE Account\n"
                    + "WHERE accNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, accNo);
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.deleteAccount()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static Account getAccount(int accNo) {
        Account a = null;
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT A.userName, C.fullName, A.accNo, A.password, A.balance FROM ACCOUNT A INNER JOIN CUSTOMER C\n"
                    + "ON A.userName = C.userName\n"
                    + "WHERE A.accNo = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, accNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Account(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getLong(5));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.getListAccount()");
            throw new RuntimeException("Số tài khoản không đúng...");
        }
        if(a != null) {
            return a;
        } else {
            throw new RuntimeException("Số tài khoản không đúng!");
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Account(100000));
//        for (Account a : getListAccount("abcd")) {
//            System.out.println(a);
//        }
System.out.println(new Account(123));
    }
}
