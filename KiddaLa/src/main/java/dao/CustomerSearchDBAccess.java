package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Customer;

public class CustomerSearchDBAccess extends Object{
	
	private static final String URL = "jdbc:mysql://localhost:3306/KIDDA_LA?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Tokyo";
	private static final String USER = "root";
	private static final String PASSWORD = "Sqlnqyqm390";
	
//	KIDDA_LAデータベースとの接続を確立する。
	private Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();   // ← この1行を追加
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
			if (tel == null) {
				throw new Exception("電話番号と氏名カナのいずれか、または両方を入力してください。");
			}
			con = createConnection();
			String sql = "SELECT CUSTID,CUSTNAME,KANA,TEL,ADDRESS " + "FROM CUSTOMER " + "WHERE TEL = ?";
			
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
			if (e.getMessage() != null && e.getMessage().contains("DB接続処理")) {
		        throw e;
			}
		    throw new Exception("顧客情報検索処理に失敗しました！管理者に連絡してください。", e);
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
			if (kana == null) {
				throw new Exception("電話番号と氏名カナのいずれか、または両方を入力してください。");
			}
			con = createConnection();	
            String sql = "SELECT CUSTID, CUSTNAME,KANA,TEL,ADDRESS " + "FROM CUSTOMER " + "WHERE KANA LIKE ?";
			
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
			if (e.getMessage() != null && e.getMessage().contains("DB接続処理")) {
		        throw e;
		    }
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
			if(tel == null && kana == null) {
				throw new Exception("一致する情報は見つかりませんでした。");
			}
			
			con = createConnection();
			StringBuilder sql = new StringBuilder(
					"SELECT CUSTID, CUSTNAME,KANA,TEL,ADDRESS " + "FROM CUSTOMER " + "WHERE 1=1");
			if (tel != null) {
				sql.append(" And TEL = ?");
			}
			if (kana != null) {
				sql.append(" AND KANA LIKE ?");
			}
           
			ps = con.prepareStatement(sql.toString());
			
			int index = 1;
			if (tel != null) {
				ps.setString(index++, tel);
			}
			if (kana != null) {
				ps.setString(index++,"%" + kana + "%");
			}
		
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
			if (e.getMessage() != null && e.getMessage().contains("DB接続処理")) {
		        throw e;
		    }
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

