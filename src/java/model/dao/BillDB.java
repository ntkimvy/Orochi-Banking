package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.DBConnect.DBContext;
import model.entity.Account;
import model.entity.Bill;

public class BillDB implements DBContext {

    public static ArrayList<Bill> getListBill(String value) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT B.billID, B.custName, B.amount, B.typeID, BT.type, B.fromDate, B.toDate, B.isPay FROM BILL B INNER JOIN BILLTYPE BT ON B.typeID = BT.typeID\n"
                    + "WHERE B.fromDate < GETDATE() AND B.toDate > GETDATE() AND B.isPay = 0 AND (B.billID = (?) OR UPPER(B.custName) LIKE '%" + value.toUpperCase() + "%')";
            PreparedStatement ps = conn.prepareStatement(query);
            int n = 0;
            try {
                n = Integer.parseInt(value);
            } catch (Exception e) {
                n = 0;
            }
            ps.setInt(1, n);
            ResultSet rs = ps.executeQuery();
            ArrayList<Bill> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Bill(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getInt(4), rs.getString(5), Date.valueOf(rs.getString(6)), Date.valueOf(rs.getString(7))));
            }
            conn.close();
            return list;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.getListAccount()");
            throw new RuntimeException("Somthing error...");
        }
    }

    public static void payBill(Bill b) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "UPDATE BILL\n"
                    + "SET isPay = 1\n"
                    + "WHERE billID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, b.getBillID());
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.getListAccount()");
            throw new RuntimeException("Somthing error...");
        }
    }
    
    public static Bill getBill(String billID) {
        try (Connection conn = DBContext.getConnection()) {
            String query = "SELECT B.billID, B.custName, B.amount, B.typeID, BT.type, B.fromDate, B.toDate, B.isPay FROM BILL B INNER JOIN BILLTYPE BT ON B.typeID = BT.typeID\n"
                    + "WHERE B.fromDate < GETDATE() AND B.toDate > GETDATE() AND B.isPay = 0 AND B.billID = (?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, billID);
            ResultSet rs = ps.executeQuery();
            Bill b = null;
            while (rs.next()) {
                b = new Bill(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getInt(4), rs.getString(5), Date.valueOf(rs.getString(6)), Date.valueOf(rs.getString(7)));
            }
            conn.close();
            return b;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error at model.dao.AccountDB.getListAccount()");
            throw new RuntimeException("Hóa đơn đã được thanh toán");
        }
    }

    public static void main(String[] args) {
        for(Bill b : getListBill("")){
            System.out.println(b);
        }
    }
}
