package model;

import java.util.ArrayList;
import java.util.Calendar;

public class OrderControlUtility {
//	顧客情報リストを検索結果表示用データに変換する
	public static String[][] customerToArray(ArrayList<Customer> list) {
		String[][] tableData = new String[list.size()][5];
		for (int i = 0; i < list.size(); i++) {
			Customer customer = list.get(i);
			tableData[i][0] = Integer.toString(customer.getCustId());
			tableData[i][1] = customer.getCustName();
			tableData[i][2] = customer.getKana();
			tableData[i][3] = customer.getTel();
			tableData[i][4] = customer.getAddress();
		}	
		return tableData;
	}
	
//	現在日付を生成して返却する
	public static String getDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		
		return String.format("%04d-%02d-%02d", year, month, day);
	}
}

