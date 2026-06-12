package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;

public class CustomerSearchDBAccess extends Object{
	
	private static final String URL ="jdbc:mysql://localhost:65534/KIDDA_LA";
	private static final String USER = "user1";
	private static final String PASSWORD = "pass1";
	
//	KIDDA_LAデータベースとの接続を確立する。
	private Connection createConnection() throws Exception {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			throw new Exception("DB接続処理に失敗しました！管理者に連絡してください。", e);
		}
		return con;
	}
	
//	KIDDA_LAデータベースとの接続を切断する。
	private void closeConnection(Connection con) throws Exception {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			throw new Exception("DB接続処理に失敗しました！管理者に連絡してください。", e);
		}
	}
	
//	引数と完全一致する顧客情報リストを返す
	public ArrayList<Customer> searchCustomerByTel(String tel) throws Exception {
		ArrayList<Customer> list = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = createConnection();
			String sql = "SELECT CUSTID, CUSTNAME,KANA, ADDRESS" + "FROM CUSTOMER" + "WHERE TEL = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1,tel);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Customer customer = new Customer(
						rs.getInt("CUSTID"),
						rs.getString("CUSTNAME"),
						rs.getString("KANA"),
						rs.getString("TEL"),
						rs.getString("ADDRESS")
						);
				list.add(customer);
			}
		} catch (Exception e) {
			throw new Exception ("顧客情報検索処理に失敗しました！管理者に連絡してください。", e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			closeConnection(con);
		}
		return list;
	}
	
//	引数kanaを含む顧客情報リストを返す
	public ArrayList<Customer> searchCustomerByKana(String kana) throws Exception {
		ArrayList<Customer> list = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = createConnection();	
            String sql = "SELECT CUSTID, CUSTNAME,KANA, ADDRESS" + "FROM CUSTOMER" + "WHERE KANA LIKE ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1,"%" + kana + "%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Customer customer = new Customer(
						rs.getInt("CUSTID"),
						rs.getString("CUSTNAME"),
						rs.getString("KANA"),
						rs.getString("TEL"),
						rs.getString("ADDRESS")
				);
				list.add(customer);
			}
		} catch (Exception e) {
			throw new Exception("一致する情報は見つかりませんでした。", e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
			    ps.close();
			}
			closeConnection(con);
		}		
		return list;
	}
	
//	引数telと完全一致かつ引数kanaを含む顧客情報リストを返す
	public ArrayList<Customer> searchCustomer(String tel,String kana) throws Exception {
		ArrayList<Customer> list = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = createConnection();	
            String sql = "SELECT CUSTID, CUSTNAME,KANA, ADDRESS" + "FROM CUSTOMER" + "WHERE TEL = ?" + "AND KANA LIKE ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1,tel);
			ps.setString(2,"%" + kana + "%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Customer customer = new Customer(
						rs.getInt("CUSTID"),
						rs.getString("CUSTNAME"),
						rs.getString("KANA"),
						rs.getString("TEL"),
						rs.getString("ADDRESS")
				);
				list.add(customer);
			}
		} catch (Exception e) {
			throw new Exception("一致する情報は見つかりませんでした。",e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
			    ps.close();
			}
			closeConnection(con);
		}
		return list;
	}
}

