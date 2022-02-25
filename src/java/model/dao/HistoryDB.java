package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import model.DBConnect.DBContext;
import model.entity.History;

public class HistoryDB implements DBContext {

    public static ArrayList<History> getListHistory(int accNo) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT H.hisID, H.accNo, A.userName, C.fullName, S.staffNo, S.fullName, H.amount, H.postBalance, H.time, H.typeID, T.type, H.note FROM HISTORY H INNER JOIN ACCOUNT A\n"
                    + "ON A.accNo = H.accNo\n"
                    + "INNER JOIN CUSTOMER C ON A.userName = C.userName\n"
                    + "LEFT JOIN STAFF S ON H.staffNo = S.userName\n"
                    + "INNER JOIN TYPE T ON H.typeID = T.typeID\n"
                    + "WHERE H.accNo = ?\n"
                    + "ORDER BY H.hisID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, accNo);
            ResultSet rs = ps.executeQuery();
            ArrayList<History> list = new ArrayList<>();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                list.add(new History(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getLong(7), rs.getLong(8), (Date) f.parse(rs.getString(9)), rs.getInt(10), rs.getString(11), rs.getString(12)));
            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.getListAccount()");
            throw new RuntimeException("Somthing error...");
        }
    }
//đang lỗi
    public static ArrayList<History> getListHistorys() {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT H.hisID, H.accNo, A.userName, C.fullName, S.staffNo, S.fullName, H.amount, H.postBalance, H.time, H.typeID, T.type, H.note FROM HISTORY H INNER JOIN ACCOUNT A\n"
                    + "ON A.accNo = H.accNo\n"
                    + "INNER JOIN CUSTOMER C ON A.userName = C.userName\n"
                    + "INNER JOIN STAFF S ON H.staffNo = S.userName\n"
                    + "INNER JOIN TYPE T ON H.typeID = T.typeID\n"
                    + "ORDER BY H.hisID DESC";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ArrayList<History> list = new ArrayList<>();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                list.add(new History(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getLong(7), rs.getLong(8), (Date) f.parse(rs.getString(9)), rs.getInt(10), rs.getString(11), rs.getString(12)));
            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.getListAccount()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static void createHistory(History h) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "INSERT INTO HISTORY(accNo, staffNo, amount, postBalance, typeID, note)\n"
                    + "VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, h.getAccNo());
            ps.setString(2, h.getStaffNo());
            ps.setLong(3, h.getAmount());
            ps.setLong(4, h.getPostBalance());
            ps.setInt(5, h.getTypeID());
            ps.setString(6, h.getNote());
            ps.executeUpdate();
            conn.commit();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Error at AccountDB.createAccount()");
        }
    }

    public static void main(String[] args) {
        for(History h : getListHistory(100001)){
            System.out.println(h.getStaffName());
        }
    }
}
